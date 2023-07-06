package com.party_grouping.controller;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.request.CharacterRequest;
import com.party_grouping.service.CharacterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.*;

@Controller
public class CharacterController {
    @Autowired
    private CharacterService characterService;
    HttpHeaders header;
    private static final String COOKIE_NAME = "character";

    public CharacterController() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    }

    @GetMapping("character")
    public String getCharacter(HttpSession session, Model model) {
        return "character";
    }

    @GetMapping("character_search")
    @ResponseBody
    public ResponseEntity<List<CharacterDto>> getCharacterSearch(@RequestParam(value = "name") String name,
                                                                 @RequestParam(value = "type") String type) {
        return ResponseEntity.ok(characterService.characterSearch(name, type));
    }

    @GetMapping("character_status")
    @ResponseBody
    public ResponseEntity<CharacterDto> getCharacterStatus(HttpSession session,
                                                           @RequestParam("apiId") String apiId,
                                                           @RequestParam("server") String server,
                                                           @RequestParam("session") boolean sessionUse) {
        CharacterDto characterDto = characterService.characterStatus(server, apiId);
        if (sessionUse) {
            List<CharacterRequest> characterSession = getSessionCharacter(session);
            characterSession.add(new CharacterRequest(apiId, server));
            setSessionCharacter(session, characterSession);
        }

        return ResponseEntity.ok(characterDto);
    }

    @GetMapping("character_session")
    @ResponseBody
    public ResponseEntity<List<CharacterDto>> getCharacterSession(HttpSession session) {
        List<CharacterRequest> requestList = getSessionCharacter(session);

        return ResponseEntity.ok(characterService.characterStatusList(false, requestList));
    }

    @PostMapping("character_status_list")
    @ResponseBody
    public ResponseEntity<List<CharacterDto>> getCharacterStatusList(@RequestBody(required = false) String json) {
        JSONObject jsonObject = new JSONObject(json);
        boolean apiUse = jsonObject.optInt("apiUse") != 0;

        return ResponseEntity.ok(characterService.characterStatusList(apiUse, characterRequestList(jsonObject)));
    }

    private List<CharacterRequest> characterRequestList(JSONObject jsonObject) {
        List<CharacterRequest> hashMap = new ArrayList<>();
        JSONArray jsonArray = jsonObject.optJSONArray("characterRequestList");

        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject arrayJsonObject = jsonArray.getJSONObject(i);
            String apiId = arrayJsonObject.optString("apiId");
            String server = arrayJsonObject.optString("server");
            if (apiId != null && server != null) {
                hashMap.add(new CharacterRequest(apiId, server));
            }
        }

        return hashMap;
    }

    @SuppressWarnings("unchecked")
    private List<CharacterRequest> getSessionCharacter(HttpSession session) {
        List<CharacterRequest> characterSession = (List<CharacterRequest>) session.getAttribute("character");
        if (characterSession == null) {
            characterSession = new ArrayList<>();
        }
        return characterSession;
    }

    private void setSessionCharacter(HttpSession session, List<CharacterRequest> characterSession) {
        session.setAttribute("character", characterSession);
    }
}
