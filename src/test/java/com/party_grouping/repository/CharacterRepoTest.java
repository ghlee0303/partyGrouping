package com.party_grouping.repository;

import com.party_grouping.config.SpringConfig;
import com.party_grouping.config.TestConfig;
import com.party_grouping.dto.CharacterDto;
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
@Import(SpringConfig.class)
public class CharacterRepoTest {
    @Autowired
    public CharacterRepo characterRepo;

    @Test
    void save() {
        CharacterEntity character = new CharacterEntity("test12223", 41254);
        System.out.println(character.getId());
    }

}