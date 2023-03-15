package com.party_grouping.dto;

import com.party_grouping.entity.DungeonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DungeonDto extends BaseDto {
    private Integer id;
    // 던전 별 분류
    // 색인과 비슷
    private Integer dungeonCode;
    private String name;
    private String description;
    private Integer levelLimit;

    public DungeonDto() {
    }

    public DungeonEntity toEntity() {
        return new DungeonEntity(this.dungeonCode, this.name, this.description, this.levelLimit);
    }
}
