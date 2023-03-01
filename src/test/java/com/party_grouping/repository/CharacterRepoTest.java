package com.party_grouping.repository;

import com.party_grouping.config.TestConfig;
import com.party_grouping.entity.CharacterEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class CharacterRepoTest {
    @Autowired
    public CharacterRepo characterRepo;

    @Test
    void save() {
        CharacterEntity character = new CharacterEntity("test123", 41254);
        int characterId = characterRepo.save(character);
        System.out.println(characterId);
    }

    @Test
    void findByIdOpt() {
    }
}