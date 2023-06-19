package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.party_grouping.api.Buff;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import static com.party_grouping.entity.QCharacterEntity.characterEntity;
import static com.party_grouping.entity.QCharacterItemEntity.characterItemEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
public class CharacterDto extends BaseDto {
    @JsonIgnore
    protected Integer id;
    protected String name;
    protected Integer level;
    protected Integer fame;
    protected String apiId;
    protected String server;
    protected String adventureName;
    protected String jobName;       // 직업군
    protected String jobGrowName;   // 전직
    protected String jobId;         // 직업군 id
    protected String jobGrowId;     // 전직 id
    protected int buffLevel;
    protected boolean buffMax;
    protected String buffName;
    protected String buffId;
    protected boolean buffer;
    protected CharacterItemDto item;

    public void setBuff(Buff buff) {
        this.buffLevel = buff.getBuffLevel();
        this.buffName = buff.getBuffName();
        this.buffId = buff.getBuffId();
        this.buffer = buff.isBuffer();
        this.buffMax = buff.isBuffMax();
    }

    @QueryProjection
    public CharacterDto(Integer id, String name, Integer level, Integer fame, String apiId, String server, String adventureName, String jobName, String jobGrowName, String jobId, String jobGrowId, int buffLevel, boolean buffMax, String buffName, String buffId, boolean buffer, CharacterItemDto item) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.fame = fame;
        this.apiId = apiId;
        this.server = server;
        this.adventureName = adventureName;
        this.jobName = jobName;
        this.jobGrowName = jobGrowName;
        this.jobId = jobId;
        this.jobGrowId = jobGrowId;
        this.buffLevel = buffLevel;
        this.buffMax = buffMax;
        this.buffName = buffName;
        this.buffId = buffId;
        this.buffer = buffer;
        this.item = item;
    }
}
