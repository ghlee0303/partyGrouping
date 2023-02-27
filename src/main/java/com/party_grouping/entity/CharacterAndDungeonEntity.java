package com.party_grouping.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "character-dungeon")
public class CharacterAndDungeonEntity extends BaseEntity {
    @Id
    public Integer id;

    public LocalDateTime clearDate;

    @ManyToOne(targetEntity = CharacterEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="character_id")
    public CharacterEntity character;

    @ManyToOne(targetEntity = DungeonEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "dungeon_id")
    public DungeonEntity dungeon;

    public CharacterAndDungeonEntity() {
    }
}
