package com.party_grouping.repository;

import com.party_grouping.dto.PartyAndCharacterDto;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.PartyAndCharacterEntity;
import com.party_grouping.entity.PartyEntity;
import com.party_grouping.entity.QPartyAndCharacterEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class PartyAndCharacterRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final QPartyAndCharacterEntity qPartyAndCharacterEntity = new QPartyAndCharacterEntity("partyAndCharacterEntity");
    private final PartyRepo partyRepo;
    private final CharacterRepo characterRepo;
    @Autowired
    private ModelMapper modelMapper;

    public PartyAndCharacterRepo(JPAQueryFactory queryFactory, PartyRepo partyRepo, CharacterRepo characterRepo) {
        this.queryFactory = queryFactory;
        this.partyRepo = partyRepo;
        this.characterRepo = characterRepo;
    }

    @Transactional
    public Integer save(PartyAndCharacterDto partyAndCharacterDto) {
        PartyEntity party = partyRepo.findByIdOptEntity(partyAndCharacterDto.getPartyDto().getId()).get();
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

    public Optional<PartyAndCharacterDto> findByIdOptDto(Integer partyAndCharacterId) {
        PartyAndCharacterEntity partyAndCharacterEntity = queryFactory
                .selectFrom(qPartyAndCharacterEntity)
                .where(qPartyAndCharacterEntity.id.eq(partyAndCharacterId))
                .fetchOne();

        return Optional.ofNullable(modelMapper.map(partyAndCharacterEntity, PartyAndCharacterDto.class));
    }
}
