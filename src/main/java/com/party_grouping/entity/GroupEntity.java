package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_table")
public class GroupEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String groupName;

    public GroupEntity() {
    }

    public GroupEntity(String groupName) {
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }
}
