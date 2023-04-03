package com.party_grouping.api.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.party_grouping.dto.CharacterDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonToCharacterDtoDeserializer extends StdDeserializer<List<CharacterDto>> {
    public JsonToCharacterDtoDeserializer() {
        this(null);
    }
    public JsonToCharacterDtoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<CharacterDto> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        JsonNode characterNode = node.get("rows");
        List<CharacterDto> characters = new ArrayList<>();

        for (JsonNode character : characterNode) {
            CharacterDto characterDto = new CharacterDto(
                    character.get("characterName").asText(),
                    character.get("level").asInt(),
                    character.get("characterId").asText(),
                    character.get("serverId").asText(),
                    character.get("jobName").asText(),
                    character.get("jobGrowName").asText(),
                    character.get("jobId").asText(),
                    character.get("jobGrowId").asText()
            );
            characters.add(characterDto);
        }

        return characters;
    }
}
