package com.party_grouping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class DungeonDto extends BaseDto {
    public Integer id;
    public Integer dungeonCode;
    public String name;
    public String description;
    public Integer levelLimit;

    public DungeonDto() {
    }

    @QueryProjection
    public DungeonDto(Integer id, Integer dungeonCode, String name, String description, Integer levelLimit) {
        this.id = id;
        this.dungeonCode = dungeonCode;
        this.name = name;
        this.description = description;
        this.levelLimit = levelLimit;
    }
}
