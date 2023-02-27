package com.party_grouping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dungeon")
public class DungeonEntity extends BaseEntity{
    @Id
    public Integer id;
    public Integer dungeonCode;
    public String name;
    public String description;
    public Integer levelLimit;

    public DungeonEntity() {
    }
}
