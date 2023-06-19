package com.party_grouping.repository;

import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.ExchangeEntity;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.party_grouping.request.ExchangeRequestDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.party_grouping.entity.QExchangeEntity.exchangeEntity;
public class ExchangeRepo {
    @PersistenceContext
    private EntityManager em;
    private final JPAQueryFactory queryFactory;
    @Autowired
    private ModelMapper modelMapper;
    private final CharacterRepo characterRepo;

    public ExchangeRepo(JPAQueryFactory queryFactory, CharacterRepo characterRepo) {
        this.queryFactory = queryFactory;
        this.characterRepo = characterRepo;
    }

    @Transactional
    public String save(ExchangeRequestDto exchangeRequestDto, String persistentKey) {
        String server = exchangeRequestDto.getServer();
        List<CharacterEntity> characterEntityList = exchangeRequestDto.getApiIdList().stream().map(apiId ->
                        characterRepo.findCharacterEntity(server, apiId)
                    .orElseThrow(() -> { throw new ApiException(ErrorCode.CHARACTER_NOT_FOUND); })
        ).collect(Collectors.toList());
        
        ExchangeEntity exchange = createEntity(characterEntityList, persistentKey);

        em.persist(exchange);
        em.flush();

        return exchange.getPersistentKey();
    }

    private ExchangeEntity createEntity(List<CharacterEntity> characterEntityList, String persistentKey) {
        return ExchangeEntity.builder()
                .character1(characterEntityList.get(0))
                .character2(characterEntityList.get(1))
                .character3(characterEntityList.get(2))
                .character4(characterEntityList.get(3))
                .persistentKey(persistentKey)
                .build();
    }

    public List<ExchangeDto> findByPersistentKeyList(List<String> persistentKey) {
        List<ExchangeEntity> exchangeList = queryFactory
                .selectFrom(exchangeEntity)
                .where(exchangeEntity.persistentKey.in(persistentKey)
                        .and(exchangeEntity.del_date.isNull()))
                .fetch();

        return exchangeList
                .stream().map(exchange -> modelMapper.map(exchange, ExchangeDto.class)).toList();
    }

    public List<ExchangeDto> findByExchangeKeyList(List<Integer> exchangeKey) {
        List<ExchangeEntity> exchangeList = queryFactory
                .selectFrom(exchangeEntity)
                .where(exchangeEntity.exchangeKey.in(exchangeKey)
                        .and(exchangeEntity.del_date.isNull()))
                .fetch();

        return exchangeList
                .stream().map(exchange -> modelMapper.map(exchange, ExchangeDto.class)).toList();
    }

    private ExchangeEntity findPersistentKey(String persistentKey) {
        return queryFactory
                .selectFrom(exchangeEntity)
                .where(exchangeEntity.persistentKey.eq(persistentKey)
                        .and(exchangeEntity.del_date.isNull()))
                .fetchOne();
    }

    @Transactional
    public void updateExchangeKey(String persistentKey, int exchangeKey) {
        ExchangeEntity exchange = findPersistentKey(persistentKey);

        if (exchange == null){
            throw new ApiException(ErrorCode.PARTY_NOT_FOUND);
        }

        exchange.setExchangeKey(exchangeKey);
    }

    @Transactional
    public void deleteExchange(String persistentKey) {
        ExchangeEntity exchange = findPersistentKey(persistentKey);
        exchange.setDel_date(LocalDateTime.now());
        em.flush();
    }

    public boolean isKeyUnique(String key) {
        return key == null || queryFactory
                .selectFrom(exchangeEntity)
                .where(exchangeEntity.persistentKey.eq(key)
                        .and(exchangeEntity.del_date.isNull()))
                .fetch() == null;
    }

    public boolean isKeyUnique(int key) {
        return key == 0 || queryFactory
                .selectFrom(exchangeEntity)
                .where(exchangeEntity.exchangeKey.eq(key)
                        .and(exchangeEntity.del_date.isNull()))
                .fetch() == null;
    }
}
