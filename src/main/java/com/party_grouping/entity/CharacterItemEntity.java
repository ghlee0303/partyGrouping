package com.party_grouping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "character_item_table")
public class CharacterItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = CharacterEntity.class)
    @JoinColumn(name="character_id")
    private CharacterEntity character;
    private Integer weaponReinforce;            // 무기 강화
    private Integer weaponRefine;               // 무기 재련
    private String weaponAmp;                   // 무기 증폭 여부
    private String title;                       // 칭호명
    private Integer amulet;                     // 목걸이 마부
    private Integer wrist;                      // 팔찌 마부
    private Integer ring;                       // 반지 마부
    private boolean siv;                        // 시브 보장 여부
    private Integer enchantSkillBonus;          // 어깨/벨트 스공 합
    private String creature;                    // 크리쳐명

    public CharacterItemEntity() {
    }

    public Integer getId() {
        return id;
    }

    public CharacterEntity getCharacter() {
        return character;
    }

    public void setCharacter(CharacterEntity character) {
        this.character = character;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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

    public String getCreature() {
        return creature;
    }

    public void setCreature(String creature) {
        this.creature = creature;
    }
}
