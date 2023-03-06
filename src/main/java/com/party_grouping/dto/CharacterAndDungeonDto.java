package com.party_grouping.dto;

import com.party_grouping.entity.CharacterAndDungeonEntity;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.DungeonEntity;
import com.querydsl.core.annotations.QueryProjection;
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

    public CharacterAndDungeonDto() {
    }

    public CharacterAndDungeonDto(Integer id, LocalDateTime clearDate, LocalDateTime reg_date, LocalDateTime del_date) {
        this.id = id;
        this.clearDate = clearDate;
        super.reg_date = reg_date;
        super.del_date = del_date;
    }

    public CharacterAndDungeonEntity toEntity() {
        return new CharacterAndDungeonEntity(character.toEntity(), dungeon.toEntity());
    }

    public CharacterAndDungeonEntity toEntity(CharacterEntity characterEntity, DungeonEntity dungeonEntity) {
        return new CharacterAndDungeonEntity(characterEntity, dungeonEntity);
    }

}
