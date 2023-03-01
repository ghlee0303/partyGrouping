package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dungeon_table")
public class DungeonEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer dungeonCode;
    public String name;
    public String description;
    public Integer levelLimit;

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
