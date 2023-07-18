package com.party_grouping.service.mock_test;

import com.party_grouping.api.Api;
import com.party_grouping.api.Buff;
import com.party_grouping.api.CharacterYaml;
import com.party_grouping.api.DungeonYaml;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.CharacterItemDto;
import com.party_grouping.service.api.DnfApiService;
import com.party_grouping.service.inter.ApiService;
import com.party_grouping.service.mock.ApiRequestMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DnfApiServiceMockTest {
    private final Api api = new ApiRequestMock();
    private final CharacterYaml characterYaml = new CharacterYaml();
    private final DungeonYaml dungeonYaml = new DungeonYaml();
    private final ApiService apiService = new DnfApiService(api, characterYaml, dungeonYaml);

    //given
    //when
    //then


    @Test
    @DisplayName("callCharacter 서버 테스트")
    void callCharacterTest1() {
        //given

        //when
        List<CharacterDto> characterDtoList = apiService.callSearch("레알", "all");

        //then
        Assertions.assertNotNull(characterDtoList);
        Assertions.assertEquals(10, characterDtoList.size());
        for (CharacterDto dto : characterDtoList) {
            Assertions.assertTrue(dto.getName().contains("레알돋는당"));
        }
    }

    @Test
    @DisplayName("callStatus 테스트")
    void callStatusTest1() {
        //given

        //when
        CharacterDto characterDto1 = apiService.callCharacterStatus("cain", "87c7ed846938a184f71b338af88bdf4a");
        CharacterDto characterDto2 = apiService.callCharacterStatus("cain", "87c7ed846938a184f71b338af88bdf4a");

        //then
    }

    @Test
    @DisplayName("callStatus 테스트")
    // 버퍼는 장비 정보를 받아오지 않음
    void callEquipmentTest1() {
        //given

        //when
        CharacterItemDto itemDto1 = apiService.callItem("cain", "87c7ed846938a184f71b338af88bdf4a");

        //then
        System.out.println(itemDto1.isTitle());
    }

    @Test
    @DisplayName("callBuff 테스트")
    void callBuffTest1() {
        //given

        //when
        Buff buff1 = apiService.callBuffSkill("cain", "00f4e7d83b6d8361a1b55c5cab0069d1");
        /*
        Buff buff2 = apiService.callBuffSkill("cain", "68642c1010daed6d46b78a80b7d0fe2d");
        Buff buff3 = apiService.callBuffSkill("cain", "a3a1fc24c7c62ae8d0f15e6cdf647642");
        Buff buff4 = apiService.callBuffSkill("cain", "87c7ed846938a184f71b338af88bdf4a");*/
        //then
        Assertions.assertEquals("미라클 비전", buff1.getBuffName());
        /*
        Assertions.assertEquals("고대의 도서관", buff2.getBuffName());
        Assertions.assertEquals("익사이팅", buff3.getBuffName());
        Assertions.assertEquals("러블리 템포", buff4.getBuffName());*/
    }

    @Test
    @DisplayName("callCreature 테스트")
    void callCreature() {
        //given

        //when
        Buff buff = apiService.callBuffSkill("cain", "00f4e7d83b6d8361a1b55c5cab0069d1");
        //then
        Assertions.assertEquals("미라클 비전", buff.getBuffName());
    }

    @Test
    @DisplayName("callAurora 테스트")
    void ddd() {
        //given

        //when
        String aurora1 = apiService.callAurora("cain", "00f4e7d83b6d8361a1b55c5cab0069d1");
        //then
    }
/*
    @Test
    void callRequestTest() {
        String search = "servers/%s/characters?characterName=%s&wordType=full&limit=8&apikey=%s";
        String status = "servers/%s/characters/%s/status?apikey=%s";
        String buff = "servers/%s/characters/%s/skill/buff/equip/equipment?apikey=%s";
        String equipment = "servers/%s/characters/%s/equip/equipment?apikey=%s";

        System.out.println(api.callRequest(search));
        System.out.println(api.callRequest(status));
        System.out.println(api.callRequest(buff));
        System.out.println(api.callRequest(equipment));
        System.out.println(api.callRequest("das"));
    }*/
}
