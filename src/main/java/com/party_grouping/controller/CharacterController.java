package com.party_grouping.controller;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.request.CharacterRequest;
import com.party_grouping.response.CharacterResponse;
import com.party_grouping.service.CharacterService;
import com.party_grouping.service.api.DnfApiService;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@Slf4j
public class CharacterController {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private DnfApiService dnfApiService;
    private Logger log = LoggerFactory.getLogger(getClass());

    public CharacterController() {
    }

    @GetMapping("/")
    public String getIndex() {
        return "redirect:/character";
    }

    @GetMapping("character")
    public String getCharacter(HttpSession session, Model model) {
        return "character";
    }

    @DeleteMapping("character")
    @ResponseBody
    public ResponseEntity<Boolean> deleteCharacter(HttpSession session,
                                   @RequestParam("apiId") String apiId,
                                   @RequestParam("server") String server) {
        String logging = "apiId : " + apiId + " / server : " + server;
        log.info("DELETE/Character Delete [{}] | Session ID [{}]", logging, session.getId());

        List<CharacterRequest> characterSession = getSessionCharacter(session);
        int beforeIndex = characterSession.size();
        characterSession = characterSession.stream()
                .filter(character -> !(character.getApiId().equals(apiId) && character.getServer().equals(server)))
                .toList();
        int afterIndex = characterSession.size();

        if (beforeIndex == afterIndex) {
            return ResponseEntity.ok(false);
        }

        setSessionCharacter(session, characterSession);

        return ResponseEntity.ok(true);
    }

    @GetMapping("character_search")
    @ResponseBody
    public ResponseEntity<List<CharacterDto>> getCharacterSearch(HttpSession session,
                                                                 @RequestParam(value = "name") String name,
                                                                 @RequestParam(value = "type") String type) {
        String logging = "name : " + name + " / type : " + type;
        log.info("GET/Character Search [{}] | Session ID [{}]", logging, session.getId());
        return ResponseEntity.ok(characterService.characterSearch(name, type));
    }

    @GetMapping("character_status")
    @ResponseBody
    public ResponseEntity<CharacterResponse> getCharacterStatus(HttpSession session,
                                                           @RequestParam("apiId") String apiId,
                                                           @RequestParam("server") String server,
                                                           @RequestParam("session") boolean sessionUse) {
        String logging = "apiId : " + apiId + " / server : " + server;
        log.info("GET/Character Status [{}] | Session ID [{}]", logging, session.getId());

        if (sessionUse) {
            List<CharacterRequest> characterSession = getSessionCharacter(session);
            characterSession.add(new CharacterRequest(apiId, server));
            setSessionCharacter(session, characterSession);
        }

        CharacterResponse characterResponse = characterService.createResponse(characterService.characterStatus(server,apiId));

        return ResponseEntity.ok(characterResponse);
    }

    @GetMapping("character_adventure")
    @ResponseBody
    public ResponseEntity<List<CharacterResponse>> getCharacterAdventure(HttpSession session,
                                                                         @RequestParam("adventure") String adventureName) {
        String logging = "adventureName : " + adventureName;
        log.info("GET/Character Adventure [{}] | Session ID [{}]", logging, session.getId());

        List<CharacterResponse> responses = characterService.createResponseList(characterService.characterAdventure(adventureName));

        return ResponseEntity.ok(responses);
    }

    @GetMapping("character_session")
    @ResponseBody
    public ResponseEntity<List<CharacterResponse>> getCharacterSession(HttpSession session) {
        log.info("GET/Character Session | Session ID [{}]", session.getId());

        List<CharacterRequest> requestList = getSessionCharacter(session);
        List<CharacterResponse> responses = characterService.createResponseList(characterService.characterStatusList(false, requestList));

        return ResponseEntity.ok(responses);
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
        Object sessionData = session.getAttribute("character");
        List<CharacterRequest> characterSession = new ArrayList<>();
        if (sessionData != null) {
            characterSession.addAll((List<CharacterRequest>) sessionData);
        }
        return characterSession;
    }

    private void setSessionCharacter(HttpSession session, List<CharacterRequest> characterSession) {
        session.setAttribute("character", characterSession);
    }
}
