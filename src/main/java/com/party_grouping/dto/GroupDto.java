package com.party_grouping.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class GroupDto extends BaseDto {
    private Integer id;
    private String groupName;

    public GroupDto() {
    }

    @QueryProjection
    public GroupDto(Integer id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }
}
