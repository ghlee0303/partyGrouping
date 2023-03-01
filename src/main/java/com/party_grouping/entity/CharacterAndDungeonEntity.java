package com.party_grouping.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "character-dungeon")
public class CharacterAndDungeonEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime clearDate;

    @ManyToOne(targetEntity = CharacterEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="character_id")
    private CharacterEntity character;

    @ManyToOne(targetEntity = DungeonEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "dungeon_id")
    private DungeonEntity dungeon;

    public CharacterAndDungeonEntity() {
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getClearDate() {
        return clearDate;
    }

    public CharacterEntity getCharacter() {
        return character;
    }

    public DungeonEntity getDungeon() {
        return dungeon;
    }
}
