package com.party_grouping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "character")
public class CharacterEntity extends BaseEntity {
    @Id
    private Integer id;

    private String name;

    private Integer level;

    public CharacterEntity() {
    }

    public CharacterEntity(Integer id, String name, Integer level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }
}
