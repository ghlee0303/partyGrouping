package com.party_grouping.repository;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.QCharacterEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class CharacterRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private QCharacterEntity qCharacterEntity = new QCharacterEntity("characterEntity");
    @Autowired
    private ModelMapper modelMapper;

    public CharacterRepo(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Transactional
    public Integer save(CharacterDto characterDto) {
        CharacterEntity character = new CharacterEntity(characterDto.getName(), characterDto.getLevel());
        em.persist(character);
        em.flush();

        return character.getId();
    }

    public Optional<CharacterDto> findByIdOptDto(Integer characterId) {
        CharacterEntity characterEntity = queryFactory
                .selectFrom(qCharacterEntity)
                .where(qCharacterEntity.id.eq(characterId))
                .fetchOne();

        return Optional.ofNullable(modelMapper.map(characterEntity, CharacterDto.class));
    }

    public List<CharacterDto> findListDto() {
        List<CharacterEntity> characterEntityList = queryFactory
                .selectFrom(qCharacterEntity)
                .fetch();

        return characterEntityList
                .stream().map(characterEntity -> modelMapper.map(characterEntity, CharacterDto.class)).toList();
    }

    public Optional<CharacterEntity> findByIdOptEntity(Integer characterId) {
        // 반드시 Repo 단에서만 사용할 것
        return Optional.ofNullable(queryFactory
                .selectFrom(qCharacterEntity)
                .where(qCharacterEntity.id.eq(characterId))
                .fetchOne());
    }
}
