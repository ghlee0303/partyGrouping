package com.party_grouping.response.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.party_grouping.dto.CharacterDto;
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
public class PACResponseDto {
    private Integer id;
    private String description;

    private CharacterDto character;
    private PartyDto party;
    private Integer partyNumber;
    private boolean clear;
    @JsonIgnore
    private LocalDateTime clearDate;

    public PACResponseDto(Integer id, String description, CharacterDto character, PartyDto party, Integer partyNumber, LocalDateTime clearDate) {
        this.id = id;
        this.description = description;
        this.character = character;
        this.party = party;
        this.partyNumber = partyNumber;
        this.clearDate = clearDate;

        // 날짜 계산해서 클리어 했는지 아닌지 판별하는 코드 작성할 것
    }


}
