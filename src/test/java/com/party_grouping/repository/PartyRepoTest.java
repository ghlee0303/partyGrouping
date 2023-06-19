package com.party_grouping.repository;

import com.party_grouping.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(SpringConfig.class)
public class PartyRepoTest {
    @Autowired
    private PartyRepo partyRepo;
    @Autowired
    private PartyAndCharacterRepo partyAndCharacterRepo;
    @Autowired
    private CharacterAndDungeonRepo characterAndDungeonRepo;

}
