package com.party_grouping.repository;

import com.party_grouping.dto.CharacterAndDungeonDto;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.CharacterItemDto;
import com.party_grouping.entity.*;
import com.party_grouping.exception.ApiException;
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
    private final CharacterRepo characterRepo;
    @Autowired
    private ModelMapper modelMapper;

    public CharacterItemRepo(JPAQueryFactory queryFactory, QCharacterItemEntity qCharacterItemEntity, CharacterRepo characterRepo) {
        this.queryFactory = queryFactory;
        this.qCharacterItemEntity = qCharacterItemEntity;
        this.characterRepo = characterRepo;
    }

    @Transactional
    public Integer save(CharacterItemDto characterItemDto, String server, String characterApiId) {
        CharacterItemEntity characterItemEntity = modelMapper.map(characterItemDto, CharacterItemEntity.class);
        characterItemEntity.setCharacter(characterRepo.findByApiIdOptEntity(server, characterApiId).orElseThrow(() -> {
            throw new ApiException("해당 캐릭터가 존재하지 않습니다.", 404);
        }));

        em.persist(characterItemEntity);
        em.flush();

        return characterItemEntity.getId();
    }
}
