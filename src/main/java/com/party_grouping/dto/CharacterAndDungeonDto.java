package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.party_grouping.entity.CharacterAndDungeonEntity;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.DungeonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class CharacterAndDungeonDto extends BaseDto {
    private Integer id;
    private LocalDateTime clearDate;

    private CharacterDto character;
    private DungeonDto dungeon;

    @JsonProperty("CAD_character_id")
    private Integer characterId;
    @JsonProperty("CAD_dungeon_id")
    private Integer dungeonId;

    public CharacterAndDungeonDto() {
    }

    public CharacterAndDungeonEntity toEntity() {
        return new CharacterAndDungeonEntity(character.toEntity(), dungeon.toEntity());
    }

    public CharacterAndDungeonEntity toEntity(CharacterEntity characterEntity, DungeonEntity dungeonEntity) {
        return new CharacterAndDungeonEntity(characterEntity, dungeonEntity);
    }

}
