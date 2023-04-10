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
    private final QCharacterEntity qCharacterEntity;
    @Autowired
    private ModelMapper modelMapper;

    public CharacterRepo(JPAQueryFactory queryFactory, QCharacterEntity qCharacterEntity) {
        this.queryFactory = queryFactory;
        this.qCharacterEntity = qCharacterEntity;
    }

    @Transactional
    public Integer save(CharacterDto characterDto) {
        CharacterEntity character = modelMapper.map(characterDto, CharacterEntity.class);
        System.out.println(character.getName() + " / " + character.getApiId());
        em.persist(character);
        em.flush();

        return character.getId();
    }

    public Optional<CharacterDto> findByIdOptDto(Integer characterId) {
        CharacterEntity characterEntity = queryFactory
                .selectFrom(qCharacterEntity)
                .where(qCharacterEntity.id.eq(characterId))
                .fetchOne();

        return Optional.ofNullable(characterEntity)
                .map(entity -> modelMapper.map(entity, CharacterDto.class));
    }

    public List<CharacterDto> findListDto() {
        // 나중에 삭제
        List<CharacterEntity> characterEntityList = queryFactory
                .selectFrom(qCharacterEntity)
                .fetch();

        return characterEntityList
                .stream().map(characterEntity -> modelMapper.map(characterEntity, CharacterDto.class)).toList();
    }

    public Optional<CharacterDto> findByApiIdOptDto(String apiId) {
        CharacterEntity characterEntity = queryFactory
                .selectFrom(qCharacterEntity)
                .where(qCharacterEntity.apiId.eq(apiId))
                .fetchOne();

        return Optional.ofNullable(characterEntity)
                .map(entity -> modelMapper.map(entity, CharacterDto.class));
    }

    public Optional<CharacterEntity> findByIdOptEntity(Integer characterId) {
        // 반드시 Repo 단에서만 사용할 것
        return Optional.ofNullable(queryFactory
                .selectFrom(qCharacterEntity)
                .where(qCharacterEntity.id.eq(characterId))
                .fetchOne());
    }

    public Optional<CharacterEntity> findByApiIdOptEntity(String apiId) {
        // 반드시 Repo 단에서만 사용할 것
        return Optional.ofNullable(queryFactory
                .selectFrom(qCharacterEntity)
                .where(qCharacterEntity.apiId.eq(apiId))
                .fetchOne());
    }

    // DB에 캐릭터가 존재한다면 update 및 Dto에 Fame set, 없다면 insert
    @Transactional
    public void apiDnfSave(List<CharacterDto> characterDtoList) {
        for (CharacterDto characterDto : characterDtoList) {
            Optional<CharacterEntity> findCharacterOpt = findByApiIdOptEntity(characterDto.getApiId());

            findCharacterOpt.ifPresentOrElse(
                    findCharacter -> {
                        findCharacter.setName(characterDto.getName());
                        findCharacter.setLevel(characterDto.getLevel());
                        findCharacter.setJobGrowName(characterDto.getJobGrowName());
                        findCharacter.setJobGrowId(characterDto.getJobGrowId());
                        characterDto.setFame(findCharacter.getFame());
                    },
                    () -> save(characterDto)
            );
        }
    }
}
