package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PartyAndCharacterDto extends BaseDto {
    @JsonIgnore
    protected Integer id;
    protected String description;

    protected CharacterDto character;
    protected PartyDto party;
    protected Integer partyNumber;

    public PartyAndCharacterDto() {
    }
}
