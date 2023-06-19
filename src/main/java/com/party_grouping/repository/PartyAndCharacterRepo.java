package com.party_grouping.repository;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.PartyAndCharacterDto;
import com.party_grouping.dto.PartyDto;
import com.party_grouping.entity.*;
import com.party_grouping.request.PACRequestDto;
import com.party_grouping.response.dto.PACResponseDto;
import com.party_grouping.util.ApiUtils;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PartyAndCharacterRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final PartyRepo partyRepo;
    private final CharacterRepo characterRepo;
    private final QCharacterAndDungeonEntity qCharacterAndDungeonEntity;
    private final QPartyAndCharacterEntity qPartyAndCharacterEntity;
    private final QPartyEntity qPartyEntity;
    @Autowired
    private ModelMapper modelMapper;

    public PartyAndCharacterRepo(JPAQueryFactory queryFactory,
                                 PartyRepo partyRepo,
                                 CharacterRepo characterRepo,
                                 QPartyAndCharacterEntity qPartyAndCharacterEntity,
                                 QCharacterAndDungeonEntity qCharacterAndDungeonEntity,
                                 QPartyEntity qPartyEntity) {
        this.queryFactory = queryFactory;
        this.partyRepo = partyRepo;
        this.characterRepo = characterRepo;
        this.qPartyAndCharacterEntity = qPartyAndCharacterEntity;
        this.qCharacterAndDungeonEntity = qCharacterAndDungeonEntity;
        this.qPartyEntity = qPartyEntity;
    }

    @Transactional
    public Integer save(PartyAndCharacterDto partyAndCharacterDto) {
        PartyEntity party = partyRepo.findByIdOptEntity(partyAndCharacterDto.getParty().getId()).get();
        CharacterEntity character = characterRepo.findByIdOptEntity(partyAndCharacterDto.getCharacter().getId()).get();
        PartyAndCharacterEntity partyAndCharacterEntity = new PartyAndCharacterEntity(
                partyAndCharacterDto.getPartyNumber(),
                partyAndCharacterDto.getDescription(),
                character,
                party);

        em.persist(partyAndCharacterEntity);
        em.flush();

        return partyAndCharacterEntity.getId();
    }

    @Transactional
    public Integer save(PACRequestDto pacRequestDto) {
        PartyEntity party = partyRepo.findByIdOptEntity(pacRequestDto.getPartyId()).get();
        CharacterEntity character = characterRepo.findByIdOptEntity(pacRequestDto.getCharacterId()).get();
        PartyAndCharacterEntity partyAndCharacterEntity = new PartyAndCharacterEntity(
                pacRequestDto.getPartyNumber(),
                character,
                party);

        em.persist(partyAndCharacterEntity);
        em.flush();

        return partyAndCharacterEntity.getId();
    }

    public List<PartyAndCharacterDto> findListDto() {
        List<PartyAndCharacterEntity> partyAndCharacterEntityList = queryFactory
                .selectFrom(qPartyAndCharacterEntity)
                .fetch();

        System.out.println("???");

        return partyAndCharacterEntityList
                .stream().map(partyAndCharacterEntity -> modelMapper.map(partyAndCharacterEntity, PartyAndCharacterDto.class)).toList();
    }

    public List<PartyAndCharacterDto> findByPartyListDto(Integer partyId) {
        List<PartyAndCharacterEntity> partyAndCharacterEntityList = queryFactory
                .selectFrom(qPartyAndCharacterEntity)
                .where(qPartyAndCharacterEntity.party.id.eq(partyId))
                .fetch();

        System.out.println("???");

        return partyAndCharacterEntityList
                .stream().map(partyAndCharacterEntity -> modelMapper.map(partyAndCharacterEntity, PartyAndCharacterDto.class)).toList();
    }

    public Optional<PartyAndCharacterDto> findByIdOptDto(Integer partyAndCharacterId) {
        PartyAndCharacterEntity partyAndCharacterEntity = queryFactory
                .selectFrom(qPartyAndCharacterEntity)
                .where(qPartyAndCharacterEntity.id.eq(partyAndCharacterId))
                .fetchOne();

        return Optional.ofNullable(partyAndCharacterEntity)
                .map(entity -> modelMapper.map(entity, PartyAndCharacterDto.class));
    }

    public List<PACResponseDto> findByPartyIdWithClearDateListResDto(Integer partyId) {
        List<Tuple> result = queryFactory
                .select(qPartyAndCharacterEntity.id,
                        qPartyAndCharacterEntity.character,
                        qPartyAndCharacterEntity.party,
                        qPartyAndCharacterEntity.partyNumber,
                        qCharacterAndDungeonEntity.id,
                        qCharacterAndDungeonEntity.clearDate)
                .from(qPartyAndCharacterEntity)
                .join(qPartyEntity).on(qPartyEntity.id.eq(qPartyAndCharacterEntity.party.id))
                .leftJoin(qCharacterAndDungeonEntity)
                .on(qPartyAndCharacterEntity.character.eq(qCharacterAndDungeonEntity.character)
                        .and(qPartyEntity.dungeon.eq(qCharacterAndDungeonEntity.dungeon)))
                .where(qPartyAndCharacterEntity.party.id.eq(partyId))
                .fetch();

        List<PACResponseDto> returnList = new ArrayList<>();
        LocalDateTime thursday = ApiUtils.getLastThursdayOfWeek();
        result.forEach(tuple -> {
            returnList.add(new PACResponseDto(
                    tuple.get(qPartyAndCharacterEntity.id),
                    tuple.get(qPartyAndCharacterEntity.description),
                    modelMapper.map(tuple.get(qPartyAndCharacterEntity.character), CharacterDto.class),
                    modelMapper.map(tuple.get(qPartyAndCharacterEntity.party), PartyDto.class),
                    tuple.get(qPartyAndCharacterEntity.partyNumber),
                    tuple.get(qCharacterAndDungeonEntity.clearDate),
                    thursday));
        });

        return returnList;
    }
}
