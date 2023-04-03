package com.party_grouping.response.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.PartyAndCharacterDto;
import com.party_grouping.dto.PartyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PACResponseDto extends PartyAndCharacterDto {
    private boolean clear;
    @JsonIgnore
    private LocalDateTime clearDate;

    public PACResponseDto(Integer id,
                          String description,
                          CharacterDto character,
                          PartyDto party,
                          Integer partyNumber,
                          LocalDateTime clearDate,
                          LocalDateTime thursday) {
        this.id = id;
        this.description = description;
        this.character = character;
        this.party = party;
        this.partyNumber = partyNumber;
        this.clearDate = clearDate;
        this.clear = clearDate != null && thursday.isBefore(clearDate);
    }
}
