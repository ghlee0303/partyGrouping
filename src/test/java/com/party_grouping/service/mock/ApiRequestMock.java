package com.party_grouping.service.mock;

import com.party_grouping.api.Api;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;

import java.util.Arrays;

public class ApiRequestMock implements Api {
    @Override
    public String callRequest(String url) {
        return judge(url);
    }

    private String judge(String url) {
        if (url.contains("characterName")) {
            return search();
        }

        Character character = characterVerify(url);

        if (url.contains("status")) {
            return character.status();
        } else if (url.contains("buff")) {
            return character.buff();
        } else if (url.contains("equipment")) {
            return character.equipment();
        } else if (url.contains("creature")) {
            return character.creature();
        } else if (url.contains("avatar")) {
            return character.avatar();
        }

        return null;
    }

    private Character characterVerify(String url) {
        String characterId = findCharacterId(url);

        return switch (characterId) {
            case "00f4e7d83b6d8361a1b55c5cab0069d1" -> new Character1();
            case "68642c1010daed6d46b78a80b7d0fe2d" -> new Character2();
            case "a3a1fc24c7c62ae8d0f15e6cdf647642" -> new Character3();
            case "87c7ed846938a184f71b338af88bdf4a" -> new Character4();
            case "a22d41ea2257bcd1459c84b96bd303e3" -> new Character5();
            default -> throw new ApiException(ErrorCode.CHARACTER_NOT_FOUND);
        };
    }

    private String findCharacterId(String url) {
        String startDelimiter = "characters/";
        String endDelimiter = "/";

        int startIndex = url.indexOf(startDelimiter);
        int endIndex = url.indexOf(endDelimiter, startIndex + startDelimiter.length());

        if (startIndex == -1 || endIndex == -1) {
            throw new ApiException(ErrorCode.CHARACTER_NOT_FOUND);
        }

        return url.substring(startIndex + startDelimiter.length(), endIndex);
    }

    private String search() {
        return  """
                {
                	"rows": [{
                		"serverId": "casillas",
                		"characterId": "aaa15058849e77086ca9564fe8d00645",
                		"characterName": "z레알돋는당z",
                		"level": 41,
                		"jobId": "a7a059ebe9e6054c0644b40ef316d6e9",
                		"jobGrowId": "4a1459a4fa3c7f59b6da2e43382ed0b9",
                		"jobName": "격투가(여)",
                		"jobGrowName": "그래플러"
                	}, {
                		"serverId": "bakal",
                		"characterId": "3df01ea7e5e1f82b697c50278bb9e8ce",
                		"characterName": "z레알돋는당z",
                		"level": 31,
                		"jobId": "a5ccbaf5538981c6ef99b236c0a60b73",
                		"jobGrowId": "df3870efe8e8754011cd12fa03cd275f",
                		"jobName": "마법사(남)",
                		"jobGrowName": "엘레멘탈 바머"
                	}, {
                		"serverId": "bakal",
                		"characterId": "c2592620625ea0b1e956d1f2ffe0acde",
                		"characterName": "q레알돋는당p",
                		"level": 84,
                		"jobId": "944b9aab492c15a8474f96947ceeb9e4",
                		"jobGrowId": "8022a99e29b5e4845985fe1d79a233f3",
                		"jobName": "거너(여)",
                		"jobGrowName": "스톰트루퍼"
                	}, {
                		"serverId": "bakal",
                		"characterId": "ec12645a36f8d3fb6a8ee34ee3d54d86",
                		"characterName": "O레알돋는당0",
                		"level": 30,
                		"jobId": "944b9aab492c15a8474f96947ceeb9e4",
                		"jobGrowId": "df3870efe8e8754011cd12fa03cd275f",
                		"jobName": "거너(여)",
                		"jobGrowName": "레인저"
                	}, {
                		"serverId": "bakal",
                		"characterId": "3c5217bf99789a4aebfdf25c34b626f5",
                		"characterName": "l레알돋는당l",
                		"level": 44,
                		"jobId": "3909d0b188e9c95311399f776e331da5",
                		"jobGrowId": "4a1459a4fa3c7f59b6da2e43382ed0b9",
                		"jobName": "마법사(여)",
                		"jobGrowName": "마도학자"
                	}, {
                		"serverId": "cain",
                		"characterId": "00f4e7d83b6d8361a1b55c5cab0069d1",
                		"characterName": "l레알돋는당l",
                		"level": 110,
                		"jobId": "afdf3b989339de478e85b614d274d1ef",
                		"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                		"jobName": "거너(남)",
                		"jobGrowName": "眞 런처"
                	}, {
                		"serverId": "cain",
                		"characterId": "0bfc08b05b911516a5f1e1d700cee133",
                		"characterName": "q레알돋는당p",
                		"level": 100,
                		"jobId": "3909d0b188e9c95311399f776e331da5",
                		"jobGrowId": "6d459bc74ba73ee4fe5cdc4655400193",
                		"jobName": "마법사(여)",
                		"jobGrowName": "眞 배틀메이지"
                	}, {
                		"serverId": "cain",
                		"characterId": "609343d697c87b060967af1e581a0bad",
                		"characterName": ":레알돋는당:",
                		"level": 85,
                		"jobId": "944b9aab492c15a8474f96947ceeb9e4",
                		"jobGrowId": "64bbd751c0fcae0e74758b871a2296c1",
                		"jobName": "거너(여)",
                		"jobGrowName": "프레이야"
                	}, {
                		"serverId": "cain",
                		"characterId": "1cbb5daccd0a88c59df7b42913e21b95",
                		"characterName": "I레알돋는당I",
                		"level": 100,
                		"jobId": "3deb7be5f01953ac8b1ecaa1e25e0420",
                		"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                		"jobName": "마창사",
                		"jobGrowName": "眞 듀얼리스트"
                	}, {
                		"serverId": "cain",
                		"characterId": "625e03b7d01e0a8b1eb1f2143d08ae54",
                		"characterName": "B레알돋는당B",
                		"level": 1,
                		"jobId": "a5ccbaf5538981c6ef99b236c0a60b73",
                		"jobGrowId": "1ea40db11ff66e70bcb0add7fae44cdb",
                		"jobName": "마법사(남)",
                		"jobGrowName": "마법사(남)"
                	}]
                }""";
    }

    interface Character {
        String status();
        String buff();
        String equipment();
        String creature();
        String avatar();
    }

    // 남런처
    // l레알돋는당l
    private static class Character1 implements Character {
        public String characterId = "00f4e7d83b6d8361a1b55c5cab0069d1";
        @Override
        public String status() {
            return """
                {
                	"characterId": "00f4e7d83b6d8361a1b55c5cab0069d1",
                	"characterName": "l레알돋는당l",
                	"level": 110,
                	"jobId": "afdf3b989339de478e85b614d274d1ef",
                	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                	"jobName": "거너(남)",
                	"jobGrowName": "眞 런처",
                	"adventureName": "레알돋는당",
                	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                	"guildName": "개시바",
                	"buff": [],
                	"status": [{
                		"name": "HP",
                		"value": 111383
                	}, {
                		"name": "MP",
                		"value": 273753
                	}, {
                		"name": "물리 방어율",
                		"value": 44.9
                	}, {
                		"name": "마법 방어율",
                		"value": 44.7
                	}, {
                		"name": "힘",
                		"value": 4741
                	}, {
                		"name": "지능",
                		"value": 3161
                	}, {
                		"name": "체력",
                		"value": 3408
                	}, {
                		"name": "정신력",
                		"value": 3157
                	}, {
                		"name": "물리 공격",
                		"value": 3201
                	}, {
                		"name": "마법 공격",
                		"value": 1934
                	}, {
                		"name": "물리 크리티컬",
                		"value": 105.8
                	}, {
                		"name": "마법 크리티컬",
                		"value": 47
                	}, {
                		"name": "독립 공격",
                		"value": 2068
                	}, {
                		"name": "공격 속도",
                		"value": 36.2
                	}, {
                		"name": "캐스팅 속도",
                		"value": 51
                	}, {
                		"name": "이동 속도",
                		"value": 35
                	}, {
                		"name": "모험가 명성",
                		"value": 45719
                	}, {
                		"name": "적중률",
                		"value": 46
                	}, {
                		"name": "회피율",
                		"value": 21
                	}, {
                		"name": "HP 회복량",
                		"value": 8050
                	}, {
                		"name": "MP 회복량",
                		"value": 15576
                	}, {
                		"name": "경직도",
                		"value": 0
                	}, {
                		"name": "히트리커버리",
                		"value": 894
                	}, {
                		"name": "화속성 강화",
                		"value": 302
                	}, {
                		"name": "화속성 저항",
                		"value": 76
                	}, {
                		"name": "수속성 강화",
                		"value": 160
                	}, {
                		"name": "수속성 저항",
                		"value": 31
                	}, {
                		"name": "명속성 강화",
                		"value": 302
                	}, {
                		"name": "명속성 저항",
                		"value": 31
                	}, {
                		"name": "암속성 강화",
                		"value": 160
                	}, {
                		"name": "암속성 저항",
                		"value": 31
                	}, {
                		"name": "물리 방어",
                		"value": 81571
                	}, {
                		"name": "마법 방어",
                		"value": 80842
                	}, {
                		"name": "피해 증가",
                		"value": 175051
                	}, {
                		"name": "버프력",
                		"value": 0
                	}, {
                		"name": "피해 증가 %",
                		"value": 44
                	}, {
                		"name": "버프력 %",
                		"value": 0
                	}, {
                		"name": "스킬 공격력 증가",
                		"value": 1671.4
                	}, {
                		"name": "쿨타임 감소",
                		"value": 15
                	}, {
                		"name": "쿨타임 회복속도 증가",
                		"value": 0
                	}, {
                		"name": "쿨타임 감소 실적용",
                		"value": 15
                	}, {
                		"name": "데미지 증가",
                		"value": 0
                	}, {
                		"name": "크리티컬 데미지 증가",
                		"value": 0
                	}, {
                		"name": "추가 데미지 증가",
                		"value": 0
                	}, {
                		"name": "모든 공격력 증가",
                		"value": 0
                	}, {
                		"name": "물리 공격력 증가",
                		"value": 0
                	}, {
                		"name": "마법 공격력 증가",
                		"value": 0
                	}, {
                		"name": "독립 공격력 증가",
                		"value": 0
                	}, {
                		"name": "힘 증가",
                		"value": 0
                	}, {
                		"name": "지능 증가",
                		"value": 0
                	}, {
                		"name": "지속피해",
                		"value": 0
                	}, {
                		"name": "물리 피해 감소",
                		"value": 0
                	}, {
                		"name": "마법 피해 감소",
                		"value": 0
                	}, {
                		"name": "출혈 데미지 전환",
                		"value": 0
                	}, {
                		"name": "중독 데미지 전환",
                		"value": 0
                	}, {
                		"name": "화상 데미지 전환",
                		"value": 0
                	}, {
                		"name": "감전 데미지 전환",
                		"value": 0
                	}, {
                		"name": "출혈 내성",
                		"value": 0
                	}, {
                		"name": "중독 내성",
                		"value": 15
                	}, {
                		"name": "화상 내성",
                		"value": 0
                	}, {
                		"name": "감전 내성",
                		"value": 0
                	}, {
                		"name": "빙결 내성",
                		"value": 0
                	}, {
                		"name": "둔화 내성",
                		"value": 20
                	}, {
                		"name": "기절 내성",
                		"value": 0
                	}, {
                		"name": "저주 내성",
                		"value": 20
                	}, {
                		"name": "암흑 내성",
                		"value": 0
                	}, {
                		"name": "석화 내성",
                		"value": 0
                	}, {
                		"name": "수면 내성",
                		"value": 0
                	}, {
                		"name": "혼란 내성",
                		"value": 0
                	}, {
                		"name": "구속 내성",
                		"value": 20
                	}]
                }
                """;
        }

        @Override
        public String buff() {
            return """
                 {
                 	"characterId": "00f4e7d83b6d8361a1b55c5cab0069d1",
                 	"characterName": "l레알돋는당l",
                 	"level": 110,
                 	"jobId": "afdf3b989339de478e85b614d274d1ef",
                 	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                 	"jobName": "거너(남)",
                 	"jobGrowName": "眞 런처",
                 	"adventureName": "레알돋는당",
                 	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                 	"guildName": "개시바",
                 	"skill": {
                 		"buff": {
                 			"skillInfo": {
                 				"skillId": "1721e94897e9961d5c98130a13392f17",
                 				"name": "미라클 비전",
                 				"option": {
                 					"level": 20,
                 					"desc": "지속시간 : {value1}초\\n중화기 적중률 증가율 : {value2}%\\n기본공격/중화기 공격력 증가율 : {value3}%",
                 					"values": ["-", "20", "94.1"]
                 				}
                 			},
                 			"equipment": [{
                 				"slotId": "WEAPON",
                 				"slotName": "무기",
                 				"itemId": "bf8f5fdee55d7cb320303c20ac474c0b",
                 				"itemName": "짙은 심연의 편린 핸드캐넌 : 미라클 비전(남)",
                 				"itemTypeId": "4ffb6f14b86f5c818a925bf58022686e",
                 				"itemType": "무기",
                 				"itemTypeDetailId": "0c6fa88af519cf12aac5eab1e3376a49",
                 				"itemTypeDetail": "핸드캐넌",
                 				"itemAvailableLevel": 100,
                 				"itemRarity": "유니크",
                 				"setItemId": "766d78752d6350b7fa6e955d128aba2a",
                 				"setItemName": "심연의 편린 미라클 비전 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "TITLE",
                 				"slotName": "칭호",
                 				"itemId": "4648eca45e5e7a8c7cf38d476c6360a5",
                 				"itemName": "돌아온 선비의 수묵화[국화]",
                 				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                 				"itemType": "액세서리",
                 				"itemTypeDetailId": "691933a06ff114348a7db936fb0aec66",
                 				"itemTypeDetail": "칭호",
                 				"itemAvailableLevel": 1,
                 				"itemRarity": "레어",
                 				"setItemId": null,
                 				"setItemName": null,
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0,
                 				"enchant": {
                 					"reinforceSkill": [{
                 						"jobId": "afdf3b989339de478e85b614d274d1ef",
                 						"jobName": "거너(남)",
                 						"skills": [{
                 							"skillId": "1721e94897e9961d5c98130a13392f17",
                 							"name": "미라클 비전",
                 							"value": 1
                 						}]
                 					}]
                 				}
                 			}, {
                 				"slotId": "JACKET",
                 				"slotName": "상의",
                 				"itemId": "45ead7b4856e604efa40abbcd9c7fea6",
                 				"itemName": "심연의 편린 상의 : 미라클 비전(남)",
                 				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                 				"itemType": "방어구",
                 				"itemTypeDetailId": "d8d2efdab287895452f0c8a54e80826d",
                 				"itemTypeDetail": "중갑 상의",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "766d78752d6350b7fa6e955d128aba2a",
                 				"setItemName": "심연의 편린 미라클 비전 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "SHOULDER",
                 				"slotName": "머리어깨",
                 				"itemId": "d1dd9bf55de8a6549e94220c437ee5b3",
                 				"itemName": "심연의 편린 어깨 : 미라클 비전(남)",
                 				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                 				"itemType": "방어구",
                 				"itemTypeDetailId": "51234d181dc4abf7dc948ca2abaa3d30",
                 				"itemTypeDetail": "중갑 머리어깨",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "766d78752d6350b7fa6e955d128aba2a",
                 				"setItemName": "심연의 편린 미라클 비전 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "PANTS",
                 				"slotName": "하의",
                 				"itemId": "3bec25f9158b83845bc052e4fa30a4cc",
                 				"itemName": "심연의 편린 하의 : 미라클 비전(남)",
                 				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                 				"itemType": "방어구",
                 				"itemTypeDetailId": "af5678d99e6c5c5cc50e60e81c66a7ac",
                 				"itemTypeDetail": "중갑 하의",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "766d78752d6350b7fa6e955d128aba2a",
                 				"setItemName": "심연의 편린 미라클 비전 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "SHOES",
                 				"slotName": "신발",
                 				"itemId": "f0d2eac53ccca6b1624b6e53ab2d6b22",
                 				"itemName": "심연의 편린 신발 : 미라클 비전(남)",
                 				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                 				"itemType": "방어구",
                 				"itemTypeDetailId": "9f097ffd4f7fddb4ccbcbca60903112c",
                 				"itemTypeDetail": "중갑 신발",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "766d78752d6350b7fa6e955d128aba2a",
                 				"setItemName": "심연의 편린 미라클 비전 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "WAIST",
                 				"slotName": "허리",
                 				"itemId": "21e5405d4c6c45ce467585756c97857b",
                 				"itemName": "심연의 편린 벨트 : 미라클 비전(남)",
                 				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                 				"itemType": "방어구",
                 				"itemTypeDetailId": "851ab631130f0f129272aa17e5053b2b",
                 				"itemTypeDetail": "중갑 허리",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "766d78752d6350b7fa6e955d128aba2a",
                 				"setItemName": "심연의 편린 미라클 비전 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "AMULET",
                 				"slotName": "목걸이",
                 				"itemId": "7f71dbed6cc4d4bad3e596308733da9b",
                 				"itemName": "심연의 편린 목걸이 : 미라클 비전(남)",
                 				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                 				"itemType": "액세서리",
                 				"itemTypeDetailId": "390e3966118b0c466ce9f8eae45e1629",
                 				"itemTypeDetail": "목걸이",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "766d78752d6350b7fa6e955d128aba2a",
                 				"setItemName": "심연의 편린 미라클 비전 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "WRIST",
                 				"slotName": "팔찌",
                 				"itemId": "fc059047c76c7f76d045e3f58a57bda3",
                 				"itemName": "심연의 편린 팔찌 : 미라클 비전(남)",
                 				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                 				"itemType": "액세서리",
                 				"itemTypeDetailId": "80bddf423629c28c7b4459c328fdffaf",
                 				"itemTypeDetail": "팔찌",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "766d78752d6350b7fa6e955d128aba2a",
                 				"setItemName": "심연의 편린 미라클 비전 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "RING",
                 				"slotName": "반지",
                 				"itemId": "ca417ebd6f3c4d2fad66bc7814e15c2c",
                 				"itemName": "심연의 편린 반지 : 미라클 비전(남)",
                 				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                 				"itemType": "액세서리",
                 				"itemTypeDetailId": "b04c7fb9b29b27b91a0a9e5a1822bc8f",
                 				"itemTypeDetail": "반지",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "766d78752d6350b7fa6e955d128aba2a",
                 				"setItemName": "심연의 편린 미라클 비전 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "SUPPORT",
                 				"slotName": "보조장비",
                 				"itemId": "95b913cc8b6507f0dd3cbb947a2fe3d0",
                 				"itemName": "왜곡된 심연의 완장 : 미라클 비전(남)",
                 				"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                 				"itemType": "추가장비",
                 				"itemTypeDetailId": "2fef5d81b7f59f0c75241890a8d941c9",
                 				"itemTypeDetail": "보조장비",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "d2b3940ff90b9f3081f64571dc2c0782",
                 				"setItemName": "왜곡된 심연의 미라클 비전(남) 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "MAGIC_STON",
                 				"slotName": "마법석",
                 				"itemId": "289c6f5ff4367366bccaae44f8380f3c",
                 				"itemName": "왜곡된 심연의 마법석 : 미라클 비전(남)",
                 				"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                 				"itemType": "추가장비",
                 				"itemTypeDetailId": "fe5f3db78175f5a3196385c688d29681",
                 				"itemTypeDetail": "마법석",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "d2b3940ff90b9f3081f64571dc2c0782",
                 				"setItemName": "왜곡된 심연의 미라클 비전(남) 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}, {
                 				"slotId": "EARRING",
                 				"slotName": "귀걸이",
                 				"itemId": "f14cff069c7e703ee630c6b74880fd5e",
                 				"itemName": "왜곡된 심연의 귀걸이 : 미라클 비전(남)",
                 				"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                 				"itemType": "추가장비",
                 				"itemTypeDetailId": "601834074c49bb0e48cb65a75a8667bc",
                 				"itemTypeDetail": "귀걸이",
                 				"itemAvailableLevel": 95,
                 				"itemRarity": "레전더리",
                 				"setItemId": "d2b3940ff90b9f3081f64571dc2c0782",
                 				"setItemName": "왜곡된 심연의 미라클 비전(남) 세트",
                 				"reinforce": 0,
                 				"amplificationName": null,
                 				"refine": 0
                 			}]
                 		}
                 	}
                 }
                """;
        }

        @Override
        public String equipment() {
            return """
                {
                	"characterId": "00f4e7d83b6d8361a1b55c5cab0069d1",
                	"characterName": "l레알돋는당l",
                	"level": 110,
                	"jobId": "afdf3b989339de478e85b614d274d1ef",
                	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                	"jobName": "거너(남)",
                	"jobGrowName": "眞 런처",
                	"adventureName": "레알돋는당",
                	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                	"guildName": "개시바",
                	"equipment": [{
                		"slotId": "WEAPON",
                		"slotName": "무기",
                		"itemId": "0615c137045d4eafc47fcb65decfa2ea",
                		"itemName": "근원을 삼킨 핸드캐넌",
                		"itemTypeId": "4ffb6f14b86f5c818a925bf58022686e",
                		"itemType": "무기",
                		"itemTypeDetailId": "0c6fa88af519cf12aac5eab1e3376a49",
                		"itemTypeDetail": "핸드캐넌",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 12,
                		"itemGradeName": "최상급",
                		"enchant": {
                			"status": [{
                				"name": "화속성강화",
                				"value": 15
                			}, {
                				"name": "명속성강화",
                				"value": 15
                			}]
                		},
                		"amplificationName": null,
                		"refine": 0,
                		"bakalInfo": {
                			"options": [{
                				"buff": 1356,
                				"explain": "스킬 공격력 7% 증가\\n피격으로 HP가 1% 이상 감소 시 10초 동안 MP 5% 감소",
                				"explainDetail": "스킬 공격력 7% 증가\\n피격으로 HP가 1% 이상 감소 시 10초 동안 MP 5% 감소 (최대 3중첩)"
                			}, {
                				"buff": 1356,
                				"explain": "스킬 공격력 7% 증가\\n피격으로 HP가 1% 이상 감소 시 10초 동안 HP 5% 감소",
                				"explainDetail": "스킬 공격력 7% 증가\\n피격으로 HP가 1% 이상 감소 시 10초 동안 HP 5% 감소 (최대 3중첩)"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "a68f2eb348e65ac31ab11e041809f17a",
                			"itemName": "마지막 불의 숨결 : 핸드캐넌"
                		},
                		"growInfo": {
                			"total": {
                				"damage": 14812,
                				"buff": 5544,
                				"level": 313
                			},
                			"options": [{
                				"level": 77,
                				"expRate": 40.18,
                				"explain": "거너 모든 스킬 공격력 10% 증가",
                				"explainDetail": "거너 모든 스킬 공격력 10% 증가",
                				"damage": 4091,
                				"default": {
                					"damage": 1482,
                					"buff": 504
                				},
                				"buff": 1391
                			}, {
                				"level": 78,
                				"expRate": 53.11,
                				"explain": "거너 1~100Lv 모든 스킬 Lv +1",
                				"explainDetail": "거너 1~100Lv 모든 스킬 Lv +1 (각성기 제외)",
                				"damage": 4111,
                				"default": {
                					"damage": 1482,
                					"buff": 504
                				},
                				"buff": 1399
                			}, {
                				"level": 79,
                				"expRate": 5.21,
                				"explain": "물리 방어력 +7000\\n마법 방어력 +7000",
                				"explainDetail": "물리 방어력 +7000\\n마법 방어력 +7000",
                				"damage": 3305,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1377
                			}, {
                				"level": 79,
                				"expRate": 70.29,
                				"explain": "화속성 저항 10 증가",
                				"explainDetail": "화속성 저항 10 증가",
                				"damage": 3305,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1377
                			}]
                		}
                	}, {
                		"slotId": "TITLE",
                		"slotName": "칭호",
                		"itemId": "65fa51b5a7a0b8af6724d06042ac9f88",
                		"itemName": "진정한 각성을 이룬 자 플래티넘[70Lv]",
                		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                		"itemType": "액세서리",
                		"itemTypeDetailId": "691933a06ff114348a7db936fb0aec66",
                		"itemTypeDetail": "칭호",
                		"itemAvailableLevel": 1,
                		"itemRarity": "레어",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 0,
                		"enchant": {
                			"status": [{
                				"name": "모든 속성 강화",
                				"value": 4
                			}, {
                				"name": "힘",
                				"value": 25
                			}, {
                				"name": "지능",
                				"value": 25
                			}, {
                				"name": "체력",
                				"value": 25
                			}, {
                				"name": "정신력",
                				"value": 25
                			}]
                		},
                		"amplificationName": null,
                		"refine": 0
                	}, {
                		"slotId": "JACKET",
                		"slotName": "상의",
                		"itemId": "5558a7ce9f0eb06af4cf84a0e4c8ee0f",
                		"itemName": "엔트 정령의 상의",
                		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                		"itemType": "방어구",
                		"itemTypeDetailId": "d8d2efdab287895452f0c8a54e80826d",
                		"itemTypeDetail": "중갑 상의",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 8,
                		"itemGradeName": "상급",
                		"enchant": {
                			"status": [{
                				"name": "물리 공격력",
                				"value": 90
                			}, {
                				"name": "힘",
                				"value": 60
                			}]
                		},
                		"amplificationName": "차원의 힘",
                		"refine": 0,
                		"machineRevolutionInfo": {
                			"options": [{
                				"buff": 1291,
                				"explain": "상태 이상에 걸린 적 공격 시 30초 동안 피해 증가 +7034\\n\\n중독 내성 +15%",
                				"explainDetail": "상태 이상에 걸린 적 공격 시 30초 동안 피해 증가 +7034 (최대 1중첩)\\n\\n중독 내성 +15%"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "8cdcc0e18b4895540a95e8a0110c09e7",
                			"itemName": "포식 : 헤집어진 상처"
                		},
                		"growInfo": {
                			"transfer": true,
                			"total": {
                				"damage": 12109,
                				"buff": 5617,
                				"level": 318
                			},
                			"options": [{
                				"level": 79,
                				"expRate": 53.01,
                				"explain": "MP MAX +5%",
                				"explainDetail": "MP MAX +5%",
                				"damage": 3305,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1377
                			}, {
                				"level": 79,
                				"expRate": 4.19,
                				"explain": "가장 높은 속성 강화 50마다 피해 증가 +2375, 모든 속도 6% 증가",
                				"explainDetail": "가장 높은 속성 강화 50마다 피해 증가 +2375, 모든 속도 6% 증가 (최대 4중첩)",
                				"damage": 3305,
                				"default": {
                					"damage": 1186,
                					"buff": 499
                				},
                				"buff": 1391
                			}, {
                				"level": 80,
                				"expRate": 0,
                				"explain": "명속성 강화 15 증가",
                				"explainDetail": "명속성 강화 15 증가",
                				"damage": 3384,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1410
                			}, {
                				"level": 80,
                				"expRate": 0,
                				"explain": "HP 40% 초과 일 때 물리, 마법 방어력 +25000, 물리, 마법 크리티컬 -8%\\nHP 40% 이하일 때 스킬 공격력 8% 증가, 공격 시 HP 1100, MP 1750 회복",
                				"explainDetail": "HP 40% 초과 일 때 물리, 마법 방어력 +25000, 물리, 마법 크리티컬 -8%\\nHP 40% 이하일 때 스킬 공격력 8% 증가, 공격 시 HP 1100, MP 1750 회복 (쿨타임 1초)",
                				"damage": 2115,
                				"default": {
                					"damage": 741,
                					"buff": 504
                				},
                				"buff": 1439,
                				"transfer": true
                			}]
                		}
                	}, {
                		"slotId": "SHOULDER",
                		"slotName": "머리어깨",
                		"itemId": "54fdf1b78b090a31a6aada25177fb361",
                		"itemName": "매니퓰레이션",
                		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                		"itemType": "방어구",
                		"itemTypeDetailId": "51234d181dc4abf7dc948ca2abaa3d30",
                		"itemTypeDetail": "중갑 머리어깨",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 8,
                		"itemGradeName": "최상급",
                		"enchant": {
                			"explain": "스킬 공격력 +2%",
                			"status": [{
                				"name": "힘",
                				"value": 40
                			}, {
                				"name": "지능",
                				"value": 40
                			}, {
                				"name": "체력",
                				"value": 40
                			}, {
                				"name": "정신력",
                				"value": 40
                			}, {
                				"name": "물리 공격력",
                				"value": 10
                			}, {
                				"name": "마법 공격력",
                				"value": 10
                			}, {
                				"name": "독립 공격력",
                				"value": 10
                			}, {
                				"name": "물리 크리티컬 히트",
                				"value": "5%"
                			}, {
                				"name": "마법 크리티컬 히트",
                				"value": "5%"
                			}]
                		},
                		"amplificationName": "차원의 힘",
                		"refine": 0,
                		"machineRevolutionInfo": {
                			"options": [{
                				"buff": 1291,
                				"explain": "가장 높은 상태 이상 내성에 따라 아래 효과 적용\\n- 10% 이상 18% 미만 : 피해 증가 +4756\\n- 18% 이상 : 피해 증가 +5311\\n\\n공격 시 적을 20초 동안 감전, 중독 중 하나의 상태로 만듦\\n감전 상태의 적 공격 시 16초 동안 MP 8% 회복\\n중독 상태의 적 공격 시 8초 동안 MP 4% 회복\\n구속 내성 +10%\\n저주 내성 +10%\\n둔화 내성 +10%",
                				"explainDetail": "가장 높은 상태 이상 내성에 따라 아래 효과 적용\\n- 10% 이상 18% 미만 : 피해 증가 +4756\\n- 18% 이상 : 피해 증가 +5311\\n\\n공격 시 적을 20초 동안 감전, 중독 중 하나의 상태로 만듦 (쿨타임 5초)\\n감전 상태의 적 공격 시 16초 동안 MP 8% 회복 (쿨타임 15초)\\n중독 상태의 적 공격 시 8초 동안 MP 4% 회복 (쿨타임 7초)\\n\\n구속 내성 +10%\\n저주 내성 +10%\\n둔화 내성 +10%"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "e80c037288101d1fe9f4dc8788eb71d4",
                			"itemName": "포식 : 자비없는 습격"
                		},
                		"growInfo": {
                			"total": {
                				"damage": 12405,
                				"buff": 5470,
                				"level": 302
                			},
                			"options": [{
                				"level": 75,
                				"expRate": 31.39,
                				"explain": "스킬 시전 시 남은 HP의 30%를 소모하여 40초 동안 피해 증가 +6077, 스킬 공격력 10% 증가",
                				"explainDetail": "스킬 시전 시 남은 HP의 30%를 소모하여 40초 동안 피해 증가 +6077, 스킬 공격력 10% 증가 (쿨타임 30초, 해당 옵션으로 HP가 1% 미만으로 떨어지지 않음)",
                				"damage": 2946,
                				"default": {
                					"damage": 1078,
                					"buff": 504
                				},
                				"buff": 1378
                			}, {
                				"level": 76,
                				"expRate": 18.66,
                				"explain": "HP 90% 이하일 때 공격 시 HP 2% 회복",
                				"explainDetail": "HP 90% 이하일 때 공격 시 HP 2% 회복 (쿨타임 5초)",
                				"damage": 2961,
                				"default": {
                					"damage": 1078,
                					"buff": 504
                				},
                				"buff": 1385
                			}, {
                				"level": 75,
                				"expRate": 36.08,
                				"explain": "스킬 MP 소모량 7% 감소",
                				"explainDetail": "스킬 MP 소모량 7% 감소",
                				"damage": 3241,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1350
                			}, {
                				"level": 76,
                				"expRate": 69.31,
                				"explain": "물리 크리티컬 히트 +5%\\n마법 크리티컬 히트 +5%",
                				"explainDetail": "물리 크리티컬 히트 +5%\\n마법 크리티컬 히트 +5%",
                				"damage": 3257,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1357
                			}]
                		}
                	}, {
                		"slotId": "PANTS",
                		"slotName": "하의",
                		"itemId": "0106839ef87aba76428ec574c78b167c",
                		"itemName": "로보티카 컴뱃 팬츠",
                		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                		"itemType": "방어구",
                		"itemTypeDetailId": "af5678d99e6c5c5cc50e60e81c66a7ac",
                		"itemTypeDetail": "중갑 하의",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 10,
                		"itemGradeName": "최상급",
                		"enchant": {
                			"status": [{
                				"name": "물리 공격력",
                				"value": 90
                			}, {
                				"name": "힘",
                				"value": 60
                			}]
                		},
                		"amplificationName": "차원의 힘",
                		"refine": 0,
                		"machineRevolutionInfo": {
                			"options": [{
                				"damage": 4325,
                				"buff": 1063,
                				"explain": "35레벨 스킬 시전 시 35레벨 스킬의 쿨타임 회복속도 10% 증가\\n- 최대 중첩 상태일 때 35레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 방해 계열 스킬에 효과 적용\\n\\n70레벨 스킬 시전 시 70레벨 스킬의 쿨타임 회복속도 10% 증가\\n- 최대 중첩 상태일 때 70레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 재현 계열 스킬에 효과 적용",
                				"explainDetail": "35레벨 스킬 시전 시 35레벨 스킬의 쿨타임 회복속도 10% 증가 (최대 4중첩)\\n- 최대 중첩 상태일 때 35레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 방해 계열 스킬에 효과 적용\\n\\n70레벨 스킬 시전 시 70레벨 스킬의 쿨타임 회복속도 10% 증가 (최대 4중첩)\\n- 최대 중첩 상태일 때 70레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 재현 계열 스킬에 효과 적용"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "aa3be9210446cb57676a1105da1aad26",
                			"itemName": "교감 : 보호하는 온기"
                		},
                		"growInfo": {
                			"total": {
                				"damage": 11382,
                				"buff": 5560,
                				"level": 320
                			},
                			"options": [{
                				"level": 80,
                				"expRate": 0,
                				"explain": "장착한 무기, 방어구, 악세서리, 특수장비의 강화/증폭 수치 합이 6 증가할 때 마다 피해 증가 +388, 스킬 공격력 0.2% 증가",
                				"explainDetail": "장착한 무기, 방어구, 악세서리, 특수장비의 강화/증폭 수치 합이 6 증가할 때 마다 피해 증가 +388, 스킬 공격력 0.2% 증가 (최대 24중첩, 휘장, 보조무기 제외)",
                				"damage": 2307,
                				"default": {
                					"damage": 808,
                					"buff": 480
                				},
                				"buff": 1370
                			}, {
                				"level": 80,
                				"expRate": 0,
                				"explain": "장착한 이 장비의 강화/증폭 수치 1 마다 모든 속도 +2%",
                				"explainDetail": "장착한 이 장비의 강화/증폭 수치 1 마다 모든 속도 +2% (최대 12중첩)",
                				"damage": 2307,
                				"default": {
                					"damage": 808,
                					"buff": 480
                				},
                				"buff": 1370
                			}, {
                				"level": 80,
                				"expRate": 0,
                				"explain": "물리 방어력 +7000\\n마법 방어력 +7000",
                				"explainDetail": "물리 방어력 +7000\\n마법 방어력 +7000",
                				"damage": 3384,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1410
                			}, {
                				"level": 80,
                				"expRate": 0,
                				"explain": "모든 속성 강화 10 증가",
                				"explainDetail": "모든 속성 강화 10 증가",
                				"damage": 3384,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1410
                			}]
                		}
                	}, {
                		"slotId": "SHOES",
                		"slotName": "신발",
                		"itemId": "3f119d5fe09aece6b1af2170dc1f0ce3",
                		"itemName": "엑셀러레이터",
                		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                		"itemType": "방어구",
                		"itemTypeDetailId": "9f097ffd4f7fddb4ccbcbca60903112c",
                		"itemTypeDetail": "중갑 신발",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 8,
                		"itemGradeName": "최상급",
                		"enchant": {
                			"status": [{
                				"name": "이동속도",
                				"value": "10%"
                			}]
                		},
                		"amplificationName": "차원의 힘",
                		"refine": 0,
                		"machineRevolutionInfo": {
                			"options": [{
                				"buff": 1291,
                				"explain": "상태 이상에 걸린 적 공격시 20% 확률로 5초 동안 적을 둔화, 구속 중 랜덤한 상태이상으로 만듦\\n\\n상태이상에 걸린 적 공격 시 40초 동안 피해 증가 +1266\\n- 피해 증가 효과가 5중첩 상태일 때 이동 속도 +10%",
                				"explainDetail": "상태 이상에 걸린 적 공격시 20% 확률로 5초 동안 적을 둔화, 구속 중 랜덤한 상태이상으로 만듦(쿨타임 8초)\\n\\n상태이상에 걸린 적 공격 시 40초 동안 피해 증가 +1266 (최대 5중첩, 쿨타임 2초)\\n- 피해 증가 효과가 5중첩 상태일 때 이동 속도 +10%"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "2e3a43fd3044f0a56ce7f491029d265e",
                			"itemName": "포식 : 추적하는 사냥꾼"
                		},
                		"growInfo": {
                			"total": {
                				"damage": 13621,
                				"buff": 5332,
                				"level": 311
                			},
                			"options": [{
                				"level": 77,
                				"expRate": 68.36,
                				"explain": "MP MAX +4196\\n남은 MP 20000마다 피해 증가 +1023\\nMP MAX가 200000 이상일 때 스킬 공격력 5% 증가",
                				"explainDetail": "MP MAX +4196\\n남은 MP 20000마다 피해 증가 +1023 (최대 10중첩)\\nMP MAX가 200000 이상일 때 스킬 공격력 5% 증가",
                				"damage": 3719,
                				"default": {
                					"damage": 1347,
                					"buff": 470
                				},
                				"buff": 1299
                			}, {
                				"level": 77,
                				"expRate": 89.77,
                				"explain": "스킬 쿨타임 15% 감소",
                				"explainDetail": "스킬 쿨타임 15% 감소 (각성기 제외)",
                				"damage": 3719,
                				"default": {
                					"damage": 1347,
                					"buff": 470
                				},
                				"buff": 1299
                			}, {
                				"level": 79,
                				"expRate": 73.87,
                				"explain": "MP 1분당 348 회복",
                				"explainDetail": "MP 1분당 348 회복",
                				"damage": 3305,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1377
                			}, {
                				"level": 78,
                				"expRate": 41.5,
                				"explain": "공격 시 MP 3500 회복",
                				"explainDetail": "공격 시 MP 3500 회복 (쿨타임 1초)",
                				"damage": 2878,
                				"default": {
                					"damage": 1037,
                					"buff": 489
                				},
                				"buff": 1357
                			}]
                		}
                	}, {
                		"slotId": "WAIST",
                		"slotName": "허리",
                		"itemId": "ab1272ec29778815de9178bc73e00e1d",
                		"itemName": "파워 플랜트",
                		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                		"itemType": "방어구",
                		"itemTypeDetailId": "851ab631130f0f129272aa17e5053b2b",
                		"itemTypeDetail": "중갑 허리",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 8,
                		"itemGradeName": "최상급",
                		"enchant": {
                			"explain": "스킬 공격력 +2%",
                			"status": [{
                				"name": "힘",
                				"value": 30
                			}, {
                				"name": "지능",
                				"value": 30
                			}, {
                				"name": "체력",
                				"value": 30
                			}, {
                				"name": "정신력",
                				"value": 30
                			}, {
                				"name": "물리 공격력",
                				"value": 6
                			}, {
                				"name": "마법 공격력",
                				"value": 6
                			}, {
                				"name": "독립 공격력",
                				"value": 6
                			}, {
                				"name": "물리 크리티컬 히트",
                				"value": "3%"
                			}, {
                				"name": "마법 크리티컬 히트",
                				"value": "3%"
                			}]
                		},
                		"amplificationName": "차원의 힘",
                		"refine": 0,
                		"machineRevolutionInfo": {
                			"options": [{
                				"buff": 1291,
                				"explain": "공격 시 적을 20초 동안 출혈, 화상 중 하나의 상태로 만듦\\nMP가 10% 이상일 때 모든 속성 강화 +25\\n\\n구속 내성 +10%\\n저주 내성 +10%\\n둔화 내성 +10%",
                				"explainDetail": "공격 시 적을 20초 동안 출혈, 화상 중 하나의 상태로 만듦 (쿨타임 5초)\\nMP가 10% 이상일 때 모든 속성 강화 +25\\n\\n구속 내성 +10%\\n저주 내성 +10%\\n둔화 내성 +10%"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "696f3d3ba4e845082a08cc81aababb1b",
                			"itemName": "포식 : 조여오는 올가미"
                		},
                		"growInfo": {
                			"total": {
                				"damage": 17699,
                				"buff": 5425,
                				"level": 307
                			},
                			"options": [{
                				"level": 77,
                				"expRate": 9.26,
                				"explain": "HP 50% 미만일 때 쿨타임 회복 속도 15% 증가, 스킬 공격력 6% 증가",
                				"explainDetail": "HP 50% 미만일 때 쿨타임 회복 속도 15% 증가 (각성기 제외), 스킬 공격력 6% 증가",
                				"damage": 5579,
                				"default": {
                					"damage": 2021,
                					"buff": 490
                				},
                				"buff": 1352
                			}, {
                				"level": 78,
                				"expRate": 86.53,
                				"explain": "HP 50% 미만일 때 공격 시 HP, MP 2000 회복",
                				"explainDetail": "HP 50% 미만일 때 공격 시 HP, MP 2000 회복 (쿨타임 1초)",
                				"damage": 5606,
                				"default": {
                					"damage": 2021,
                					"buff": 490
                				},
                				"buff": 1359
                			}, {
                				"level": 76,
                				"expRate": 33.08,
                				"explain": "스킬 MP 소모량 7% 감소",
                				"explainDetail": "스킬 MP 소모량 7% 감소",
                				"damage": 3257,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1357
                			}, {
                				"level": 76,
                				"expRate": 28.63,
                				"explain": "공격 속도 +8%\\n캐스팅 속도 +12%",
                				"explainDetail": "공격 속도 +8%\\n캐스팅 속도 +12%",
                				"damage": 3257,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1357
                			}]
                		}
                	}, {
                		"slotId": "AMULET",
                		"slotName": "목걸이",
                		"itemId": "bbd070d70a5168fabecaef168427487d",
                		"itemName": "검은 별",
                		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                		"itemType": "액세서리",
                		"itemTypeDetailId": "390e3966118b0c466ce9f8eae45e1629",
                		"itemTypeDetail": "목걸이",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 10,
                		"itemGradeName": "상급",
                		"enchant": {
                			"status": [{
                				"name": "화속성강화",
                				"value": 30
                			}, {
                				"name": "명속성강화",
                				"value": 30
                			}]
                		},
                		"amplificationName": null,
                		"refine": 0,
                		"ispinsInfo": {
                			"options": [{
                				"buff": 1405,
                				"explain": "물리, 마법 방어력 총 합이 90000 이상일 때 피해 증가 +7815\\n\\n물리, 마법 방어력 +14000\\n\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 -2800",
                				"explainDetail": "물리, 마법 방어력 총 합이 90000 이상일 때 피해 증가 +7815\\n\\n물리, 마법 방어력 +14000\\n\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 -2800 (최대 5중첩)\\n- 2초 동안 피격되지 않을 시 물리, 마법 방어력 감소 중첩 수 1 감소"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "3b62a91abc21df1a2609af53cb15d432",
                			"itemName": "천계 연합군 : 모든 이를 위한 기도"
                		},
                		"growInfo": {
                			"total": {
                				"damage": 13921,
                				"buff": 5461,
                				"level": 314
                			},
                			"options": [{
                				"level": 77,
                				"expRate": 99.94,
                				"explain": "HP가 80% 이상일 때 피해 증가 +1227\\nHP가 50% 이상 80% 미만일 때 피해 증가 +7364\\nHP가 50% 미만일 때 피해 증가 +7364, 스킬 공격력 7% 증가",
                				"explainDetail": "HP가 80% 이상일 때 피해 증가 +1227\\nHP가 50% 이상 80% 미만일 때 피해 증가 +7364\\nHP가 50% 미만일 때 피해 증가 +7364, 스킬 공격력 7% 증가",
                				"damage": 2975,
                				"default": {
                					"damage": 1078,
                					"buff": 475
                				},
                				"buff": 1312
                			}, {
                				"level": 79,
                				"expRate": 27.4,
                				"explain": "HP가 50% 미만일 때 모든 속도 +20%",
                				"explainDetail": "HP가 50% 미만일 때 모든 속도 +20%",
                				"damage": 3004,
                				"default": {
                					"damage": 1078,
                					"buff": 475
                				},
                				"buff": 1325
                			}, {
                				"level": 78,
                				"expRate": 56.72,
                				"explain": "적중률 +10%",
                				"explainDetail": "적중률 +10%",
                				"damage": 3289,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1371
                			}, {
                				"level": 80,
                				"expRate": 0,
                				"explain": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                				"explainDetail": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                				"damage": 4653,
                				"default": {
                					"damage": 1630,
                					"buff": 509
                				},
                				"buff": 1453
                			}]
                		}
                	}, {
                		"slotId": "WRIST",
                		"slotName": "팔찌",
                		"itemId": "fd19e43acb9bf998f94df0db7729be06",
                		"itemName": "억제된 마력의 팔찌",
                		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                		"itemType": "액세서리",
                		"itemTypeDetailId": "80bddf423629c28c7b4459c328fdffaf",
                		"itemTypeDetail": "팔찌",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 10,
                		"itemGradeName": "최상급",
                		"enchant": {
                			"status": [{
                				"name": "화속성강화",
                				"value": 30
                			}, {
                				"name": "명속성강화",
                				"value": 30
                			}]
                		},
                		"amplificationName": null,
                		"refine": 0,
                		"ispinsInfo": {
                			"options": [{
                				"buff": 1405,
                				"explain": "HP MAX, MP MAX 총 합이 220000 이상일 때 피해 증가 +7815\\n\\nHP MAX +1200, MP MAX +1890\\n\\n피격으로 HP가 1% 이상 감소 시 HP MAX -240, MP MAX -378",
                				"explainDetail": "HP MAX, MP MAX 총 합이 220000 이상일 때 피해 증가 +7815\\n\\nHP MAX +1200, MP MAX +1890\\n\\n피격으로 HP가 1% 이상 감소 시 HP MAX -240, MP MAX -378 (최대 5중첩)\\n- 2초 동안 피격되지 않을 시 HP MAX, MP MAX 감소 중첩 수 1 감소"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "cb41d2e497f1a7ac1ae2711670da9861",
                			"itemName": "천계 연합군 : 꺾이지 않는 의지"
                		},
                		"growInfo": {
                			"total": {
                				"damage": 15488,
                				"buff": 5438,
                				"level": 309
                			},
                			"options": [{
                				"level": 77,
                				"expRate": 10.56,
                				"explain": "던전 입장 시 HP를 99% 소모하고 사용가능한 최대 HP의 99% 제한\\n피격 시 HP 대신 MP를 먼저 소모\\n장비 또는 HP 회복 스킬로 인한 HP 회복량의 25% 만큼 MP 회복",
                				"explainDetail": "던전 입장 시 HP를 99% 소모하고 사용가능한 최대 HP의 99% 제한 (제한된 수치를 초과하여 HP 회복 불가)\\n피격 시 HP 대신 MP를 먼저 소모\\n장비 또는 HP 회복 스킬로 인한 HP 회복량의 25% 만큼 MP 회복",
                				"damage": 4463,
                				"default": {
                					"damage": 1616,
                					"buff": 490
                				},
                				"buff": 1352
                			}, {
                				"level": 77,
                				"expRate": 41.91,
                				"explain": "MP MAX +4196\\n던전 입장 시 피해 증가 +7364, 스킬 공격력 15% 증가",
                				"explainDetail": "MP MAX +4196\\n던전 입장 시 피해 증가 +7364, 스킬 공격력 15% 증가",
                				"damage": 4463,
                				"default": {
                					"damage": 1616,
                					"buff": 490
                				},
                				"buff": 1352
                			}, {
                				"level": 79,
                				"expRate": 61.81,
                				"explain": "MP 1분당 348 회복",
                				"explainDetail": "MP 1분당 348 회복",
                				"damage": 3305,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1377
                			}, {
                				"level": 76,
                				"expRate": 67.24,
                				"explain": "물리 방어력 +7000\\n마법 방어력 +7000",
                				"explainDetail": "물리 방어력 +7000\\n마법 방어력 +7000",
                				"damage": 3257,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1357
                			}]
                		}
                	}, {
                		"slotId": "RING",
                		"slotName": "반지",
                		"itemId": "639dedaa8b9218023da86ff44f80662f",
                		"itemName": "골렘의 중추석 반지",
                		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                		"itemType": "액세서리",
                		"itemTypeDetailId": "b04c7fb9b29b27b91a0a9e5a1822bc8f",
                		"itemTypeDetail": "반지",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 10,
                		"itemGradeName": "최상급",
                		"enchant": {
                			"status": [{
                				"name": "화속성강화",
                				"value": 30
                			}, {
                				"name": "명속성강화",
                				"value": 30
                			}]
                		},
                		"amplificationName": null,
                		"refine": 0,
                		"ispinsInfo": {
                			"options": [{
                				"buff": 1518,
                				"explain": "피격으로 HP가 1% 이상 감소 시 MP 5000 감소\\n\\n현재 MP 4% 마다 피해 증가 +1876\\n\\n스킬로 인해 소모한 MP 5000 마다 10초동안 장비로 인한 MP 회복 효과 10% 증가",
                				"explainDetail": "피격으로 HP가 1% 이상 감소 시 MP 5000 감소 (쿨타임 0.5초)\\n\\n현재 MP 4% 마다 피해 증가 +1876 (최대 5중첩)\\n\\n스킬로 인해 소모한 MP 5000 마다 10초동안 장비로 인한 MP 회복 효과 10% 증가 (최대 1중첩)"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "1fd082cd996a9eb02645be09c5e89d41",
                			"itemName": "흑룡 : 끝없는 암흑의 굴레"
                		},
                		"growInfo": {
                			"total": {
                				"damage": 17238,
                				"buff": 5217,
                				"level": 287
                			},
                			"options": [{
                				"level": 71,
                				"expRate": 97.59,
                				"explain": "HP가 30% 미만일 때 2초마다 피해 증가 +1192\\n피해 증가 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 피해 증가 중첩 수 1 감소",
                				"explainDetail": "HP가 30% 미만일 때 2초마다 피해 증가 +1192 (최대 3중첩)\\n피해 증가 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 피해 증가 중첩 수 1 감소 (쿨타임 1초)",
                				"damage": 5417,
                				"default": {
                					"damage": 2021,
                					"buff": 475
                				},
                				"buff": 1274
                			}, {
                				"level": 71,
                				"expRate": 95.74,
                				"explain": "HP가 30% 미만일 때 2초마다 물리, 마법 방어력 +4000\\n물리, 마법 방어력 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 중첩 수 1 감소",
                				"explainDetail": "HP가 30% 미만일 때 2초마다 물리, 마법 방어력 +4000 (최대 3중첩)\\n물리, 마법 방어력 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 중첩 수 1 감소 (쿨타임 1초)",
                				"damage": 5417,
                				"default": {
                					"damage": 2021,
                					"buff": 475
                				},
                				"buff": 1274
                			}, {
                				"level": 72,
                				"expRate": 23.08,
                				"explain": "적중률 +10%",
                				"explainDetail": "적중률 +10%",
                				"damage": 3194,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1331
                			}, {
                				"level": 73,
                				"expRate": 64.19,
                				"explain": "회피율 +8%",
                				"explainDetail": "회피율 +8%",
                				"damage": 3210,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1338
                			}]
                		}
                	}, {
                		"slotId": "SUPPORT",
                		"slotName": "보조장비",
                		"itemId": "573399fba4997923936a56fe15d50d6f",
                		"itemName": "엔트 정령의 성배",
                		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                		"itemType": "추가장비",
                		"itemTypeDetailId": "2fef5d81b7f59f0c75241890a8d941c9",
                		"itemTypeDetail": "보조장비",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 7,
                		"itemGradeName": "최상급",
                		"enchant": {
                			"status": [{
                				"name": "물리 크리티컬 히트",
                				"value": "3%"
                			}, {
                				"name": "마법 크리티컬 히트",
                				"value": "3%"
                			}, {
                				"name": "모든 속성 강화",
                				"value": 12
                			}, {
                				"name": "피해 증가",
                				"value": 3
                			}]
                		},
                		"amplificationName": null,
                		"refine": 0,
                		"dimensionCloisterInfo": {
                			"options": [{
                				"damage": 2350,
                				"buff": 1178,
                				"explain": "스킬 공격력 3% 증가\\n\\n1~40Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가",
                				"explainDetail": "스킬 공격력 3% 증가\\n\\n1~40Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가 (각성기 제외)"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "56422d257499f7c60c7dad02d0f43db2",
                			"itemName": "심연의 결집 : 정의되지 않는 존재"
                		},
                		"growInfo": {
                			"transfer": true,
                			"total": {
                				"damage": 6768,
                				"buff": 5727,
                				"level": 320
                			},
                			"options": [{
                				"level": 80,
                				"expRate": 0,
                				"explain": "용족 타입의 적 공격 시 스킬 공격력 7% 증가",
                				"explainDetail": "용족 타입의 적 공격 시 스킬 공격력 7% 증가",
                				"damage": 846,
                				"default": {
                					"damage": 296,
                					"buff": 504
                				},
                				"buff": 1439
                			}, {
                				"level": 80,
                				"expRate": 0,
                				"explain": "출혈 상태 적 공격 시 스킬 공격력 5% 증가",
                				"explainDetail": "출혈 상태 적 공격 시 스킬 공격력 5% 증가",
                				"damage": 1269,
                				"default": {
                					"damage": 445,
                					"buff": 504
                				},
                				"buff": 1439,
                				"transfer": true
                			}, {
                				"level": 80,
                				"expRate": 0,
                				"explain": "명속성 강화 15 증가",
                				"explainDetail": "명속성 강화 15 증가",
                				"damage": 3384,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1410
                			}, {
                				"level": 80,
                				"expRate": 0,
                				"explain": "감전 상태 적 공격 시 스킬 공격력 5% 증가",
                				"explainDetail": "감전 상태 적 공격 시 스킬 공격력 5% 증가",
                				"damage": 1269,
                				"default": {
                					"damage": 445,
                					"buff": 504
                				},
                				"buff": 1439
                			}]
                		}
                	}, {
                		"slotId": "MAGIC_STON",
                		"slotName": "마법석",
                		"itemId": "dab2cee8ec27e4b0962afe5ca7078a26",
                		"itemName": "숨결을 삼킨 용옥",
                		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                		"itemType": "추가장비",
                		"itemTypeDetailId": "fe5f3db78175f5a3196385c688d29681",
                		"itemTypeDetail": "마법석",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 8,
                		"itemGradeName": "최상급",
                		"enchant": {
                			"status": [{
                				"name": "모든 속성 강화",
                				"value": 30
                			}]
                		},
                		"amplificationName": "차원의 힘",
                		"refine": 0,
                		"dimensionCloisterInfo": {
                			"options": [{
                				"damage": 2350,
                				"buff": 1178,
                				"explain": "스킬 공격력 3% 증가\\n\\n45Lv, 60~70Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가",
                				"explainDetail": "스킬 공격력 3% 증가\\n\\n45Lv, 60~70Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가 (각성기 제외)"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "2950e00eced48e19bf4c161b3bc7df7e",
                			"itemName": "심연의 결집 : 무한한 수축"
                		},
                		"growInfo": {
                			"total": {
                				"damage": 14034,
                				"buff": 5264,
                				"level": 311
                			},
                			"options": [{
                				"level": 77,
                				"expRate": 82.79,
                				"explain": "커맨드로 스킬 시전 시 15초 동안 1스택을 획득하며 스택 중첩 수에 따라 아래 효과 발동\\n- 1 스택 이상 3 스택 미만 : 스킬 공격력 2% 증가\\n- 3 스택 : 스킬 공격력 5% 증가\\n- 4 스택 : 스킬 공격력 9% 증가",
                				"explainDetail": "커맨드로 스킬 시전 시 15초 동안 1스택을 획득하며 스택 중첩 수에 따라 아래 효과 발동 (최대 4중첩, 쿨타임 2초)\\n- 1 스택 이상 3 스택 미만 : 스킬 공격력 2% 증가\\n- 3 스택 : 스킬 공격력 5% 증가\\n- 4 스택 : 스킬 공격력 9% 증가",
                				"damage": 3719,
                				"default": {
                					"damage": 1347,
                					"buff": 456
                				},
                				"buff": 1259
                			}, {
                				"level": 78,
                				"expRate": 21.85,
                				"explain": "커맨드로 스킬 시전 시 20초 동안 피격 시 받는 데미지 12% 감소, 피해 증가 +4933",
                				"explainDetail": "커맨드로 스킬 시전 시 20초 동안 피격 시 받는 데미지 12% 감소, 피해 증가 +4933 (최대 1중첩)",
                				"damage": 3737,
                				"default": {
                					"damage": 1347,
                					"buff": 456
                				},
                				"buff": 1265
                			}, {
                				"level": 79,
                				"expRate": 17.61,
                				"explain": "물리 크리티컬 히트 +5%\\n마법 크리티컬 히트 +5%",
                				"explainDetail": "물리 크리티컬 히트 +5%\\n마법 크리티컬 히트 +5%",
                				"damage": 3305,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1377
                			}, {
                				"level": 77,
                				"expRate": 42.97,
                				"explain": "HP 1분당 460.2 회복",
                				"explainDetail": "HP 1분당 460.2 회복",
                				"damage": 3273,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1363
                			}]
                		}
                	}, {
                		"slotId": "EARRING",
                		"slotName": "귀걸이",
                		"itemId": "c0ef77f4ab5c758765326bf5a8b8f23f",
                		"itemName": "폭풍을 삼킨 에너지",
                		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                		"itemType": "추가장비",
                		"itemTypeDetailId": "601834074c49bb0e48cb65a75a8667bc",
                		"itemTypeDetail": "귀걸이",
                		"itemAvailableLevel": 105,
                		"itemRarity": "에픽",
                		"setItemId": null,
                		"setItemName": null,
                		"reinforce": 10,
                		"itemGradeName": "최상급",
                		"enchant": {
                			"status": [{
                				"name": "모든 속성 강화",
                				"value": 11
                			}, {
                				"name": "힘",
                				"value": 50
                			}, {
                				"name": "지능",
                				"value": 50
                			}, {
                				"name": "체력",
                				"value": 50
                			}, {
                				"name": "정신력",
                				"value": 50
                			}]
                		},
                		"amplificationName": null,
                		"refine": 0,
                		"dimensionCloisterInfo": {
                			"options": [{
                				"damage": 2350,
                				"buff": 1178,
                				"explain": "스킬 공격력 3% 증가\\n\\n75~80Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가",
                				"explainDetail": "스킬 공격력 3% 증가\\n\\n75~80Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가 (각성기 제외)"
                			}]
                		},
                		"upgradeInfo": {
                			"itemId": "1ba6221619f88691ed8f0c3adba4db7a",
                			"itemName": "심연의 결집 : 무정형의 힘"
                		},
                		"growInfo": {
                			"total": {
                				"damage": 14199,
                				"buff": 5383,
                				"level": 317
                			},
                			"options": [{
                				"level": 79,
                				"expRate": 59.39,
                				"explain": "소모품으로 인한 MP 회복 효과 50% 증가\\nHP 50% 이하일 때 스킬 공격력 10% 증가, 모든 속성 강화 +15",
                				"explainDetail": "소모품으로 인한 MP 회복 효과 50% 증가\\nHP 50% 이하일 때 스킬 공격력 10% 증가, 모든 속성 강화 +15",
                				"damage": 3755,
                				"default": {
                					"damage": 1347,
                					"buff": 466
                				},
                				"buff": 1298
                			}, {
                				"level": 79,
                				"expRate": 35.3,
                				"explain": "스킬 MP 소모량 20% 감소\\nMP MAX +4196",
                				"explainDetail": "스킬 MP 소모량 20% 감소\\nMP MAX +4196",
                				"damage": 3755,
                				"default": {
                					"damage": 1347,
                					"buff": 466
                				},
                				"buff": 1298
                			}, {
                				"level": 80,
                				"expRate": 0,
                				"explain": "적중률 +10%",
                				"explainDetail": "적중률 +10%",
                				"damage": 3384,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1410
                			}, {
                				"level": 79,
                				"expRate": 62.8,
                				"explain": "회피율 +8%",
                				"explainDetail": "회피율 +8%",
                				"damage": 3305,
                				"default": {
                					"damage": 1186,
                					"buff": 494
                				},
                				"buff": 1377
                			}]
                		}
                	}],
                	"setItemInfo": []
                }
                """;
        }

        @Override
        public String creature() {
            return """
                    {
                    	"characterId": "00f4e7d83b6d8361a1b55c5cab0069d1",
                    	"characterName": "l레알돋는당l",
                    	"level": 110,
                    	"jobId": "afdf3b989339de478e85b614d274d1ef",
                    	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                    	"jobName": "거너(남)",
                    	"jobGrowName": "眞 런처",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"creature": {
                    		"itemId": "f5c87c63bbdaa5413a105640cafbc30a",
                    		"itemName": "순백의 나비 공주",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"artifact": [{
                    			"slotColor": "RED",
                    			"itemName": "와이번의 발톱",
                    			"itemAvailableLevel": 0,
                    			"itemRarity": "언커먼"
                    		}, {
                    			"slotColor": "BLUE",
                    			"itemName": "바게스트의 이빨",
                    			"itemAvailableLevel": 0,
                    			"itemRarity": "언커먼"
                    		}, {
                    			"slotColor": "GREEN",
                    			"itemName": "바람의 오브",
                    			"itemAvailableLevel": 0,
                    			"itemRarity": "레어"
                    		}]
                    	}
                    }
                    """;
        }

        @Override
        public String avatar() {
            return """
                    {
                    	"characterId": "00f4e7d83b6d8361a1b55c5cab0069d1",
                    	"characterName": "l레알돋는당l",
                    	"level": 110,
                    	"jobId": "afdf3b989339de478e85b614d274d1ef",
                    	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                    	"jobName": "거너(남)",
                    	"jobGrowName": "眞 런처",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"avatar": [{
                    		"slotId": "HEADGEAR",
                    		"slotName": "모자 아바타",
                    		"itemId": "80c6a6b7b5c1ab32bb401dfadf76ace8",
                    		"itemName": "레어 모자 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "00efcc95cfc4a259dd163c1ef09f155c",
                    			"itemName": "단진의 금빛 후라이머리"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "캐스팅 속도 14.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "붉은빛",
                    			"itemName": "화려한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "붉은빛",
                    			"itemName": "화려한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "HAIR",
                    		"slotName": "머리 아바타",
                    		"itemId": "b0300fbad0d097936e50b26fe4928e62",
                    		"itemName": "레어 머리 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "캐스팅 속도 14.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "붉은빛",
                    			"itemName": "찬란한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "유니크"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "붉은빛",
                    			"itemName": "찬란한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "유니크"
                    		}]
                    	}, {
                    		"slotId": "FACE",
                    		"slotName": "얼굴 아바타",
                    		"itemId": "57014a03513e7016ad42f555e962c70e",
                    		"itemName": "고결한 프로즌임페리얼 상흔",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "공격 속도 6.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "노란빛",
                    			"itemName": "찬란한 옐로우 엠블렘[힘]",
                    			"itemRarity": "유니크"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "노란빛",
                    			"itemName": "찬란한 옐로우 엠블렘[힘]",
                    			"itemRarity": "유니크"
                    		}]
                    	}, {
                    		"slotId": "JACKET",
                    		"slotName": "상의 아바타",
                    		"itemId": "85e7b8996505181acf24d5c608a78fea",
                    		"itemName": "레어 상의 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "a35927332eca51609f60faeac9f505a5",
                    			"itemName": "투버튼 제복 상의 [C타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "중화기 마스터리 스킬Lv +1",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "플래티넘",
                    			"itemName": "플래티넘 엠블렘[중화기 마스터리]",
                    			"itemRarity": "레전더리"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 듀얼 엠블렘[힘 + 물리크리티컬]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 3,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 듀얼 엠블렘[힘 + 물리크리티컬]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "PANTS",
                    		"slotName": "하의 아바타",
                    		"itemId": "4642fc7b2ee2531000ef1b3b12dcc9ff",
                    		"itemName": "레어 하의 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "24205a59311e2a080b7a17153e90980a",
                    			"itemName": "제복 하의[C타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "HP MAX 400 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "플래티넘",
                    			"itemName": "플래티넘 엠블렘[중화기 마스터리]",
                    			"itemRarity": "레전더리"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 듀얼 엠블렘[힘 + 물리크리티컬]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 3,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 녹색빛 엠블렘[물리크리티컬]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "SHOES",
                    		"slotName": "신발 아바타",
                    		"itemId": "4968cbfb1e0a6bd9839b4df313209d2c",
                    		"itemName": "레어 신발 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "8fbcc6ad1844452b4284d39d59831ce6",
                    			"itemName": "통굽 단화[B타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "힘 55 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "푸른빛",
                    			"itemName": "찬란한 다색 엠블렘[모든속성저항]",
                    			"itemRarity": "유니크"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "푸른빛",
                    			"itemName": "찬란한 다색 엠블렘[모든속성저항]",
                    			"itemRarity": "유니크"
                    		}]
                    	}, {
                    		"slotId": "BREAST",
                    		"slotName": "목가슴 아바타",
                    		"itemId": "f16143ae8c170759ae6d54ac5ce3c2db",
                    		"itemName": "레어 목가슴 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "e79414826dbd62e50712ecc22c4e917a",
                    			"itemName": "스타일리쉬 망토[B타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "공격 속도 6.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "노란빛",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "노란빛",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "WAIST",
                    		"slotName": "허리 아바타",
                    		"itemId": "70f7d394bbdb24d2be71aa978cf3d416",
                    		"itemName": "레어 허리 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "화속성 저항 35 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "푸른빛",
                    			"itemName": "찬란한 다색 엠블렘[모든속성저항]",
                    			"itemRarity": "유니크"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "푸른빛",
                    			"itemName": "찬란한 다색 엠블렘[모든속성저항]",
                    			"itemRarity": "유니크"
                    		}]
                    	}, {
                    		"slotId": "SKIN",
                    		"slotName": "스킨 아바타",
                    		"itemId": "8107640f137b1134c9153fd5af91b898",
                    		"itemName": "금룡의 살굿빛 피부[C타입]",
                    		"itemRarity": "커먼",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "물리 방어력 1000 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "다색",
                    			"itemName": "화려한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "다색",
                    			"itemName": "화려한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "AURORA",
                    		"slotName": "오라 아바타",
                    		"itemId": "63547e0206380c391c4503431f9012e4",
                    		"itemName": "RANTRIX",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": null,
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "다색",
                    			"itemName": "화려한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "다색",
                    			"itemName": "화려한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "WEAPON",
                    		"slotName": "무기 아바타",
                    		"itemId": "3b58c1f640abd63173661e2ed853ab54",
                    		"itemName": "무기 클론 아바타",
                    		"itemRarity": "커먼",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": null,
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "다색",
                    			"itemName": "화려한 듀얼 엠블렘[힘 + 물리크리티컬]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "다색",
                    			"itemName": "찬란한 듀얼 엠블렘[힘 + 물리크리티컬]",
                    			"itemRarity": "유니크"
                    		}]
                    	}]
                    }
                    """;
        }
    }

    // 마도학자
    // 이키,더_비키
    private static class Character2 implements Character {
        public String characterId = "68642c1010daed6d46b78a80b7d0fe2d";
        @Override
        public String status() {
            return """
                {
                	"characterId": "68642c1010daed6d46b78a80b7d0fe2d",
                	"characterName": "이키,더_비키",
                	"level": 110,
                	"jobId": "3909d0b188e9c95311399f776e331da5",
                	"jobGrowId": "c9b492038ee3ca8d27d7004cf58d59f3",
                	"jobName": "마법사(여)",
                	"jobGrowName": "眞 마도학자",
                	"adventureName": "레알돋는당",
                	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                	"guildName": "개시바",
                	"buff": [{
                		"name": "모험단 버프",
                		"level": 38,
                		"status": [{
                			"name": "힘",
                			"value": 280
                		}, {
                			"name": "지능",
                			"value": 280
                		}, {
                			"name": "체력",
                			"value": 280
                		}, {
                			"name": "정신력",
                			"value": 280
                		}]
                	}, {
                		"name": "무제한 길드능력치",
                		"status": [{
                			"name": "힘",
                			"value": 60
                		}, {
                			"name": "지능",
                			"value": 60
                		}, {
                			"name": "체력",
                			"value": 60
                		}, {
                			"name": "정신력",
                			"value": 60
                		}]
                	}, {
                		"name": "기간제 길드능력치",
                		"status": [{
                			"name": "힘",
                			"value": 40
                		}, {
                			"name": "지능",
                			"value": 40
                		}, {
                			"name": "체력",
                			"value": 40
                		}, {
                			"name": "정신력",
                			"value": 40
                		}]
                	}],
                	"status": [{
                		"name": "HP",
                		"value": 129182
                	}, {
                		"name": "MP",
                		"value": 310833
                	}, {
                		"name": "물리 방어율",
                		"value": 33.3
                	}, {
                		"name": "마법 방어율",
                		"value": 37.7
                	}, {
                		"name": "힘",
                		"value": 3465
                	}, {
                		"name": "지능",
                		"value": 4477
                	}, {
                		"name": "체력",
                		"value": 3383
                	}, {
                		"name": "정신력",
                		"value": 3598
                	}, {
                		"name": "물리 공격",
                		"value": 1961
                	}, {
                		"name": "마법 공격",
                		"value": 2094
                	}, {
                		"name": "물리 크리티컬",
                		"value": 50.5
                	}, {
                		"name": "마법 크리티컬",
                		"value": 104.8
                	}, {
                		"name": "독립 공격",
                		"value": 2797
                	}, {
                		"name": "공격 속도",
                		"value": 48
                	}, {
                		"name": "캐스팅 속도",
                		"value": 88.9
                	}, {
                		"name": "이동 속도",
                		"value": 54.4
                	}, {
                		"name": "모험가 명성",
                		"value": 44686
                	}, {
                		"name": "적중률",
                		"value": 50.5
                	}, {
                		"name": "회피율",
                		"value": 29
                	}, {
                		"name": "HP 회복량",
                		"value": 1307
                	}, {
                		"name": "MP 회복량",
                		"value": 25626
                	}, {
                		"name": "경직도",
                		"value": 0
                	}, {
                		"name": "히트리커버리",
                		"value": 794
                	}, {
                		"name": "화속성 강화",
                		"value": 287
                	}, {
                		"name": "화속성 저항",
                		"value": 15
                	}, {
                		"name": "수속성 강화",
                		"value": 258
                	}, {
                		"name": "수속성 저항",
                		"value": 50
                	}, {
                		"name": "명속성 강화",
                		"value": 258
                	}, {
                		"name": "명속성 저항",
                		"value": 15
                	}, {
                		"name": "암속성 강화",
                		"value": 258
                	}, {
                		"name": "암속성 저항",
                		"value": 15
                	}, {
                		"name": "물리 방어",
                		"value": 49827
                	}, {
                		"name": "마법 방어",
                		"value": 60611
                	}, {
                		"name": "피해 증가",
                		"value": 189072
                	}, {
                		"name": "버프력",
                		"value": 0
                	}, {
                		"name": "피해 증가 %",
                		"value": 44
                	}, {
                		"name": "버프력 %",
                		"value": 0
                	}, {
                		"name": "스킬 공격력 증가",
                		"value": 1620.1
                	}, {
                		"name": "쿨타임 감소",
                		"value": 15
                	}, {
                		"name": "쿨타임 회복속도 증가",
                		"value": 0
                	}, {
                		"name": "쿨타임 감소 실적용",
                		"value": 15
                	}, {
                		"name": "데미지 증가",
                		"value": 0
                	}, {
                		"name": "크리티컬 데미지 증가",
                		"value": 0
                	}, {
                		"name": "추가 데미지 증가",
                		"value": 0
                	}, {
                		"name": "모든 공격력 증가",
                		"value": 0
                	}, {
                		"name": "물리 공격력 증가",
                		"value": 0
                	}, {
                		"name": "마법 공격력 증가",
                		"value": 0
                	}, {
                		"name": "독립 공격력 증가",
                		"value": 0
                	}, {
                		"name": "힘 증가",
                		"value": 0
                	}, {
                		"name": "지능 증가",
                		"value": 0
                	}, {
                		"name": "지속피해",
                		"value": 0
                	}, {
                		"name": "물리 피해 감소",
                		"value": 0
                	}, {
                		"name": "마법 피해 감소",
                		"value": 0
                	}, {
                		"name": "출혈 데미지 전환",
                		"value": 0
                	}, {
                		"name": "중독 데미지 전환",
                		"value": 0
                	}, {
                		"name": "화상 데미지 전환",
                		"value": 0
                	}, {
                		"name": "감전 데미지 전환",
                		"value": 0
                	}, {
                		"name": "출혈 내성",
                		"value": 0
                	}, {
                		"name": "중독 내성",
                		"value": 0
                	}, {
                		"name": "화상 내성",
                		"value": 0
                	}, {
                		"name": "감전 내성",
                		"value": 0
                	}, {
                		"name": "빙결 내성",
                		"value": 0
                	}, {
                		"name": "둔화 내성",
                		"value": 20
                	}, {
                		"name": "기절 내성",
                		"value": 0
                	}, {
                		"name": "저주 내성",
                		"value": 20
                	}, {
                		"name": "암흑 내성",
                		"value": 0
                	}, {
                		"name": "석화 내성",
                		"value": 0
                	}, {
                		"name": "수면 내성",
                		"value": 0
                	}, {
                		"name": "혼란 내성",
                		"value": 0
                	}, {
                		"name": "구속 내성",
                		"value": 20
                	}]
                }
                """;
        }
        @Override
        public String buff() {
            return """
                    {
                    	"characterId": "68642c1010daed6d46b78a80b7d0fe2d",
                    	"characterName": "이키,더_비키",
                    	"level": 110,
                    	"jobId": "3909d0b188e9c95311399f776e331da5",
                    	"jobGrowId": "c9b492038ee3ca8d27d7004cf58d59f3",
                    	"jobName": "마법사(여)",
                    	"jobGrowName": "眞 마도학자",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"skill": {
                    		"buff": {
                    			"skillInfo": {
                    				"skillId": "01384bbfc346775d1267fa0bc4ca605f",
                    				"name": "고대의 도서관",
                    				"option": {
                    					"level": 20,
                    					"desc": "버프 유지 시간 : {value1}초\\n마도학자 기본/스킬 공격력 증가량 : {value2}%",
                    					"values": ["-", "90.5"]
                    				}
                    			},
                    			"equipment": [{
                    				"slotId": "WEAPON",
                    				"slotName": "무기",
                    				"itemId": "62a6da9bb1513e6df72c52a95e9e84aa",
                    				"itemName": "심연의 편린 브러쉬S : 고대의 도서관",
                    				"itemTypeId": "4ffb6f14b86f5c818a925bf58022686e",
                    				"itemType": "무기",
                    				"itemTypeDetailId": "a8dc5d83f1607d87b99a92c0dacd6fa3",
                    				"itemTypeDetail": "빗자루",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "a58260bc65ca632ab046df0bee7bb8c0",
                    				"setItemName": "심연의 편린 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "TITLE",
                    				"slotName": "칭호",
                    				"itemId": "7e936df91b06487f212ac9e11283c362",
                    				"itemName": "해적의 모험[불]",
                    				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    				"itemType": "액세서리",
                    				"itemTypeDetailId": "691933a06ff114348a7db936fb0aec66",
                    				"itemTypeDetail": "칭호",
                    				"itemAvailableLevel": 1,
                    				"itemRarity": "레어",
                    				"setItemId": null,
                    				"setItemName": null,
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0,
                    				"enchant": {
                    					"reinforceSkill": [{
                    						"jobId": "3909d0b188e9c95311399f776e331da5",
                    						"jobName": "마법사(여)",
                    						"skills": [{
                    							"skillId": "01384bbfc346775d1267fa0bc4ca605f",
                    							"name": "고대의 도서관",
                    							"value": 1
                    						}]
                    					}]
                    				}
                    			}, {
                    				"slotId": "JACKET",
                    				"slotName": "상의",
                    				"itemId": "365c77306a90b87a93130983b0f959db",
                    				"itemName": "심연의 편린 상의 : 고대의 도서관",
                    				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    				"itemType": "방어구",
                    				"itemTypeDetailId": "78ed3499a2adf3caebc3391b32fdccc7",
                    				"itemTypeDetail": "가죽 상의",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "a58260bc65ca632ab046df0bee7bb8c0",
                    				"setItemName": "심연의 편린 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "SHOULDER",
                    				"slotName": "머리어깨",
                    				"itemId": "470bcd692fe1f4425cd4b96774040623",
                    				"itemName": "심연의 편린 어깨 : 고대의 도서관",
                    				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    				"itemType": "방어구",
                    				"itemTypeDetailId": "271597ef830b7e0ff8e97e5fc2a7623c",
                    				"itemTypeDetail": "가죽 머리어깨",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "a58260bc65ca632ab046df0bee7bb8c0",
                    				"setItemName": "심연의 편린 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "PANTS",
                    				"slotName": "하의",
                    				"itemId": "1d1008186f395d3afb22a0d89cad6d00",
                    				"itemName": "심연의 편린 하의 : 고대의 도서관",
                    				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    				"itemType": "방어구",
                    				"itemTypeDetailId": "2267f6e837bda673babc358112393d02",
                    				"itemTypeDetail": "가죽 하의",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "a58260bc65ca632ab046df0bee7bb8c0",
                    				"setItemName": "심연의 편린 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "SHOES",
                    				"slotName": "신발",
                    				"itemId": "1b53d4b54aa48d9994163f1730e2dc60",
                    				"itemName": "심연의 편린 신발 : 고대의 도서관",
                    				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    				"itemType": "방어구",
                    				"itemTypeDetailId": "47992b63ee0ddbbbb1e3ade4bb31b081",
                    				"itemTypeDetail": "가죽 신발",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "a58260bc65ca632ab046df0bee7bb8c0",
                    				"setItemName": "심연의 편린 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "WAIST",
                    				"slotName": "허리",
                    				"itemId": "809a2a0a5b08c6371b595a9a9f1566a7",
                    				"itemName": "심연의 편린 벨트 : 고대의 도서관",
                    				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    				"itemType": "방어구",
                    				"itemTypeDetailId": "8d0f75caa866626d3c6f9a78e1178830",
                    				"itemTypeDetail": "가죽 허리",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "a58260bc65ca632ab046df0bee7bb8c0",
                    				"setItemName": "심연의 편린 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "AMULET",
                    				"slotName": "목걸이",
                    				"itemId": "49e1535ffc6f7ca1b3308ed02e1d2352",
                    				"itemName": "심연의 편린 목걸이 : 고대의 도서관",
                    				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    				"itemType": "액세서리",
                    				"itemTypeDetailId": "390e3966118b0c466ce9f8eae45e1629",
                    				"itemTypeDetail": "목걸이",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "a58260bc65ca632ab046df0bee7bb8c0",
                    				"setItemName": "심연의 편린 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "WRIST",
                    				"slotName": "팔찌",
                    				"itemId": "1eb93eda8c337aa440a29eb7e2afe2a8",
                    				"itemName": "심연의 편린 팔찌 : 고대의 도서관",
                    				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    				"itemType": "액세서리",
                    				"itemTypeDetailId": "80bddf423629c28c7b4459c328fdffaf",
                    				"itemTypeDetail": "팔찌",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "a58260bc65ca632ab046df0bee7bb8c0",
                    				"setItemName": "심연의 편린 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "RING",
                    				"slotName": "반지",
                    				"itemId": "6bec4cfcf2f23f34064c72b6ca74e76d",
                    				"itemName": "심연의 편린 반지 : 고대의 도서관",
                    				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    				"itemType": "액세서리",
                    				"itemTypeDetailId": "b04c7fb9b29b27b91a0a9e5a1822bc8f",
                    				"itemTypeDetail": "반지",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "a58260bc65ca632ab046df0bee7bb8c0",
                    				"setItemName": "심연의 편린 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "SUPPORT",
                    				"slotName": "보조장비",
                    				"itemId": "64c6cab8116c39da6c37476336f4d453",
                    				"itemName": "왜곡된 심연의 완장 : 고대의 도서관",
                    				"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    				"itemType": "추가장비",
                    				"itemTypeDetailId": "2fef5d81b7f59f0c75241890a8d941c9",
                    				"itemTypeDetail": "보조장비",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "522045a2f7cceabbbd4935cea6a46435",
                    				"setItemName": "왜곡된 심연의 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "MAGIC_STON",
                    				"slotName": "마법석",
                    				"itemId": "2e7cd458f02667415646c9d2c48f717f",
                    				"itemName": "왜곡된 심연의 마법석 : 고대의 도서관",
                    				"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    				"itemType": "추가장비",
                    				"itemTypeDetailId": "fe5f3db78175f5a3196385c688d29681",
                    				"itemTypeDetail": "마법석",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "522045a2f7cceabbbd4935cea6a46435",
                    				"setItemName": "왜곡된 심연의 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "EARRING",
                    				"slotName": "귀걸이",
                    				"itemId": "eabcda6e36f22590f8cbb79a49c7432a",
                    				"itemName": "왜곡된 심연의 귀걸이 : 고대의 도서관",
                    				"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    				"itemType": "추가장비",
                    				"itemTypeDetailId": "601834074c49bb0e48cb65a75a8667bc",
                    				"itemTypeDetail": "귀걸이",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "522045a2f7cceabbbd4935cea6a46435",
                    				"setItemName": "왜곡된 심연의 고대의 도서관 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}]
                    		}
                    	}
                    }
                    """;
        }
        @Override
        public String equipment() {
            return """
                    {
                    	"characterId": "68642c1010daed6d46b78a80b7d0fe2d",
                    	"characterName": "이키,더_비키",
                    	"level": 110,
                    	"jobId": "3909d0b188e9c95311399f776e331da5",
                    	"jobGrowId": "c9b492038ee3ca8d27d7004cf58d59f3",
                    	"jobName": "마법사(여)",
                    	"jobGrowName": "眞 마도학자",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"equipment": [{
                    		"slotId": "WEAPON",
                    		"slotName": "무기",
                    		"itemId": "cde24e89de1344351eb45c0246d1cd03",
                    		"itemName": "근원을 삼킨 빗자루",
                    		"itemTypeId": "4ffb6f14b86f5c818a925bf58022686e",
                    		"itemType": "무기",
                    		"itemTypeDetailId": "a8dc5d83f1607d87b99a92c0dacd6fa3",
                    		"itemTypeDetail": "빗자루",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 10,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "화속성강화",
                    				"value": 15
                    			}, {
                    				"name": "수속성강화",
                    				"value": 15
                    			}]
                    		},
                    		"amplificationName": "차원의 지능",
                    		"refine": 8,
                    		"bakalInfo": {
                    			"options": [{
                    				"buff": 1356,
                    				"explain": "스킬 공격력 7% 증가\\n피격으로 HP가 1% 이상 감소 시 10초 동안 HP 5% 감소",
                    				"explainDetail": "스킬 공격력 7% 증가\\n피격으로 HP가 1% 이상 감소 시 10초 동안 HP 5% 감소 (최대 3중첩)"
                    			}, {
                    				"buff": 1356,
                    				"explain": "스킬 공격력 7% 증가\\n피격으로 HP가 1% 이상 감소 시 10초 동안 MP 5% 감소",
                    				"explainDetail": "스킬 공격력 7% 증가\\n피격으로 HP가 1% 이상 감소 시 10초 동안 MP 5% 감소 (최대 3중첩)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "201dff85917dcc9ca123b558910c2027",
                    			"itemName": "마지막 불의 숨결 : 빗자루"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 14376,
                    				"buff": 5378,
                    				"level": 288
                    			},
                    			"options": [{
                    				"level": 73,
                    				"expRate": 82.93,
                    				"explain": "마법사/크리에이터 모든 스킬 공격력 10% 증가",
                    				"explainDetail": "마법사/크리에이터 모든 스킬 공격력 10% 증가",
                    				"damage": 4012,
                    				"default": {
                    					"damage": 1482,
                    					"buff": 504
                    				},
                    				"buff": 1365
                    			}, {
                    				"level": 72,
                    				"expRate": 80.2,
                    				"explain": "마법사/크리에이터 1~100Lv 모든 스킬 Lv +1",
                    				"explainDetail": "마법사/크리에이터 1~100Lv 모든 스킬 Lv +1 (각성기 제외)",
                    				"damage": 3992,
                    				"default": {
                    					"damage": 1482,
                    					"buff": 504
                    				},
                    				"buff": 1358
                    			}, {
                    				"level": 72,
                    				"expRate": 0.33,
                    				"explain": "HP MAX +600",
                    				"explainDetail": "HP MAX +600",
                    				"damage": 3194,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1331
                    			}, {
                    				"level": 71,
                    				"expRate": 7.44,
                    				"explain": "MP 1분당 348 회복",
                    				"explainDetail": "MP 1분당 348 회복",
                    				"damage": 3178,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1324
                    			}]
                    		}
                    	}, {
                    		"slotId": "TITLE",
                    		"slotName": "칭호",
                    		"itemId": "c89a01593a155a3cbaaf272084d8fb57",
                    		"itemName": "진정한 각성을 이룬 자",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "691933a06ff114348a7db936fb0aec66",
                    		"itemTypeDetail": "칭호",
                    		"itemAvailableLevel": 1,
                    		"itemRarity": "레어",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 4
                    			}, {
                    				"name": "힘",
                    				"value": 25
                    			}, {
                    				"name": "지능",
                    				"value": 25
                    			}, {
                    				"name": "체력",
                    				"value": 25
                    			}, {
                    				"name": "정신력",
                    				"value": 25
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "JACKET",
                    		"slotName": "상의",
                    		"itemId": "8fce56a94be3eb247df238340593a8a4",
                    		"itemName": "엔트 정령의 상의",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "78ed3499a2adf3caebc3391b32fdccc7",
                    		"itemTypeDetail": "가죽 상의",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "독립 공격력",
                    				"value": 70
                    			}, {
                    				"name": "힘",
                    				"value": 40
                    			}, {
                    				"name": "지능",
                    				"value": 40
                    			}]
                    		},
                    		"amplificationName": "차원의 지능",
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"damage": 4685,
                    				"buff": 1291,
                    				"explain": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 공격 속도, 이동 속도 +4%, 캐스팅 속도 +6%\\n- 파티원이 1명일 때 공격 시 30% 확률로 적에게 뇌광폭발 생성",
                    				"explainDetail": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 공격 속도, 이동 속도 +4%, 캐스팅 속도 +6% (최대 4중첩)\\n- 파티원이 1명일 때 공격 시 30% 확률로 적에게 뇌광폭발 생성 (쿨타임 4초, 적의 방어력과 상관없이 총 피해 증가 수치의 500%만큼 피해를 줌)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "51ee9e62bdca253954fba873934c9193",
                    			"itemName": "뇌광 : 천둥을 품은 용심"
                    		},
                    		"growInfo": {
                    			"transfer": true,
                    			"total": {
                    				"damage": 11981,
                    				"buff": 5550,
                    				"level": 316
                    			},
                    			"options": [{
                    				"level": 79,
                    				"expRate": 56.48,
                    				"explain": "HP 40% 초과 일 때 물리, 마법 방어력 +25000, 물리, 마법 크리티컬 -8%\\nHP 40% 이하일 때 스킬 공격력 8% 증가, 공격 시 HP 1100, MP 1750 회복",
                    				"explainDetail": "HP 40% 초과 일 때 물리, 마법 방어력 +25000, 물리, 마법 크리티컬 -8%\\nHP 40% 이하일 때 스킬 공격력 8% 증가, 공격 시 HP 1100, MP 1750 회복 (쿨타임 1초)",
                    				"damage": 2066,
                    				"default": {
                    					"damage": 741,
                    					"buff": 504
                    				},
                    				"buff": 1405
                    			}, {
                    				"level": 79,
                    				"expRate": 1.8,
                    				"explain": "화속성 강화 15 증가",
                    				"explainDetail": "화속성 강화 15 증가",
                    				"damage": 3305,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1377,
                    				"transfer": true
                    			}, {
                    				"level": 79,
                    				"expRate": 4.47,
                    				"explain": "모든 속성 강화 10 증가",
                    				"explainDetail": "모든 속성 강화 10 증가",
                    				"damage": 3305,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1377
                    			}, {
                    				"level": 79,
                    				"expRate": 36.29,
                    				"explain": "가장 높은 속성 강화 50마다 피해 증가 +2375, 모든 속도 6% 증가",
                    				"explainDetail": "가장 높은 속성 강화 50마다 피해 증가 +2375, 모든 속도 6% 증가 (최대 4중첩)",
                    				"damage": 3305,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 499
                    				},
                    				"buff": 1391
                    			}]
                    		}
                    	}, {
                    		"slotId": "SHOULDER",
                    		"slotName": "머리어깨",
                    		"itemId": "480452079c1c187796105e3790e595bc",
                    		"itemName": "매니퓰레이션",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "271597ef830b7e0ff8e97e5fc2a7623c",
                    		"itemTypeDetail": "가죽 머리어깨",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"explain": "스킬 공격력 +1%",
                    			"status": [{
                    				"name": "물리 공격력",
                    				"value": 30
                    			}, {
                    				"name": "마법 공격력",
                    				"value": 30
                    			}, {
                    				"name": "독립 공격력",
                    				"value": 30
                    			}, {
                    				"name": "물리 크리티컬 히트",
                    				"value": "5%"
                    			}, {
                    				"name": "마법 크리티컬 히트",
                    				"value": "5%"
                    			}]
                    		},
                    		"amplificationName": "차원의 지능",
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"damage": 3756,
                    				"buff": 1063,
                    				"explain": "40레벨 스킬 시전 시 40레벨 스킬의 쿨타임 회복속도 15% 증가\\n- 최대 중첩 상태일 때 40레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 수호 계열 스킬에 효과 적용\\n\\n75레벨 스킬 시전 시 75레벨 스킬의 쿨타임 회복속도 15% 증가\\n- 최대 중첩 상태일 때 75레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 조율 계열 스킬에 효과 적용",
                    				"explainDetail": "40레벨 스킬 시전 시 40레벨 스킬의 쿨타임 회복속도 15% 증가 (최대 3중첩)\\n- 최대 중첩 상태일 때 40레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 수호 계열 스킬에 효과 적용\\n\\n75레벨 스킬 시전 시 75레벨 스킬의 쿨타임 회복속도 15% 증가 (최대 3중첩)\\n- 최대 중첩 상태일 때 75레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 조율 계열 스킬에 효과 적용"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "be8bb0a24a891647a32229cb1e920051",
                    			"itemName": "교감 : 감싸안는 햇살"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 12389,
                    				"buff": 5462,
                    				"level": 301
                    			},
                    			"options": [{
                    				"level": 77,
                    				"expRate": 41.8,
                    				"explain": "스킬 시전 시 남은 HP의 30%를 소모하여 40초 동안 피해 증가 +6137, 스킬 공격력 10% 증가",
                    				"explainDetail": "스킬 시전 시 남은 HP의 30%를 소모하여 40초 동안 피해 증가 +6137, 스킬 공격력 10% 증가 (쿨타임 30초, 해당 옵션으로 HP가 1% 미만으로 떨어지지 않음)",
                    				"damage": 2975,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 504
                    				},
                    				"buff": 1391
                    			}, {
                    				"level": 74,
                    				"expRate": 67.4,
                    				"explain": "HP 90% 이하일 때 공격 시 HP 2% 회복",
                    				"explainDetail": "HP 90% 이하일 때 공격 시 HP 2% 회복 (쿨타임 5초)",
                    				"damage": 2932,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 504
                    				},
                    				"buff": 1371
                    			}, {
                    				"level": 75,
                    				"expRate": 21.78,
                    				"explain": "스킬 MP 소모량 7% 감소",
                    				"explainDetail": "스킬 MP 소모량 7% 감소",
                    				"damage": 3241,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1350
                    			}, {
                    				"level": 75,
                    				"expRate": 17.28,
                    				"explain": "물리 크리티컬 히트 +5%\\n마법 크리티컬 히트 +5%",
                    				"explainDetail": "물리 크리티컬 히트 +5%\\n마법 크리티컬 히트 +5%",
                    				"damage": 3241,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1350
                    			}]
                    		}
                    	}, {
                    		"slotId": "PANTS",
                    		"slotName": "하의",
                    		"itemId": "d3e8e68964c5fd2e55f9aeedcc49d9fa",
                    		"itemName": "언리밋 사이버네틱",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "2267f6e837bda673babc358112393d02",
                    		"itemTypeDetail": "가죽 하의",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "독립 공격력",
                    				"value": 70
                    			}, {
                    				"name": "힘",
                    				"value": 40
                    			}, {
                    				"name": "지능",
                    				"value": 40
                    			}]
                    		},
                    		"amplificationName": "차원의 지능",
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"buff": 1291,
                    				"explain": "둔화 상태의 적 공격 시 5초 동안 구속 상태로 만듦\\n\\n자신의 주변 500px 범위 내에 상태이상이 걸린 적이 존재하면 20초 동안 피해 증가 +6213, 공격속도 +8%, 캐스팅속도 +12%\\n\\n구속 내성 +10%\\n저주 내성 +10%\\n둔화 내성 +10%",
                    				"explainDetail": "둔화 상태의 적 공격 시 5초 동안 구속 상태로 만듦 (쿨타임 10초)\\n\\n자신의 주변 500px 범위 내에 상태이상이 걸린 적이 존재하면 20초 동안 피해 증가 +6213, 공격속도 +8%, 캐스팅속도 +12% (최대 1중첩)\\n\\n구속 내성 +10%\\n저주 내성 +10%\\n둔화 내성 +10%"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "b040382ef0a0f361de63a451ee415dc3",
                    			"itemName": "포식 : 사그라진 고동"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 16262,
                    				"buff": 5390,
                    				"level": 310
                    			},
                    			"options": [{
                    				"level": 78,
                    				"expRate": 36.48,
                    				"explain": "장착 시 20초마다 자신의 주변 250px의 적을 5초 동안 저주 상태로 만듦\\n대쉬 1초 마다 저주 적용 범위 50px 증가, HP 0.5% 감소\\n대쉬 상태가 아닐 때 1초마다 저주 적용 범위 50px 감소",
                    				"explainDetail": "장착 시 20초마다 자신의 주변 250px의 적을 5초 동안 저주 상태로 만듦\\n대쉬 1초 마다 저주 적용 범위 50px 증가 (최대 250px 증가), HP 0.5% 감소 (해당 옵션으로 HP가 1% 미만으로 감소하지 않음)\\n대쉬 상태가 아닐 때 1초마다 저주 적용 범위 50px 감소 (250px 미만으로 감소하지 않음)",
                    				"damage": 4858,
                    				"default": {
                    					"damage": 1751,
                    					"buff": 480
                    				},
                    				"buff": 1332
                    			}, {
                    				"level": 78,
                    				"expRate": 29.74,
                    				"explain": "HP가 70% 이상일 때 스킬 쿨타임 10% 감소\\nHP가 50% 이상 70% 미만일 때 스킬 쿨타임 12% 감소\\nHP가 50% 미만일 때 스킬 쿨타임 15% 감소",
                    				"explainDetail": "HP가 70% 이상일 때 스킬 쿨타임 10% 감소 (각성기 제외)\\nHP가 50% 이상 70% 미만일 때 스킬 쿨타임 12% 감소 (각성기 제외)\\nHP가 50% 미만일 때 스킬 쿨타임 15% 감소 (각성기 제외)",
                    				"damage": 4858,
                    				"default": {
                    					"damage": 1751,
                    					"buff": 480
                    				},
                    				"buff": 1332
                    			}, {
                    				"level": 77,
                    				"expRate": 93.14,
                    				"explain": "이동 속도 +8%",
                    				"explainDetail": "이동 속도 +8%",
                    				"damage": 3273,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1363
                    			}, {
                    				"level": 77,
                    				"expRate": 90.4,
                    				"explain": "회피율 +8%",
                    				"explainDetail": "회피율 +8%",
                    				"damage": 3273,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1363
                    			}]
                    		}
                    	}, {
                    		"slotId": "SHOES",
                    		"slotName": "신발",
                    		"itemId": "7d66f7dd960933be4e1e5abaa9f32f45",
                    		"itemName": "엑셀러레이터",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "47992b63ee0ddbbbb1e3ade4bb31b081",
                    		"itemTypeDetail": "가죽 신발",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "마법 크리티컬 히트",
                    				"value": "5%"
                    			}]
                    		},
                    		"amplificationName": "차원의 지능",
                    		"refine": 0,
                    		"growInfo": {
                    			"total": {
                    				"damage": 13166,
                    				"buff": 5149,
                    				"level": 283
                    			},
                    			"options": [{
                    				"level": 72,
                    				"expRate": 4.4,
                    				"explain": "MP MAX +4196\\n남은 MP 20000마다 피해 증가 +998\\nMP MAX가 200000 이상일 때 스킬 공격력 5% 증가",
                    				"explainDetail": "MP MAX +4196\\n남은 MP 20000마다 피해 증가 +998 (최대 10중첩)\\nMP MAX가 200000 이상일 때 스킬 공격력 5% 증가",
                    				"damage": 3629,
                    				"default": {
                    					"damage": 1347,
                    					"buff": 470
                    				},
                    				"buff": 1267
                    			}, {
                    				"level": 70,
                    				"expRate": 40.81,
                    				"explain": "스킬 쿨타임 15% 감소",
                    				"explainDetail": "스킬 쿨타임 15% 감소 (각성기 제외)",
                    				"damage": 3593,
                    				"default": {
                    					"damage": 1347,
                    					"buff": 470
                    				},
                    				"buff": 1254
                    			}, {
                    				"level": 71,
                    				"expRate": 59.27,
                    				"explain": "MP 1분당 348 회복",
                    				"explainDetail": "MP 1분당 348 회복",
                    				"damage": 3178,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1324
                    			}, {
                    				"level": 70,
                    				"expRate": 61.16,
                    				"explain": "공격 시 MP 3500 회복",
                    				"explainDetail": "공격 시 MP 3500 회복 (쿨타임 1초)",
                    				"damage": 2766,
                    				"default": {
                    					"damage": 1037,
                    					"buff": 489
                    				},
                    				"buff": 1304
                    			}]
                    		}
                    	}, {
                    		"slotId": "WAIST",
                    		"slotName": "허리",
                    		"itemId": "32ca7d7da53d0e9b2aafacfb5ec57dcf",
                    		"itemName": "파워 플랜트",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "8d0f75caa866626d3c6f9a78e1178830",
                    		"itemTypeDetail": "가죽 허리",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"explain": "스킬 공격력 +2%",
                    			"status": [{
                    				"name": "힘",
                    				"value": 30
                    			}, {
                    				"name": "지능",
                    				"value": 30
                    			}, {
                    				"name": "체력",
                    				"value": 30
                    			}, {
                    				"name": "정신력",
                    				"value": 30
                    			}, {
                    				"name": "물리 공격력",
                    				"value": 6
                    			}, {
                    				"name": "마법 공격력",
                    				"value": 6
                    			}, {
                    				"name": "독립 공격력",
                    				"value": 6
                    			}, {
                    				"name": "물리 크리티컬 히트",
                    				"value": "3%"
                    			}, {
                    				"name": "마법 크리티컬 히트",
                    				"value": "3%"
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"buff": 1291,
                    				"explain": "공격 시 적을 20초 동안 출혈, 화상 중 하나의 상태로 만듦\\nMP가 10% 이상일 때 모든 속성 강화 +25\\n\\n구속 내성 +10%\\n저주 내성 +10%\\n둔화 내성 +10%",
                    				"explainDetail": "공격 시 적을 20초 동안 출혈, 화상 중 하나의 상태로 만듦 (쿨타임 5초)\\nMP가 10% 이상일 때 모든 속성 강화 +25\\n\\n구속 내성 +10%\\n저주 내성 +10%\\n둔화 내성 +10%"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "3ce72504c5b4353c5fb82a74e7ddebaf",
                    			"itemName": "포식 : 조여오는 올가미"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 17844,
                    				"buff": 5472,
                    				"level": 314
                    			},
                    			"options": [{
                    				"level": 79,
                    				"expRate": 16.44,
                    				"explain": "HP 50% 미만일 때 쿨타임 회복 속도 15% 증가, 스킬 공격력 6% 증가",
                    				"explainDetail": "HP 50% 미만일 때 쿨타임 회복 속도 15% 증가 (각성기 제외), 스킬 공격력 6% 증가",
                    				"damage": 5633,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 490
                    				},
                    				"buff": 1365
                    			}, {
                    				"level": 79,
                    				"expRate": 38.14,
                    				"explain": "HP 50% 미만일 때 공격 시 HP, MP 2000 회복",
                    				"explainDetail": "HP 50% 미만일 때 공격 시 HP, MP 2000 회복 (쿨타임 1초)",
                    				"damage": 5633,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 490
                    				},
                    				"buff": 1365
                    			}, {
                    				"level": 78,
                    				"expRate": 18.02,
                    				"explain": "스킬 MP 소모량 7% 감소",
                    				"explainDetail": "스킬 MP 소모량 7% 감소",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}, {
                    				"level": 78,
                    				"expRate": 29.14,
                    				"explain": "공격 속도 +8%\\n캐스팅 속도 +12%",
                    				"explainDetail": "공격 속도 +8%\\n캐스팅 속도 +12%",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}]
                    		}
                    	}, {
                    		"slotId": "AMULET",
                    		"slotName": "목걸이",
                    		"itemId": "bbd070d70a5168fabecaef168427487d",
                    		"itemName": "검은 별",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "390e3966118b0c466ce9f8eae45e1629",
                    		"itemTypeDetail": "목걸이",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "화속성강화",
                    				"value": 30
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"ispinsInfo": {
                    			"options": [{
                    				"buff": 1405,
                    				"explain": "물리, 마법 방어력 총 합이 90000 이상일 때 피해 증가 +7815\\n\\n물리, 마법 방어력 +14000\\n\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 -2800",
                    				"explainDetail": "물리, 마법 방어력 총 합이 90000 이상일 때 피해 증가 +7815\\n\\n물리, 마법 방어력 +14000\\n\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 -2800 (최대 5중첩)\\n- 2초 동안 피격되지 않을 시 물리, 마법 방어력 감소 중첩 수 1 감소"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "3b62a91abc21df1a2609af53cb15d432",
                    			"itemName": "천계 연합군 : 모든 이를 위한 기도"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 13548,
                    				"buff": 5328,
                    				"level": 298
                    			},
                    			"options": [{
                    				"level": 74,
                    				"expRate": 63.62,
                    				"explain": "HP가 80% 이상일 때 피해 증가 +1210\\nHP가 50% 이상 80% 미만일 때 피해 증가 +7258\\nHP가 50% 미만일 때 피해 증가 +7258, 스킬 공격력 7% 증가",
                    				"explainDetail": "HP가 80% 이상일 때 피해 증가 +1210\\nHP가 50% 이상 80% 미만일 때 피해 증가 +7258\\nHP가 50% 미만일 때 피해 증가 +7258, 스킬 공격력 7% 증가",
                    				"damage": 2932,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 475
                    				},
                    				"buff": 1293
                    			}, {
                    				"level": 75,
                    				"expRate": 86.7,
                    				"explain": "HP가 50% 미만일 때 모든 속도 +20%",
                    				"explainDetail": "HP가 50% 미만일 때 모든 속도 +20%",
                    				"damage": 2946,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 475
                    				},
                    				"buff": 1299
                    			}, {
                    				"level": 76,
                    				"expRate": 5.99,
                    				"explain": "적중률 +10%",
                    				"explainDetail": "적중률 +10%",
                    				"damage": 3257,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1357
                    			}, {
                    				"level": 73,
                    				"expRate": 95.94,
                    				"explain": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"explainDetail": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"damage": 4413,
                    				"default": {
                    					"damage": 1630,
                    					"buff": 509
                    				},
                    				"buff": 1379
                    			}]
                    		}
                    	}, {
                    		"slotId": "WRIST",
                    		"slotName": "팔찌",
                    		"itemId": "fd19e43acb9bf998f94df0db7729be06",
                    		"itemName": "억제된 마력의 팔찌",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "80bddf423629c28c7b4459c328fdffaf",
                    		"itemTypeDetail": "팔찌",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "화속성강화",
                    				"value": 30
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"ispinsInfo": {
                    			"options": [{
                    				"buff": 1405,
                    				"explain": "HP MAX, MP MAX 총 합이 220000 이상일 때 피해 증가 +7815\\n\\nHP MAX +1200, MP MAX +1890\\n\\n피격으로 HP가 1% 이상 감소 시 HP MAX -240, MP MAX -378",
                    				"explainDetail": "HP MAX, MP MAX 총 합이 220000 이상일 때 피해 증가 +7815\\n\\nHP MAX +1200, MP MAX +1890\\n\\n피격으로 HP가 1% 이상 감소 시 HP MAX -240, MP MAX -378 (최대 5중첩)\\n- 2초 동안 피격되지 않을 시 HP MAX, MP MAX 감소 중첩 수 1 감소"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "cb41d2e497f1a7ac1ae2711670da9861",
                    			"itemName": "천계 연합군 : 꺾이지 않는 의지"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 15546,
                    				"buff": 5460,
                    				"level": 312
                    			},
                    			"options": [{
                    				"level": 78,
                    				"expRate": 42.13,
                    				"explain": "던전 입장 시 HP를 99% 소모하고 사용가능한 최대 HP의 99% 제한\\n피격 시 HP 대신 MP를 먼저 소모\\n장비 또는 HP 회복 스킬로 인한 HP 회복량의 25% 만큼 MP 회복",
                    				"explainDetail": "던전 입장 시 HP를 99% 소모하고 사용가능한 최대 HP의 99% 제한 (제한된 수치를 초과하여 HP 회복 불가)\\n피격 시 HP 대신 MP를 먼저 소모\\n장비 또는 HP 회복 스킬로 인한 HP 회복량의 25% 만큼 MP 회복",
                    				"damage": 4484,
                    				"default": {
                    					"damage": 1616,
                    					"buff": 490
                    				},
                    				"buff": 1359
                    			}, {
                    				"level": 78,
                    				"expRate": 44.31,
                    				"explain": "MP MAX +4196\\n던전 입장 시 피해 증가 +7400, 스킬 공격력 15% 증가",
                    				"explainDetail": "MP MAX +4196\\n던전 입장 시 피해 증가 +7400, 스킬 공격력 15% 증가",
                    				"damage": 4484,
                    				"default": {
                    					"damage": 1616,
                    					"buff": 490
                    				},
                    				"buff": 1359
                    			}, {
                    				"level": 78,
                    				"expRate": 90.28,
                    				"explain": "MP 1분당 348 회복",
                    				"explainDetail": "MP 1분당 348 회복",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}, {
                    				"level": 78,
                    				"expRate": 83.28,
                    				"explain": "물리 방어력 +7000\\n마법 방어력 +7000",
                    				"explainDetail": "물리 방어력 +7000\\n마법 방어력 +7000",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}]
                    		}
                    	}, {
                    		"slotId": "RING",
                    		"slotName": "반지",
                    		"itemId": "639dedaa8b9218023da86ff44f80662f",
                    		"itemName": "골렘의 중추석 반지",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "b04c7fb9b29b27b91a0a9e5a1822bc8f",
                    		"itemTypeDetail": "반지",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "화속성강화",
                    				"value": 30
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"ispinsInfo": {
                    			"options": [{
                    				"buff": 1518,
                    				"explain": "피격으로 HP가 1% 이상 감소 시 MP 5000 감소\\n\\n현재 MP 4% 마다 피해 증가 +1876\\n\\n스킬로 인해 소모한 MP 5000 마다 10초동안 장비로 인한 MP 회복 효과 10% 증가",
                    				"explainDetail": "피격으로 HP가 1% 이상 감소 시 MP 5000 감소 (쿨타임 0.5초)\\n\\n현재 MP 4% 마다 피해 증가 +1876 (최대 5중첩)\\n\\n스킬로 인해 소모한 MP 5000 마다 10초동안 장비로 인한 MP 회복 효과 10% 증가 (최대 1중첩)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "1fd082cd996a9eb02645be09c5e89d41",
                    			"itemName": "흑룡 : 끝없는 암흑의 굴레"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 17747,
                    				"buff": 5365,
                    				"level": 310
                    			},
                    			"options": [{
                    				"level": 77,
                    				"expRate": 51.22,
                    				"explain": "HP가 30% 미만일 때 2초마다 피해 증가 +1227\\n피해 증가 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 피해 증가 중첩 수 1 감소",
                    				"explainDetail": "HP가 30% 미만일 때 2초마다 피해 증가 +1227 (최대 3중첩)\\n피해 증가 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 피해 증가 중첩 수 1 감소 (쿨타임 1초)",
                    				"damage": 5579,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 475
                    				},
                    				"buff": 1312
                    			}, {
                    				"level": 78,
                    				"expRate": 67.25,
                    				"explain": "HP가 30% 미만일 때 2초마다 물리, 마법 방어력 +4000\\n물리, 마법 방어력 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 중첩 수 1 감소",
                    				"explainDetail": "HP가 30% 미만일 때 2초마다 물리, 마법 방어력 +4000 (최대 3중첩)\\n물리, 마법 방어력 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 중첩 수 1 감소 (쿨타임 1초)",
                    				"damage": 5606,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 475
                    				},
                    				"buff": 1319
                    			}, {
                    				"level": 77,
                    				"expRate": 84.41,
                    				"explain": "적중률 +10%",
                    				"explainDetail": "적중률 +10%",
                    				"damage": 3273,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1363
                    			}, {
                    				"level": 78,
                    				"expRate": 31.45,
                    				"explain": "회피율 +8%",
                    				"explainDetail": "회피율 +8%",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}]
                    		}
                    	}, {
                    		"slotId": "SUPPORT",
                    		"slotName": "보조장비",
                    		"itemId": "573399fba4997923936a56fe15d50d6f",
                    		"itemName": "엔트 정령의 성배",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "2fef5d81b7f59f0c75241890a8d941c9",
                    		"itemTypeDetail": "보조장비",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 11,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "물리 크리티컬 히트",
                    				"value": "3%"
                    			}, {
                    				"name": "마법 크리티컬 히트",
                    				"value": "3%"
                    			}, {
                    				"name": "모든 속성 강화",
                    				"value": 12
                    			}, {
                    				"name": "피해 증가",
                    				"value": 3
                    			}]
                    		},
                    		"amplificationName": "차원의 지능",
                    		"refine": 0,
                    		"dimensionCloisterInfo": {
                    			"options": [{
                    				"damage": 2350,
                    				"buff": 1178,
                    				"explain": "스킬 공격력 3% 증가\\n\\n1~40Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가",
                    				"explainDetail": "스킬 공격력 3% 증가\\n\\n1~40Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가 (각성기 제외)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "56422d257499f7c60c7dad02d0f43db2",
                    			"itemName": "심연의 결집 : 정의되지 않는 존재"
                    		},
                    		"growInfo": {
                    			"transfer": true,
                    			"total": {
                    				"damage": 6559,
                    				"buff": 5546,
                    				"level": 309
                    			},
                    			"options": [{
                    				"level": 78,
                    				"expRate": 49.07,
                    				"explain": "HP 40% 초과일 때 화속성 저항 +40\\nHP 40% 이하일 때 스킬 공격력 5% 증가",
                    				"explainDetail": "HP 40% 초과일 때 화속성 저항 +40\\nHP 40% 이하일 때 스킬 공격력 5% 증가",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}, {
                    				"level": 76,
                    				"expRate": 12.87,
                    				"explain": "출혈 상태 적 공격 시 스킬 공격력 5% 증가",
                    				"explainDetail": "출혈 상태 적 공격 시 스킬 공격력 5% 증가",
                    				"damage": 1221,
                    				"default": {
                    					"damage": 445,
                    					"buff": 504
                    				},
                    				"buff": 1385
                    			}, {
                    				"level": 77,
                    				"expRate": 94.4,
                    				"explain": "화상 상태 적 공격 시 스킬 공격력 5% 증가",
                    				"explainDetail": "화상 상태 적 공격 시 스킬 공격력 5% 증가",
                    				"damage": 1227,
                    				"default": {
                    					"damage": 445,
                    					"buff": 504
                    				},
                    				"buff": 1391,
                    				"transfer": true
                    			}, {
                    				"level": 78,
                    				"expRate": 56.54,
                    				"explain": "용족 타입의 적 공격 시 스킬 공격력 7% 증가",
                    				"explainDetail": "용족 타입의 적 공격 시 스킬 공격력 7% 증가",
                    				"damage": 822,
                    				"default": {
                    					"damage": 296,
                    					"buff": 504
                    				},
                    				"buff": 1399
                    			}]
                    		}
                    	}, {
                    		"slotId": "MAGIC_STON",
                    		"slotName": "마법석",
                    		"itemId": "5e597f850be761e18324dd8627f31855",
                    		"itemName": "승리가 약속된 시간",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "fe5f3db78175f5a3196385c688d29681",
                    		"itemTypeDetail": "마법석",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 7,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 30
                    			}]
                    		},
                    		"amplificationName": "차원의 지능",
                    		"refine": 0,
                    		"dimensionCloisterInfo": {
                    			"options": [{
                    				"damage": 2350,
                    				"buff": 1178,
                    				"explain": "스킬 공격력 3% 증가\\n\\n45Lv, 60~70Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가",
                    				"explainDetail": "스킬 공격력 3% 증가\\n\\n45Lv, 60~70Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가 (각성기 제외)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "2950e00eced48e19bf4c161b3bc7df7e",
                    			"itemName": "심연의 결집 : 무한한 수축"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 15568,
                    				"buff": 5332,
                    				"level": 313
                    			},
                    			"options": [{
                    				"level": 79,
                    				"expRate": 29.7,
                    				"explain": "HP가 50% 미만일 때 물리방어율과 마법방어율의 합 7% 마다 피해 증가 +1239\\n피해 증가 효과가 최대 중첩일 때 스킬 공격력 8% 증가",
                    				"explainDetail": "HP가 50% 미만일 때 물리방어율과 마법방어율의 합 7% 마다 피해 증가 +1239 (최대 10중첩)\\n피해 증가 효과가 최대 중첩일 때 스킬 공격력 8% 증가",
                    				"damage": 4506,
                    				"default": {
                    					"damage": 1616,
                    					"buff": 466
                    				},
                    				"buff": 1298
                    			}, {
                    				"level": 78,
                    				"expRate": 44.3,
                    				"explain": "HP가 50% 미만이면서 물리방어율과 마법방어율의 합이 70% 이상일 때 20초마다 5회 피격 시 파괴되는 슈퍼아머 부여",
                    				"explainDetail": "HP가 50% 미만이면서 물리방어율과 마법방어율의 합이 70% 이상일 때 20초마다 5회 피격 시 파괴되는 슈퍼아머 부여",
                    				"damage": 4484,
                    				"default": {
                    					"damage": 1616,
                    					"buff": 466
                    				},
                    				"buff": 1292
                    			}, {
                    				"level": 78,
                    				"expRate": 21.78,
                    				"explain": "MP MAX +945",
                    				"explainDetail": "MP MAX +945",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}, {
                    				"level": 78,
                    				"expRate": 89.21,
                    				"explain": "이동 속도 +8%",
                    				"explainDetail": "이동 속도 +8%",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}]
                    		}
                    	}, {
                    		"slotId": "EARRING",
                    		"slotName": "귀걸이",
                    		"itemId": "c0ef77f4ab5c758765326bf5a8b8f23f",
                    		"itemName": "폭풍을 삼킨 에너지",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "601834074c49bb0e48cb65a75a8667bc",
                    		"itemTypeDetail": "귀걸이",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 10,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 11
                    			}, {
                    				"name": "힘",
                    				"value": 50
                    			}, {
                    				"name": "지능",
                    				"value": 50
                    			}, {
                    				"name": "체력",
                    				"value": 50
                    			}, {
                    				"name": "정신력",
                    				"value": 50
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"dimensionCloisterInfo": {
                    			"options": [{
                    				"damage": 2350,
                    				"buff": 1178,
                    				"explain": "스킬 공격력 3% 증가\\n\\n75~80Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가",
                    				"explainDetail": "스킬 공격력 3% 증가\\n\\n75~80Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가 (각성기 제외)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "1ba6221619f88691ed8f0c3adba4db7a",
                    			"itemName": "심연의 결집 : 무정형의 힘"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 13900,
                    				"buff": 5265,
                    				"level": 303
                    			},
                    			"options": [{
                    				"level": 76,
                    				"expRate": 71.91,
                    				"explain": "소모품으로 인한 MP 회복 효과 50% 증가\\nHP 50% 이하일 때 스킬 공격력 10% 증가, 모든 속성 강화 +15",
                    				"explainDetail": "소모품으로 인한 MP 회복 효과 50% 증가\\nHP 50% 이하일 때 스킬 공격력 10% 증가, 모든 속성 강화 +15",
                    				"damage": 3701,
                    				"default": {
                    					"damage": 1347,
                    					"buff": 466
                    				},
                    				"buff": 1279
                    			}, {
                    				"level": 76,
                    				"expRate": 61.9,
                    				"explain": "스킬 MP 소모량 20% 감소\\nMP MAX +4196",
                    				"explainDetail": "스킬 MP 소모량 20% 감소\\nMP MAX +4196",
                    				"damage": 3701,
                    				"default": {
                    					"damage": 1347,
                    					"buff": 466
                    				},
                    				"buff": 1279
                    			}, {
                    				"level": 75,
                    				"expRate": 91.68,
                    				"explain": "적중률 +10%",
                    				"explainDetail": "적중률 +10%",
                    				"damage": 3241,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1350
                    			}, {
                    				"level": 76,
                    				"expRate": 48.96,
                    				"explain": "회피율 +8%",
                    				"explainDetail": "회피율 +8%",
                    				"damage": 3257,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1357
                    			}]
                    		}
                    	}],
                    	"setItemInfo": []
                    }
                    """;
        }

        @Override
        public String creature() {
            return """
                    {
                    	"characterId": "68642c1010daed6d46b78a80b7d0fe2d",
                    	"characterName": "이키,더_비키",
                    	"level": 110,
                    	"jobId": "3909d0b188e9c95311399f776e331da5",
                    	"jobGrowId": "c9b492038ee3ca8d27d7004cf58d59f3",
                    	"jobName": "마법사(여)",
                    	"jobGrowName": "眞 마도학자",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"creature": {
                    		"itemId": "f5c87c63bbdaa5413a105640cafbc30a",
                    		"itemName": "순백의 나비 공주",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"artifact": [{
                    			"slotColor": "RED",
                    			"itemName": "레프리콘의 모자",
                    			"itemAvailableLevel": 0,
                    			"itemRarity": "언커먼"
                    		}, {
                    			"slotColor": "BLUE",
                    			"itemName": "세이렌의 날개",
                    			"itemAvailableLevel": 0,
                    			"itemRarity": "언커먼"
                    		}, {
                    			"slotColor": "GREEN",
                    			"itemName": "에메랄드 오브",
                    			"itemAvailableLevel": 0,
                    			"itemRarity": "레어"
                    		}]
                    	}
                    }
                    """;
        }

        @Override
        public String avatar() {
            return null;
        }

    }

    // 트래블러
    // T레알돋는당T
    private static class Character3 implements Character {
        public String characterId = "a3a1fc24c7c62ae8d0f15e6cdf647642";
        @Override
        public String status() {
            return """
                {
                	"characterId": "a3a1fc24c7c62ae8d0f15e6cdf647642",
                	"characterName": "T레알돋는당T",
                	"level": 110,
                	"jobId": "b9cb48777665de22c006fabaf9a560b3",
                	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                	"jobName": "아처",
                	"jobGrowName": "眞 트래블러",
                	"adventureName": "레알돋는당",
                	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                	"guildName": "개시바",
                	"buff": [{
                		"name": "모험단 버프",
                		"level": 38,
                		"status": [{
                			"name": "힘",
                			"value": 280
                		}, {
                			"name": "지능",
                			"value": 280
                		}, {
                			"name": "체력",
                			"value": 280
                		}, {
                			"name": "정신력",
                			"value": 280
                		}]
                	}, {
                		"name": "무제한 길드능력치",
                		"status": [{
                			"name": "힘",
                			"value": 60
                		}, {
                			"name": "지능",
                			"value": 60
                		}, {
                			"name": "체력",
                			"value": 60
                		}, {
                			"name": "정신력",
                			"value": 60
                		}]
                	}, {
                		"name": "기간제 길드능력치",
                		"status": [{
                			"name": "힘",
                			"value": 40
                		}, {
                			"name": "지능",
                			"value": 40
                		}, {
                			"name": "체력",
                			"value": 40
                		}, {
                			"name": "정신력",
                			"value": 40
                		}]
                	}],
                	"status": [{
                		"name": "HP",
                		"value": 112968
                	}, {
                		"name": "MP",
                		"value": 307032
                	}, {
                		"name": "물리 방어율",
                		"value": 32.8
                	}, {
                		"name": "마법 방어율",
                		"value": 37
                	}, {
                		"name": "힘",
                		"value": 4117
                	}, {
                		"name": "지능",
                		"value": 3199
                	}, {
                		"name": "체력",
                		"value": 3181
                	}, {
                		"name": "정신력",
                		"value": 3311
                	}, {
                		"name": "물리 공격",
                		"value": 3955
                	}, {
                		"name": "마법 공격",
                		"value": 2311
                	}, {
                		"name": "물리 크리티컬",
                		"value": 69.3
                	}, {
                		"name": "마법 크리티컬",
                		"value": 44.5
                	}, {
                		"name": "독립 공격",
                		"value": 2228
                	}, {
                		"name": "공격 속도",
                		"value": 66.6
                	}, {
                		"name": "캐스팅 속도",
                		"value": 67.5
                	}, {
                		"name": "이동 속도",
                		"value": 50.4
                	}, {
                		"name": "모험가 명성",
                		"value": 40552
                	}, {
                		"name": "적중률",
                		"value": 60.5
                	}, {
                		"name": "회피율",
                		"value": 37
                	}, {
                		"name": "HP 회복량",
                		"value": 1234
                	}, {
                		"name": "MP 회복량",
                		"value": 15426
                	}, {
                		"name": "경직도",
                		"value": 0
                	}, {
                		"name": "히트리커버리",
                		"value": 851
                	}, {
                		"name": "화속성 강화",
                		"value": 252
                	}, {
                		"name": "화속성 저항",
                		"value": 11
                	}, {
                		"name": "수속성 강화",
                		"value": 252
                	}, {
                		"name": "수속성 저항",
                		"value": 11
                	}, {
                		"name": "명속성 강화",
                		"value": 252
                	}, {
                		"name": "명속성 저항",
                		"value": 46
                	}, {
                		"name": "암속성 강화",
                		"value": 252
                	}, {
                		"name": "암속성 저항",
                		"value": 11
                	}, {
                		"name": "물리 방어",
                		"value": 48766
                	}, {
                		"name": "마법 방어",
                		"value": 58655
                	}, {
                		"name": "피해 증가",
                		"value": 183299
                	}, {
                		"name": "버프력",
                		"value": 0
                	}, {
                		"name": "피해 증가 %",
                		"value": 40
                	}, {
                		"name": "버프력 %",
                		"value": 0
                	}, {
                		"name": "스킬 공격력 증가",
                		"value": 1234.9
                	}, {
                		"name": "쿨타임 감소",
                		"value": 15
                	}, {
                		"name": "쿨타임 회복속도 증가",
                		"value": 0
                	}, {
                		"name": "쿨타임 감소 실적용",
                		"value": 15
                	}, {
                		"name": "데미지 증가",
                		"value": 0
                	}, {
                		"name": "크리티컬 데미지 증가",
                		"value": 0
                	}, {
                		"name": "추가 데미지 증가",
                		"value": 0
                	}, {
                		"name": "모든 공격력 증가",
                		"value": 0
                	}, {
                		"name": "물리 공격력 증가",
                		"value": 0
                	}, {
                		"name": "마법 공격력 증가",
                		"value": 0
                	}, {
                		"name": "독립 공격력 증가",
                		"value": 0
                	}, {
                		"name": "힘 증가",
                		"value": 0
                	}, {
                		"name": "지능 증가",
                		"value": 0
                	}, {
                		"name": "지속피해",
                		"value": 0
                	}, {
                		"name": "물리 피해 감소",
                		"value": -10
                	}, {
                		"name": "마법 피해 감소",
                		"value": -10
                	}, {
                		"name": "출혈 데미지 전환",
                		"value": 0
                	}, {
                		"name": "중독 데미지 전환",
                		"value": 0
                	}, {
                		"name": "화상 데미지 전환",
                		"value": 0
                	}, {
                		"name": "감전 데미지 전환",
                		"value": 0
                	}, {
                		"name": "출혈 내성",
                		"value": 0
                	}, {
                		"name": "중독 내성",
                		"value": 0
                	}, {
                		"name": "화상 내성",
                		"value": 0
                	}, {
                		"name": "감전 내성",
                		"value": 0
                	}, {
                		"name": "빙결 내성",
                		"value": 0
                	}, {
                		"name": "둔화 내성",
                		"value": 10
                	}, {
                		"name": "기절 내성",
                		"value": 0
                	}, {
                		"name": "저주 내성",
                		"value": 10
                	}, {
                		"name": "암흑 내성",
                		"value": 0
                	}, {
                		"name": "석화 내성",
                		"value": 0
                	}, {
                		"name": "수면 내성",
                		"value": 0
                	}, {
                		"name": "혼란 내성",
                		"value": 0
                	}, {
                		"name": "구속 내성",
                		"value": 10
                	}]
                }
                """;
        }

        @Override
        public String buff() {
            return """
                    {
                    	"characterId": "a3a1fc24c7c62ae8d0f15e6cdf647642",
                    	"characterName": "T레알돋는당T",
                    	"level": 110,
                    	"jobId": "b9cb48777665de22c006fabaf9a560b3",
                    	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                    	"jobName": "아처",
                    	"jobGrowName": "眞 트래블러",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"skill": {
                    		"buff": {
                    			"skillInfo": {
                    				"skillId": "9cb6f9ed646fa87f9b7680a42ce83d1a",
                    				"name": "익사이팅",
                    				"option": {
                    					"level": 20,
                    					"desc": "지속 시간 : {value1}초\\n기본 공격 및 스킬 공격력 증가량 : {value2}%\\n이동 속도 증가량 : {value3}%",
                    					"values": ["-", "97", "15"]
                    				}
                    			},
                    			"equipment": [{
                    				"slotId": "WEAPON",
                    				"slotName": "무기",
                    				"itemId": "46ce1b0f9c8e9c5897a108b31c1fedd8",
                    				"itemName": "심연의 편린 롱보우S : 익사이팅",
                    				"itemTypeId": "4ffb6f14b86f5c818a925bf58022686e",
                    				"itemType": "무기",
                    				"itemTypeDetailId": "3df98b9ffe982fa49d0a082fe9604a98",
                    				"itemTypeDetail": "장궁",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "d68bd3e39dd3b4511e6a2a273cdf1c2e",
                    				"setItemName": "심연의 편린 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "TITLE",
                    				"slotName": "칭호",
                    				"itemId": "865dbb30703fff25f95950f2292dae09",
                    				"itemName": "모험가의 의지[빛]",
                    				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    				"itemType": "액세서리",
                    				"itemTypeDetailId": "691933a06ff114348a7db936fb0aec66",
                    				"itemTypeDetail": "칭호",
                    				"itemAvailableLevel": 1,
                    				"itemRarity": "레어",
                    				"setItemId": null,
                    				"setItemName": null,
                    				"reinforce": 0,
                    				"enchant": {
                    					"reinforceSkill": [{
                    						"jobId": "b9cb48777665de22c006fabaf9a560b3",
                    						"jobName": "아처",
                    						"skills": [{
                    							"skillId": "9cb6f9ed646fa87f9b7680a42ce83d1a",
                    							"name": "익사이팅",
                    							"value": "2"
                    						}]
                    					}]
                    				},
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "JACKET",
                    				"slotName": "상의",
                    				"itemId": "ef2afbf179475a9855d656f751342707",
                    				"itemName": "심연의 편린 상의 : 익사이팅",
                    				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    				"itemType": "방어구",
                    				"itemTypeDetailId": "78ed3499a2adf3caebc3391b32fdccc7",
                    				"itemTypeDetail": "가죽 상의",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "d68bd3e39dd3b4511e6a2a273cdf1c2e",
                    				"setItemName": "심연의 편린 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "SHOULDER",
                    				"slotName": "머리어깨",
                    				"itemId": "205382af59bc111c89e8c1cd308154d1",
                    				"itemName": "심연의 편린 어깨 : 익사이팅",
                    				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    				"itemType": "방어구",
                    				"itemTypeDetailId": "271597ef830b7e0ff8e97e5fc2a7623c",
                    				"itemTypeDetail": "가죽 머리어깨",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "d68bd3e39dd3b4511e6a2a273cdf1c2e",
                    				"setItemName": "심연의 편린 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "PANTS",
                    				"slotName": "하의",
                    				"itemId": "c56b12055a5ead8540340d0d1a9cf77b",
                    				"itemName": "심연의 편린 하의 : 익사이팅",
                    				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    				"itemType": "방어구",
                    				"itemTypeDetailId": "2267f6e837bda673babc358112393d02",
                    				"itemTypeDetail": "가죽 하의",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "d68bd3e39dd3b4511e6a2a273cdf1c2e",
                    				"setItemName": "심연의 편린 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "SHOES",
                    				"slotName": "신발",
                    				"itemId": "f24f90d345c5cdfe7b313b2eded087ce",
                    				"itemName": "심연의 편린 신발 : 익사이팅",
                    				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    				"itemType": "방어구",
                    				"itemTypeDetailId": "47992b63ee0ddbbbb1e3ade4bb31b081",
                    				"itemTypeDetail": "가죽 신발",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "d68bd3e39dd3b4511e6a2a273cdf1c2e",
                    				"setItemName": "심연의 편린 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "WAIST",
                    				"slotName": "허리",
                    				"itemId": "66f91d893a62e4aeea5bbe5cf77fe1c2",
                    				"itemName": "심연의 편린 벨트 : 익사이팅",
                    				"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    				"itemType": "방어구",
                    				"itemTypeDetailId": "8d0f75caa866626d3c6f9a78e1178830",
                    				"itemTypeDetail": "가죽 허리",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "d68bd3e39dd3b4511e6a2a273cdf1c2e",
                    				"setItemName": "심연의 편린 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "AMULET",
                    				"slotName": "목걸이",
                    				"itemId": "893a8160dcb78ed3a89d9d05a291490a",
                    				"itemName": "심연의 편린 목걸이 : 익사이팅",
                    				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    				"itemType": "액세서리",
                    				"itemTypeDetailId": "390e3966118b0c466ce9f8eae45e1629",
                    				"itemTypeDetail": "목걸이",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "d68bd3e39dd3b4511e6a2a273cdf1c2e",
                    				"setItemName": "심연의 편린 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "WRIST",
                    				"slotName": "팔찌",
                    				"itemId": "5cc6d133eb8abb46c2a14111c62dbca3",
                    				"itemName": "심연의 편린 팔찌 : 익사이팅",
                    				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    				"itemType": "액세서리",
                    				"itemTypeDetailId": "80bddf423629c28c7b4459c328fdffaf",
                    				"itemTypeDetail": "팔찌",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "d68bd3e39dd3b4511e6a2a273cdf1c2e",
                    				"setItemName": "심연의 편린 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "RING",
                    				"slotName": "반지",
                    				"itemId": "de73e8e6d0f894cccdbd8458ed4b9ce3",
                    				"itemName": "심연의 편린 반지 : 익사이팅",
                    				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    				"itemType": "액세서리",
                    				"itemTypeDetailId": "b04c7fb9b29b27b91a0a9e5a1822bc8f",
                    				"itemTypeDetail": "반지",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "d68bd3e39dd3b4511e6a2a273cdf1c2e",
                    				"setItemName": "심연의 편린 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "SUPPORT",
                    				"slotName": "보조장비",
                    				"itemId": "d2a5d34f8122647fed7a7255907ff92c",
                    				"itemName": "침식된 심연의 완장 : 익사이팅",
                    				"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    				"itemType": "추가장비",
                    				"itemTypeDetailId": "2fef5d81b7f59f0c75241890a8d941c9",
                    				"itemTypeDetail": "보조장비",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "23ce4855f6e1148a2103e067514768d0",
                    				"setItemName": "침식된 심연의 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "MAGIC_STON",
                    				"slotName": "마법석",
                    				"itemId": "7883507c0f5839f1c1f349ccfaf2c00e",
                    				"itemName": "침식된 심연의 마법석 : 익사이팅",
                    				"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    				"itemType": "추가장비",
                    				"itemTypeDetailId": "fe5f3db78175f5a3196385c688d29681",
                    				"itemTypeDetail": "마법석",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "23ce4855f6e1148a2103e067514768d0",
                    				"setItemName": "침식된 심연의 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}, {
                    				"slotId": "EARRING",
                    				"slotName": "귀걸이",
                    				"itemId": "5a74368e9d0ff4dd7d24e33146bdc3a5",
                    				"itemName": "침식된 심연의 귀걸이 : 익사이팅",
                    				"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    				"itemType": "추가장비",
                    				"itemTypeDetailId": "601834074c49bb0e48cb65a75a8667bc",
                    				"itemTypeDetail": "귀걸이",
                    				"itemAvailableLevel": 95,
                    				"itemRarity": "레전더리",
                    				"setItemId": "23ce4855f6e1148a2103e067514768d0",
                    				"setItemName": "침식된 심연의 익사이팅 세트",
                    				"reinforce": 0,
                    				"amplificationName": null,
                    				"refine": 0
                    			}]
                    		}
                    	}
                    }
                    """;
        }

        @Override
        public String equipment() {
            return """
                    {
                    	"characterId": "a3a1fc24c7c62ae8d0f15e6cdf647642",
                    	"characterName": "T레알돋는당T",
                    	"level": 110,
                    	"jobId": "b9cb48777665de22c006fabaf9a560b3",
                    	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                    	"jobName": "아처",
                    	"jobGrowName": "眞 트래블러",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"equipment": [{
                    		"slotId": "WEAPON",
                    		"slotName": "무기",
                    		"itemId": "bd3c319e4a33fe0660c3cb3bc60ee926",
                    		"itemName": "근원을 삼킨 장궁",
                    		"itemTypeId": "4ffb6f14b86f5c818a925bf58022686e",
                    		"itemType": "무기",
                    		"itemTypeDetailId": "3df98b9ffe982fa49d0a082fe9604a98",
                    		"itemTypeDetail": "장궁",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 12,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 13
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"growInfo": {
                    			"total": {
                    				"damage": 13690,
                    				"buff": 5123,
                    				"level": 266
                    			},
                    			"options": [{
                    				"level": 67,
                    				"expRate": 66.36,
                    				"explain": "아처 모든 스킬 공격력 10% 증가",
                    				"explainDetail": "아처 모든 스킬 공격력 10% 증가",
                    				"damage": 3814,
                    				"default": {
                    					"damage": 1482,
                    					"buff": 504
                    				},
                    				"buff": 1297
                    			}, {
                    				"level": 65,
                    				"expRate": 80.36,
                    				"explain": "아처 1~100Lv 모든 스킬Lv +1",
                    				"explainDetail": "아처 1~100Lv 모든 스킬Lv +1 (각성기 제외)",
                    				"damage": 3774,
                    				"default": {
                    					"damage": 1482,
                    					"buff": 504
                    				},
                    				"buff": 1284
                    			}, {
                    				"level": 68,
                    				"expRate": 40.46,
                    				"explain": "HP MAX +5%",
                    				"explainDetail": "HP MAX +5%",
                    				"damage": 3067,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1278
                    			}, {
                    				"level": 66,
                    				"expRate": 64.12,
                    				"explain": "회피율 +8%",
                    				"explainDetail": "회피율 +8%",
                    				"damage": 3035,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1264
                    			}]
                    		}
                    	}, {
                    		"slotId": "TITLE",
                    		"slotName": "칭호",
                    		"itemId": "7c7315e17d9ec4e8acf6f87f6153c93d",
                    		"itemName": "아라드 패스 2023 시즌3[기간제]",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "691933a06ff114348a7db936fb0aec66",
                    		"itemTypeDetail": "칭호",
                    		"itemAvailableLevel": 1,
                    		"itemRarity": "레어",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 4
                    			}, {
                    				"name": "힘",
                    				"value": 25
                    			}, {
                    				"name": "지능",
                    				"value": 25
                    			}, {
                    				"name": "체력",
                    				"value": 25
                    			}, {
                    				"name": "정신력",
                    				"value": 25
                    			}]
                    		},
                    		"amplificationName": null,
                    		"expiredDate": 1687986000,
                    		"refine": 0
                    	}, {
                    		"slotId": "JACKET",
                    		"slotName": "상의",
                    		"itemId": "8fce56a94be3eb247df238340593a8a4",
                    		"itemName": "엔트 정령의 상의",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "78ed3499a2adf3caebc3391b32fdccc7",
                    		"itemTypeDetail": "가죽 상의",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "물리 공격력",
                    				"value": 70
                    			}, {
                    				"name": "힘",
                    				"value": 40
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"damage": 4685,
                    				"buff": 1291,
                    				"explain": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 공격 속도, 이동 속도 +4%, 캐스팅 속도 +6%\\n- 파티원이 1명일 때 공격 시 30% 확률로 적에게 뇌광폭발 생성",
                    				"explainDetail": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 공격 속도, 이동 속도 +4%, 캐스팅 속도 +6% (최대 4중첩)\\n- 파티원이 1명일 때 공격 시 30% 확률로 적에게 뇌광폭발 생성 (쿨타임 4초, 적의 방어력과 상관없이 총 피해 증가 수치의 500%만큼 피해를 줌)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "51ee9e62bdca253954fba873934c9193",
                    			"itemName": "뇌광 : 천둥을 품은 용심"
                    		},
                    		"growInfo": {
                    			"transfer": true,
                    			"total": {
                    				"damage": 11400,
                    				"buff": 5154,
                    				"level": 265
                    			},
                    			"options": [{
                    				"level": 66,
                    				"expRate": 21.32,
                    				"explain": "5회 공격 시마다 아래 효과 적용\\n- 피해 증가 +456\\n- 물리 방어력 500 감소\\n- 마법 방어력 500 감소",
                    				"explainDetail": "5회 공격 시마다 아래 효과 적용 (최대 10중첩)\\n- 피해 증가 +456\\n- 물리 방어력 500 감소\\n- 마법 방어력 500 감소",
                    				"damage": 3035,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 509
                    				},
                    				"buff": 1303
                    			}, {
                    				"level": 67,
                    				"expRate": 81.66,
                    				"explain": "피격 시 30초 동안 5회 피격 시 파괴되는 슈퍼아머 적용",
                    				"explainDetail": "피격 시 30초 동안 5회 피격 시 파괴되는 슈퍼아머 적용 (쿨타임 5초)",
                    				"damage": 3433,
                    				"default": {
                    					"damage": 1334,
                    					"buff": 499
                    				},
                    				"buff": 1284
                    			}, {
                    				"level": 66,
                    				"expRate": 21.32,
                    				"explain": "가장 높은 속성 강화 50마다 피해 증가 +2182, 모든 속도 6% 증가",
                    				"explainDetail": "가장 높은 속성 강화 50마다 피해 증가 +2182, 모든 속도 6% 증가 (최대 4중첩)",
                    				"damage": 3035,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 499
                    				},
                    				"buff": 1277,
                    				"transfer": true
                    			}, {
                    				"level": 66,
                    				"expRate": 31.28,
                    				"explain": "HP 40% 초과 일 때 물리, 마법 방어력 +25000, 물리, 마법 크리티컬 -8%\\nHP 40% 이하일 때 스킬 공격력 8% 증가, 공격 시 HP 1100, MP 1750 회복",
                    				"explainDetail": "HP 40% 초과 일 때 물리, 마법 방어력 +25000, 물리, 마법 크리티컬 -8%\\nHP 40% 이하일 때 스킬 공격력 8% 증가, 공격 시 HP 1100, MP 1750 회복 (쿨타임 1초)",
                    				"damage": 1897,
                    				"default": {
                    					"damage": 741,
                    					"buff": 504
                    				},
                    				"buff": 1290
                    			}]
                    		}
                    	}, {
                    		"slotId": "SHOULDER",
                    		"slotName": "머리어깨",
                    		"itemId": "480452079c1c187796105e3790e595bc",
                    		"itemName": "매니퓰레이션",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "271597ef830b7e0ff8e97e5fc2a7623c",
                    		"itemTypeDetail": "가죽 머리어깨",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"explain": "스킬 공격력 +1%",
                    			"status": [{
                    				"name": "물리 공격력",
                    				"value": 30
                    			}, {
                    				"name": "마법 공격력",
                    				"value": 30
                    			}, {
                    				"name": "독립 공격력",
                    				"value": 30
                    			}, {
                    				"name": "물리 크리티컬 히트",
                    				"value": "5%"
                    			}, {
                    				"name": "마법 크리티컬 히트",
                    				"value": "5%"
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"damage": 3756,
                    				"buff": 1063,
                    				"explain": "40레벨 스킬 시전 시 40레벨 스킬의 쿨타임 회복속도 15% 증가\\n- 최대 중첩 상태일 때 40레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 수호 계열 스킬에 효과 적용\\n\\n75레벨 스킬 시전 시 75레벨 스킬의 쿨타임 회복속도 15% 증가\\n- 최대 중첩 상태일 때 75레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 조율 계열 스킬에 효과 적용",
                    				"explainDetail": "40레벨 스킬 시전 시 40레벨 스킬의 쿨타임 회복속도 15% 증가 (최대 3중첩)\\n- 최대 중첩 상태일 때 40레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 수호 계열 스킬에 효과 적용\\n\\n75레벨 스킬 시전 시 75레벨 스킬의 쿨타임 회복속도 15% 증가 (최대 3중첩)\\n- 최대 중첩 상태일 때 75레벨 스킬 시전 시 중첩 초기화\\n- 크리에이터 직업은 조율 계열 스킬에 효과 적용"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "be8bb0a24a891647a32229cb1e920051",
                    			"itemName": "교감 : 감싸안는 햇살"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 11709,
                    				"buff": 5163,
                    				"level": 272
                    			},
                    			"options": [{
                    				"level": 68,
                    				"expRate": 53.02,
                    				"explain": "스킬 시전 시 남은 HP의 30%를 소모하여 40초 동안 피해 증가 +5751, 스킬 공격력 10% 증가",
                    				"explainDetail": "스킬 시전 시 남은 HP의 30%를 소모하여 40초 동안 피해 증가 +5751, 스킬 공격력 10% 증가 (쿨타임 30초, 해당 옵션으로 HP가 1% 미만으로 떨어지지 않음)",
                    				"damage": 2788,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 504
                    				},
                    				"buff": 1304
                    			}, {
                    				"level": 68,
                    				"expRate": 43.85,
                    				"explain": "HP 90% 이하일 때 공격 시 HP 2% 회복",
                    				"explainDetail": "HP 90% 이하일 때 공격 시 HP 2% 회복 (쿨타임 5초)",
                    				"damage": 2788,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 504
                    				},
                    				"buff": 1304
                    			}, {
                    				"level": 67,
                    				"expRate": 36.89,
                    				"explain": "스킬 MP 소모량 7% 감소",
                    				"explainDetail": "스킬 MP 소모량 7% 감소",
                    				"damage": 3051,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1271
                    			}, {
                    				"level": 69,
                    				"expRate": 56.24,
                    				"explain": "물리 크리티컬 히트 +5%\\n마법 크리티컬 히트 +5%",
                    				"explainDetail": "물리 크리티컬 히트 +5%\\n마법 크리티컬 히트 +5%",
                    				"damage": 3082,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1284
                    			}]
                    		}
                    	}, {
                    		"slotId": "PANTS",
                    		"slotName": "하의",
                    		"itemId": "d3e8e68964c5fd2e55f9aeedcc49d9fa",
                    		"itemName": "언리밋 사이버네틱",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "2267f6e837bda673babc358112393d02",
                    		"itemTypeDetail": "가죽 하의",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "물리 공격력",
                    				"value": 70
                    			}, {
                    				"name": "힘",
                    				"value": 40
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"growInfo": {
                    			"total": {
                    				"damage": 15076,
                    				"buff": 4999,
                    				"level": 266
                    			},
                    			"options": [{
                    				"level": 67,
                    				"expRate": 49.14,
                    				"explain": "장착 시 20초마다 자신의 주변 250px의 적을 5초 동안 저주 상태로 만듦\\n대쉬 1초 마다 저주 적용 범위 50px 증가, HP 0.5% 감소\\n대쉬 상태가 아닐 때 1초마다 저주 적용 범위 50px 감소",
                    				"explainDetail": "장착 시 20초마다 자신의 주변 250px의 적을 5초 동안 저주 상태로 만듦\\n대쉬 1초 마다 저주 적용 범위 50px 증가 (최대 250px 증가), HP 0.5% 감소 (해당 옵션으로 HP가 1% 미만으로 감소하지 않음)\\n대쉬 상태가 아닐 때 1초마다 저주 적용 범위 50px 감소 (250px 미만으로 감소하지 않음)",
                    				"damage": 4507,
                    				"default": {
                    					"damage": 1751,
                    					"buff": 480
                    				},
                    				"buff": 1235
                    			}, {
                    				"level": 66,
                    				"expRate": 85.83,
                    				"explain": "HP가 70% 이상일 때 스킬 쿨타임 10% 감소\\nHP가 50% 이상 70% 미만일 때 스킬 쿨타임 12% 감소\\nHP가 50% 미만일 때 스킬 쿨타임 15% 감소",
                    				"explainDetail": "HP가 70% 이상일 때 스킬 쿨타임 10% 감소 (각성기 제외)\\nHP가 50% 이상 70% 미만일 때 스킬 쿨타임 12% 감소 (각성기 제외)\\nHP가 50% 미만일 때 스킬 쿨타임 15% 감소 (각성기 제외)",
                    				"damage": 4484,
                    				"default": {
                    					"damage": 1751,
                    					"buff": 480
                    				},
                    				"buff": 1229
                    			}, {
                    				"level": 64,
                    				"expRate": 53.72,
                    				"explain": "이동 속도 +8%",
                    				"explainDetail": "이동 속도 +8%",
                    				"damage": 3003,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1251
                    			}, {
                    				"level": 69,
                    				"expRate": 30,
                    				"explain": "회피율 +8%",
                    				"explainDetail": "회피율 +8%",
                    				"damage": 3082,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1284
                    			}]
                    		}
                    	}, {
                    		"slotId": "SHOES",
                    		"slotName": "신발",
                    		"itemId": "7d66f7dd960933be4e1e5abaa9f32f45",
                    		"itemName": "엑셀러레이터",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "47992b63ee0ddbbbb1e3ade4bb31b081",
                    		"itemTypeDetail": "가죽 신발",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "힘",
                    				"value": 90
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"growInfo": {
                    			"total": {
                    				"damage": 13114,
                    				"buff": 5129,
                    				"level": 280
                    			},
                    			"options": [{
                    				"level": 70,
                    				"expRate": 95.03,
                    				"explain": "MP MAX +4196\\n남은 MP 20000마다 피해 증가 +988\\nMP MAX가 200000 이상일 때 스킬 공격력 5% 증가",
                    				"explainDetail": "MP MAX +4196\\n남은 MP 20000마다 피해 증가 +988 (최대 10중첩)\\nMP MAX가 200000 이상일 때 스킬 공격력 5% 증가",
                    				"damage": 3593,
                    				"default": {
                    					"damage": 1347,
                    					"buff": 470
                    				},
                    				"buff": 1254
                    			}, {
                    				"level": 70,
                    				"expRate": 40.31,
                    				"explain": "스킬 쿨타임 15% 감소",
                    				"explainDetail": "스킬 쿨타임 15% 감소 (각성기 제외)",
                    				"damage": 3593,
                    				"default": {
                    					"damage": 1347,
                    					"buff": 470
                    				},
                    				"buff": 1254
                    			}, {
                    				"level": 70,
                    				"expRate": 12.76,
                    				"explain": "MP 1분당 348 회복",
                    				"explainDetail": "MP 1분당 348 회복",
                    				"damage": 3162,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1317
                    			}, {
                    				"level": 70,
                    				"expRate": 30.26,
                    				"explain": "공격 시 MP 3500 회복",
                    				"explainDetail": "공격 시 MP 3500 회복 (쿨타임 1초)",
                    				"damage": 2766,
                    				"default": {
                    					"damage": 1037,
                    					"buff": 489
                    				},
                    				"buff": 1304
                    			}]
                    		}
                    	}, {
                    		"slotId": "WAIST",
                    		"slotName": "허리",
                    		"itemId": "32ca7d7da53d0e9b2aafacfb5ec57dcf",
                    		"itemName": "파워 플랜트",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "8d0f75caa866626d3c6f9a78e1178830",
                    		"itemTypeDetail": "가죽 허리",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"explain": "스킬 공격력 +2%",
                    			"status": [{
                    				"name": "힘",
                    				"value": 30
                    			}, {
                    				"name": "지능",
                    				"value": 30
                    			}, {
                    				"name": "체력",
                    				"value": 30
                    			}, {
                    				"name": "정신력",
                    				"value": 30
                    			}, {
                    				"name": "물리 공격력",
                    				"value": 6
                    			}, {
                    				"name": "마법 공격력",
                    				"value": 6
                    			}, {
                    				"name": "독립 공격력",
                    				"value": 6
                    			}, {
                    				"name": "물리 크리티컬 히트",
                    				"value": "3%"
                    			}, {
                    				"name": "마법 크리티컬 히트",
                    				"value": "3%"
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"buff": 1291,
                    				"explain": "공격 시 적을 20초 동안 출혈, 화상 중 하나의 상태로 만듦\\nMP가 10% 이상일 때 모든 속성 강화 +25\\n\\n구속 내성 +10%\\n저주 내성 +10%\\n둔화 내성 +10%",
                    				"explainDetail": "공격 시 적을 20초 동안 출혈, 화상 중 하나의 상태로 만듦 (쿨타임 5초)\\nMP가 10% 이상일 때 모든 속성 강화 +25\\n\\n구속 내성 +10%\\n저주 내성 +10%\\n둔화 내성 +10%"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "3ce72504c5b4353c5fb82a74e7ddebaf",
                    			"itemName": "포식 : 조여오는 올가미"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 16699,
                    				"buff": 5102,
                    				"level": 270
                    			},
                    			"options": [{
                    				"level": 71,
                    				"expRate": 80.91,
                    				"explain": "HP 50% 미만일 때 쿨타임 회복 속도 15% 증가, 스킬 공격력 6% 증가",
                    				"explainDetail": "HP 50% 미만일 때 쿨타임 회복 속도 15% 증가 (각성기 제외), 스킬 공격력 6% 증가",
                    				"damage": 5417,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 490
                    				},
                    				"buff": 1313
                    			}, {
                    				"level": 68,
                    				"expRate": 21.67,
                    				"explain": "HP 50% 미만일 때 공격 시 HP, MP 2000 회복",
                    				"explainDetail": "HP 50% 미만일 때 공격 시 HP, MP 2000 회복 (쿨타임 1초)",
                    				"damage": 5228,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 490
                    				},
                    				"buff": 1267
                    			}, {
                    				"level": 65,
                    				"expRate": 78.65,
                    				"explain": "스킬 MP 소모량 7% 감소",
                    				"explainDetail": "스킬 MP 소모량 7% 감소",
                    				"damage": 3019,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1258
                    			}, {
                    				"level": 66,
                    				"expRate": 84.12,
                    				"explain": "공격 속도 +8%\\n캐스팅 속도 +12%",
                    				"explainDetail": "공격 속도 +8%\\n캐스팅 속도 +12%",
                    				"damage": 3035,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1264
                    			}]
                    		}
                    	}, {
                    		"slotId": "AMULET",
                    		"slotName": "목걸이",
                    		"itemId": "bbd070d70a5168fabecaef168427487d",
                    		"itemName": "검은 별",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "390e3966118b0c466ce9f8eae45e1629",
                    		"itemTypeDetail": "목걸이",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 33
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"ispinsInfo": {
                    			"options": [{
                    				"buff": 1405,
                    				"explain": "물리, 마법 방어력 총 합이 90000 이상일 때 피해 증가 +7815\\n\\n물리, 마법 방어력 +14000\\n\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 -2800",
                    				"explainDetail": "물리, 마법 방어력 총 합이 90000 이상일 때 피해 증가 +7815\\n\\n물리, 마법 방어력 +14000\\n\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 -2800 (최대 5중첩)\\n- 2초 동안 피격되지 않을 시 물리, 마법 방어력 감소 중첩 수 1 감소"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "3b62a91abc21df1a2609af53cb15d432",
                    			"itemName": "천계 연합군 : 모든 이를 위한 기도"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 12895,
                    				"buff": 5067,
                    				"level": 274
                    			},
                    			"options": [{
                    				"level": 69,
                    				"expRate": 20.44,
                    				"explain": "HP가 80% 이상일 때 피해 증가 +1156\\nHP가 50% 이상 80% 미만일 때 피해 증가 +6935\\nHP가 50% 미만일 때 피해 증가 +6935, 스킬 공격력 7% 증가",
                    				"explainDetail": "HP가 80% 이상일 때 피해 증가 +1156\\nHP가 50% 이상 80% 미만일 때 피해 증가 +6935\\nHP가 50% 미만일 때 피해 증가 +6935, 스킬 공격력 7% 증가",
                    				"damage": 2802,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 475
                    				},
                    				"buff": 1236
                    			}, {
                    				"level": 68,
                    				"expRate": 23.77,
                    				"explain": "HP가 50% 미만일 때 모든 속도 +20%",
                    				"explainDetail": "HP가 50% 미만일 때 모든 속도 +20%",
                    				"damage": 2788,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 475
                    				},
                    				"buff": 1230
                    			}, {
                    				"level": 68,
                    				"expRate": 45.79,
                    				"explain": "적중률 +10%",
                    				"explainDetail": "적중률 +10%",
                    				"damage": 3067,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1278
                    			}, {
                    				"level": 69,
                    				"expRate": 84.61,
                    				"explain": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"explainDetail": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"damage": 4238,
                    				"default": {
                    					"damage": 1630,
                    					"buff": 509
                    				},
                    				"buff": 1323
                    			}]
                    		}
                    	}, {
                    		"slotId": "WRIST",
                    		"slotName": "팔찌",
                    		"itemId": "fd19e43acb9bf998f94df0db7729be06",
                    		"itemName": "억제된 마력의 팔찌",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "80bddf423629c28c7b4459c328fdffaf",
                    		"itemTypeDetail": "팔찌",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 33
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"ispinsInfo": {
                    			"options": [{
                    				"buff": 1405,
                    				"explain": "HP MAX, MP MAX 총 합이 220000 이상일 때 피해 증가 +7815\\n\\nHP MAX +1200, MP MAX +1890\\n\\n피격으로 HP가 1% 이상 감소 시 HP MAX -240, MP MAX -378",
                    				"explainDetail": "HP MAX, MP MAX 총 합이 220000 이상일 때 피해 증가 +7815\\n\\nHP MAX +1200, MP MAX +1890\\n\\n피격으로 HP가 1% 이상 감소 시 HP MAX -240, MP MAX -378 (최대 5중첩)\\n- 2초 동안 피격되지 않을 시 HP MAX, MP MAX 감소 중첩 수 1 감소"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "cb41d2e497f1a7ac1ae2711670da9861",
                    			"itemName": "천계 연합군 : 꺾이지 않는 의지"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 14312,
                    				"buff": 5018,
                    				"level": 261
                    			},
                    			"options": [{
                    				"level": 68,
                    				"expRate": 95.96,
                    				"explain": "던전 입장 시 HP를 99% 소모하고 사용가능한 최대 HP의 99% 제한\\n피격 시 HP 대신 MP를 먼저 소모\\n장비 또는 HP 회복 스킬로 인한 HP 회복량의 25% 만큼 MP 회복",
                    				"explainDetail": "던전 입장 시 HP를 99% 소모하고 사용가능한 최대 HP의 99% 제한 (제한된 수치를 초과하여 HP 회복 불가)\\n피격 시 HP 대신 MP를 먼저 소모\\n장비 또는 HP 회복 스킬로 인한 HP 회복량의 25% 만큼 MP 회복",
                    				"damage": 4182,
                    				"default": {
                    					"damage": 1616,
                    					"buff": 490
                    				},
                    				"buff": 1267
                    			}, {
                    				"level": 66,
                    				"expRate": 31.12,
                    				"explain": "MP MAX +4196\\n던전 입장 시 피해 증가 +6829, 스킬 공격력 15% 증가",
                    				"explainDetail": "MP MAX +4196\\n던전 입장 시 피해 증가 +6829, 스킬 공격력 15% 증가",
                    				"damage": 4139,
                    				"default": {
                    					"damage": 1616,
                    					"buff": 490
                    				},
                    				"buff": 1254
                    			}, {
                    				"level": 63,
                    				"expRate": 10.87,
                    				"explain": "MP 1분당 348 회복",
                    				"explainDetail": "MP 1분당 348 회복",
                    				"damage": 2988,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1246
                    			}, {
                    				"level": 64,
                    				"expRate": 27.01,
                    				"explain": "물리 방어력 +7000\\n마법 방어력 +7000",
                    				"explainDetail": "물리 방어력 +7000\\n마법 방어력 +7000",
                    				"damage": 3003,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1251
                    			}]
                    		}
                    	}, {
                    		"slotId": "RING",
                    		"slotName": "반지",
                    		"itemId": "639dedaa8b9218023da86ff44f80662f",
                    		"itemName": "골렘의 중추석 반지",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "b04c7fb9b29b27b91a0a9e5a1822bc8f",
                    		"itemTypeDetail": "반지",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 33
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"ispinsInfo": {
                    			"options": [{
                    				"buff": 1518,
                    				"explain": "피격으로 HP가 1% 이상 감소 시 MP 5000 감소\\n\\n현재 MP 4% 마다 피해 증가 +1876\\n\\n스킬로 인해 소모한 MP 5000 마다 10초동안 장비로 인한 MP 회복 효과 10% 증가",
                    				"explainDetail": "피격으로 HP가 1% 이상 감소 시 MP 5000 감소 (쿨타임 0.5초)\\n\\n현재 MP 4% 마다 피해 증가 +1876 (최대 5중첩)\\n\\n스킬로 인해 소모한 MP 5000 마다 10초동안 장비로 인한 MP 회복 효과 10% 증가 (최대 1중첩)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "1fd082cd996a9eb02645be09c5e89d41",
                    			"itemName": "흑룡 : 끝없는 암흑의 굴레"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 16406,
                    				"buff": 4962,
                    				"level": 264
                    			},
                    			"options": [{
                    				"level": 67,
                    				"expRate": 57.45,
                    				"explain": "HP가 30% 미만일 때 2초마다 피해 증가 +1144\\n피해 증가 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 피해 증가 중첩 수 1 감소",
                    				"explainDetail": "HP가 30% 미만일 때 2초마다 피해 증가 +1144 (최대 3중첩)\\n피해 증가 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 피해 증가 중첩 수 1 감소 (쿨타임 1초)",
                    				"damage": 5201,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 475
                    				},
                    				"buff": 1223
                    			}, {
                    				"level": 64,
                    				"expRate": 30.09,
                    				"explain": "HP가 30% 미만일 때 2초마다 물리, 마법 방어력 +4000\\n물리, 마법 방어력 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 중첩 수 1 감소",
                    				"explainDetail": "HP가 30% 미만일 때 2초마다 물리, 마법 방어력 +4000 (최대 3중첩)\\n물리, 마법 방어력 3중첩일 때 스킬 공격력 4% 증가\\n피격으로 HP가 1% 이상 감소 시 물리, 마법 방어력 중첩 수 1 감소 (쿨타임 1초)",
                    				"damage": 5120,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 475
                    				},
                    				"buff": 1204
                    			}, {
                    				"level": 64,
                    				"expRate": 76.59,
                    				"explain": "적중률 +10%",
                    				"explainDetail": "적중률 +10%",
                    				"damage": 3003,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1251
                    			}, {
                    				"level": 69,
                    				"expRate": 71.8,
                    				"explain": "회피율 +8%",
                    				"explainDetail": "회피율 +8%",
                    				"damage": 3082,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1284
                    			}]
                    		}
                    	}, {
                    		"slotId": "SUPPORT",
                    		"slotName": "보조장비",
                    		"itemId": "7f807013fd7ffcb5cd7cfd82bf984c52",
                    		"itemName": "파괴된 생명",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "2fef5d81b7f59f0c75241890a8d941c9",
                    		"itemTypeDetail": "보조장비",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "물리 공격력",
                    				"value": 110
                    			}, {
                    				"name": "마법 공격력",
                    				"value": 110
                    			}, {
                    				"name": "독립 공격력",
                    				"value": 110
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"dimensionCloisterInfo": {
                    			"options": [{
                    				"damage": 2350,
                    				"buff": 1178,
                    				"explain": "스킬 공격력 3% 증가\\n\\n1~40Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가",
                    				"explainDetail": "스킬 공격력 3% 증가\\n\\n1~40Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가 (각성기 제외)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "56422d257499f7c60c7dad02d0f43db2",
                    			"itemName": "심연의 결집 : 정의되지 않는 존재"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 11766,
                    				"buff": 5090,
                    				"level": 272
                    			},
                    			"options": [{
                    				"level": 71,
                    				"expRate": 30.9,
                    				"explain": "HP 50% 미만일 때 아래의 효과 적용\\n- 물리, 마법 방어력 +7000\\n- 피해 증가 +5958\\n- 스킬 공격력 7% 증가\\n피격 데미지 10% 증가",
                    				"explainDetail": "HP 50% 미만일 때 아래의 효과 적용\\n- 물리, 마법 방어력 +7000\\n- 피해 증가 +5958\\n- 스킬 공격력 7% 증가\\n피격 데미지 10% 증가",
                    				"damage": 2889,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 485
                    				},
                    				"buff": 1300
                    			}, {
                    				"level": 66,
                    				"expRate": 20.97,
                    				"explain": "HP 50% 미만일 때 물리, 마법 크리티컬 히트 +20%\\nHP 10% 이상일 때 공격 시 HP 5% 감소",
                    				"explainDetail": "HP 50% 미만일 때 물리, 마법 크리티컬 히트 +20%\\nHP 10% 이상일 때 공격 시 HP 5% 감소 (해당 옵션으로 HP가 5% 미만으로 감소하지 않음, 쿨타임 10초)",
                    				"damage": 2759,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 485
                    				},
                    				"buff": 1241
                    			}, {
                    				"level": 68,
                    				"expRate": 30.93,
                    				"explain": "공격 속도 +8%\\n캐스팅 속도 +12%",
                    				"explainDetail": "공격 속도 +8%\\n캐스팅 속도 +12%",
                    				"damage": 3067,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1278
                    			}, {
                    				"level": 67,
                    				"expRate": 73.31,
                    				"explain": "적중률 +10%",
                    				"explainDetail": "적중률 +10%",
                    				"damage": 3051,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1271
                    			}]
                    		}
                    	}, {
                    		"slotId": "MAGIC_STON",
                    		"slotName": "마법석",
                    		"itemId": "5e597f850be761e18324dd8627f31855",
                    		"itemName": "승리가 약속된 시간",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "fe5f3db78175f5a3196385c688d29681",
                    		"itemTypeDetail": "마법석",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 30
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"growInfo": {
                    			"total": {
                    				"damage": 14748,
                    				"buff": 5063,
                    				"level": 279
                    			},
                    			"options": [{
                    				"level": 69,
                    				"expRate": 46.06,
                    				"explain": "HP가 50% 미만일 때 물리방어율과 마법방어율의 합 7% 마다 피해 증가 +1156\\n피해 증가 효과가 최대 중첩일 때 스킬 공격력 8% 증가",
                    				"explainDetail": "HP가 50% 미만일 때 물리방어율과 마법방어율의 합 7% 마다 피해 증가 +1156 (최대 10중첩)\\n피해 증가 효과가 최대 중첩일 때 스킬 공격력 8% 증가",
                    				"damage": 4204,
                    				"default": {
                    					"damage": 1616,
                    					"buff": 466
                    				},
                    				"buff": 1211
                    			}, {
                    				"level": 69,
                    				"expRate": 80.73,
                    				"explain": "HP가 50% 미만이면서 물리방어율과 마법방어율의 합이 70% 이상일 때 20초마다 5회 피격 시 파괴되는 슈퍼아머 부여",
                    				"explainDetail": "HP가 50% 미만이면서 물리방어율과 마법방어율의 합이 70% 이상일 때 20초마다 5회 피격 시 파괴되는 슈퍼아머 부여",
                    				"damage": 4204,
                    				"default": {
                    					"damage": 1616,
                    					"buff": 466
                    				},
                    				"buff": 1211
                    			}, {
                    				"level": 70,
                    				"expRate": 58.86,
                    				"explain": "MP MAX +945",
                    				"explainDetail": "MP MAX +945",
                    				"damage": 3162,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1317
                    			}, {
                    				"level": 71,
                    				"expRate": 24.29,
                    				"explain": "이동 속도 +8%",
                    				"explainDetail": "이동 속도 +8%",
                    				"damage": 3178,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1324
                    			}]
                    		}
                    	}, {
                    		"slotId": "EARRING",
                    		"slotName": "귀걸이",
                    		"itemId": "c0ef77f4ab5c758765326bf5a8b8f23f",
                    		"itemName": "폭풍을 삼킨 에너지",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "601834074c49bb0e48cb65a75a8667bc",
                    		"itemTypeDetail": "귀걸이",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 10,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 11
                    			}, {
                    				"name": "힘",
                    				"value": 50
                    			}, {
                    				"name": "지능",
                    				"value": 50
                    			}, {
                    				"name": "체력",
                    				"value": 50
                    			}, {
                    				"name": "정신력",
                    				"value": 50
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0,
                    		"dimensionCloisterInfo": {
                    			"options": [{
                    				"damage": 2350,
                    				"buff": 1178,
                    				"explain": "스킬 공격력 3% 증가\\n\\n75~80Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가",
                    				"explainDetail": "스킬 공격력 3% 증가\\n\\n75~80Lv 스킬 범위 15% 증가\\n모든 스킬 범위 5% 증가 (각성기 제외)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "1ba6221619f88691ed8f0c3adba4db7a",
                    			"itemName": "심연의 결집 : 무정형의 힘"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 13648,
                    				"buff": 5170,
                    				"level": 288
                    			},
                    			"options": [{
                    				"level": 72,
                    				"expRate": 24.42,
                    				"explain": "소모품으로 인한 MP 회복 효과 50% 증가\\nHP 50% 이하일 때 스킬 공격력 10% 증가, 모든 속성 강화 +15",
                    				"explainDetail": "소모품으로 인한 MP 회복 효과 50% 증가\\nHP 50% 이하일 때 스킬 공격력 10% 증가, 모든 속성 강화 +15",
                    				"damage": 3629,
                    				"default": {
                    					"damage": 1347,
                    					"buff": 466
                    				},
                    				"buff": 1254
                    			}, {
                    				"level": 73,
                    				"expRate": 27.69,
                    				"explain": "스킬 MP 소모량 20% 감소\\nMP MAX +4196",
                    				"explainDetail": "스킬 MP 소모량 20% 감소\\nMP MAX +4196",
                    				"damage": 3647,
                    				"default": {
                    					"damage": 1347,
                    					"buff": 466
                    				},
                    				"buff": 1261
                    			}, {
                    				"level": 71,
                    				"expRate": 34.93,
                    				"explain": "적중률 +10%",
                    				"explainDetail": "적중률 +10%",
                    				"damage": 3178,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1324
                    			}, {
                    				"level": 72,
                    				"expRate": 95.61,
                    				"explain": "회피율 +8%",
                    				"explainDetail": "회피율 +8%",
                    				"damage": 3194,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1331
                    			}]
                    		}
                    	}],
                    	"setItemInfo": []
                    }
                    """;
        }

        @Override
        public String creature() {
            return """
                    {
                    	"characterId": "a3a1fc24c7c62ae8d0f15e6cdf647642",
                    	"characterName": "T레알돋는당T",
                    	"level": 110,
                    	"jobId": "b9cb48777665de22c006fabaf9a560b3",
                    	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                    	"jobName": "아처",
                    	"jobGrowName": "眞 트래블러",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"creature": {
                    		"itemId": "f5c87c63bbdaa5413a105640cafbc30a",
                    		"itemName": "순백의 나비 공주",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"artifact": []
                    	}
                    }
                    """;
        }

        @Override
        public String avatar() {
            return """
                    {
                    	"characterId": "a3a1fc24c7c62ae8d0f15e6cdf647642",
                    	"characterName": "T레알돋는당T",
                    	"level": 110,
                    	"jobId": "b9cb48777665de22c006fabaf9a560b3",
                    	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                    	"jobName": "아처",
                    	"jobGrowName": "眞 트래블러",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"avatar": [{
                    		"slotId": "HEADGEAR",
                    		"slotName": "모자 아바타",
                    		"itemId": "2defdc2422ff3c61f5ac1af583e79459",
                    		"itemName": "레어 모자 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "캐스팅 속도 14.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "붉은빛",
                    			"itemName": "화려한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "붉은빛",
                    			"itemName": "화려한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "HAIR",
                    		"slotName": "머리 아바타",
                    		"itemId": "970d6d278c4fa2d2e8c9e74f33a46ae1",
                    		"itemName": "레어 머리 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "525048ce95a353c395045c2ea2753857",
                    			"itemName": "헤드라이너의 웨이비 헤어[A타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "캐스팅 속도 14.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "붉은빛",
                    			"itemName": "화려한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "붉은빛",
                    			"itemName": "화려한 붉은빛 엠블렘[힘]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "FACE",
                    		"slotName": "얼굴 아바타",
                    		"itemId": "2ee5151fe13188a556ea594222187bb5",
                    		"itemName": "레어 얼굴 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "공격 속도 6.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "노란빛",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "노란빛",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "JACKET",
                    		"slotName": "상의 아바타",
                    		"itemId": "cb12bb026990e7595103bb214c31960d",
                    		"itemName": "레어 상의 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "여행자의 직감 스킬Lv +1",
                    		"emblems": [{
                    			"slotNo": 2,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 녹색빛 엠블렘[물리크리티컬]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 3,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 녹색빛 엠블렘[물리크리티컬]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "PANTS",
                    		"slotName": "하의 아바타",
                    		"itemId": "37b761ce1173ea36d816e642aba5ba7b",
                    		"itemName": "레어 하의 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "9f6a4ade21765d65a9692d6dae49fca3",
                    			"itemName": "순백의 서약 팬츠와 스타킹[A타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "MP MAX 400 증가",
                    		"emblems": [{
                    			"slotNo": 2,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 녹색빛 엠블렘[물리크리티컬]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 3,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 녹색빛 엠블렘[물리크리티컬]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "SHOES",
                    		"slotName": "신발 아바타",
                    		"itemId": "0371784066078365c89897de3cacb6d8",
                    		"itemName": "레어 신발 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "f9c9ec5e868f6a4655e5cec5c8b8e31f",
                    			"itemName": "트래블러의 슬림 롱 부츠"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "힘 55 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "푸른빛",
                    			"itemName": "화려한 푸른빛 엠블렘[이동속도]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "푸른빛",
                    			"itemName": "화려한 푸른빛 엠블렘[이동속도]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "BREAST",
                    		"slotName": "목가슴 아바타",
                    		"itemId": "3aaebf23928e11dafd00150b320e95cd",
                    		"itemName": "레어 목가슴 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "공격 속도 6.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "노란빛",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "노란빛",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "WAIST",
                    		"slotName": "허리 아바타",
                    		"itemId": "88b6d64f0766484b2cdcc898eea4dc18",
                    		"itemName": "레어 허리 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "fd988afaa16372749d30987b513b5dc0",
                    			"itemName": "트래블러의 가터 벨트"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "명속성 저항 35 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "푸른빛",
                    			"itemName": "화려한 푸른빛 엠블렘[이동속도]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "푸른빛",
                    			"itemName": "화려한 푸른빛 엠블렘[이동속도]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "SKIN",
                    		"slotName": "스킨 아바타",
                    		"itemId": "5e3801619172d842c08a4bc4d1d1aa34",
                    		"itemName": "진 트래블러의 순백의 피부[A타입]",
                    		"itemRarity": "커먼",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "물리 방어력 1000 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "다색",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "다색",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "AURORA",
                    		"slotName": "오라 아바타",
                    		"itemId": "a22e17aab329c48681d52a0136587422",
                    		"itemName": "축복의 연인",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": null,
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "다색",
                    			"itemName": "찬란한 녹색빛 엠블렘[물리크리티컬]",
                    			"itemRarity": "유니크"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "다색",
                    			"itemName": "찬란한 녹색빛 엠블렘[물리크리티컬]",
                    			"itemRarity": "유니크"
                    		}]
                    	}, {
                    		"slotId": "WEAPON",
                    		"slotName": "무기 아바타",
                    		"itemId": "95efa7c12ebfbc9c5fe32541a280e600",
                    		"itemName": "메탈 컴파운드 보우",
                    		"itemRarity": "커먼",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": null,
                    		"emblems": []
                    	}]
                    }
                    """;
        }
    }

    // 뮤즈
    // Les_Paul
    private static class Character4 implements Character {
        public String characterId = "87c7ed846938a184f71b338af88bdf4a";
        @Override
        public String status() {
            return """
                {
                	"characterId": "87c7ed846938a184f71b338af88bdf4a",
                	"characterName": "Les_Paul",
                	"level": 110,
                	"jobId": "b9cb48777665de22c006fabaf9a560b3",
                	"jobGrowId": "37495b941da3b1661bc900e68ef3b2c6",
                	"jobName": "아처",
                	"jobGrowName": "眞 뮤즈",
                	"adventureName": "레알돋는당",
                	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                	"guildName": "개시바",
                	"buff": [{
                		"name": "모험단 버프",
                		"level": 38,
                		"status": [{
                			"name": "힘",
                			"value": 280
                		}, {
                			"name": "지능",
                			"value": 280
                		}, {
                			"name": "체력",
                			"value": 280
                		}, {
                			"name": "정신력",
                			"value": 280
                		}]
                	}, {
                		"name": "무제한 길드능력치",
                		"status": [{
                			"name": "힘",
                			"value": 60
                		}, {
                			"name": "지능",
                			"value": 60
                		}, {
                			"name": "체력",
                			"value": 60
                		}, {
                			"name": "정신력",
                			"value": 60
                		}]
                	}, {
                		"name": "기간제 길드능력치",
                		"status": [{
                			"name": "힘",
                			"value": 40
                		}, {
                			"name": "지능",
                			"value": 40
                		}, {
                			"name": "체력",
                			"value": 40
                		}, {
                			"name": "정신력",
                			"value": 40
                		}]
                	}],
                	"status": [{
                		"name": "HP",
                		"value": 144907
                	}, {
                		"name": "MP",
                		"value": 78740
                	}, {
                		"name": "물리 방어율",
                		"value": 42.3
                	}, {
                		"name": "마법 방어율",
                		"value": 43.8
                	}, {
                		"name": "힘",
                		"value": 3977
                	}, {
                		"name": "지능",
                		"value": 4293
                	}, {
                		"name": "체력",
                		"value": 4823
                	}, {
                		"name": "정신력",
                		"value": 7029
                	}, {
                		"name": "물리 공격",
                		"value": 1909
                	}, {
                		"name": "마법 공격",
                		"value": 1842
                	}, {
                		"name": "물리 크리티컬",
                		"value": 35.4
                	}, {
                		"name": "마법 크리티컬",
                		"value": 77.4
                	}, {
                		"name": "독립 공격",
                		"value": 2605
                	}, {
                		"name": "공격 속도",
                		"value": 45.4
                	}, {
                		"name": "캐스팅 속도",
                		"value": 37.5
                	}, {
                		"name": "이동 속도",
                		"value": 43.4
                	}, {
                		"name": "모험가 명성",
                		"value": 44430
                	}, {
                		"name": "적중률",
                		"value": 25.5
                	}, {
                		"name": "회피율",
                		"value": 13
                	}, {
                		"name": "HP 회복량",
                		"value": 11164
                	}, {
                		"name": "MP 회복량",
                		"value": 23145
                	}, {
                		"name": "경직도",
                		"value": 0
                	}, {
                		"name": "히트리커버리",
                		"value": 756
                	}, {
                		"name": "화속성 강화",
                		"value": 168
                	}, {
                		"name": "화속성 저항",
                		"value": 41
                	}, {
                		"name": "수속성 강화",
                		"value": 153
                	}, {
                		"name": "수속성 저항",
                		"value": -14
                	}, {
                		"name": "명속성 강화",
                		"value": 153
                	}, {
                		"name": "명속성 저항",
                		"value": -14
                	}, {
                		"name": "암속성 강화",
                		"value": 153
                	}, {
                		"name": "암속성 저항",
                		"value": -14
                	}, {
                		"name": "물리 방어",
                		"value": 73295
                	}, {
                		"name": "마법 방어",
                		"value": 77983
                	}, {
                		"name": "피해 증가",
                		"value": 234272
                	}, {
                		"name": "버프력",
                		"value": 100228
                	}, {
                		"name": "피해 증가 %",
                		"value": 40
                	}, {
                		"name": "버프력 %",
                		"value": 8
                	}, {
                		"name": "스킬 공격력 증가",
                		"value": 981.8
                	}, {
                		"name": "쿨타임 감소",
                		"value": 0
                	}, {
                		"name": "쿨타임 회복속도 증가",
                		"value": 0
                	}, {
                		"name": "쿨타임 감소 실적용",
                		"value": 0
                	}, {
                		"name": "데미지 증가",
                		"value": 0
                	}, {
                		"name": "크리티컬 데미지 증가",
                		"value": 0
                	}, {
                		"name": "추가 데미지 증가",
                		"value": 0
                	}, {
                		"name": "모든 공격력 증가",
                		"value": 0
                	}, {
                		"name": "물리 공격력 증가",
                		"value": 0
                	}, {
                		"name": "마법 공격력 증가",
                		"value": 0
                	}, {
                		"name": "독립 공격력 증가",
                		"value": 0
                	}, {
                		"name": "힘 증가",
                		"value": 0
                	}, {
                		"name": "지능 증가",
                		"value": 0
                	}, {
                		"name": "지속피해",
                		"value": 0
                	}, {
                		"name": "물리 피해 감소",
                		"value": 15
                	}, {
                		"name": "마법 피해 감소",
                		"value": 15
                	}, {
                		"name": "출혈 데미지 전환",
                		"value": 0
                	}, {
                		"name": "중독 데미지 전환",
                		"value": 0
                	}, {
                		"name": "화상 데미지 전환",
                		"value": 0
                	}, {
                		"name": "감전 데미지 전환",
                		"value": 0
                	}, {
                		"name": "출혈 내성",
                		"value": -10
                	}, {
                		"name": "중독 내성",
                		"value": -10
                	}, {
                		"name": "화상 내성",
                		"value": -10
                	}, {
                		"name": "감전 내성",
                		"value": -10
                	}, {
                		"name": "빙결 내성",
                		"value": -10
                	}, {
                		"name": "둔화 내성",
                		"value": -10
                	}, {
                		"name": "기절 내성",
                		"value": -10
                	}, {
                		"name": "저주 내성",
                		"value": -10
                	}, {
                		"name": "암흑 내성",
                		"value": -10
                	}, {
                		"name": "석화 내성",
                		"value": -10
                	}, {
                		"name": "수면 내성",
                		"value": -10
                	}, {
                		"name": "혼란 내성",
                		"value": -10
                	}, {
                		"name": "구속 내성",
                		"value": -10
                	}]
                }
                """;
        }

        @Override
        public String buff() {
            return """
                    {
                    	"characterId": "87c7ed846938a184f71b338af88bdf4a",
                    	"characterName": "Les_Paul",
                    	"level": 110,
                    	"jobId": "b9cb48777665de22c006fabaf9a560b3",
                    	"jobGrowId": "37495b941da3b1661bc900e68ef3b2c6",
                    	"jobName": "아처",
                    	"jobGrowName": "眞 뮤즈",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"skill": {
                    		"buff": {
                    			"skillInfo": {
                    				"skillId": "78be08a3f8c834d3b06fa20c6a08c5a5",
                    				"name": "러블리 템포",
                    				"option": {
                    					"level": 15,
                    					"desc": "버프 적용 범위 : {value1}px\\n지속시간 : {value2}초\\n적중률 증가량 : {value3}%\\n\\n[뮤즈 전용 효과]\\n기본 공격 및 스킬 공격력 증가량 : {value4}%\\n\\n[파티원 전용 효과]\\n힘 증가량 : {value5}\\n지능 증가량 : {value6}\\n물리 공격력 증가량 : {value7}\\n마법 공격력 증가량 : {value8}\\n독립 공격력 증가량 : {value9}",
                    					"values": ["900", "120", "27.6", "55", "379", "379", "63", "63", "63"]
                    				}
                    			},
                    			"equipment": [{
                    				"slotId": "TITLE",
                    				"slotName": "칭호",
                    				"itemId": "e1d06c515754f8cb2205a83adcb72b22",
                    				"itemName": "모험가의 의지[빛]",
                    				"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    				"itemType": "액세서리",
                    				"itemTypeDetailId": "691933a06ff114348a7db936fb0aec66",
                    				"itemTypeDetail": "칭호",
                    				"itemAvailableLevel": 1,
                    				"itemRarity": "레어",
                    				"setItemId": null,
                    				"setItemName": null,
                    				"reinforce": 0,
                    				"enchant": {
                    					"reinforceSkill": [{
                    						"jobId": "3909d0b188e9c95311399f776e331da5",
                    						"jobName": "마법사(여)",
                    						"skills": [{
                    							"skillId": "61c8cb33dd20b4ff335e8deed70d3d9c",
                    							"name": "금단의 저주",
                    							"value": "2"
                    						}]
                    					}, {
                    						"jobId": "f6a4ad30555b99b499c07835f87ce522",
                    						"jobName": "프리스트(남)",
                    						"skills": [{
                    							"skillId": "e4c354a89c337310aeb7041d5e742828",
                    							"name": "영광의 축복",
                    							"value": "2"
                    						}]
                    					}, {
                    						"jobId": "0c1b401bb09241570d364420b3ba3fd7",
                    						"jobName": "프리스트(여)",
                    						"skills": [{
                    							"skillId": "8c2379737c5acc935c1731f67f607655",
                    							"name": "용맹의 축복",
                    							"value": "2"
                    						}]
                    					}, {
                    						"jobId": "b9cb48777665de22c006fabaf9a560b3",
                    						"jobName": "아처",
                    						"skills": [{
                    							"skillId": "78be08a3f8c834d3b06fa20c6a08c5a5",
                    							"name": "러블리 템포",
                    							"value": "2"
                    						}]
                    					}]
                    				},
                    				"amplificationName": null,
                    				"refine": 0
                    			}]
                    		}
                    	}
                    }
                    """;
        }

        @Override
        public String equipment() {
            return """
                    {
                    	"characterId": "87c7ed846938a184f71b338af88bdf4a",
                    	"characterName": "Les_Paul",
                    	"level": 110,
                    	"jobId": "b9cb48777665de22c006fabaf9a560b3",
                    	"jobGrowId": "37495b941da3b1661bc900e68ef3b2c6",
                    	"jobName": "아처",
                    	"jobGrowName": "眞 뮤즈",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"equipment": [{
                    		"slotId": "WEAPON",
                    		"slotName": "무기",
                    		"itemId": "a348b7d4f74c979d3f3d23275bc4829e",
                    		"itemName": "결전의 선현궁 - 뮤즈",
                    		"itemTypeId": "4ffb6f14b86f5c818a925bf58022686e",
                    		"itemType": "무기",
                    		"itemTypeDetailId": "390e3966118b0c466ce9f8eae45e1629",
                    		"itemTypeDetail": "선현궁",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 10,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "체력",
                    				"value": 90
                    			}, {
                    				"name": "정신력",
                    				"value": 90
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 8,
                    		"growInfo": {
                    			"total": {
                    				"damage": 20990,
                    				"buff": 5053,
                    				"level": 297
                    			},
                    			"options": [{
                    				"level": 75,
                    				"expRate": 25.07,
                    				"explain": "센세이션 싱글 플레이 시 독립 공격력 20% 추가 증가\\n컬쳐 쇼크 스킬 범위 20% 증가\\n컬쳐 쇼크 스킬이 끝날 때 추가 파동 발생\\n- 추가 파동은 2초 동안 5회\\n- 추가 파동 공격력 : 1회 마다 세 번째 파동 공격력의 30%",
                    				"explainDetail": "센세이션 싱글 플레이 시 독립 공격력 20% 추가 증가\\n컬쳐 쇼크 스킬 범위 20% 증가\\n컬쳐 쇼크 스킬이 끝날 때 추가 파동 발생\\n- 추가 파동은 2초 동안 5회\\n- 추가 파동 공격력 : 1회 마다 세 번째 파동 공격력의 30%",
                    				"damage": 5266,
                    				"default": {
                    					"damage": 1927,
                    					"buff": 464
                    				},
                    				"buff": 1268
                    			}, {
                    				"level": 73,
                    				"expRate": 52.12,
                    				"explain": "사운드 오브 뮤즈 스킬 시전 중 스포트라이트가 추가로 발생하여 스킬 강화\\n- 사운드 오브 뮤즈 스킬 범위 15% 증가\\n- 사운드 오브 뮤즈 스킬 스킬 공격력 : 20% 증가\\n- 사운드 오브 뮤즈 스킬로 공격 시 컬쳐 쇼크 쿨타임 초기화",
                    				"explainDetail": "사운드 오브 뮤즈 스킬 시전 중 스포트라이트가 추가로 발생하여 스킬 강화\\n- 사운드 오브 뮤즈 스킬 범위 15% 증가\\n- 사운드 오브 뮤즈 스킬 스킬 공격력 : 20% 증가\\n- 사운드 오브 뮤즈 스킬로 공격 시 컬쳐 쇼크 쿨타임 초기화",
                    				"damage": 5216,
                    				"default": {
                    					"damage": 1927,
                    					"buff": 464
                    				},
                    				"buff": 1256
                    			}, {
                    				"level": 75,
                    				"expRate": 30.88,
                    				"explain": "장착 시 파티원에게 러블리 템포 효과를 8% 증가시키는 버프 적용\\n밤과 꿈 HP 회복량 50% 감소\\n달콤한 칸타빌레 HP 회복량 50% 감소",
                    				"explainDetail": "장착 시 파티원에게 러블리 템포 효과를 8% 증가시키는 버프 적용 (장착 해제 시 러블리 템포 버프 효과 삭제, 버프 강화 무기 슬롯에 장비가 등록되어 있는 경우 적용되지 않음)\\n밤과 꿈 HP 회복량 50% 감소\\n달콤한 칸타빌레 HP 회복량 50% 감소",
                    				"damage": 5266,
                    				"default": {
                    					"damage": 1927,
                    					"buff": 464
                    				},
                    				"buff": 1268
                    			}, {
                    				"level": 74,
                    				"expRate": 90.29,
                    				"explain": "밤과 꿈 스킬 범위 20% 증가",
                    				"explainDetail": "밤과 꿈 스킬 범위 20% 증가",
                    				"damage": 5242,
                    				"default": {
                    					"damage": 1927,
                    					"buff": 464
                    				},
                    				"buff": 1261
                    			}]
                    		}
                    	}, {
                    		"slotId": "TITLE",
                    		"slotName": "칭호",
                    		"itemId": "c89a01593a155a3cbaaf272084d8fb57",
                    		"itemName": "진정한 각성을 이룬 자",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "691933a06ff114348a7db936fb0aec66",
                    		"itemTypeDetail": "칭호",
                    		"itemAvailableLevel": 1,
                    		"itemRarity": "레어",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"enchant": {
                    			"status": [{
                    				"name": "모든 속성 강화",
                    				"value": 3
                    			}, {
                    				"name": "힘",
                    				"value": 20
                    			}, {
                    				"name": "지능",
                    				"value": 20
                    			}, {
                    				"name": "체력",
                    				"value": 20
                    			}, {
                    				"name": "정신력",
                    				"value": 20
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "JACKET",
                    		"slotName": "상의",
                    		"itemId": "e72946e344232c60015248ea2d8a382e",
                    		"itemName": "딥 다이버 슈트",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "fd2e4e03a93fb8096fca4d2187fb39db",
                    		"itemTypeDetail": "판금 상의",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 8,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "체력",
                    				"value": 90
                    			}, {
                    				"name": "정신력",
                    				"value": 90
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"damage": 4685,
                    				"buff": 1291,
                    				"explain": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 공격 속도, 이동 속도 +4%, 캐스팅 속도 +6%\\n- 파티원이 1명일 때 공격 시 30% 확률로 적에게 뇌광폭발 생성",
                    				"explainDetail": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 공격 속도, 이동 속도 +4%, 캐스팅 속도 +6% (최대 4중첩)\\n- 파티원이 1명일 때 공격 시 30% 확률로 적에게 뇌광폭발 생성 (쿨타임 4초, 적의 방어력과 상관없이 총 피해 증가 수치의 500%만큼 피해를 줌)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "cef49e565beee41babb0d61fa6107176",
                    			"itemName": "뇌광 : 천둥을 품은 용심"
                    		},
                    		"growInfo": {
                    			"transfer": true,
                    			"total": {
                    				"damage": 15856,
                    				"buff": 5730,
                    				"level": 318
                    			},
                    			"options": [{
                    				"level": 79,
                    				"expRate": 73.91,
                    				"explain": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"explainDetail": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"damage": 4544,
                    				"default": {
                    					"damage": 1630,
                    					"buff": 509
                    				},
                    				"buff": 1419,
                    				"transfer": true
                    			}, {
                    				"level": 80,
                    				"expRate": 0,
                    				"explain": "500px 범위 내 둔화 상태인 대상 하나 당 피해 증가 +3666",
                    				"explainDetail": "500px 범위 내 둔화 상태인 대상 하나 당 피해 증가 +3666 (최대 3중첩)",
                    				"damage": 3384,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 504
                    				},
                    				"buff": 1439
                    			}, {
                    				"level": 79,
                    				"expRate": 1.39,
                    				"explain": "물리 크리티컬 히트 +7%\\n마법 크리티컬 히트 +7%\\n모든 상태 이상 내성 10% 감소",
                    				"explainDetail": "물리 크리티컬 히트 +7%\\n마법 크리티컬 히트 +7%\\n모든 상태 이상 내성 10% 감소",
                    				"damage": 4544,
                    				"default": {
                    					"damage": 1630,
                    					"buff": 509
                    				},
                    				"buff": 1419
                    			}, {
                    				"level": 80,
                    				"expRate": 0,
                    				"explain": "5회 공격 시마다 아래 효과 적용\\n- 피해 증가 +508\\n- 물리 방어력 500 감소\\n- 마법 방어력 500 감소",
                    				"explainDetail": "5회 공격 시마다 아래 효과 적용 (최대 10중첩)\\n- 피해 증가 +508\\n- 물리 방어력 500 감소\\n- 마법 방어력 500 감소",
                    				"damage": 3384,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 509
                    				},
                    				"buff": 1453
                    			}]
                    		}
                    	}, {
                    		"slotId": "SHOULDER",
                    		"slotName": "머리어깨",
                    		"itemId": "c3e0d16dba6eb280767b09f5730d6466",
                    		"itemName": "자유를 수호하는 윙 숄더",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "a4e1213018d003906149dffd2a2029e8",
                    		"itemTypeDetail": "판금 머리어깨",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 8,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "체력",
                    				"value": 90
                    			}, {
                    				"name": "정신력",
                    				"value": 90
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"damage": 5861,
                    				"buff": 1291,
                    				"explain": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 피격 시 받는 데미지 3% 감소\\n- 파티원이 1명일 때 공격 시 25% 확률로 800px 범위 내 랜덤한 적에게 낙뢰 최대 3개 생성\\n- 낙뢰에 피격된 적을 30% 확률로 3초 동안 감전 상태로 만듦",
                    				"explainDetail": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 피격 시 받는 데미지 3% 감소 (최대 4중첩)\\n- 파티원이 1명일 때 공격 시 25% 확률로 800px 범위 내 랜덤한 적에게 낙뢰 최대 3개 생성 (쿨타임 3초, 적의 방어력과 상관없이 총 피해 증가 수치의 100%만큼의 피해를 줌)\\n- 낙뢰에 피격된 적을 30% 확률로 3초 동안 감전 상태로 만듦"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "424a5caa037bcf098cb50b70bc3b3117",
                    			"itemName": "뇌광 : 우레를 감싼 용익"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 17517,
                    				"buff": 5345,
                    				"level": 299
                    			},
                    			"options": [{
                    				"level": 75,
                    				"expRate": 57.18,
                    				"explain": "500px 범위 내 화상 상태 적 1마리당 모든 속도 +3%, 화속성 저항 +1\\n네임드, 보스 몬스터가 화상 상태일 때 모든 속도, 화속성 저항 증가 최대 중첩 적용",
                    				"explainDetail": "500px 범위 내 화상 상태 적 1마리당 모든 속도 +3%, 화속성 저항 +1 (최대 10중첩)\\n네임드, 보스 몬스터가 화상 상태일 때 모든 속도, 화속성 저항 증가 최대 중첩 적용",
                    				"damage": 5525,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 480
                    				},
                    				"buff": 1312
                    			}, {
                    				"level": 75,
                    				"expRate": 25.99,
                    				"explain": "피격 시 받는 데미지 15% 감소\\n화상 상태 적 공격 시 스킬 공격력 5% 증가",
                    				"explainDetail": "피격 시 받는 데미지 15% 감소\\n화상 상태 적 공격 시 스킬 공격력 5% 증가",
                    				"damage": 5525,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 480
                    				},
                    				"buff": 1312
                    			}, {
                    				"level": 74,
                    				"expRate": 91.66,
                    				"explain": "500px 범위 내 화상 상태인 대상 하나 당 피해 증가 +1936",
                    				"explainDetail": "500px 범위 내 화상 상태인 대상 하나 당 피해 증가 +1936 (최대 5중첩)",
                    				"damage": 3226,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 504
                    				},
                    				"buff": 1371
                    			}, {
                    				"level": 75,
                    				"expRate": 35.39,
                    				"explain": "화속성 강화 15 증가",
                    				"explainDetail": "화속성 강화 15 증가",
                    				"damage": 3241,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1350
                    			}]
                    		}
                    	}, {
                    		"slotId": "PANTS",
                    		"slotName": "하의",
                    		"itemId": "cab483b0a2723fd522ad6a173765a253",
                    		"itemName": "로보티카 컴뱃 팬츠",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "d944e7adc2b12b60036b0da689051147",
                    		"itemTypeDetail": "판금 하의",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 10,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "체력",
                    				"value": 90
                    			}, {
                    				"name": "정신력",
                    				"value": 90
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"damage": 4685,
                    				"buff": 1291,
                    				"explain": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 HP MAX +300, MP MAX +473\\n- 파티원이 1명일 때 공격 시 30% 확률로 적에게 뇌광폭발 생성",
                    				"explainDetail": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 HP MAX +300, MP MAX +473 (최대 4중첩)\\n- 파티원이 1명일 때 공격 시 30% 확률로 적에게 뇌광폭발 생성 (쿨타임 4초, 적의 방어력과 상관없이 총 피해 증가 수치의 500%만큼 피해를 줌)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "ecb7bd892dce52ecb841231c6b3ebce3",
                    			"itemName": "뇌광 : 낙뢰를 내린 용각"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 10976,
                    				"buff": 5364,
                    				"level": 306
                    			},
                    			"options": [{
                    				"level": 77,
                    				"expRate": 38.59,
                    				"explain": "장착한 무기, 방어구, 악세서리, 특수장비의 강화/증폭 수치 합이 6 증가할 때 마다 피해 증가 +375, 스킬 공격력 0.2% 증가",
                    				"explainDetail": "장착한 무기, 방어구, 악세서리, 특수장비의 강화/증폭 수치 합이 6 증가할 때 마다 피해 증가 +375, 스킬 공격력 0.2% 증가 (최대 24중첩, 휘장, 보조무기 제외)",
                    				"damage": 2231,
                    				"default": {
                    					"damage": 808,
                    					"buff": 480
                    				},
                    				"buff": 1325
                    			}, {
                    				"level": 77,
                    				"expRate": 60.2,
                    				"explain": "장착한 이 장비의 강화/증폭 수치 1 마다 모든 속도 +2%",
                    				"explainDetail": "장착한 이 장비의 강화/증폭 수치 1 마다 모든 속도 +2% (최대 12중첩)",
                    				"damage": 2231,
                    				"default": {
                    					"damage": 808,
                    					"buff": 480
                    				},
                    				"buff": 1325
                    			}, {
                    				"level": 76,
                    				"expRate": 13.06,
                    				"explain": "물리 방어력 +7000\\n마법 방어력 +7000",
                    				"explainDetail": "물리 방어력 +7000\\n마법 방어력 +7000",
                    				"damage": 3257,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1357
                    			}, {
                    				"level": 76,
                    				"expRate": 45.6,
                    				"explain": "모든 속성 강화 10 증가",
                    				"explainDetail": "모든 속성 강화 10 증가",
                    				"damage": 3257,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1357
                    			}]
                    		}
                    	}, {
                    		"slotId": "SHOES",
                    		"slotName": "신발",
                    		"itemId": "fc9a49a7af5709126b6a52fc745f71bc",
                    		"itemName": "내딛는 용기",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "48e9f8b4f2ba996e22e2d9f176c9c8fd",
                    		"itemTypeDetail": "판금 신발",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 8,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "체력",
                    				"value": 90
                    			}, {
                    				"name": "정신력",
                    				"value": 90
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"damage": 6213,
                    				"buff": 1291,
                    				"explain": "무색 큐브 조각을 소모하는 스킬 시전 시 소모한 무색 큐브 조각 1개마다 격노의 기운 2 획득\\n- 격노의 기운을 지니고 있는 동안 슈퍼아머\\n- 격노의 기운은 1초마다 10 감소\\n- 격노 장비 추가 장착 시마다 격노의 기운 최대치 10 증가\\n\\n대시 시 격노의 기운 10 획득\\n이동 속도가 100% 이상일 때 대시 유지 시 격노의 기운이 감소하지 않음\\n\\n격노의 기운 획득 시 10초 동안 아래 효과 적용\\n- 이동 속도 +8%\\n- 이동 속도가 100% 이상일 때 대시 중 받는 데미지 10% 감소",
                    				"explainDetail": "무색 큐브 조각을 소모하는 스킬 시전 시 소모한 무색 큐브 조각 1개마다 격노의 기운 2 획득 (최대 35)\\n- 격노의 기운을 지니고 있는 동안 슈퍼아머\\n- 격노의 기운은 1초마다 10 감소\\n- 격노 장비 추가 장착 시마다 격노의 기운 최대치 10 증가\\n\\n대시 시 격노의 기운 10 획득 (쿨타임 1초)\\n이동 속도가 100% 이상일 때 대시 유지 시 격노의 기운이 감소하지 않음\\n\\n격노의 기운 획득 시 10초 동안 아래 효과 적용\\n- 이동 속도 +8%\\n- 이동 속도가 100% 이상일 때 대시 중 받는 데미지 10% 감소"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "bbbfad49bb38dbe5e4df59b5d92524cf",
                    			"itemName": "격노 : 방향을 잃은 갈망"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 17779,
                    				"buff": 5325,
                    				"level": 312
                    			},
                    			"options": [{
                    				"level": 78,
                    				"expRate": 34.23,
                    				"explain": "캐릭터의 적중률이 35% 이상일 때 피해 증가 +9044, 모든 속성 강화 +25",
                    				"explainDetail": "캐릭터의 적중률이 35% 이상일 때 피해 증가 +9044, 모든 속성 강화 +25",
                    				"damage": 5606,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 466
                    				},
                    				"buff": 1292
                    			}, {
                    				"level": 77,
                    				"expRate": 91.69,
                    				"explain": "캐릭터의 이동 속도가 50% 이상일 때 모든 직업 1~25 레벨 모든 스킬 Lv +2",
                    				"explainDetail": "캐릭터의 이동 속도가 50% 이상일 때 모든 직업 1~25 레벨 모든 스킬 Lv +2",
                    				"damage": 5579,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 466
                    				},
                    				"buff": 1285
                    			}, {
                    				"level": 79,
                    				"expRate": 33.6,
                    				"explain": "적중률 +10%",
                    				"explainDetail": "적중률 +10%",
                    				"damage": 3305,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1377
                    			}, {
                    				"level": 78,
                    				"expRate": 8.34,
                    				"explain": "이동 속도 +8%",
                    				"explainDetail": "이동 속도 +8%",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}]
                    		}
                    	}, {
                    		"slotId": "WAIST",
                    		"slotName": "허리",
                    		"itemId": "29ef2acd2a9c62873cd88499d01a1571",
                    		"itemName": "익스펜션 서플라이 벨트",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "05e3f1f7234913d35c9fc209dde01a6d",
                    		"itemTypeDetail": "판금 허리",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 8,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "체력",
                    				"value": 90
                    			}, {
                    				"name": "정신력",
                    				"value": 90
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"machineRevolutionInfo": {
                    			"options": [{
                    				"damage": 6213,
                    				"buff": 1291,
                    				"explain": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 물리, 마법 방어력 +3500\\n- 파티원이 1명일 때 공격 시 25% 확률로 800px 범위 내 랜덤한 적에게 낙뢰 최대 3개 생성\\n- 낙뢰에 피격된 적을 30% 확률로 3초 동안 감전 상태로 만듦",
                    				"explainDetail": "던전 입장 시 용의 권능이 발동하여 아래의 효과 적용\\n- 파티원 1명당 물리, 마법 방어력 +3500 (최대 4중첩)\\n- 파티원이 1명일 때 공격 시 25% 확률로 800px 범위 내 랜덤한 적에게 낙뢰 최대 3개 생성 (쿨타임 3초, 적의 방어력과 상관없이 총 피해 증가 수치의 100%만큼의 피해를 줌)\\n- 낙뢰에 피격된 적을 30% 확률로 3초 동안 감전 상태로 만듦"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "b4f01254eea851567184b39616d40b6e",
                    			"itemName": "뇌광 : 번개를 담은 용린"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 14776,
                    				"buff": 5399,
                    				"level": 311
                    			},
                    			"options": [{
                    				"level": 76,
                    				"expRate": 90.63,
                    				"explain": "화속성 저항 11마다 모든 속도 +5%",
                    				"explainDetail": "화속성 저항 11마다 모든 속도 +5% (최대 9중첩)",
                    				"damage": 4071,
                    				"default": {
                    					"damage": 1482,
                    					"buff": 480
                    				},
                    				"buff": 1319
                    			}, {
                    				"level": 78,
                    				"expRate": 42.17,
                    				"explain": "HP 50% 이상일 때 쿨타임 회복 속도 15% 증가, 스킬 공격력 7% 증가",
                    				"explainDetail": "HP 50% 이상일 때 쿨타임 회복 속도 15% 증가 (각성기 제외), 스킬 공격력 7% 증가",
                    				"damage": 4111,
                    				"default": {
                    					"damage": 1482,
                    					"buff": 480
                    				},
                    				"buff": 1332
                    			}, {
                    				"level": 79,
                    				"expRate": 77.15,
                    				"explain": "화속성 저항 10 증가",
                    				"explainDetail": "화속성 저항 10 증가",
                    				"damage": 3305,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1377
                    			}, {
                    				"level": 78,
                    				"expRate": 52.79,
                    				"explain": "피격 시 60초 동안 화속성 저항 20 증가",
                    				"explainDetail": "피격 시 60초 동안 화속성 저항 20 증가 (쿨타임 5초, 최대 1중첩)",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}]
                    		}
                    	}, {
                    		"slotId": "AMULET",
                    		"slotName": "목걸이",
                    		"itemId": "c9478e22a6d836cdce8a73cf61d2ec64",
                    		"itemName": "폭룡왕의 지배 - 공포",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "390e3966118b0c466ce9f8eae45e1629",
                    		"itemTypeDetail": "목걸이",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 8,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "힘",
                    				"value": 90
                    			}, {
                    				"name": "지능",
                    				"value": 90
                    			}, {
                    				"name": "체력",
                    				"value": 90
                    			}, {
                    				"name": "정신력",
                    				"value": 90
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"ispinsInfo": {
                    			"options": [{
                    				"buff": 1518,
                    				"explain": "공격 시 MP 2000 감소\\n\\n현재 MP 2% 마다 피해 증가 +1876",
                    				"explainDetail": "공격 시 MP 2000 감소 (쿨타임 1초)\\n\\n현재 MP 2% 마다 피해 증가 +1876 (최대 5중첩)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "27843216918f310d010f1014661c81bd",
                    			"itemName": "흑룡 : 주시하는 용의 눈동자"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 20355,
                    				"buff": 5373,
                    				"level": 305
                    			},
                    			"options": [{
                    				"level": 77,
                    				"expRate": 36.26,
                    				"explain": "장착 시 최대 HP의 15% 만큼 자동 충전되는 보호막 적용\\n장비로 인한 보호막 최대 수치 8% 증가",
                    				"explainDetail": "장착 시 최대 HP의 15% 만큼 자동 충전되는 보호막 적용 (보호막은 5초 동안 자신 또는 보호막이 피격되지 않을 경우, 1초 마다 보호막 최대 수치의 20%씩 충전 됩니다.)\\n장비로 인한 보호막 최대 수치 8% 증가",
                    				"damage": 6322,
                    				"default": {
                    					"damage": 2290,
                    					"buff": 475
                    				},
                    				"buff": 1312
                    			}, {
                    				"level": 76,
                    				"expRate": 98.26,
                    				"explain": "자동 충전 보호막의 피격 조건 시간 1초 감소\\n던전 입장 시 모든 속성 강화 +10, 공격 속도, 이동 속도 +20%, 캐스팅 속도 +30%",
                    				"explainDetail": "자동 충전 보호막의 피격 조건 시간 1초 감소 (피격되지 않아야하는 조건 시간이 감소합니다.)\\n던전 입장 시 모든 속성 강화 +10, 공격 속도, 이동 속도 +20%, 캐스팅 속도 +30%",
                    				"damage": 6292,
                    				"default": {
                    					"damage": 2290,
                    					"buff": 475
                    				},
                    				"buff": 1306
                    			}, {
                    				"level": 75,
                    				"expRate": 82.22,
                    				"explain": "HP MAX +600",
                    				"explainDetail": "HP MAX +600",
                    				"damage": 3241,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1350
                    			}, {
                    				"level": 77,
                    				"expRate": 54.18,
                    				"explain": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"explainDetail": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"damage": 4500,
                    				"default": {
                    					"damage": 1630,
                    					"buff": 509
                    				},
                    				"buff": 1405
                    			}]
                    		}
                    	}, {
                    		"slotId": "WRIST",
                    		"slotName": "팔찌",
                    		"itemId": "9e3029a34e4c0a4ec4b4a064cf1485e5",
                    		"itemName": "엔트 정령의 팔찌",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "80bddf423629c28c7b4459c328fdffaf",
                    		"itemTypeDetail": "팔찌",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 8,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "힘",
                    				"value": 90
                    			}, {
                    				"name": "지능",
                    				"value": 90
                    			}, {
                    				"name": "체력",
                    				"value": 90
                    			}, {
                    				"name": "정신력",
                    				"value": 90
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"ispinsInfo": {
                    			"options": [{
                    				"buff": 1518,
                    				"explain": "스킬 시전 시 마나번 디버프가 발동하여 1초마다 MP 1% 감소\\n\\nMP가 50% 미만일 때 1초마다 MP 2% 회복",
                    				"explainDetail": "스킬 시전 시 마나번 디버프가 발동하여 1초마다 MP 1% 감소 (최대 5중첩)\\n- 마나번 디버프 중첩 수 1당 피해 증가 +1876\\n\\nMP가 50% 미만일 때 1초마다 MP 2% 회복\\n- MP가 10% 감소할 때마다 1초마다 MP 1% 추가 회복"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "db3a4579b25e7dbdca33f56195ce259f",
                    			"itemName": "흑룡 : 어두운 힘의 근본"
                    		},
                    		"growInfo": {
                    			"transfer": true,
                    			"total": {
                    				"damage": 11739,
                    				"buff": 5491,
                    				"level": 299
                    			},
                    			"options": [{
                    				"level": 74,
                    				"expRate": 21.46,
                    				"explain": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"explainDetail": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"damage": 4435,
                    				"default": {
                    					"damage": 1630,
                    					"buff": 509
                    				},
                    				"buff": 1385
                    			}, {
                    				"level": 74,
                    				"expRate": 77.28,
                    				"explain": "야수 타입의 적 공격 시 스킬 공격력 7% 증가",
                    				"explainDetail": "야수 타입의 적 공격 시 스킬 공격력 7% 증가",
                    				"damage": 806,
                    				"default": {
                    					"damage": 296,
                    					"buff": 504
                    				},
                    				"buff": 1371
                    			}, {
                    				"level": 75,
                    				"expRate": 65.26,
                    				"explain": "해당 옵션을 보유한 파티원 1명당 피해 증가 +3038 (자신 포함)",
                    				"explainDetail": "해당 옵션을 보유한 파티원 1명당 피해 증가 +3038 (자신 포함)",
                    				"damage": 3241,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 504
                    				},
                    				"buff": 1378
                    			}, {
                    				"level": 76,
                    				"expRate": 55.68,
                    				"explain": "회피율 +8%",
                    				"explainDetail": "회피율 +8%",
                    				"damage": 3257,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1357
                    			}]
                    		}
                    	}, {
                    		"slotId": "RING",
                    		"slotName": "반지",
                    		"itemId": "509508d2bdb68a333a7e095ab6f86011",
                    		"itemName": "뚜렷해지는 소멸된 사념",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "b04c7fb9b29b27b91a0a9e5a1822bc8f",
                    		"itemTypeDetail": "반지",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 8,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "힘",
                    				"value": 90
                    			}, {
                    				"name": "지능",
                    				"value": 90
                    			}, {
                    				"name": "체력",
                    				"value": 90
                    			}, {
                    				"name": "정신력",
                    				"value": 90
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"ispinsInfo": {
                    			"options": [{
                    				"buff": 1518,
                    				"explain": "피격으로 HP가 1% 이상 감소 시 MP 5000 감소\\n\\n현재 MP 4% 마다 피해 증가 +1876\\n\\n스킬로 인해 소모한 MP 5000 마다 10초동안 장비로 인한 MP 회복 효과 10% 증가",
                    				"explainDetail": "피격으로 HP가 1% 이상 감소 시 MP 5000 감소 (쿨타임 0.5초)\\n\\n현재 MP 4% 마다 피해 증가 +1876 (최대 5중첩)\\n\\n스킬로 인해 소모한 MP 5000 마다 10초동안 장비로 인한 MP 회복 효과 10% 증가 (최대 1중첩)"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "1fd082cd996a9eb02645be09c5e89d41",
                    			"itemName": "흑룡 : 끝없는 암흑의 굴레"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 17463,
                    				"buff": 5332,
                    				"level": 297
                    			},
                    			"options": [{
                    				"level": 73,
                    				"expRate": 77.82,
                    				"explain": "HP가 20% 이하일 때 피격 시 모든 MP를 소모하여 소모한 MP만큼 HP 회복",
                    				"explainDetail": "HP가 20% 이하일 때 피격 시 모든 MP를 소모하여 소모한 MP만큼 HP 회복 (쿨타임 60초)",
                    				"damage": 5471,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 485
                    				},
                    				"buff": 1313
                    			}, {
                    				"level": 75,
                    				"expRate": 36.78,
                    				"explain": "HP가 50% 이하일 때 초당 HP 3% 회복\\nMP가 50% 이하일 때 초당 MP 4% 회복",
                    				"explainDetail": "HP가 50% 이하일 때 초당 HP 3% 회복\\nMP가 50% 이하일 때 초당 MP 4% 회복",
                    				"damage": 5525,
                    				"default": {
                    					"damage": 2021,
                    					"buff": 485
                    				},
                    				"buff": 1325
                    			}, {
                    				"level": 74,
                    				"expRate": 86.04,
                    				"explain": "물리 방어력 +7000\\n마법 방어력 +7000",
                    				"explainDetail": "물리 방어력 +7000\\n마법 방어력 +7000",
                    				"damage": 3226,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1344
                    			}, {
                    				"level": 75,
                    				"expRate": 69.6,
                    				"explain": "MP MAX +945",
                    				"explainDetail": "MP MAX +945",
                    				"damage": 3241,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1350
                    			}]
                    		}
                    	}, {
                    		"slotId": "SUPPORT",
                    		"slotName": "보조장비",
                    		"itemId": "e2ab555a05e8de69133e0bfa4ab5bfd1",
                    		"itemName": "블루 베릴 퍼퓸",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "2fef5d81b7f59f0c75241890a8d941c9",
                    		"itemTypeDetail": "보조장비",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 8,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "힘",
                    				"value": 100
                    			}, {
                    				"name": "지능",
                    				"value": 100
                    			}, {
                    				"name": "체력",
                    				"value": 100
                    			}, {
                    				"name": "정신력",
                    				"value": 100
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"growInfo": {
                    			"transfer": true,
                    			"total": {
                    				"damage": 9419,
                    				"buff": 5558,
                    				"level": 309
                    			},
                    			"options": [{
                    				"level": 77,
                    				"expRate": 52.49,
                    				"explain": "천사 타입의 적 공격 시 스킬 공격력 7% 증가",
                    				"explainDetail": "천사 타입의 적 공격 시 스킬 공격력 7% 증가",
                    				"damage": 818,
                    				"default": {
                    					"damage": 296,
                    					"buff": 504
                    				},
                    				"buff": 1391
                    			}, {
                    				"level": 79,
                    				"expRate": 73.82,
                    				"explain": "MP가 30% 이하일 때 무색 큐브 조각 10개를 소모하여 MP 30% 회복",
                    				"explainDetail": "MP가 30% 이하일 때 무색 큐브 조각 10개를 소모하여 MP 30% 회복 (쿨타임 10초)",
                    				"damage": 3305,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1377,
                    				"transfer": true
                    			}, {
                    				"level": 76,
                    				"expRate": 49.89,
                    				"explain": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"explainDetail": "모든 속성 강화 15 증가\\n모든 속성 저항 10 감소",
                    				"damage": 4478,
                    				"default": {
                    					"damage": 1630,
                    					"buff": 509
                    				},
                    				"buff": 1399
                    			}, {
                    				"level": 77,
                    				"expRate": 3.28,
                    				"explain": "수면 상태 적 공격 시 30초 동안 스킬 공격력 15% 증가",
                    				"explainDetail": "수면 상태 적 공격 시 30초 동안 스킬 공격력 15% 증가 (최대 1중첩)",
                    				"damage": 818,
                    				"default": {
                    					"damage": 296,
                    					"buff": 504
                    				},
                    				"buff": 1391
                    			}]
                    		}
                    	}, {
                    		"slotId": "MAGIC_STON",
                    		"slotName": "마법석",
                    		"itemId": "9f30cf8ea43c6090797cb210d0d7723c",
                    		"itemName": "언비튼 메달",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "fe5f3db78175f5a3196385c688d29681",
                    		"itemTypeDetail": "마법석",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 8,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "힘",
                    				"value": 100
                    			}, {
                    				"name": "지능",
                    				"value": 100
                    			}, {
                    				"name": "체력",
                    				"value": 100
                    			}, {
                    				"name": "정신력",
                    				"value": 100
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"dimensionCloisterInfo": {
                    			"options": [{
                    				"damage": 8560,
                    				"buff": 1291,
                    				"explain": "공격 시 적에게 대지의 태동을 생성하며 아래 효과 적용\\n- 대지의 태동에 피격 된 적을 10% 확률로 5초 동안 기절 상태로 만듦",
                    				"explainDetail": "공격 시 적에게 대지의 태동을 생성하며 아래 효과 적용 (쿨타임 1초, 적의 방어력과 상관없이 총 피해 증가 수치의 125%만큼 피해를 줌)\\n- 대지의 태동에 피격 된 적을 10% 확률로 5초 동안 기절 상태로 만듦"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "323e201088055e1ed622be84b7479103",
                    			"itemName": "슈페리어 : 매직 서클로"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 15525,
                    				"buff": 5293,
                    				"level": 311
                    			},
                    			"options": [{
                    				"level": 77,
                    				"expRate": 75.26,
                    				"explain": "피격 시 받는 공격을 화속성으로 적용\\n피격 시 10초 동안 화속성 저항 +5",
                    				"explainDetail": "피격 시 받는 공격을 화속성으로 적용\\n피격 시 10초 동안 화속성 저항 +5 (최대 5중첩)",
                    				"damage": 4463,
                    				"default": {
                    					"damage": 1616,
                    					"buff": 461
                    				},
                    				"buff": 1272
                    			}, {
                    				"level": 78,
                    				"expRate": 82.19,
                    				"explain": "화속성 피격 시 5초 동안 HP 5% 회복\\n화속성 저항 30 이상일 때 피해 증가 +8222",
                    				"explainDetail": "화속성 피격 시 5초 동안 HP 5% 회복 (쿨타임 7초)\\n화속성 저항 30 이상일 때 피해 증가 +8222",
                    				"damage": 4484,
                    				"default": {
                    					"damage": 1616,
                    					"buff": 461
                    				},
                    				"buff": 1279
                    			}, {
                    				"level": 78,
                    				"expRate": 30.15,
                    				"explain": "화속성 저항 10 증가",
                    				"explainDetail": "화속성 저항 10 증가",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}, {
                    				"level": 78,
                    				"expRate": 65.73,
                    				"explain": "HP MAX +600",
                    				"explainDetail": "HP MAX +600",
                    				"damage": 3289,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1371
                    			}]
                    		}
                    	}, {
                    		"slotId": "EARRING",
                    		"slotName": "귀걸이",
                    		"itemId": "922575ac85da5f999815024b6b2ec2a7",
                    		"itemName": "시각의 관점",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "601834074c49bb0e48cb65a75a8667bc",
                    		"itemTypeDetail": "귀걸이",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 8,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "힘",
                    				"value": 175
                    			}, {
                    				"name": "지능",
                    				"value": 175
                    			}, {
                    				"name": "체력",
                    				"value": 175
                    			}, {
                    				"name": "정신력",
                    				"value": 175
                    			}]
                    		},
                    		"amplificationName": "차원의 정신력",
                    		"refine": 0,
                    		"dimensionCloisterInfo": {
                    			"options": [{
                    				"damage": 8560,
                    				"buff": 1291,
                    				"explain": "공격 시 적에게 물결의 파동을 생성하며 아래 효과 적용\\n- 물결의 파동에 피격 된 적을 10% 확률로 5초 동안 빙결 상태로 만듦",
                    				"explainDetail": "공격 시 적에게 물결의 파동을 생성하며 아래 효과 적용 (쿨타임 3초, 적의 방어력과 상관없이 총 피해 증가 수치의 375%만큼 피해를 줌)\\n- 물결의 파동에 피격 된 적을 10% 확률로 5초 동안 빙결 상태로 만듦"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "70558b17d3d49fc8599f8e0d751bce9d",
                    			"itemName": "슈페리어 : 코스믹 이어링"
                    		},
                    		"growInfo": {
                    			"total": {
                    				"damage": 12405,
                    				"buff": 4415,
                    				"level": 302
                    			},
                    			"options": [{
                    				"level": 76,
                    				"expRate": 61.69,
                    				"explain": "해당 장비의 마법 부여 효과 70% 증가",
                    				"explainDetail": "해당 장비의 마법 부여 효과 70% 증가 (스킬 레벨 효과 및 모험가 명성 제외)",
                    				"damage": 2961,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 312
                    				},
                    				"buff": 857
                    			}, {
                    				"level": 76,
                    				"expRate": 45.09,
                    				"explain": "공격 시 10초 동안 해당 장비의 마법 부여 효과 30% 증가",
                    				"explainDetail": "공격 시 10초 동안 해당 장비의 마법 부여 효과 30% 증가 (최대 1중첩, 스킬 레벨 효과 및 모험가 명성 제외)",
                    				"damage": 2961,
                    				"default": {
                    					"damage": 1078,
                    					"buff": 312
                    				},
                    				"buff": 857
                    			}, {
                    				"level": 76,
                    				"expRate": 29.11,
                    				"explain": "HP 1분당 460.2 회복",
                    				"explainDetail": "HP 1분당 460.2 회복",
                    				"damage": 3257,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1357
                    			}, {
                    				"level": 74,
                    				"expRate": 88.42,
                    				"explain": "MP 1분당 348 회복",
                    				"explainDetail": "MP 1분당 348 회복",
                    				"damage": 3226,
                    				"default": {
                    					"damage": 1186,
                    					"buff": 494
                    				},
                    				"buff": 1344
                    			}]
                    		}
                    	}],
                    	"setItemInfo": []
                    }
                    """;
        }

        @Override
        public String creature() {
            return """
                    {
                    	"characterId": "87c7ed846938a184f71b338af88bdf4a",
                    	"characterName": "Les_Paul",
                    	"level": 110,
                    	"jobId": "b9cb48777665de22c006fabaf9a560b3",
                    	"jobGrowId": "37495b941da3b1661bc900e68ef3b2c6",
                    	"jobName": "아처",
                    	"jobGrowName": "眞 뮤즈",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"creature": {
                    		"itemId": "f5c87c63bbdaa5413a105640cafbc30a",
                    		"itemName": "순백의 나비 공주",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"artifact": [{
                    			"slotColor": "RED",
                    			"itemName": "가고일의 눈",
                    			"itemAvailableLevel": 0,
                    			"itemRarity": "레어"
                    		}, {
                    			"slotColor": "BLUE",
                    			"itemName": "실프의 눈물",
                    			"itemAvailableLevel": 0,
                    			"itemRarity": "레어"
                    		}, {
                    			"slotColor": "GREEN",
                    			"itemName": "바람의 머리장식",
                    			"itemAvailableLevel": 0,
                    			"itemRarity": "레어"
                    		}]
                    	}
                    }
                    """;
        }

        @Override
        public String avatar() {
            return """
                    {
                    	"characterId": "87c7ed846938a184f71b338af88bdf4a",
                    	"characterName": "Les_Paul",
                    	"level": 110,
                    	"jobId": "b9cb48777665de22c006fabaf9a560b3",
                    	"jobGrowId": "37495b941da3b1661bc900e68ef3b2c6",
                    	"jobName": "아처",
                    	"jobGrowName": "眞 뮤즈",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"avatar": [{
                    		"slotId": "HEADGEAR",
                    		"slotName": "모자 아바타",
                    		"itemId": "2defdc2422ff3c61f5ac1af583e79459",
                    		"itemName": "레어 모자 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "a72fdf735b3c39014e0d61a810e846a1",
                    			"itemName": "헤드라이너의 나비 베레모[A타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "정신력 55 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "붉은빛",
                    			"itemName": "찬란한 붉은빛 엠블렘[정신력]",
                    			"itemRarity": "유니크"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "붉은빛",
                    			"itemName": "찬란한 붉은빛 엠블렘[정신력]",
                    			"itemRarity": "유니크"
                    		}]
                    	}, {
                    		"slotId": "HAIR",
                    		"slotName": "머리 아바타",
                    		"itemId": "970d6d278c4fa2d2e8c9e74f33a46ae1",
                    		"itemName": "레어 머리 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "f3d8d0f8eef64eb5adb4f271ef50322e",
                    			"itemName": "헤드라이너의 웨이비 헤어[A타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "정신력 55 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "붉은빛",
                    			"itemName": "찬란한 붉은빛 엠블렘[정신력]",
                    			"itemRarity": "유니크"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "붉은빛",
                    			"itemName": "찬란한 붉은빛 엠블렘[정신력]",
                    			"itemRarity": "유니크"
                    		}]
                    	}, {
                    		"slotId": "FACE",
                    		"slotName": "얼굴 아바타",
                    		"itemId": "2ee5151fe13188a556ea594222187bb5",
                    		"itemName": "레어 얼굴 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "a5823356ef7ab5fb67a5b4426de5d762",
                    			"itemName": "헤드라이너의 맑은 눈[A타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "공격 속도 6.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "노란빛",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "노란빛",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "JACKET",
                    		"slotName": "상의 아바타",
                    		"itemId": "cb12bb026990e7595103bb214c31960d",
                    		"itemName": "레어 상의 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "6ff130bf5e4bf2d0ab433afaccf21fa5",
                    			"itemName": "헤드라이너의 보송 오프 숄더[A타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "온 더 스테이지 스킬Lv +1",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "플래티넘",
                    			"itemName": "플래티넘 엠블렘[러블리 템포]",
                    			"itemRarity": "레전더리"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 녹색빛 엠블렘[HP MAX]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 3,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 녹색빛 엠블렘[HP MAX]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "PANTS",
                    		"slotName": "하의 아바타",
                    		"itemId": "37b761ce1173ea36d816e642aba5ba7b",
                    		"itemName": "레어 하의 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "62c929efdfde35000e2e6e880d1bb9db",
                    			"itemName": "헤드라이너의 치마 바지[A타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "HP MAX 400 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "플래티넘",
                    			"itemName": "플래티넘 엠블렘[러블리 템포]",
                    			"itemRarity": "레전더리"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 녹색빛 엠블렘[HP MAX]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 3,
                    			"slotColor": "녹색빛",
                    			"itemName": "화려한 녹색빛 엠블렘[HP MAX]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "SHOES",
                    		"slotName": "신발 아바타",
                    		"itemId": "0371784066078365c89897de3cacb6d8",
                    		"itemName": "레어 신발 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "3a5586f4b9393c0e8b19ddb9119704ad",
                    			"itemName": "헤드라이너의 체인 워커[A타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "이동 속도 6.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "푸른빛",
                    			"itemName": "화려한 푸른빛 엠블렘[이동속도]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "푸른빛",
                    			"itemName": "화려한 푸른빛 엠블렘[이동속도]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "BREAST",
                    		"slotName": "목가슴 아바타",
                    		"itemId": "3aaebf23928e11dafd00150b320e95cd",
                    		"itemName": "레어 목가슴 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "b888f43397b85a26f3802f69cfb2e383",
                    			"itemName": "헤드라이너의 피크 초커[A타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "공격 속도 6.0% 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "노란빛",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "노란빛",
                    			"itemName": "화려한 노란빛 엠블렘[공격속도]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "WAIST",
                    		"slotName": "허리 아바타",
                    		"itemId": "88b6d64f0766484b2cdcc898eea4dc18",
                    		"itemName": "레어 허리 클론 아바타",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "bd6d541bf9d8a7f72c33dbd80982ce91",
                    			"itemName": "헤드라이너의 나비 체인 벨트[A타입]"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "화속성 저항 35 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "푸른빛",
                    			"itemName": "화려한 푸른빛 엠블렘[이동속도]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "푸른빛",
                    			"itemName": "화려한 푸른빛 엠블렘[이동속도]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "SKIN",
                    		"slotName": "스킨 아바타",
                    		"itemId": "892fa00f5367420267414c4d4cdc0064",
                    		"itemName": "진 뮤즈의 순백의 피부[A타입]",
                    		"itemRarity": "커먼",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "물리 방어력 1000 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "다색",
                    			"itemName": "화려한 붉은빛 엠블렘[정신력]",
                    			"itemRarity": "레어"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "다색",
                    			"itemName": "화려한 붉은빛 엠블렘[정신력]",
                    			"itemRarity": "레어"
                    		}]
                    	}, {
                    		"slotId": "AURORA",
                    		"slotName": "오라 아바타",
                    		"itemId": "35a3c29664972158840223b6b0d09960",
                    		"itemName": "로얄 패스 오라[기간제]",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": null,
                    		"emblems": []
                    	}, {
                    		"slotId": "WEAPON",
                    		"slotName": "무기 아바타",
                    		"itemId": "2650069a87d6eaaac188383346669205",
                    		"itemName": "레어 무기 클론 아바타[80Lv]",
                    		"itemRarity": "레어",
                    		"clone": {
                    			"itemId": "e0011085622b7f21a88786efc62453c4",
                    			"itemName": "일렉 기타"
                    		},
                    		"random": {
                    			"itemId": null,
                    			"itemName": null
                    		},
                    		"optionAbility": "정신력 55 증가",
                    		"emblems": [{
                    			"slotNo": 1,
                    			"slotColor": "다색",
                    			"itemName": "찬란한 붉은빛 엠블렘[정신력]",
                    			"itemRarity": "유니크"
                    		}, {
                    			"slotNo": 2,
                    			"slotColor": "다색",
                    			"itemName": "찬란한 붉은빛 엠블렘[정신력]",
                    			"itemRarity": "유니크"
                    		}]
                    	}]
                    }
                    """;
        }
    }

    // 남메카
    // o레알돋는당o
    // 크리쳐, 버프강화, 아바타 x
    private static class Character5 implements Character {
        public String characterId = "a22d41ea2257bcd1459c84b96bd303e3";
        @Override
        public String status() {
            return """
                    {
                    	"characterId": "a22d41ea2257bcd1459c84b96bd303e3",
                    	"characterName": "v레알돋는당v",
                    	"level": 100,
                    	"jobId": "0c1b401bb09241570d364420b3ba3fd7",
                    	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                    	"jobName": "프리스트(여)",
                    	"jobGrowName": "眞 이단심판관",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"buff": [{
                    		"name": "모험단 버프",
                    		"level": 37,
                    		"status": [{
                    			"name": "힘",
                    			"value": 280
                    		}, {
                    			"name": "지능",
                    			"value": 280
                    		}, {
                    			"name": "체력",
                    			"value": 280
                    		}, {
                    			"name": "정신력",
                    			"value": 280
                    		}]
                    	}, {
                    		"name": "무제한 길드능력치",
                    		"status": [{
                    			"name": "힘",
                    			"value": 30
                    		}, {
                    			"name": "지능",
                    			"value": 30
                    		}, {
                    			"name": "체력",
                    			"value": 30
                    		}, {
                    			"name": "정신력",
                    			"value": 30
                    		}]
                    	}, {
                    		"name": "기간제 길드능력치",
                    		"status": [{
                    			"name": "힘",
                    			"value": 30
                    		}, {
                    			"name": "지능",
                    			"value": 30
                    		}, {
                    			"name": "체력",
                    			"value": 30
                    		}, {
                    			"name": "정신력",
                    			"value": 30
                    		}]
                    	}],
                    	"status": [{
                    		"name": "HP",
                    		"value": 94425
                    	}, {
                    		"name": "MP",
                    		"value": 53344
                    	}, {
                    		"name": "물리 방어율",
                    		"value": 23.3
                    	}, {
                    		"name": "마법 방어율",
                    		"value": 21.5
                    	}, {
                    		"name": "힘",
                    		"value": 3485
                    	}, {
                    		"name": "지능",
                    		"value": 2820
                    	}, {
                    		"name": "체력",
                    		"value": 2001
                    	}, {
                    		"name": "정신력",
                    		"value": 1614
                    	}, {
                    		"name": "물리 공격",
                    		"value": 2005
                    	}, {
                    		"name": "마법 공격",
                    		"value": 1254
                    	}, {
                    		"name": "물리 크리티컬",
                    		"value": 57
                    	}, {
                    		"name": "마법 크리티컬",
                    		"value": 47
                    	}, {
                    		"name": "독립 공격",
                    		"value": 1959
                    	}, {
                    		"name": "공격 속도",
                    		"value": 17
                    	}, {
                    		"name": "캐스팅 속도",
                    		"value": 37
                    	}, {
                    		"name": "이동 속도",
                    		"value": 36
                    	}, {
                    		"name": "모험가 명성",
                    		"value": 10272
                    	}, {
                    		"name": "적중률",
                    		"value": 20
                    	}, {
                    		"name": "회피율",
                    		"value": 4
                    	}, {
                    		"name": "HP 회복량",
                    		"value": 810
                    	}, {
                    		"name": "MP 회복량",
                    		"value": 2883
                    	}, {
                    		"name": "경직도",
                    		"value": 0
                    	}, {
                    		"name": "히트리커버리",
                    		"value": 684
                    	}, {
                    		"name": "화속성 강화",
                    		"value": 20
                    	}, {
                    		"name": "화속성 저항",
                    		"value": 11
                    	}, {
                    		"name": "수속성 강화",
                    		"value": 13
                    	}, {
                    		"name": "수속성 저항",
                    		"value": 11
                    	}, {
                    		"name": "명속성 강화",
                    		"value": 20
                    	}, {
                    		"name": "명속성 저항",
                    		"value": 11
                    	}, {
                    		"name": "암속성 강화",
                    		"value": 13
                    	}, {
                    		"name": "암속성 저항",
                    		"value": 11
                    	}, {
                    		"name": "물리 방어",
                    		"value": 30300
                    	}, {
                    		"name": "마법 방어",
                    		"value": 27376
                    	}, {
                    		"name": "피해 증가",
                    		"value": 48444
                    	}, {
                    		"name": "버프력",
                    		"value": 0
                    	}, {
                    		"name": "데미지 증가",
                    		"value": 0
                    	}, {
                    		"name": "크리티컬 데미지 증가",
                    		"value": 0
                    	}, {
                    		"name": "추가 데미지 증가",
                    		"value": 0
                    	}, {
                    		"name": "모든 공격력 증가",
                    		"value": 5
                    	}, {
                    		"name": "스킬 공격력 증가",
                    		"value": 716.5
                    	}, {
                    		"name": "물리 공격력 증가",
                    		"value": 0
                    	}, {
                    		"name": "마법 공격력 증가",
                    		"value": 0
                    	}, {
                    		"name": "독립 공격력 증가",
                    		"value": 0
                    	}, {
                    		"name": "힘 증가",
                    		"value": 6
                    	}, {
                    		"name": "지능 증가",
                    		"value": 6
                    	}, {
                    		"name": "지속피해",
                    		"value": 0
                    	}, {
                    		"name": "쿨타임 감소",
                    		"value": 0
                    	}, {
                    		"name": "물리 피해 감소",
                    		"value": 0
                    	}, {
                    		"name": "마법 피해 감소",
                    		"value": 0
                    	}, {
                    		"name": "출혈 데미지 전환",
                    		"value": 0
                    	}, {
                    		"name": "중독 데미지 전환",
                    		"value": 0
                    	}, {
                    		"name": "화상 데미지 전환",
                    		"value": 0
                    	}, {
                    		"name": "감전 데미지 전환",
                    		"value": 0
                    	}, {
                    		"name": "출혈 내성",
                    		"value": 0
                    	}, {
                    		"name": "중독 내성",
                    		"value": 0
                    	}, {
                    		"name": "화상 내성",
                    		"value": 0
                    	}, {
                    		"name": "감전 내성",
                    		"value": 0
                    	}, {
                    		"name": "빙결 내성",
                    		"value": 0
                    	}, {
                    		"name": "둔화 내성",
                    		"value": 0
                    	}, {
                    		"name": "기절 내성",
                    		"value": 0
                    	}, {
                    		"name": "저주 내성",
                    		"value": 0
                    	}, {
                    		"name": "암흑 내성",
                    		"value": 0
                    	}, {
                    		"name": "석화 내성",
                    		"value": 0
                    	}, {
                    		"name": "수면 내성",
                    		"value": 0
                    	}, {
                    		"name": "혼란 내성",
                    		"value": 0
                    	}, {
                    		"name": "구속 내성",
                    		"value": 0
                    	}]
                    }
                    """;
        }

        @Override
        public String buff() {
            return """
                    {
                      "characterId" : "a22d41ea2257bcd1459c84b96bd303e3",
                      "characterName" : "v레알돋는당v",
                      "level" : 100,
                      "jobId" : "0c1b401bb09241570d364420b3ba3fd7",
                      "jobGrowId" : "618326026de1a1f1cfba5dbd0b8396e7",
                      "jobName" : "프리스트(여)",
                      "jobGrowName" : "眞 이단심판관",
                      "adventureName" : "레알돋는당",
                      "guildId" : "a8c2264db4b35110f220670aa8ada80a",
                      "guildName" : "개시바",
                      "skill" : {
                        "buff" : {
                          "skillInfo" : {
                            "skillId" : "e49e57b2e8fbeceb0a2c56a0c63fe6c5",
                            "name" : "광적인 믿음",
                            "option" : {
                              "level" : 12,
                              "desc" : "버프 유지 시간 : {value1}초\\n물리 크리티컬 확률 증가율 : {value2}%\\n물리 크리티컬 공격력 증가율 : {value3}%",
                              "values" : [ "-", "12", "53" ]
                            }
                          },
                          "equipment" : [ {
                            "slotId" : "WEAPON",
                            "slotName" : "무기",
                            "itemId" : "859062737fae8bcc1075989b4411931d",
                            "itemName" : "심연의 편린 배틀액스S : 광적인 믿음",
                            "itemTypeId" : "4ffb6f14b86f5c818a925bf58022686e",
                            "itemType" : "무기",
                            "itemTypeDetailId" : "c6d5c7c1a46377150d46ad070ae38492",
                            "itemTypeDetail" : "배틀액스",
                            "itemAvailableLevel" : 95,
                            "itemRarity" : "레전더리",
                            "setItemId" : "1c0b481a0b9aff802b96db97071d3e10",
                            "setItemName" : "심연의 편린 광적인 믿음 세트",
                            "reinforce" : 0,
                            "amplificationName" : null,
                            "refine" : 0
                          } ]
                        }
                      }
                    }
                    """;
        }

        @Override
        public String equipment() {
            return """
                    {
                    	"characterId": "a22d41ea2257bcd1459c84b96bd303e3",
                    	"characterName": "v레알돋는당v",
                    	"level": 100,
                    	"jobId": "0c1b401bb09241570d364420b3ba3fd7",
                    	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                    	"jobName": "프리스트(여)",
                    	"jobGrowName": "眞 이단심판관",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"equipment": [{
                    		"slotId": "WEAPON",
                    		"slotName": "무기",
                    		"itemId": "a49ea86dcefb6d13119ed06a99b57441",
                    		"itemName": "신념의 무게",
                    		"itemTypeId": "4ffb6f14b86f5c818a925bf58022686e",
                    		"itemType": "무기",
                    		"itemTypeDetailId": "543c20eb9822b8cfdc5a41b6e4bfac98",
                    		"itemTypeDetail": "배틀액스",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"transformInfo": {
                    			"explain": "피해 증가 +250",
                    			"explainDetail": "피해 증가 +250 (변환 시 랜덤한 피해 증가 375 ~ 750 옵션으로 변경됩니다.)",
                    			"optionType": "transformOption",
                    			"active": false
                    		},
                    		"itemGradeName": "최상급",
                    		"amplificationName": null,
                    		"refine": 0,
                    		"sirocoInfo": {
                    			"options": [{
                    				"explain": "힘, 지능 6% 증가",
                    				"explainDetail": "힘, 지능 6% 증가 (6%~10%)",
                    				"buffExplain": "-버퍼 전용 옵션\\n\\n수호의 은총 체력, 정신력 125 증가\\n계시 : 아리아, 퍼페티어 지능 125 증가",
                    				"buffExplainDetail": "-버퍼 전용 옵션\\n\\n수호의 은총 체력, 정신력 125 증가 (125~185, 해당 옵션은 30의 배수로만 나옵니다.)\\n계시 : 아리아, 퍼페티어 지능 125 증가 (125~185, 해당 옵션은 30의 배수로만 나옵니다.)"
                    			}, {
                    				"explain": "무형, 무의식, 환영 장비 모두 장착 시 던전 내에서 효과 발동\\n- 공격 시 5% 추가 데미지",
                    				"explainDetail": "무형, 무의식, 환영 장비 모두 장착 시 던전 내에서 효과 발동\\n- 공격 시 5% 추가 데미지 (3%~5%)",
                    				"buffExplain": "-버퍼 전용 옵션\\n\\n무형, 무의식, 환영 장비 모두 장착 시 던전 내에서 효과 발동\\n- 30Lv 버프 스킬 물리, 마법, 독립 공격력 증가량 3% 증가 \\n50Lv 액티브 스킬 힘, 지능 증가량 25 증가",
                    				"buffExplainDetail": "-버퍼 전용 옵션\\n\\n무형, 무의식, 환영 장비 모두 장착 시 던전 내에서 효과 발동\\n- 30Lv 버프 스킬 물리, 마법, 독립 공격력 증가량 3% (1%~3%)증가 \\n50Lv 액티브 스킬 힘, 지능 증가량 25 증가"
                    			}]
                    		},
                    		"upgradeInfo": {
                    			"itemId": "b4e66979f382a9fb2d93eced09c95927",
                    			"itemName": "무형의 잔향"
                    		}
                    	}, {
                    		"slotId": "TITLE",
                    		"slotName": "칭호",
                    		"itemId": "5e87ce4a46b20aa458c127fe5a5d731a",
                    		"itemName": "판타스틱 점핑",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "691933a06ff114348a7db936fb0aec66",
                    		"itemTypeDetail": "칭호",
                    		"itemAvailableLevel": 1,
                    		"itemRarity": "에픽",
                    		"setItemId": null,
                    		"setItemName": null,
                    		"reinforce": 0,
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "JACKET",
                    		"slotName": "상의",
                    		"itemId": "e34f66d79c65dbfc9fded26ec4093e1e",
                    		"itemName": "종말의 역전",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "d8d2efdab287895452f0c8a54e80826d",
                    		"itemTypeDetail": "중갑 상의",
                    		"itemAvailableLevel": 105,
                    		"itemRarity": "신화",
                    		"setItemId": "33348fff4c17d204c4655ee50e2c6bdc",
                    		"setItemName": "기구한 운명 세트",
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"amplificationName": null,
                    		"refine": 0,
                    		"mythologyInfo": {
                    			"options": [{
                    				"explain": "모든 직업 1~48레벨 모든 스킬Lv +1",
                    				"explainDetail": "모든 직업 1~48레벨 모든 스킬Lv +1 (35~48)",
                    				"buffExplain": "50Lv 액티브 스킬 힘, 지능 증가량 4% 증가",
                    				"buffExplainDetail": "50Lv 액티브 스킬 힘, 지능 증가량 4% 증가 (1%~4%)"
                    			}, {
                    				"explain": "물리, 마법, 독립 공격력 70 증가",
                    				"explainDetail": "물리, 마법, 독립 공격력 70 증가 (10~70, 해당 옵션 수치는 10의 배수로만 나옵니다.)",
                    				"buffExplain": "수호의 은총, 계시 : 아리아, 퍼페티어, 센세이션 스킬Lv +7",
                    				"buffExplainDetail": "수호의 은총, 계시 : 아리아, 퍼페티어, 센세이션 스킬Lv +7 (1~7)"
                    			}, {
                    				"explain": "모든 공격력 5% 증가",
                    				"explainDetail": "모든 공격력 5% 증가 (1%~5%)",
                    				"buffExplain": "30Lv 버프 스킬 물리, 마법, 독립 공격력 증가량 5% 증가",
                    				"buffExplainDetail": "30Lv 버프 스킬 물리, 마법, 독립 공격력 증가량 5% 증가 (1%~5%)"
                    			}, {
                    				"explain": "힘, 지능 140 증가",
                    				"explainDetail": "힘, 지능 140 증가 (40~160, 해당 옵션 수치는 20의 배수로만 나옵니다.)",
                    				"buffExplain": "수호의 은총 체력, 정신력 140 증가",
                    				"buffExplainDetail": "수호의 은총 체력, 정신력 140 증가 (40~160, 해당 옵션 수치는 20의 배수로만 나옵니다.)"
                    			}]
                    		}
                    	}, {
                    		"slotId": "SHOULDER",
                    		"slotName": "머리어깨",
                    		"itemId": "202e3240a7d184a10a00b10f54134747",
                    		"itemName": "트로피카 : 두리안",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "51234d181dc4abf7dc948ca2abaa3d30",
                    		"itemTypeDetail": "중갑 머리어깨",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": "25f94ec65bfb7fe250744c3ef186a25f",
                    		"setItemName": "열대의 트로피카 세트",
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "마법 크리티컬 히트",
                    				"value": "5%"
                    			}, {
                    				"name": "물리 크리티컬 히트",
                    				"value": "5%"
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "PANTS",
                    		"slotName": "하의",
                    		"itemId": "4f9b33ab2503c2625772b22fb3cbac7b",
                    		"itemName": "따르는 광기의 기운",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "af5678d99e6c5c5cc50e60e81c66a7ac",
                    		"itemTypeDetail": "중갑 하의",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": "4409ac3fd335adeea84fe227fcbe0fa9",
                    		"setItemName": "광란의 추종자 세트",
                    		"reinforce": 0,
                    		"transformInfo": {
                    			"explain": "피해 증가 +50",
                    			"explainDetail": "피해 증가 +50 (변환 시 랜덤한 피해 증가 150 ~ 450 옵션으로 변경됩니다. 또한 버퍼 전용 옵션이 추가됩니다.)",
                    			"optionType": "transformOption",
                    			"active": false
                    		},
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "물리 공격력",
                    				"value": 50
                    			}, {
                    				"name": "마법 공격력",
                    				"value": 50
                    			}, {
                    				"name": "독립 공격력",
                    				"value": 50
                    			}, {
                    				"name": "힘",
                    				"value": 20
                    			}, {
                    				"name": "지능",
                    				"value": 20
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "SHOES",
                    		"slotName": "신발",
                    		"itemId": "81b19b0ddf770a74ea5751d7ab9fc781",
                    		"itemName": "차원을 걷는 물소 부츠",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "9f097ffd4f7fddb4ccbcbca60903112c",
                    		"itemTypeDetail": "중갑 신발",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": "bcd72f6c009738cb256b13403fc84b8c",
                    		"setItemName": "차원의 여행자 세트",
                    		"reinforce": 0,
                    		"itemGradeName": "상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "마법 크리티컬 히트",
                    				"value": "5%"
                    			}, {
                    				"name": "물리 크리티컬 히트",
                    				"value": "5%"
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "WAIST",
                    		"slotName": "허리",
                    		"itemId": "12ba2c9116503d08356510a6e53e3530",
                    		"itemName": "트로피카 : 망고스틴",
                    		"itemTypeId": "b09752bfff1935188ea2735cd925a872",
                    		"itemType": "방어구",
                    		"itemTypeDetailId": "851ab631130f0f129272aa17e5053b2b",
                    		"itemTypeDetail": "중갑 허리",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": "25f94ec65bfb7fe250744c3ef186a25f",
                    		"setItemName": "열대의 트로피카 세트",
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "마법 크리티컬 히트",
                    				"value": "5%"
                    			}, {
                    				"name": "물리 크리티컬 히트",
                    				"value": "5%"
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "AMULET",
                    		"slotName": "목걸이",
                    		"itemId": "da5e4132290136b6bae3d1d8e2692446",
                    		"itemName": "비통한 자의 목걸이",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "390e3966118b0c466ce9f8eae45e1629",
                    		"itemTypeDetail": "목걸이",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": "33348fff4c17d204c4655ee50e2c6bdc",
                    		"setItemName": "기구한 운명 세트",
                    		"reinforce": 0,
                    		"itemGradeName": "상급",
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "WRIST",
                    		"slotName": "팔찌",
                    		"itemId": "2b0d249f743023bebc653fd5b7bcac8e",
                    		"itemName": "광란을 품은 자",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "80bddf423629c28c7b4459c328fdffaf",
                    		"itemTypeDetail": "팔찌",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": "4409ac3fd335adeea84fe227fcbe0fa9",
                    		"setItemName": "광란의 추종자 세트",
                    		"reinforce": 0,
                    		"itemGradeName": "상급",
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "RING",
                    		"slotName": "반지",
                    		"itemId": "e79a8bcf880c6467f10b4f3530f64f4a",
                    		"itemName": "차원을 지나는 자의 인장",
                    		"itemTypeId": "e490f8cce6bacd43bef1e2f5e0c4943d",
                    		"itemType": "액세서리",
                    		"itemTypeDetailId": "b04c7fb9b29b27b91a0a9e5a1822bc8f",
                    		"itemTypeDetail": "반지",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": "bcd72f6c009738cb256b13403fc84b8c",
                    		"setItemName": "차원의 여행자 세트",
                    		"reinforce": 0,
                    		"transformInfo": {
                    			"explain": "피해 증가 +50",
                    			"explainDetail": "피해 증가 +50 (변환 시 랜덤한 피해 증가 150 ~ 450 옵션으로 변경됩니다. 또한 버퍼 전용 옵션이 추가됩니다.)",
                    			"optionType": "transformOption",
                    			"active": false
                    		},
                    		"itemGradeName": "상급",
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "SUPPORT",
                    		"slotName": "보조장비",
                    		"itemId": "33727ea5e4d52bf641bd15ba2556bc75",
                    		"itemName": "비운의 유물",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "2fef5d81b7f59f0c75241890a8d941c9",
                    		"itemTypeDetail": "보조장비",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": "33348fff4c17d204c4655ee50e2c6bdc",
                    		"setItemName": "기구한 운명 세트",
                    		"reinforce": 0,
                    		"transformInfo": {
                    			"explain": "피해 증가 +50",
                    			"explainDetail": "피해 증가 +50 (변환 시 랜덤한 피해 증가 150 ~ 450 옵션으로 변경됩니다. 또한 버퍼 전용 옵션이 추가됩니다.)",
                    			"optionType": "transformOption",
                    			"active": false
                    		},
                    		"itemGradeName": "최상급",
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "MAGIC_STON",
                    		"slotName": "마법석",
                    		"itemId": "147e35acab785cc70a363286d38689c0",
                    		"itemName": "폭주하는 광란의 힘",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "fe5f3db78175f5a3196385c688d29681",
                    		"itemTypeDetail": "마법석",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": "4409ac3fd335adeea84fe227fcbe0fa9",
                    		"setItemName": "광란의 추종자 세트",
                    		"reinforce": 0,
                    		"itemGradeName": "최상급",
                    		"amplificationName": null,
                    		"refine": 0
                    	}, {
                    		"slotId": "EARRING",
                    		"slotName": "귀걸이",
                    		"itemId": "ecfc048f1bcb1b9aa4156126cd1a3448",
                    		"itemName": "차원을 맴도는 혜성",
                    		"itemTypeId": "87ff665868ffd50f8aef7948548439bd",
                    		"itemType": "추가장비",
                    		"itemTypeDetailId": "601834074c49bb0e48cb65a75a8667bc",
                    		"itemTypeDetail": "귀걸이",
                    		"itemAvailableLevel": 100,
                    		"itemRarity": "에픽",
                    		"setItemId": "bcd72f6c009738cb256b13403fc84b8c",
                    		"setItemName": "차원의 여행자 세트",
                    		"reinforce": 6,
                    		"itemGradeName": "상급",
                    		"enchant": {
                    			"status": [{
                    				"name": "힘",
                    				"value": 110
                    			}, {
                    				"name": "지능",
                    				"value": 110
                    			}, {
                    				"name": "체력",
                    				"value": 110
                    			}, {
                    				"name": "정신력",
                    				"value": 110
                    			}]
                    		},
                    		"amplificationName": null,
                    		"refine": 0
                    	}],
                    	"setItemInfo": [{
                    		"setItemId": "bcd72f6c009738cb256b13403fc84b8c",
                    		"setItemName": "차원의 여행자 세트",
                    		"slotInfo": [{
                    			"slotId": "SHOES",
                    			"slotName": "신발",
                    			"itemRarity": "에픽"
                    		}, {
                    			"slotId": "RING",
                    			"slotName": "반지",
                    			"itemRarity": "에픽"
                    		}, {
                    			"slotId": "EARRING",
                    			"slotName": "귀걸이",
                    			"itemRarity": "에픽"
                    		}],
                    		"activeSetNo": 3
                    	}, {
                    		"setItemId": "33348fff4c17d204c4655ee50e2c6bdc",
                    		"setItemName": "기구한 운명 세트",
                    		"slotInfo": [{
                    			"slotId": "JACKET",
                    			"slotName": "상의",
                    			"itemRarity": "신화"
                    		}, {
                    			"slotId": "AMULET",
                    			"slotName": "목걸이",
                    			"itemRarity": "에픽"
                    		}, {
                    			"slotId": "SUPPORT",
                    			"slotName": "보조장비",
                    			"itemRarity": "에픽"
                    		}],
                    		"activeSetNo": 3
                    	}, {
                    		"setItemId": "4409ac3fd335adeea84fe227fcbe0fa9",
                    		"setItemName": "광란의 추종자 세트",
                    		"slotInfo": [{
                    			"slotId": "PANTS",
                    			"slotName": "하의",
                    			"itemRarity": "에픽"
                    		}, {
                    			"slotId": "WRIST",
                    			"slotName": "팔찌",
                    			"itemRarity": "에픽"
                    		}, {
                    			"slotId": "MAGIC_STON",
                    			"slotName": "마법석",
                    			"itemRarity": "에픽"
                    		}],
                    		"activeSetNo": 3
                    	}, {
                    		"setItemId": "25f94ec65bfb7fe250744c3ef186a25f",
                    		"setItemName": "열대의 트로피카 세트",
                    		"slotInfo": [{
                    			"slotId": "SHOULDER",
                    			"slotName": "머리어깨",
                    			"itemRarity": "에픽"
                    		}, {
                    			"slotId": "WAIST",
                    			"slotName": "허리",
                    			"itemRarity": "에픽"
                    		}],
                    		"activeSetNo": 2
                    	}]
                    }
                    """;
        }

        @Override
        public String creature() {
            return """
                    {
                      "characterId" : "a22d41ea2257bcd1459c84b96bd303e3",
                      "characterName" : "v레알돋는당v",
                      "level" : 100,
                      "jobId" : "0c1b401bb09241570d364420b3ba3fd7",
                      "jobGrowId" : "618326026de1a1f1cfba5dbd0b8396e7",
                      "jobName" : "프리스트(여)",
                      "jobGrowName" : "眞 이단심판관",
                      "adventureName" : "레알돋는당",
                      "guildId" : "a8c2264db4b35110f220670aa8ada80a",
                      "guildName" : "개시바",
                      "creature" : null
                    }
                    """;
        }

        @Override
        public String avatar() {
            return """
                    {
                    	"characterId": "27b4a0549c017683693e35a429fe09d6",
                    	"characterName": "o레알돋는당o",
                    	"level": 100,
                    	"jobId": "afdf3b989339de478e85b614d274d1ef",
                    	"jobGrowId": "6d459bc74ba73ee4fe5cdc4655400193",
                    	"jobName": "거너(남)",
                    	"jobGrowName": "眞 메카닉",
                    	"adventureName": "레알돋는당",
                    	"guildId": "a8c2264db4b35110f220670aa8ada80a",
                    	"guildName": "개시바",
                    	"avatar": []
                    }
                    """;
        }
    }
}
