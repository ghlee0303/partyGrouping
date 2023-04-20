package com.party_grouping.repository;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.entity.ExchangeEntity;
import com.party_grouping.entity.QDungeonEntity;
import com.party_grouping.entity.QExchangeEntity;
import com.party_grouping.exception.ApiException;
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
    public String save(ExchangeDto exchangeDto) {
        System.out.println(exchangeDto);
        ExchangeEntity exchangeEntity = toEntity(exchangeDto);
        em.persist(exchangeEntity);
        em.flush();

        return exchangeEntity.getExchangeKey();
    }

    private ExchangeEntity toEntity(ExchangeDto exchangeDto) {
        CharacterEntity character1 = characterRepo.findByApiIdOptEntity(exchangeDto.getCharacter1().getApiId())
                .orElseThrow(() -> { throw new ApiException("해당 캐릭터를 찾을 수 없습니다.", 404); });
        CharacterEntity character2 = characterRepo.findByApiIdOptEntity(exchangeDto.getCharacter2().getApiId())
                .orElseThrow(() -> { throw new ApiException("해당 캐릭터를 찾을 수 없습니다.", 404); });
        CharacterEntity character3 = characterRepo.findByApiIdOptEntity(exchangeDto.getCharacter3().getApiId())
                .orElseThrow(() -> { throw new ApiException("해당 캐릭터를 찾을 수 없습니다.", 404); });
        CharacterEntity character4 = characterRepo.findByApiIdOptEntity(exchangeDto.getCharacter4().getApiId())
                .orElseThrow(() -> { throw new ApiException("해당 캐릭터를 찾을 수 없습니다.", 404); });

        return ExchangeEntity.builder()
                .character1(character1)
                .character2(character2)
                .character3(character3)
                .character4(character4)
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
