package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dungeon_table")
public class DungeonEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer dungeonCode;
    private String name;
    private String description;
    private Integer levelLimit;

    public DungeonEntity() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getDungeonCode() {
        return dungeonCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getLevelLimit() {
        return levelLimit;
    }
}
