package com.party_grouping.repository;

import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.ExchangeEntity;
import com.party_grouping.entity.QExchangeEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.*;

public class ExchangeRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final QExchangeEntity qExchangeEntity;
    @Autowired
    private ModelMapper modelMapper;
    private final CharacterRepo characterRepo;

    public ExchangeRepo(JPAQueryFactory queryFactory, QExchangeEntity qExchangeEntity, CharacterRepo characterRepo) {
        this.queryFactory = queryFactory;
        this.qExchangeEntity = qExchangeEntity;
        this.characterRepo = characterRepo;
    }

    @Transactional
    public String save(List<CharacterEntity> characterEntityList) {
        ExchangeEntity exchangeEntity = createEntity(characterEntityList);
        em.persist(exchangeEntity);
        em.flush();

        return exchangeEntity.getExchangeKey();
    }
    private ExchangeEntity createEntity(List<CharacterEntity> characterEntityList) {

        return ExchangeEntity.builder()
                .character1(characterEntityList.get(0))
                .character2(characterEntityList.get(1))
                .character3(characterEntityList.get(2))
                .character4(characterEntityList.get(3))
                .exchangeKey(generateExchangeKey())
                .build();
    }

    public Optional<ExchangeDto> findByIdOptDto(Integer exchangeId) {
        ExchangeEntity exchangeEntity = queryFactory
                .selectFrom(qExchangeEntity)
                .where(qExchangeEntity.id.eq(exchangeId))
                .fetchOne();

        return Optional.ofNullable(exchangeEntity)
                .map(entity -> modelMapper.map(entity, ExchangeDto.class));
    }

    private boolean isExchangeKeyUnique(String exchangeKey) {
        if (exchangeKey == null) {
            return true;
        }

        Long count = queryFactory.select(qExchangeEntity.count())
                .from(qExchangeEntity)
                .where(qExchangeEntity.exchangeKey.eq(exchangeKey))
                .fetchOne();

        return count != 0;
    }

    private String generateExchangeKey() {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int LENGTH = 9;
        String exchangeKey = null;

        while (isExchangeKeyUnique(exchangeKey)) {
            StringBuilder builder = new StringBuilder();
            SecureRandom random = new SecureRandom();
            while (builder.length() < LENGTH) {
                int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(index));
            }
            exchangeKey = builder.toString();
        }

        return exchangeKey;
    }
}
