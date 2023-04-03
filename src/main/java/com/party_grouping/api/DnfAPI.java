package com.party_grouping.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.party_grouping.api.deserializer.JsonToCharacterDtoDeserializer;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.exception.JsonToDtoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
public class DnfAPI {
    @Value("${dnf-api-key}")
    private String apiKey;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public DnfAPI() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<CharacterDto> callCharacterAPI(String server, String name) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new JsonToCharacterDtoDeserializer());
        System.out.println(apiKey);

        this.objectMapper.registerModule(module);
        String url = String.format("https://api.neople.co.kr/df/servers/%s/characters?characterName=%s&wordType=full&apikey=%s", server, name, apiKey);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();

        try {
            TypeReference<List<CharacterDto>> typeReference = new TypeReference<>(){};
            List<CharacterDto> characters = objectMapper.readValue(responseBody, typeReference);

            if (characters.isEmpty()) {
                throw new JsonToDtoException("검색 결과가 없습니다.", 302);
            }

            return characters;
        } catch (JsonProcessingException e) {
            // 로깅 처리
            throw new JsonToDtoException("JSON 처리 중 오류가 발생했습니다.", 301);
        }
    }
}
