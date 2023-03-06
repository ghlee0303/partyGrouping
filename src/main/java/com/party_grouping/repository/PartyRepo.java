package com.party_grouping.repository;

import com.party_grouping.dto.PartyDto;
import com.party_grouping.entity.DungeonEntity;
import com.party_grouping.entity.GroupEntity;
import com.party_grouping.entity.PartyEntity;
import com.party_grouping.entity.QPartyEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class PartyRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final QPartyEntity qPartyEntity = new QPartyEntity("partyEntity");
    private final DungeonRepo dungeonRepo;
    private final GroupRepo groupRepo;
    @Autowired
    private ModelMapper modelMapper;

    public PartyRepo(JPAQueryFactory queryFactory, DungeonRepo dungeonRepo, GroupRepo groupRepo) {
        this.queryFactory = queryFactory;
        this.dungeonRepo = dungeonRepo;
        this.groupRepo = groupRepo;
    }

    @Transactional
    public Integer save(PartyDto partyDto) {
        DungeonEntity dungeon = dungeonRepo.findByIdOptEntity(partyDto.getDungeon().getId()).get();
        GroupEntity group = groupRepo.findByIdOptEntity(partyDto.getParty().getId()).get();

        PartyEntity party = new PartyEntity(
                partyDto.getPartyName(),
                partyDto.getEntryTime(),
                dungeon,
                group);
        em.persist(party);
        em.flush();

        return group.getId();
    }

    public Optional<PartyDto> findByIdOptDto(Integer partyId) {
        PartyEntity partyEntity = queryFactory
                .selectFrom(qPartyEntity)
                .where(qPartyEntity.id.eq(partyId))
                .fetchOne();

        return Optional.ofNullable(modelMapper.map(partyEntity, PartyDto.class));
    }

    public Optional<PartyEntity> findByIdOptEntity(Integer partyId) {
        // 반드시 Repo 단에서만 사용할 것
        return Optional.ofNullable(queryFactory
                .selectFrom(qPartyEntity)
                .where(qPartyEntity.id.eq(partyId))
                .fetchOne());
    }
}
