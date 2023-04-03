package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.party_grouping.entity.DungeonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DungeonDto extends BaseDto {
    private Integer id;
    // 던전 별 분류
    // 색인과 비슷
    @JsonProperty("dungeon_code")
    protected Integer dungeonCode;
    @JsonProperty("dungeon_name")
    protected String name;
    @JsonProperty("dungeon_description")
    protected String description;
    @JsonProperty("dungeon_level_limit")
    protected Integer levelLimit;

    public DungeonDto() {
    }

    public DungeonEntity toEntity() {
        return new DungeonEntity(this.dungeonCode, this.name, this.description, this.levelLimit);
    }
}
