package com.party_grouping.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CADRequestDto {
    @JsonProperty("cad_character_id")
    private Integer characterId;
    @JsonProperty("cad_dungeon_id")
    private Integer dungeonId;
    @JsonProperty("cad_clear")
    private boolean clear;

    private LocalDateTime clearDate;

    public CADRequestDto(Integer characterId, Integer dungeonId, boolean clear) {
        this.characterId = characterId;
        this.dungeonId = dungeonId;
        this.clear = clear;
    }

}
