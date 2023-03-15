package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PartyAndCharacterDto extends BaseDto{
    private Integer id;
    private String description;

    private CharacterDto character;
    private PartyDto party;

    @JsonProperty("pac_party_number")
    private Integer partyNumber;
    @JsonProperty("pac_character_id")
    private Integer characterId;
    @JsonProperty("pac_party_id")
    private Integer partyId;

    public PartyAndCharacterDto() {
    }
}
