package com.party_grouping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class GroupAndCharacterDto extends BaseDto {
    private Integer id;
    private CharacterDto characterDto;
    private GroupDto groupDto;

    public GroupAndCharacterDto() {
    }

    @QueryProjection
    public GroupAndCharacterDto(Integer id, CharacterDto characterDto, GroupDto groupDto) {
        this.id = id;
        this.characterDto = characterDto;
        this.groupDto = groupDto;
    }
}
