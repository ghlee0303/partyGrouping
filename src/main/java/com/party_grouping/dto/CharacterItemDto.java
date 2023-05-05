package com.party_grouping.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterItemDto {
    protected Integer id;
    protected CharacterDto character;
    protected Integer weaponReinforce;
    protected Integer weaponRefine;
    protected String weaponAmp;
    protected String title;
    protected Integer amulet;
    protected Integer wrist;
    protected Integer ring;
    protected boolean siv;
    protected Integer enchantSkillBonus;
    protected String creature;
}
