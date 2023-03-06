package com.party_grouping.repository;

import com.party_grouping.dto.CharacterAndDungeonDto;
import com.party_grouping.dto.GroupAndCharacterDto;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.GroupAndCharacterEntity;
import com.party_grouping.entity.GroupEntity;
import com.party_grouping.entity.QGroupAndCharacterEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GroupAndCharacterRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final QGroupAndCharacterEntity qGroupAndCharacterEntity = new QGroupAndCharacterEntity("groupAndCharacterEntity");
    private final GroupRepo groupRepo;
    private final CharacterRepo characterRepo;
    @Autowired
    private ModelMapper modelMapper;

    public GroupAndCharacterRepo(JPAQueryFactory queryFactory, GroupRepo groupRepo, CharacterRepo characterRepo) {
        this.queryFactory = queryFactory;
        this.groupRepo = groupRepo;
        this.characterRepo = characterRepo;
    }

    @Transactional
    public Integer save(GroupAndCharacterDto groupAndCharacterDto) {
        CharacterEntity character = characterRepo.findByIdOptEntity(groupAndCharacterDto.getCharacter().getId()).get();
        GroupEntity group = groupRepo.findByIdOptEntity(groupAndCharacterDto.getGroup().getId()).get();
        GroupAndCharacterEntity groupAndCharacterEntity = new GroupAndCharacterEntity(character, group);

        em.persist(groupAndCharacterEntity);
        em.flush();

        return groupAndCharacterEntity.getId();
    }

    public Optional<GroupAndCharacterDto> findByIdOptDto(Integer groupAndCharacterId) {
        GroupAndCharacterEntity groupAndCharacter = queryFactory
                .selectFrom(qGroupAndCharacterEntity)
                .where(qGroupAndCharacterEntity.id.eq(groupAndCharacterId))
                .fetchOne();

        return Optional.ofNullable(modelMapper.map(groupAndCharacter, GroupAndCharacterDto.class));
    }
}
