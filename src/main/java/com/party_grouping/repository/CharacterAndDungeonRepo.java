package com.party_grouping.repository;

import com.party_grouping.dto.CharacterAndDungeonDto;
import com.party_grouping.entity.CharacterAndDungeonEntity;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.DungeonEntity;
import com.party_grouping.entity.QCharacterAndDungeonEntity;
import com.party_grouping.request.CADRequestDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CharacterAndDungeonRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final CharacterRepo characterRepo;
    private final DungeonRepo dungeonRepo;
    private final QCharacterAndDungeonEntity qCharacterAndDungeonEntity;
    @Autowired
    private ModelMapper modelMapper;

    public CharacterAndDungeonRepo(JPAQueryFactory queryFactory, CharacterRepo characterRepo, DungeonRepo dungeonRepo, QCharacterAndDungeonEntity qCharacterAndDungeonEntity) {
        this.queryFactory = queryFactory;
        this.characterRepo = characterRepo;
        this.dungeonRepo = dungeonRepo;
        this.qCharacterAndDungeonEntity = qCharacterAndDungeonEntity;
    }

    @Transactional
    public Integer save(CharacterAndDungeonDto characterAndDungeonDto) {
        CharacterEntity character = characterRepo.findByIdOptEntity(characterAndDungeonDto.getCharacter().getId()).get();
        DungeonEntity dungeon = dungeonRepo.findByIdOptEntity(characterAndDungeonDto.getDungeon().getId()).get();
        CharacterAndDungeonEntity characterAndDungeon = new CharacterAndDungeonEntity(character, dungeon);

        em.persist(characterAndDungeon);
        em.flush();

        return characterAndDungeon.getId();
    }

    @Transactional
    public Integer save(CADRequestDto cadRequestDto) {
        CharacterEntity character = characterRepo.findByIdOptEntity(cadRequestDto.getCharacterId()).get();
        DungeonEntity dungeon = dungeonRepo.findByIdOptEntity(cadRequestDto.getDungeonId()).get();
        CharacterAndDungeonEntity characterAndDungeon = new CharacterAndDungeonEntity(character, dungeon, cadRequestDto.getClearDate());

        em.persist(characterAndDungeon);
        em.flush();

        return characterAndDungeon.getId();
    }

    public Optional<CharacterAndDungeonDto> findByIdOptDto(Integer characterAndDungeonId) {
        // 반드시 Repo 단에서만 사용할 것
        CharacterAndDungeonEntity characterAndDungeon = queryFactory
                .selectFrom(qCharacterAndDungeonEntity)
                .where(qCharacterAndDungeonEntity.id.eq(characterAndDungeonId))
                .fetchOne();

        return Optional.ofNullable(characterAndDungeon)
                .map(entity -> modelMapper.map(entity, CharacterAndDungeonDto.class));
    }
}
