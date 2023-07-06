package com.party_grouping.repository;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.QCharacterDto;
import com.party_grouping.dto.QCharacterItemDto;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.request.CharacterRequest;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.party_grouping.entity.QCharacterEntity.characterEntity;
import static com.party_grouping.entity.QCharacterItemEntity.characterItemEntity;

public class CharacterRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    @Autowired
    private ModelMapper modelMapper;
    private final CharacterItemRepo characterItemRepo;

    public CharacterRepo(JPAQueryFactory queryFactory, CharacterItemRepo characterItemRepo) {
        this.queryFactory = queryFactory;
        this.characterItemRepo = characterItemRepo;
    }

    @Transactional
    public Integer save(CharacterDto characterDto) {
        CharacterEntity character = modelMapper.map(characterDto, CharacterEntity.class);
        em.persist(character);
        em.flush();

        return character.getId();
    }

    public Optional<CharacterDto> findCharacterDto(String server, String apiId) {

        return Optional.ofNullable(selectQuery()
                .where(characterEntity.apiId.eq(apiId)
                                .and(characterEntity.server.eq(server))
                                .and(characterEntity.del_date.isNull()))
                .fetchOne());
    }

    public Optional<CharacterEntity> findByIdOptEntity(Integer characterId) {
        // 반드시 Repo 단에서만 사용할 것
        return Optional.ofNullable(queryFactory
                .selectFrom(characterEntity)
                .where(characterEntity.id.eq(characterId)
                        .and(characterEntity.del_date.isNull()))
                .fetchOne());
    }

    public Optional<CharacterEntity> findCharacterEntity(String server, String apiId) {
        // 반드시 Repo 단에서만 사용할 것
        return Optional.ofNullable(queryFactory
                .selectFrom(characterEntity)
                .where(characterEntity.apiId.eq(apiId)
                        .and(characterEntity.server.eq(server))
                        .and(characterEntity.del_date.isNull()))
                .fetchOne());
    }

    public List<CharacterDto> findByAdventureDto(String adventure) {
        return selectQuery()
                .where(characterEntity.adventureName.eq(adventure)
                        .and(characterEntity.del_date.isNull()))
                .fetch();
    }

    public List<CharacterDto> findStatusList(List<CharacterRequest> characterRequestList) {
        BooleanExpression whereClause = buildFindStatusMultiQuery(characterRequestList);

        List<CharacterDto> result = selectQuery()
                .where(whereClause)
                .fetch();
        Collections.reverse(result);

        return result;
    }

    public QCharacterDto qCharacterDto() {
        return new QCharacterDto(characterEntity.id,
                characterEntity.name,
                characterEntity.level,
                characterEntity.fame,
                characterEntity.apiId,
                characterEntity.server,
                characterEntity.adventureName,
                characterEntity.jobName,
                characterEntity.jobGrowName,
                characterEntity.jobId,
                characterEntity.jobGrowId,
                characterEntity.buffLevel,
                characterEntity.buffMax,
                characterEntity.buffName,
                characterEntity.buffId,
                characterEntity.buffer,
                new QCharacterItemDto(characterItemEntity.id,
                        characterItemEntity.weaponReinforce,
                        characterItemEntity.weaponRefine,
                        characterItemEntity.weaponAmp,
                        characterItemEntity.wrist,
                        characterItemEntity.amulet,
                        characterItemEntity.ring,
                        characterItemEntity.siv,
                        characterItemEntity.creature,
                        characterItemEntity.aurora,
                        characterItemEntity.title,
                        characterItemEntity.enchantSkillBonus));
    }

    private JPAQuery<CharacterDto> selectQuery() {
        return queryFactory
                .select(qCharacterDto())
                .from(characterEntity)
                .leftJoin(characterEntity.item, characterItemEntity);
    }

    private BooleanExpression buildFindStatusMultiQuery(List<CharacterRequest> characterRequestList) {
        BooleanExpression whereClause = Expressions.FALSE;

        for (CharacterRequest req : characterRequestList) {
            String apiId = req.getApiId();
            String server = req.getServer();

            BooleanExpression condition = characterEntity.apiId.eq(apiId)
                            .and(characterEntity.server.eq(server)
                            .and(characterEntity.del_date.isNull()));

            if (whereClause == Expressions.FALSE) {
                whereClause = condition;
            } else {
                whereClause = whereClause.or(condition);
            }
        }


        return whereClause;
    }

    // DB에 캐릭터가 존재한다면 update 없다면 insert
    @Transactional
    public void characterStatus(CharacterDto characterDto) {
        Optional<CharacterEntity> findCharacterOpt = findCharacterEntity(characterDto.getServer(), characterDto.getApiId());
        findCharacterOpt.ifPresentOrElse(
                findCharacter -> {
                    findCharacter.setName(characterDto.getName());
                    findCharacter.setLevel(characterDto.getLevel());
                    findCharacter.setJobGrowName(characterDto.getJobGrowName());
                    findCharacter.setJobGrowId(characterDto.getJobGrowId());
                    findCharacter.setFame(characterDto.getFame());
                    findCharacter.setAdventureName(characterDto.getAdventureName());
                    findCharacter.setBuffLevel(characterDto.getBuffLevel());
                    findCharacter.setBuffMax(characterDto.isBuffMax());
                    findCharacter.setBuffer(characterDto.isBuffer());
                    characterItemRepo.characterItem(findCharacter.getItem(), characterDto.getItem());
                },
                () -> {
                    save(characterDto);
                }
        );
    }
}
