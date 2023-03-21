package com.party_grouping.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PartyAndCharacterDto extends BaseDto {
    private Integer id;
    private String description;

    private CharacterDto character;
    private PartyDto party;
    private Integer partyNumber;

    public PartyAndCharacterDto() {
    }
}
