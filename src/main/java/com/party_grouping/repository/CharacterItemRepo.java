package com.party_grouping.repository;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.CharacterItemDto;
import com.party_grouping.entity.*;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CharacterItemRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final QCharacterItemEntity qCharacterItemEntity;
    @Autowired
    private ModelMapper modelMapper;

    public CharacterItemRepo(JPAQueryFactory queryFactory, QCharacterItemEntity qCharacterItemEntity) {
        this.queryFactory = queryFactory;
        this.qCharacterItemEntity = qCharacterItemEntity;
    }

    @Transactional
    public Integer save(CharacterItemDto characterItemDto) {
        CharacterItemEntity characterItemEntity = modelMapper.map(characterItemDto, CharacterItemEntity.class);
        em.persist(characterItemEntity);
        em.flush();

        return characterItemEntity.getId();
    }

    @Transactional
    public void updateItem(CharacterItemEntity findItem, CharacterItemDto characterItemDto) {
        findItem.setWeaponReinforce(characterItemDto.getWeaponReinforce());
        findItem.setWeaponRefine(characterItemDto.getWeaponRefine());
        findItem.setWeaponAmp(characterItemDto.getWeaponAmp());
        findItem.setTitle(characterItemDto.isTitle());
        findItem.setAmulet(characterItemDto.getAmulet());
        findItem.setWrist(characterItemDto.getWrist());
        findItem.setRing(characterItemDto.getRing());
        findItem.setSiv(characterItemDto.isSiv());
        findItem.setEnchantSkillBonus(characterItemDto.getEnchantSkillBonus());
        findItem.setCreature(characterItemDto.isCreature());
    }

    public void characterItem(CharacterItemEntity findItem, CharacterItemDto characterItemDto) {
        // characterItemDto == null 은 버퍼의 경우
        if (findItem == null && characterItemDto != null) {
            save(characterItemDto);
        } else if (characterItemDto != null) {
            updateItem(findItem, characterItemDto);
        }
    }
}
