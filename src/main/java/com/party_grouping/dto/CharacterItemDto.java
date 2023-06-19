package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
public class CharacterItemDto extends BaseDto {
    @JsonIgnore
    protected Integer id;
    protected Integer weaponReinforce;       //  강화 or 증폭
    protected Integer weaponRefine;          // 재련
    protected String weaponAmp;              // 증폭 항목
    protected Integer wrist;                 // 팔찌
    protected Integer amulet;                // 목걸이
    protected Integer ring;                  // 반지
    protected boolean siv;                   // 시브 보장 여부
    protected boolean creature;              // 종결 크리쳐 여부
    protected boolean aurora;                // 종결오라 여부
    protected boolean title;                 // 종결 칭호 여부
    protected Integer enchantSkillBonus;     // 어벨 합 스증

    @QueryProjection
    public CharacterItemDto(Integer id, Integer weaponReinforce, Integer weaponRefine, String weaponAmp, Integer wrist, Integer amulet, Integer ring, boolean siv, boolean creature, boolean aurora, boolean title, Integer enchantSkillBonus) {
        this.id = id;
        this.weaponReinforce = weaponReinforce;
        this.weaponRefine = weaponRefine;
        this.weaponAmp = weaponAmp;
        this.wrist = wrist;
        this.amulet = amulet;
        this.ring = ring;
        this.siv = siv;
        this.creature = creature;
        this.aurora = aurora;
        this.title = title;
        this.enchantSkillBonus = enchantSkillBonus;
    }
}
