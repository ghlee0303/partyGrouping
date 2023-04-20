package com.party_grouping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exchange_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String exchangeKey;

    @ManyToOne(targetEntity = CharacterEntity.class)
    @JoinColumn(name="character_id1")
    private CharacterEntity character1;

    @ManyToOne(targetEntity = CharacterEntity.class)
    @JoinColumn(name="character_id2")
    private CharacterEntity character2;

    @ManyToOne(targetEntity = CharacterEntity.class)
    @JoinColumn(name="character_id3")
    private CharacterEntity character3;

    @ManyToOne(targetEntity = CharacterEntity.class)
    @JoinColumn(name="character_id4")
    private CharacterEntity character4;

    public Integer getId() {
        return id;
    }

    public String getExchangeKey() {
        return exchangeKey;
    }

    public void setExchangeKey(String exchangeKey) {
        this.exchangeKey = exchangeKey;
    }

    public CharacterEntity getCharacter1() {
        return character1;
    }

    public void setCharacter1(CharacterEntity character1) {
        this.character1 = character1;
    }

    public CharacterEntity getCharacter2() {
        return character2;
    }

    public void setCharacter2(CharacterEntity character2) {
        this.character2 = character2;
    }

    public CharacterEntity getCharacter3() {
        return character3;
    }

    public void setCharacter3(CharacterEntity character3) {
        this.character3 = character3;
    }

    public CharacterEntity getCharacter4() {
        return character4;
    }

    public void setCharacter4(CharacterEntity character4) {
        this.character4 = character4;
    }
}
