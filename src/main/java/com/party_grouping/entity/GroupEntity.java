package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_table")
public class GroupEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public GroupEntity() {
    }

    public GroupEntity(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
