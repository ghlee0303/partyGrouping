package com.party_grouping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PartyAndCharacterDto extends BaseDto{
    private Integer id;
    private Integer partyNumber;
    private String description;

    public PartyAndCharacterDto() {
    }

    @QueryProjection
    public PartyAndCharacterDto(Integer id, Integer partyNumber, String description) {
        this.id = id;
        this.partyNumber = partyNumber;
        this.description = description;
    }
}
