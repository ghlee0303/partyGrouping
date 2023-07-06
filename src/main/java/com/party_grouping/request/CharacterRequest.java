package com.party_grouping.request;

import lombok.Data;

@Data
public class CharacterRequest {
    String apiId;
    String server;

    public CharacterRequest(String apiId, String server) {
        this.apiId = apiId;
        this.server = server;
    }
}
