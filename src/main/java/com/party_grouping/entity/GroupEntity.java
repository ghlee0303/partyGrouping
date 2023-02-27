package com.party_grouping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group")
public class GroupEntity extends BaseEntity {
    @Id
    public Integer id;
    public String groupName;

    public GroupEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }
}
