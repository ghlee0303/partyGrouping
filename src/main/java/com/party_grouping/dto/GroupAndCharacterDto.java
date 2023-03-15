package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(callSuper=false)
public class GroupAndCharacterDto extends BaseDto {
    private Integer id;

    private CharacterDto character;
    private GroupDto group;

    @JsonProperty("GAC_character_id")
    private Integer characterId;
    @JsonProperty("GAC_group_id")
    private Integer groupId;

    public GroupAndCharacterDto() {
    }

    public GroupAndCharacterDto(Integer id, LocalDateTime reg_date, LocalDateTime del_date) {
        this.id = id;
        super.reg_date = reg_date;
        super.del_date = del_date;
    }
}
