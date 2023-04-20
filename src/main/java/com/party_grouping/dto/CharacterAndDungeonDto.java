package com.party_grouping.dto;

import com.party_grouping.entity.CharacterAndDungeonEntity;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.DungeonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class CharacterAndDungeonDto extends BaseDto {
    protected Integer id;
    protected LocalDateTime clearDate;

    protected CharacterDto character;
    protected DungeonDto dungeon;

    public CharacterAndDungeonDto() {
    }

}
