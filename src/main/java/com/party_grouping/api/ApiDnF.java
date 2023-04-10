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
import java.util.List;

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

    public void callCharacterFame(String server, String characterApiId) {
        String url = String.format("https://api.neople.co.kr/df/servers/%s/characters/%s/status?apikey=%s", server, characterApiId, apiKey);
        Mono<String> characterFame = requestAPIDnF(url)
                .bodyToMono(String.class);

        System.out.println(characterFame.block());
    }

    public List<CharacterDto> callCharacter(String server, String name) {
        String url = String.format("/servers/%s/characters?characterName=%s&wordType=full&apikey=%s", server, name, apiKey);
        Mono<String> characterData = requestAPIDnF(url)
                .bodyToMono(String.class);

        return parsingCharacterJson(characterData.block());
    }

    private List<CharacterDto> parsingCharacterJson(String response) {
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
        return new CharacterDto(
                json.getString("characterName"),
                json.getInt("level"),
                json.getString("characterId"),
                json.getString("serverId"),
                json.getString("jobName"),
                json.getString("jobGrowName"),
                json.getString("jobId"),
                json.getString("jobGrowId")
        );
    }
}
