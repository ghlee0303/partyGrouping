package com.party_grouping.request;

import lombok.Data;

@Data
public class CharacterRequestDto {
    String apiId;
    String server;

    public CharacterRequestDto(String apiId, String server) {
        this.apiId = apiId;
        this.server = server;
    }
}
