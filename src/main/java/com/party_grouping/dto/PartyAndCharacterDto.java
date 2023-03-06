package com.party_grouping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class PartyAndCharacterDto extends BaseDto{
    private Integer id;
    private Integer partyNumber;
    private String description;

    private CharacterDto character;
    private PartyDto partyDto;

    public PartyAndCharacterDto() {
    }

    @QueryProjection
    public PartyAndCharacterDto(Integer id, Integer partyNumber, String description, LocalDateTime reg_date, LocalDateTime del_date) {
        this.id = id;
        this.partyNumber = partyNumber;
        this.description = description;
        super.reg_date = reg_date;
        super.del_date = del_date;
    }
}
