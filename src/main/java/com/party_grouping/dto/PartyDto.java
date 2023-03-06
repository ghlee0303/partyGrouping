package com.party_grouping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class PartyDto extends BaseDto {
    private Integer id;
    private String partyName;
    private LocalDateTime entryTime;
    private PartyDto party;
    private DungeonDto dungeon;

    public PartyDto() {
    }

    public PartyDto(Integer id, String partyName, LocalDateTime entryTime, LocalDateTime reg_date, LocalDateTime del_date) {
        this.id = id;
        this.partyName = partyName;
        this.entryTime = entryTime;
        super.reg_date = reg_date;
        super.del_date = del_date;
    }
}
