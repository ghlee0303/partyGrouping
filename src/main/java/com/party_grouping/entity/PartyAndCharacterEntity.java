package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "party-character")
public class PartyAndCharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Integer getId() {
        return id;
    }

    public Integer getPartyNumber() {
        return partyNumber;
    }

    public String getDescription() {
        return description;
    }

    public CharacterEntity getCharacter() {
        return character;
    }

    public PartyEntity getParty() {
        return party;
    }
}
