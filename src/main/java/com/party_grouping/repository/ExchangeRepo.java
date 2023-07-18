package com.party_grouping.repository;

import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.ExchangeEntity;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.party_grouping.request.ExchangeRequest;
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
    public ExchangeDto save(ExchangeRequest exchangeRequest) {
        String server = exchangeRequest.getServer();
        List<CharacterEntity> characterEntityList = exchangeRequest.getApiIdList().stream().map(apiId ->
                        characterRepo.findCharacterEntity(server, apiId)
                    .orElseThrow(() -> { throw new ApiException(ErrorCode.CHARACTER_NOT_FOUND); })
        ).collect(Collectors.toList());

        ExchangeEntity entity = createEntity(characterEntityList);
        entity.setAdventureName(exchangeRequest.getAdventureName());

        em.persist(entity);
        em.flush();

        return modelMapper.map(entity, ExchangeDto.class);
    }

    private ExchangeEntity createEntity(List<CharacterEntity> characterEntityList) {
        return ExchangeEntity.builder()
                .character1(characterEntityList.get(0))
                .character2(characterEntityList.get(1))
                .character3(characterEntityList.get(2))
                .character4(characterEntityList.get(3))
                .build();
    }

    // 메소드명에 Exchange가 붙어 있을 경우 public, Dto 리턴
    // 없으면 private, entity 리턴
    public Optional<ExchangeDto> findExchange(Integer id) {
        return Optional.ofNullable(findById(id))
                .map(entity -> modelMapper.map(entity, ExchangeDto.class));
    }

    public Optional<ExchangeDto> findExchangeByKey(Integer exchangeKey) {
        return Optional.ofNullable(findByExchangeKey(exchangeKey))
                .map(entity -> modelMapper.map(entity, ExchangeDto.class));
    }

    public List<ExchangeDto> findExchangeList(List<Integer> id) {
        List<ExchangeEntity> exchangeList = queryFactory
                .selectFrom(exchangeEntity)
                .where(exchangeEntity.id.in(id)
                        .and(exchangeEntity.del_date.isNull()))
                .fetch();

        return exchangeList
                .stream().map(exchange -> modelMapper.map(exchange, ExchangeDto.class)).toList();
    }

    public List<ExchangeDto> findExchangeByAdventure(String adventureName) {
        List<ExchangeEntity> exchangeList = queryFactory
                .selectFrom(exchangeEntity)
                .where(exchangeEntity.adventureName.in(adventureName)
                        .and(exchangeEntity.del_date.isNull()))
                .fetch();

        return exchangeList
                .stream().map(exchange -> modelMapper.map(exchange, ExchangeDto.class)).toList();
    }

    private ExchangeEntity findByExchangeKey(Integer exchangeKey) {
        return queryFactory
                .selectFrom(exchangeEntity)
                .where(exchangeEntity.exchangeKey.eq(exchangeKey)
                        .and(exchangeEntity.del_date.isNull()))
                .fetchOne();
    }

    private ExchangeEntity findById(Integer id) {
        return queryFactory
                .selectFrom(exchangeEntity)
                .where(exchangeEntity.id.eq(id)
                        .and(exchangeEntity.del_date.isNull()))
                .fetchOne();
    }

    @Transactional
    public Integer setExchangeKey(Integer id, Integer exchangeKey) {
        ExchangeEntity exchange = findById(id);

        if (exchange == null)
            return null;

        exchange.setExchangeKey(exchangeKey);
        return exchangeKey;
    }

    @Transactional
    public boolean deleteExchange(Integer id) {
        ExchangeEntity exchange = findById(id);
        if (exchange == null)
            return false;

        exchange.setDel_date(LocalDateTime.now());
        em.flush();

        return true;
    }

    public boolean isKeyUnique(int key) {
        return key == 0 || queryFactory
                .selectFrom(exchangeEntity)
                .where(exchangeEntity.exchangeKey.eq(key)
                        .and(exchangeEntity.del_date.isNull()))
                .fetch() == null;
    }
}
