package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.party_grouping.entity.GroupEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GroupDto extends BaseDto {
    protected Integer id;
    @JsonProperty("group_name")
    protected String name;

    public GroupDto() {
    }

    public GroupEntity toEntity() {
        return new GroupEntity(this.name);
    }
}
