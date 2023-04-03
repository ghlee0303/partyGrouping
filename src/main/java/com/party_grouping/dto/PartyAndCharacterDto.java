package com.party_grouping.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PartyAndCharacterDto extends BaseDto {
    protected Integer id;
    protected String description;

    protected CharacterDto character;
    protected PartyDto party;
    protected Integer partyNumber;

    public PartyAndCharacterDto() {
    }
}
