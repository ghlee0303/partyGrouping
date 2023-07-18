package com.party_grouping.service;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.request.ExchangeRequest;
import com.party_grouping.response.CharacterResponse;
import com.party_grouping.response.ExchangeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ExchangeServiceTest {
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private CharacterService characterService;

    @Test
    void test() {
        // Given
        ExchangeRequest request = testReq2();
        characterService.characterStatus(request.getServer(), request.getApiIdList().get(0));
        characterService.characterStatus(request.getServer(), request.getApiIdList().get(1));
        characterService.characterStatus(request.getServer(), request.getApiIdList().get(2));
        characterService.characterStatus(request.getServer(), request.getApiIdList().get(3));

        exchangeService.save(request);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        // When
        List<ExchangeResponse> responseList = exchangeService.findExchangeList(integerList).stream()
                .map(exchangeService::createResponse).toList();

        // Then

        responseList.forEach((response) -> {
            System.out.println(response.getCharacterList().get(0).getCharacter().getName());
            System.out.println(response.getCharacterList().get(0).getDungeon());
        });
    }

    ExchangeRequest testReq() {
        List<String> apiIdList = new ArrayList<>();
        apiIdList.add("00f4e7d83b6d8361a1b55c5cab0069d1");
        apiIdList.add("68642c1010daed6d46b78a80b7d0fe2d");
        apiIdList.add("a3a1fc24c7c62ae8d0f15e6cdf647642");
        apiIdList.add("87c7ed846938a184f71b338af88bdf4a");
        return ExchangeRequest.builder()
                .adventureName("레알돋는당")
                .server("cain")
                .apiIdList(apiIdList)
                .build();
    }

    ExchangeRequest testReq2() {
        List<String> apiIdList = new ArrayList<>();
        apiIdList.add("28a1d76988a244cca306044e2fee105d");      // 하얀별의안경
        apiIdList.add("1a80db82e8acafea783ed3e112d98a1b");      // 하얀별의빛
        apiIdList.add("27b64663066c45a380686cf282a7212f");      // 하얀별의갓경
        apiIdList.add("0431754ab446f71e35116ac0d251fee7");      // 버그메이커
        return ExchangeRequest.builder()
                .adventureName("하얀별동네")
                .server("bakal")
                .apiIdList(apiIdList)
                .build();
    }



}
