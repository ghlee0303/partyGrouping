package com.party_grouping.repository;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.QCharacterDto;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.QCharacterEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CharacterRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private QCharacterEntity qCharacterEntity = new QCharacterEntity("character");

    public CharacterRepo(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Transactional
    public Integer save(CharacterEntity character) {
        em.persist(character);
        em.flush();
        return character.getId();
    }

    public Optional<CharacterDto> findByIdOptDto(int characterId) {
        return Optional.ofNullable(queryFactory
                .select(new QCharacterDto(qCharacterEntity.id, qCharacterEntity.name, qCharacterEntity.level))
                .from(qCharacterEntity)
                .where(qCharacterEntity.id.eq(characterId))
                .fetchOne());
    }
}
