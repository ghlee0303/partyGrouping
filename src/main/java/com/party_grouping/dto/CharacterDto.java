package com.party_grouping.dto;

import com.party_grouping.entity.CharacterEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CharacterDto extends BaseDto {
    private Integer id;
    private String name;
    private Integer level;

    public CharacterDto() {
    }

    public CharacterDto(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    public void callTime() {
        System.out.println(super.getDel_date());
    }

    public CharacterEntity toEntity() {
        return new CharacterEntity(this.name, this.level);
    }
}
