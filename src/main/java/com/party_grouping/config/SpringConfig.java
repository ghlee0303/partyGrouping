package com.party_grouping.config;

import com.party_grouping.entity.*;
import com.party_grouping.repository.*;
import com.party_grouping.service.*;
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
        return new CharacterRepo(jpaQueryFactory(), qCharacterEntity());
    }

    @Bean
    public CharacterService characterService() {
        return new CharacterService(characterRepo());
    }

    @Bean
    public DungeonRepo dungeonRepo() {
        return new DungeonRepo(jpaQueryFactory(), qDungeonEntity());
    }

    @Bean
    public DungeonService dungeonService() {
        return new DungeonService(dungeonRepo());
    }

    @Bean
    public CharacterAndDungeonRepo characterAndDungeonRepo() {
        return new CharacterAndDungeonRepo(jpaQueryFactory(), characterRepo(), dungeonRepo(), qCharacterAndDungeonEntity());
    }

    @Bean
    public CharacterAndDungeonService characterAndDungeonService() {
        return new CharacterAndDungeonService(characterAndDungeonRepo(), characterRepo(), dungeonRepo());
    }

    @Bean
    public PartyRepo partyRepo() {
        return new PartyRepo(jpaQueryFactory(), dungeonRepo(), groupRepo());
    }

    @Bean
    public PartyService partyService() {
        return new PartyService(partyRepo());
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
    public PartyAndCharacterService partyAndCharacterService() {
        return new PartyAndCharacterService(partyAndCharacterRepo(), partyRepo(), characterRepo());
    }

    @Bean
    public GroupRepo groupRepo() {
        return new GroupRepo(jpaQueryFactory(), qGroupEntity());
    }

    @Bean
    public GroupService groupService() {
        return new GroupService(groupRepo());
    }

    @Bean
    public GroupAndCharacterRepo groupAndCharacterRepo() {
        return new GroupAndCharacterRepo(jpaQueryFactory(), groupRepo(), characterRepo(), qGroupAndCharacterEntity());
    }

    @Bean
    public GroupAndCharacterService groupAndCharacterService() {
        return new GroupAndCharacterService(groupAndCharacterRepo(), groupRepo(), characterRepo());
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
