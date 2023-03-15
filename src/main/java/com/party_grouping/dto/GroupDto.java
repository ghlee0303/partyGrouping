package com.party_grouping.dto;

import com.party_grouping.entity.GroupEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GroupDto extends BaseDto {
    private Integer id;
    private String name;

    public GroupDto() {
    }

    public GroupEntity toEntity() {
        return new GroupEntity(this.name);
    }
}
