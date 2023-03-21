package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "party_and_character")
public class PartyAndCharacterEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer partyNumber;
    private String description;

    @ManyToOne(targetEntity = CharacterEntity.class)
    @JoinColumn(name="character_id")
    private CharacterEntity character;

    @ManyToOne(targetEntity = PartyEntity.class)
    @JoinColumn(name = "party_id")
    private PartyEntity party;

    public PartyAndCharacterEntity() {
    }

    public PartyAndCharacterEntity(Integer partyNumber, CharacterEntity character, PartyEntity party) {
        this.partyNumber = partyNumber;
        this.character = character;
        this.party = party;
    }

    public PartyAndCharacterEntity(Integer partyNumber, String description, CharacterEntity character, PartyEntity party) {
        this.partyNumber = partyNumber;
        this.description = description;
        this.character = character;
        this.party = party;
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

    public void setPartyNumber(Integer partyNumber) {
        this.partyNumber = partyNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCharacter(CharacterEntity character) {
        this.character = character;
    }

    public void setParty(PartyEntity party) {
        this.party = party;
    }
}
