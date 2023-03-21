package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_and_character")
public class GroupAndCharacterEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = CharacterEntity.class)
    @JoinColumn(name="character_id")
    private CharacterEntity character;

    @ManyToOne(targetEntity = GroupEntity.class)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    public GroupAndCharacterEntity() {
    }

    public GroupAndCharacterEntity(CharacterEntity character, GroupEntity group) {
        this.character = character;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public CharacterEntity getCharacter() {
        return character;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setCharacter(CharacterEntity character) {
        this.character = character;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
