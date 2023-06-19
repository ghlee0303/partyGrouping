package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(callSuper=false)
public class GroupAndCharacterDto extends BaseDto {
    @JsonIgnore
    protected Integer id;

    protected CharacterDto character;
    protected GroupDto group;

    public GroupAndCharacterDto() {
    }

    public GroupAndCharacterDto(Integer id, LocalDateTime reg_date, LocalDateTime del_date) {
        this.id = id;
        super.reg_date = reg_date;
        super.del_date = del_date;
    }
}
