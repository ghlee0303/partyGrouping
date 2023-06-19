package com.party_grouping.service;

import com.party_grouping.dto.CharacterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class CharacterItemServiceTest {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterItemService characterItemService;

    @Test
    void test() {
        // Given
        CharacterDto characterDto = characterService.characterStatus("cain", "68642c1010daed6d46b78a80b7d0fe2d");

        // When

        // Then

        System.out.println(characterDto.getItem().isTitle());

    }


}
