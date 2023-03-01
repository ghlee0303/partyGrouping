package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group-character")
public class GroupAndCharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @ManyToOne(targetEntity = CharacterEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="character_id")
    public CharacterEntity character;

    @ManyToOne(targetEntity = GroupEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    public GroupEntity group;

    public GroupAndCharacterEntity() {
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
}
