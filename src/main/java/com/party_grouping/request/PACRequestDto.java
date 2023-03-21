package com.party_grouping.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PACRequestDto {
    @JsonProperty("pac_party_number")
    private Integer partyNumber;
    @JsonProperty("pac_character_id")
    private Integer characterId;
    @JsonProperty("pac_party_id")
    private Integer partyId;

}
