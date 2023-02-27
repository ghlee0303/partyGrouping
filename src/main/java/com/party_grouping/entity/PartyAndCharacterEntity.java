package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "party-character")
public class PartyAndCharacterEntity {
    @Id
    public Integer id;
    public Integer partyNumber;
    public String description;

    @ManyToOne(targetEntity = CharacterEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="character_id")
    public CharacterEntity character;

    @ManyToOne(targetEntity = PartyEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    public PartyEntity party;

    public PartyAndCharacterEntity() {
    }
}
