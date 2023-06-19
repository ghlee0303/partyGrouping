package com.party_grouping.api;

import lombok.Data;

import java.util.HashMap;

@Data
public class Buff {
    int buffLevel;
    String buffName;
    boolean buffer;
    String buffId;
    boolean buffMax;

    public Buff() {
    }

    public Buff(int buffLevel, String buffName, String buffId, HashMap<String, Integer> anotherMaxLevel) {
        this.buffLevel = buffLevel;
        this.buffName = buffName;
        this.buffId = buffId;
        this.buffer = isBufferJob();
        this.buffMax = isBuffLevelMax(anotherMaxLevel);
    }

    private boolean isBufferJob() {
        return switch (this.buffName) {
            case "용맹의 축복", "영광의 축복", "러블리 템포", "금단의 저주" -> true;
            default -> false;
        };
    }

    private boolean isBuffLevelMax(HashMap<String, Integer> anotherMaxLevel) {
        if (buffer) {
            return false;
        }

        Integer maxLevel = anotherMaxLevel.getOrDefault(buffName, 20);

        return maxLevel.equals(buffLevel);
    }
}
