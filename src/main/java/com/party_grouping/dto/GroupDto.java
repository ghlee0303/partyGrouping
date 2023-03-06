package com.party_grouping.dto;

import com.party_grouping.entity.GroupEntity;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class GroupDto extends BaseDto {
    private Integer id;
    private String groupName;

    public GroupDto() {
    }

    @QueryProjection
    public GroupDto(Integer id, String groupName, LocalDateTime reg_date, LocalDateTime del_date) {
        this.id = id;
        this.groupName = groupName;
        super.reg_date = reg_date;
        super.del_date = del_date;
    }

    public GroupEntity toEntity() {
        return new GroupEntity(this.groupName);
    }
}
