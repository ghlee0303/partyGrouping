package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.party_grouping.entity.GroupEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class PartyDto extends BaseDto {
    @JsonIgnore
    protected Integer id;
    protected String name;
    protected LocalDateTime entryTime;

    protected DungeonDto dungeon;
    protected GroupDto group;

    public PartyDto() {
    }

    public PartyDto(Integer id, String name, LocalDateTime entryTime, LocalDateTime reg_date, LocalDateTime del_date) {
        this.id = id;
        this.name = name;
        this.entryTime = entryTime;
        super.reg_date = reg_date;
        super.del_date = del_date;
    }
}
