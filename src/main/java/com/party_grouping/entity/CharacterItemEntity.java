package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "character_item_table")
public class CharacterItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer weaponReinforce;            // 무기 강화
    private Integer weaponRefine;               // 무기 재련
    private String weaponAmp;                   // 무기 증폭 여부
    private Integer amulet;                     // 목걸이 마부
    private Integer wrist;                      // 팔찌 마부
    private Integer ring;                       // 반지 마부
    private boolean siv;                        // 시브 보장 여부
    private boolean creature;                   // 종결 크리쳐 여부
    private boolean aurora;                     // 종결오라 여부
    private boolean title;                      // 종결 칭호 여부
    private Integer enchantSkillBonus;          // 어깨/벨트 스공 합

    public CharacterItemEntity() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getWeaponReinforce() {
        return weaponReinforce;
    }

    public void setWeaponReinforce(Integer weaponReinforce) {
        this.weaponReinforce = weaponReinforce;
    }

    public Integer getWeaponRefine() {
        return weaponRefine;
    }

    public void setWeaponRefine(Integer weaponRefine) {
        this.weaponRefine = weaponRefine;
    }

    public String getWeaponAmp() {
        return weaponAmp;
    }

    public void setWeaponAmp(String weaponAmp) {
        this.weaponAmp = weaponAmp;
    }

    public boolean isTitle() {
        return title;
    }

    public void setTitle(boolean title) {
        this.title = title;
    }

    public Integer getAmulet() {
        return amulet;
    }

    public void setAmulet(Integer amulet) {
        this.amulet = amulet;
    }

    public Integer getWrist() {
        return wrist;
    }

    public void setWrist(Integer wrist) {
        this.wrist = wrist;
    }

    public Integer getRing() {
        return ring;
    }

    public void setRing(Integer ring) {
        this.ring = ring;
    }

    public boolean isSiv() {
        return siv;
    }

    public void setSiv(boolean siv) {
        this.siv = siv;
    }

    public Integer getEnchantSkillBonus() {
        return enchantSkillBonus;
    }

    public void setEnchantSkillBonus(Integer enchantSkillBonus) {
        this.enchantSkillBonus = enchantSkillBonus;
    }

    public boolean isAurora() {
        return aurora;
    }

    public void setAurora(boolean aurora) {
        this.aurora = aurora;
    }

    public boolean isCreature() {
        return creature;
    }

    public void setCreature(boolean creature) {
        this.creature = creature;
    }
}
