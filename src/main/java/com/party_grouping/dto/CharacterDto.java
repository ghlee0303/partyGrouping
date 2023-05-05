package com.party_grouping.dto;

import com.party_grouping.api.Buff;
import com.party_grouping.entity.CharacterEntity;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDto extends BaseDto {
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
    protected String buffName;
    protected String buffId;
    protected boolean buffer;

    public void setBuff(Buff buff) {
        this.buffLevel = buff.getBuffLevel();
        this.buffName = buff.getBuffName();
        this.buffId = buff.getBuffId();
        this.buffer = buff.isBuffer();

    }

}
