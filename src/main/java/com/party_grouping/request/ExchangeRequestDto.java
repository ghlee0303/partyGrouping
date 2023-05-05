package com.party_grouping.request;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRequestDto {
    private String server;
    private List<String> apiIdList;

    public ExchangeRequestDto() {
    }

    public ExchangeRequestDto(JSONObject jsonObject) {
        this.server = jsonObject.getString("server");
        this.apiIdList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            JSONObject apiIdObject = jsonObject.getJSONArray("apiIdList").getJSONObject(i);
            String apiId = apiIdObject.getString("apiId");
            apiIdList.add(apiId);
        }
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public List<String> getApiIdList() {
        return apiIdList;
    }

    public void setApiIdList(List<String> apiIdList) {
        this.apiIdList = apiIdList;
    }
}
