package com.party_grouping.service;

import com.party_grouping.dto.CharacterAndDungeonDto;
import com.party_grouping.dto.CharacterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CharacterAndDungeonServiceTest {
    @Autowired
    private CharacterAndDungeonService characterAndDungeonService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private DungeonService dungeonService;



}
