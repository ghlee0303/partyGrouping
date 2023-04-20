package com.party_grouping.api;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.exception.ApiException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiDnF {
    @Value("${dnf-api-key}")
    private String apiKey;
    private final WebClient webClient;

    public ApiDnF() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.neople.co.kr/df/")
                .build();
    }

    public WebClient.ResponseSpec requestAPIDnF(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode() == HttpStatus.BAD_REQUEST) {
                        throw new ApiException("요청에 대한 유효성 검증 실패 또는 필수 파라미터 에러", response.statusCode().value());
                    } else if (response.statusCode() == HttpStatus.UNAUTHORIZED) {
                        throw new ApiException("인증 오류", response.statusCode().value());
                    } else if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        throw new ApiException("존재하지 않은 리소스 또는 페이지", response.statusCode().value());
                    } else {
                        throw new ApiException("클라이언트 에러 발생", response.statusCode().value());
                    }
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    if (response.statusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                        throw new ApiException("서비스 이용 불가", response.statusCode().value());
                    } else {
                        throw new ApiException("서버 에러 발생", response.statusCode().value());
                    }
                });
    }

    public CharacterDto callCharacterStatus(String server, String characterApiId) {
        String url = String.format("servers/%s/characters/%s/status?apikey=%s", server, characterApiId, apiKey);
        Mono<String> characterStatus = requestAPIDnF(url)
                .bodyToMono(String.class);

        JSONObject jsonObject = new JSONObject(characterStatus.block());

        if (jsonObject.getJSONArray("status").isEmpty()){
            throw new ApiException("캐릭터가 잠금 상태입니다.", 404);
        }

        int fame = jsonObject.getJSONArray("status").getJSONObject(16).getInt("value");
        String adventureName = jsonObject.getString("adventureName");

        CharacterDto characterDto = setCharacterDto(jsonObject);
        characterDto.setFame(fame);
        characterDto.setAdventureName(adventureName);
        characterDto.setServer(server);

        Buff buff = callBuffSkill(server, characterApiId);
        characterDto.setBuffLevel(buff.buffLevel);
        characterDto.setBuffName(buff.buffName);
        characterDto.setBuffId(buff.buffId);
        characterDto.setBuffer(buff.buffer);
        System.out.println(characterDto);

        return characterDto;
    }

    public List<CharacterDto> callCharacter(String name) {
        String url = String.format("/servers/all/characters?characterName=%s&wordType=full&apikey=%s", name, apiKey);
        Mono<String> characterData = requestAPIDnF(url)
                .bodyToMono(String.class);

        return parsingCharacterListJson(characterData.block());
    }

    public Buff callBuffSkill(String server, String characterApiId) {
        String url = String.format("servers/%s/characters/%s/skill/buff/equip/equipment?apikey=%s", server, characterApiId, apiKey);
        Mono<String> characterData = requestAPIDnF(url)
                .bodyToMono(String.class);

        JSONObject buff = new JSONObject(characterData.block())
                .getJSONObject("skill")
                .optJSONObject("buff");

        if (buff == null) {
            throw new ApiException("버프강화를 선택해주세요.", 404);
        }

        JSONObject skillInfo = buff
                .getJSONObject("skillInfo");

        String buffName = skillInfo.getString("name");
        String buffId = skillInfo.getString("skillId");
        int buffLevel = skillInfo.getJSONObject("option").getInt("level");

        return new Buff(buffLevel, buffName, buffId);
    }

    private List<CharacterDto> parsingCharacterListJson(String response) {
        List<CharacterDto> characterDtoList = new ArrayList<>();

        JSONArray jsonArray = new JSONObject(response)
                .getJSONArray("rows");

        if (jsonArray.isEmpty()) {
            throw new ApiException("검색 결과가 없습니다.", 404);
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            characterDtoList.add(setCharacterDto(jsonArray.getJSONObject(i)));
        }

        return characterDtoList;
    }

    private CharacterDto setCharacterDto(JSONObject json) {
        return CharacterDto.builder()
                .name(json.getString("characterName"))
                .level(json.getInt("level"))
                .apiId(json.getString("characterId"))
                .server(json.optString("serverId"))
                .jobName(json.getString("jobName"))
                .jobGrowName(json.getString("jobGrowName"))
                .jobId(json.getString("jobId"))
                .jobGrowId(json.getString("jobGrowId"))
                .build();
    }

    private static class Buff {
        int buffLevel;
        String buffName;
        String buffId;
        boolean buffer;

        public Buff(int buffLevel, String buffName, String buffId) {
            this.buffLevel = buffLevel;
            this.buffName = buffName;
            this.buffId = buffId;
            this.buffer = isBuffer(buffName);
        }

        private boolean isBuffer(String buffName) {
            return buffName.equals("용맹의 축복")
                    || buffName.equals("영광의 축복")
                    || buffName.equals("러블리 템포")
                    || buffName.equals("금단의 저주");
        }
    }
}
