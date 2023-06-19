package com.party_grouping.api;

import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import org.json.JSONObject;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class ApiRequest implements Api {
    private final WebClient webClient;

    public ApiRequest() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.neople.co.kr/df/")
                .build();
    }

    @Override
    public String callRequest(String url) {
        try {
            return webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException ex) {
            throw errorHandler(ex.getResponseBodyAsString());
        }
    }

    private ApiException errorHandler(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody)
                .optJSONObject("error");

        if (jsonObject == null) {
            return new ApiException(500);       // 알 수 없는 에러
        }

        String code = jsonObject.optString("code");
        String message = jsonObject.optString("message");
        int status = jsonObject.optInt("status");

        System.out.println(jsonObject);

        return switch (code) {
            case "DNF001" -> new ApiException(ErrorCode.CHARACTER_NOT_FOUND);
            case "DNF980" -> new ApiException(ErrorCode.SYSTEM_INSPECT);
            default -> new ApiException(code, message, status);
        };
    }
}
