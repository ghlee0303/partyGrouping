package com.party_grouping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PartyDto extends BaseDto {
    private Integer id;
    private String partyName;
    private LocalDateTime entryTime;

    public PartyDto() {
    }

    @QueryProjection
    public PartyDto(Integer id, String partyName, LocalDateTime entryTime) {
        this.id = id;
        this.partyName = partyName;
        this.entryTime = entryTime;
    }
}
