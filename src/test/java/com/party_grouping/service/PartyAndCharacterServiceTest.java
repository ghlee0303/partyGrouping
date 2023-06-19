package com.party_grouping.service;

import com.party_grouping.response.dto.PACResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PartyAndCharacterServiceTest {
    @Autowired
    private PartyAndCharacterService partyAndCharacterService;

    @Test
    void test1() {
        List<List<PACResponseDto>> pac = partyAndCharacterService.doublePACResponseDtoList(1);
        System.out.println(pac);
        pac.forEach(pa -> {
            System.out.println(pa);
        });
    }
}
