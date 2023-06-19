package com.party_grouping.service;

import com.party_grouping.api.CharacterYaml;
import com.party_grouping.dto.CharacterDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@SpringBootTest
public class CharacterServiceTest {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterYaml characterYaml;

    @Test
    void Test1() {
        System.out.println(characterYaml.getBuff());
        System.out.println(characterYaml.getBuffer());
        System.out.println(characterYaml.getElemental());
        System.out.println(characterYaml.getTitle());
        System.out.println(characterYaml.getCreature());
        System.out.println(characterYaml.getAurora());
    }
    @Test
    void test() {
        // Given
        CharacterDto characterDto = characterService.characterStatus("prey", "7870bfed4e22a77e8e6d11413ec0e479");

        // When

        // Then

        System.out.println(characterDto.getItem().isTitle());
        System.out.println(characterDto.getItem().isAurora());
        System.out.println(characterDto.getItem().isCreature());
    }

}
