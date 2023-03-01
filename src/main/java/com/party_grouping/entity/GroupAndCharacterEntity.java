package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group-character")
public class GroupAndCharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = CharacterEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="character_id")
    private CharacterEntity character;

    @ManyToOne(targetEntity = GroupEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

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
