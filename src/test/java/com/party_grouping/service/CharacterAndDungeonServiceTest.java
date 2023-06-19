package com.party_grouping.service;

import com.party_grouping.dto.CharacterAndDungeonDto;
import com.party_grouping.dto.CharacterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CharacterAndDungeonServiceTest {
    @Autowired
    private CharacterAndDungeonService characterAndDungeonService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private DungeonService dungeonService;


    @Test
    void Test2() {
        CharacterAndDungeonDto characterAndDungeonDto = characterAndDungeonService.characterAndDungeonDtoOpt(1).get();
        System.out.println(characterAndDungeonDto.getId());
        System.out.println(characterAndDungeonDto.getCharacter().getName());
        System.out.println(characterAndDungeonDto.getCharacter().getLevel());

    }
}
