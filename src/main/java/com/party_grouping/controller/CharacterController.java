package com.party_grouping.controller;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.request.CharacterRequestDto;
import com.party_grouping.service.CharacterService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
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
    public String getCharacter(HttpServletRequest request, HttpServletResponse response,
                               @CookieValue(value = COOKIE_NAME, required = false) String cookieValue) {
        Cookie[] cookies = request.getCookies();

        for(Cookie c : cookies) {
            System.out.println(c.getName() + " : " + c.getValue());
        }
        return "character";
    }

    @GetMapping("character_search")
    @ResponseBody
    public ResponseEntity<List<CharacterDto>> getCharacterSearch(@RequestParam(value = "name") String name,
                                                                 @RequestParam(value = "type") String type) {
        return new ResponseEntity<>(characterService.characterSearch(name, type), header, HttpStatus.OK);
    }

    @GetMapping("character_status")
    @ResponseBody
    public ResponseEntity<CharacterDto> getCharacterStatus(@RequestParam("apiId") String apiId,
                                                           @RequestParam("server") String server) {
        return new ResponseEntity<>(characterService.characterStatus(server, apiId), header, HttpStatus.OK);
    }

    @PostMapping("character_status_list")
    @ResponseBody
    public ResponseEntity<List<CharacterDto>> getCharacterStatusList(@RequestBody(required = false) String json) {
        JSONObject jsonObject = new JSONObject(json);
        boolean apiUse = jsonObject.optInt("apiUse") != 0;
        System.out.println(jsonObject);

        return new ResponseEntity<>(characterService.characterStatusList(apiUse, characterRequestList(jsonObject)), header, HttpStatus.OK);
    }

    private LinkedHashMap<String, String> characterRequestList(JSONObject jsonObject) {
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        JSONArray jsonArray = jsonObject.optJSONArray("characterRequestList");

        //for (int i = jsonArray.length()-1; i >= 0; i--) {
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject arrayJsonObject = jsonArray.getJSONObject(i);
            String apiId = arrayJsonObject.optString("apiId");
            String server = arrayJsonObject.optString("server");
            if (apiId != null && server != null) {
                hashMap.put(apiId, server);
                System.out.println(hashMap);
            }
        }

        return hashMap;
    }



    /*@PostMapping("character_status_list")
    @ResponseBody
    public ResponseEntity<List<CharacterDto>> getCharacterStatusList(@RequestBody(required = false) List<CharacterData> characterDataList) {
        if (characterDataList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), header, HttpStatus.OK);
        }

        List<CharacterDto> characterDtoList = characterDataList.stream()
                .map(characterData -> characterService.characterStatus(characterData.server, characterData.apiId)).toList();

        return new ResponseEntity<>(characterDtoList, header, HttpStatus.OK);
    }*/

    @Data
    public static class CharacterData {
        String apiId;
        String server;
    }
}
