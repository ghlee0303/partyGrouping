package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.party_grouping.entity.CharacterEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CharacterDto extends BaseDto {
    protected Integer id;
    @JsonProperty("character_name")
    protected String name;
    @JsonProperty("character_level")
    protected Integer level;
    protected String apiId;
    protected String server;
    protected String jobName;       // 직업군
    protected String jobGrowName;   // 전직
    protected String jobId;         // 직업군 id
    protected String jobGrowId;     // 전직 id

    public CharacterDto() {
    }

    public CharacterDto(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    public CharacterDto(String name, Integer level, String apiId, String server, String jobName, String jobGrowName, String jobId, String jobGrowId) {
        this.name = name;
        this.level = level;
        this.apiId = apiId;
        this.server = server;
        this.jobName = jobName;
        this.jobGrowName = jobGrowName;
        this.jobId = jobId;
        this.jobGrowId = jobGrowId;
    }

    public void callTime() {
        System.out.println(super.getDel_date());
    }

    public CharacterEntity toEntity() {
        return new CharacterEntity(this.name, this.level);
    }
}
