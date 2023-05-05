package com.party_grouping.api;

import lombok.Data;

@Data
public class Buff {
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
        return switch (buffName) {
            case "용맹의 축복", "영광의 축복", "러블리 템포", "금단의 저주" -> true;
            default -> false;
        };
    }
}
