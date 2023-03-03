package com.party_grouping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CharacterAndDungeonDto extends BaseDto {
    private Integer id;
    private LocalDateTime clearDate;
    private CharacterDto character;
    private DungeonDto dungeon;

    public CharacterAndDungeonDto() {
    }

    @QueryProjection
    public CharacterAndDungeonDto(Integer id, LocalDateTime clearDate, CharacterDto character, DungeonDto dungeon) {
        this.id = id;
        this.clearDate = clearDate;
        this.character = character;
        this.dungeon = dungeon;
    }
}
