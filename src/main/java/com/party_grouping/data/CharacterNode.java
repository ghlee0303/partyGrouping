package com.party_grouping.data;

import lombok.Data;

@Data
public class CharacterNode {
    private String apiId;
    private String server;

    public CharacterNode(String apiId, String server) {
        this.apiId = apiId;
        this.server = server;
    }
}
