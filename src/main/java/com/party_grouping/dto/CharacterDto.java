package com.party_grouping.dto;

import com.party_grouping.entity.CharacterEntity;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class CharacterDto extends BaseDto {
    private Integer id;
    private String name;
    private Integer level;

    public CharacterDto() {
    }

    @QueryProjection
    public CharacterDto(Integer id, String name, Integer level, LocalDateTime reg_date, LocalDateTime del_date) {
        this.id = id;
        this.name = name;
        this.level = level;
        super.reg_date = reg_date;
        super.del_date = del_date;
    }

    public void callTime() {
        System.out.println(super.getDel_date());
    }

    public CharacterEntity toEntity() {
        return new CharacterEntity(this.name, this.level);
    }

    public String imDto() {
        return "yes";
    }
}
