package com.party_grouping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CharacterDto extends BaseDto {
    private Integer id;
    private String name;
    private Integer level;

    public CharacterDto() {
    }

    @QueryProjection
    public CharacterDto(Integer id, String name, Integer level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public void callTime() {
        System.out.println(super.getDel_date());
    }
}
