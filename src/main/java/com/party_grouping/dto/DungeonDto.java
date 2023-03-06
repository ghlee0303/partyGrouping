package com.party_grouping.dto;

import com.party_grouping.entity.DungeonEntity;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class DungeonDto extends BaseDto {
    private Integer id;
    private Integer dungeonCode;
    private String name;
    private String description;
    private Integer levelLimit;

    public DungeonDto() {
    }

    @QueryProjection
    public DungeonDto(Integer id, Integer dungeonCode, String name, String description, Integer levelLimit, LocalDateTime reg_date, LocalDateTime del_date) {
        this.id = id;
        this.dungeonCode = dungeonCode;
        this.name = name;
        this.description = description;
        this.levelLimit = levelLimit;
        super.setReg_date(reg_date);
        super.setDel_date(del_date);
    }

    public DungeonEntity toEntity() {
        return new DungeonEntity(this.dungeonCode, this.name, this.description, this.levelLimit);
    }
}
