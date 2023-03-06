package com.party_grouping.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "character_and_dungeon")
public class CharacterAndDungeonEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime clearDate;

    @ManyToOne(targetEntity = CharacterEntity.class)
    @JoinColumn(name="character_id")
    private CharacterEntity character;

    @ManyToOne(targetEntity = DungeonEntity.class)
    @JoinColumn(name = "dungeon_id")
    private DungeonEntity dungeon;

    public CharacterAndDungeonEntity() {
    }

    public CharacterAndDungeonEntity(CharacterEntity character, DungeonEntity dungeon) {
        this.character = character;
        this.dungeon = dungeon;
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

    public void setClearDate(LocalDateTime clearDate) {
        this.clearDate = clearDate;
    }

    public void setCharacter(CharacterEntity character) {
        this.character = character;
    }

    public void setDungeon(DungeonEntity dungeon) {
        this.dungeon = dungeon;
    }
}
