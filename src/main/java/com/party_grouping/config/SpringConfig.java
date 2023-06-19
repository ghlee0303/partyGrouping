package com.party_grouping.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.party_grouping.api.ApiRequest;
import com.party_grouping.entity.*;
import com.party_grouping.repository.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {
    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }

    @Bean
    public CharacterRepo characterRepo() {
        return new CharacterRepo(jpaQueryFactory(), characterItemRepo());
    }

    @Bean
    public DungeonRepo dungeonRepo() {
        return new DungeonRepo(jpaQueryFactory(), qDungeonEntity());
    }

    @Bean
    public ExchangeRepo exchangeRepo() {
        return new ExchangeRepo(jpaQueryFactory(), characterRepo());
    }

    @Bean
    public CharacterAndDungeonRepo characterAndDungeonRepo() {
        return new CharacterAndDungeonRepo(jpaQueryFactory(), characterRepo(), dungeonRepo(), qCharacterAndDungeonEntity());
    }

    @Bean
    public PartyRepo partyRepo() {
        return new PartyRepo(jpaQueryFactory(), dungeonRepo(), groupRepo());
    }

    @Bean
    public PartyAndCharacterRepo partyAndCharacterRepo() {
        return new PartyAndCharacterRepo(
                jpaQueryFactory(),
                partyRepo(),
                characterRepo(),
                qPartyAndCharacterEntity(),
                qCharacterAndDungeonEntity(),
                qPartyEntity());
    }

    @Bean
    public GroupRepo groupRepo() {
        return new GroupRepo(jpaQueryFactory(), qGroupEntity());
    }

    @Bean
    public GroupAndCharacterRepo groupAndCharacterRepo() {
        return new GroupAndCharacterRepo(jpaQueryFactory(), groupRepo(), characterRepo(), qGroupAndCharacterEntity());
    }
    @Bean
    public CharacterItemRepo characterItemRepo() {
        return new CharacterItemRepo(jpaQueryFactory(), qCharacterItemEntity());
    }

    @Bean
    public QCharacterEntity qCharacterEntity() {
        return new QCharacterEntity("characterEntity");
    }

    @Bean
    public QDungeonEntity qDungeonEntity() {
        return new QDungeonEntity("dungeonEntity");
    }

    @Bean
    public QExchangeEntity qExchangeEntity() {
        return new QExchangeEntity("exchangeEntity");
    }

    @Bean
    public QGroupEntity qGroupEntity() {
        return new QGroupEntity("groupEntity");
    }

    @Bean
    public QPartyEntity qPartyEntity() {
        return new QPartyEntity("partyEntity");
    }

    @Bean
    public QCharacterAndDungeonEntity qCharacterAndDungeonEntity(){
        return new QCharacterAndDungeonEntity("characterAndDungeonEntity");
    }

    @Bean
    public QPartyAndCharacterEntity qPartyAndCharacterEntity() {
        return new QPartyAndCharacterEntity("partyAndCharacterEntity");
    }

    @Bean
    public QGroupAndCharacterEntity qGroupAndCharacterEntity() {
        return new QGroupAndCharacterEntity("groupAndCharacterEntity");
    }

    @Bean
    public QCharacterItemEntity qCharacterItemEntity() {
        return new QCharacterItemEntity("characterItemEntity");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public ApiRequest apiRequest() {
        return new ApiRequest();
    }

}
