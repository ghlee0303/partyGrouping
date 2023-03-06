package com.party_grouping.repository;

import com.party_grouping.dto.GroupDto;
import com.party_grouping.dto.QGroupDto;
import com.party_grouping.entity.GroupEntity;
import com.party_grouping.entity.QGroupEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GroupRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final QGroupEntity qGroupEntity = new QGroupEntity("groupEntity");
    @Autowired
    private ModelMapper modelMapper;

    public GroupRepo(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Transactional
    public Integer save(GroupDto groupDto) {
        GroupEntity group = new GroupEntity(groupDto.getGroupName());
        em.persist(group);
        em.flush();

        return group.getId();
    }

    public Optional<GroupDto> findByIdOptDto(Integer groupId) {
        GroupEntity groupEntity = queryFactory
                .selectFrom(qGroupEntity)
                .where(qGroupEntity.id.eq(groupId))
                .fetchOne();

        return Optional.ofNullable(modelMapper.map(groupEntity, GroupDto.class));
    }

    public Optional<GroupEntity> findByIdOptEntity(Integer groupId) {
        // 반드시 Repo 단에서만 사용할 것
        return Optional.ofNullable(queryFactory
                .selectFrom(qGroupEntity)
                .where(qGroupEntity.id.eq(groupId))
                .fetchOne());
    }
}
