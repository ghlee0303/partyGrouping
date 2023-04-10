package com.party_grouping.repository;

import com.party_grouping.dto.GroupAndCharacterDto;
import com.party_grouping.dto.GroupDto;
import com.party_grouping.entity.GroupEntity;
import com.party_grouping.entity.QGroupEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GroupRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final QGroupEntity qGroupEntity;
    @Autowired
    private ModelMapper modelMapper;

    public GroupRepo(JPAQueryFactory queryFactory, QGroupEntity qGroupEntity) {
        this.queryFactory = queryFactory;
        this.qGroupEntity = qGroupEntity;
    }

    @Transactional
    public Integer save(GroupDto groupDto) {
        GroupEntity group = new GroupEntity(groupDto.getName());
        em.persist(group);
        em.flush();

        return group.getId();
    }

    public Optional<GroupDto> findByIdOptDto(Integer groupId) {
        GroupEntity groupEntity = queryFactory
                .selectFrom(qGroupEntity)
                .where(qGroupEntity.id.eq(groupId))
                .fetchOne();

        return Optional.ofNullable(groupEntity)
                .map(entity -> modelMapper.map(entity, GroupDto.class));
    }

    public List<GroupDto> findListDto() {
        List<GroupEntity> groupEntityList = queryFactory
                .selectFrom(qGroupEntity)
                .fetch();

        return groupEntityList
                .stream().map(groupEntity -> modelMapper.map(groupEntity, GroupDto.class)).toList();
    }

    public Optional<GroupEntity> findByIdOptEntity(Integer groupId) {
        // 반드시 Repo 단에서만 사용할 것
        return Optional.ofNullable(queryFactory
                .selectFrom(qGroupEntity)
                .where(qGroupEntity.id.eq(groupId))
                .fetchOne());
    }

}
