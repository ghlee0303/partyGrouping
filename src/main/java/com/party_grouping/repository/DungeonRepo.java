package com.party_grouping.repository;

import com.party_grouping.dto.DungeonDto;
import com.party_grouping.dto.QDungeonDto;
import com.party_grouping.entity.DungeonEntity;
import com.party_grouping.entity.QDungeonEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class DungeonRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private QDungeonEntity qDungeonEntity = new QDungeonEntity("dungeonEntity");
    @Autowired
    private ModelMapper modelMapper;

    public DungeonRepo(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Transactional
    public Integer save(DungeonDto dungeonDto) {
        DungeonEntity dungeon = new DungeonEntity(
                dungeonDto.getDungeonCode(),
                dungeonDto.getName(),
                dungeonDto.getDescription(),
                dungeonDto.getLevelLimit());

        em.persist(dungeon);
        em.flush();

        return dungeon.getId();
    }

    public Optional<DungeonDto> findByIdOptDto(Integer dungeonId) {
        DungeonEntity dungeonEntity = queryFactory
                .selectFrom(qDungeonEntity)
                .where(qDungeonEntity.id.eq(dungeonId))
                .fetchOne();

        return Optional.ofNullable(modelMapper.map(dungeonEntity, DungeonDto.class));
    }

    public Optional<DungeonEntity> findByIdOptEntity(Integer dungeonId) {
        // 반드시 Repo 단에서만 사용할 것
        return Optional.ofNullable(queryFactory
                .selectFrom(qDungeonEntity)
                .where(qDungeonEntity.id.eq(dungeonId))
                .fetchOne());
    }
}
