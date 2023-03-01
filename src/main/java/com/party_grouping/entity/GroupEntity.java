package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_table")
public class GroupEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
