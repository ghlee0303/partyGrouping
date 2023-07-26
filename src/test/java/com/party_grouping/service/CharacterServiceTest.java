package com.party_grouping.service;

import com.party_grouping.api.CharacterYaml;
import com.party_grouping.data.CharacterNode;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.request.CharacterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class CharacterServiceTest {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterYaml characterYaml;

    @Test
    void Test1() {
        LinkedList<CharacterNode> linkedList = new LinkedList<>();
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

    @Test
    void test2() {
        // Given
        List<CharacterRequest> requestList = new ArrayList<>();
        requestList.add(new CharacterRequest("0431754ab446f71e35116ac0d251fee7", "bakal"));
        requestList.add(new CharacterRequest("68642c1010daed6d46b78a80b7d0fe2d", "cain"));
        requestList.add(new CharacterRequest("a3a1fc24c7c62ae8d0f15e6cdf647642", "cain"));

        // When
        characterService.characterStatusList(true, requestList);
        List<CharacterDto> dtoList = characterService.characterStatusList(false, requestList);

        // Then
        System.out.println(dtoList);

    }

    @Test
    void test3() {
        characterService.characterStatus("cain", "00f4e7d83b6d8361a1b55c5cab0069d1");
        Optional<CharacterDto> dto = characterService.findCharacter("cain", "00f4e7d83b6d8361a1b55c5cab0069d1");
        System.out.println(dto.get());

    }

}
