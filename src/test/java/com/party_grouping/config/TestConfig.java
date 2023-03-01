package com.party_grouping.config;

import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.repository.CharacterRepoTest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }

    @Bean
    public CharacterRepo characterResp() {
        return new CharacterRepo(jpaQueryFactory());
    }

}
