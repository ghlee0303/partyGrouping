package com.party_grouping.service.mock_test;

import com.party_grouping.util.ApiUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MockTest {

    @Test
    void sss() {
        LocalDateTime l = LocalDateTime.now().minusDays(2);

        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ApiUtils.getNowDunDate()));
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }

    @Test
    void eee() {
        String jsonText1 = """
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
                	"timeline": {
                		"date": {
                			"start": "2023-07-06 10:00",
                			"end": "2023-07-10 00:25"
                		},
                		"next": null,
                		"rows": [{
                			"code": 209,
                			"name": "레기온 클리어",
                			"date": "2023-07-09 03:52",
                			"data": {
                				"regionName": "차원회랑"
                			}
                		}, {
                			"code": 209,
                			"name": "레기온 클리어",
                			"date": "2023-07-09 03:23",
                			"data": {
                				"regionName": "이스핀즈"
                			}
                		}, {
                			"code": 201,
                			"name": "레이드",
                			"date": "2023-07-09 02:30",
                			"data": {
                				"raidName": "기계 혁명",
                				"raidPartyName": "일반 4.5 + 랏버퍼",
                				"modeName": "바칼 레이드"
                			}
                		}]
                	}
                }
                """;

        String jsonText2 = """
                {
                	"characterId": "b421dac6620a25467f9870d1f4991506",
                	"characterName": "하얀별의엘븐",
                	"level": 110,
                	"jobId": "0ee8fa5dc525c1a1f23fc6911e921e4a",
                	"jobGrowId": "37495b941da3b1661bc900e68ef3b2c6",
                	"jobName": "나이트",
                	"jobGrowName": "眞 엘븐나이트",
                	"adventureName": "하얀별동네",
                	"guildId": "0d20e4c11ca1cebc5fc695c25eb0f425",
                	"guildName": "ICO",
                	"timeline": {
                		"date": {
                			"start": "2023-06-10 01:36",
                			"end": "2023-07-10 01:36"
                		},
                		"next": null,
                		"rows": [{
                			"code": 209,
                			"name": "레기온 클리어",
                			"date": "2023-07-06 18:52",
                			"data": {
                				"regionName": "이스핀즈"
                			}
                		}, {
                			"code": 201,
                			"name": "레이드",
                			"date": "2023-07-04 18:12",
                			"data": {
                				"raidName": "기계 혁명",
                				"raidPartyName": "하얀별의엘븐",
                				"modeName": "개전"
                			}
                		}]
                	}
                }
                """;

        String jsonText3 = """
                {
                	"characterId": "1a80db82e8acafea783ed3e112d98a1b",
                	"characterName": "하얀별의빛",
                	"level": 110,
                	"jobId": "afdf3b989339de478e85b614d274d1ef",
                	"jobGrowId": "618326026de1a1f1cfba5dbd0b8396e7",
                	"jobName": "거너(남)",
                	"jobGrowName": "眞 런처",
                	"adventureName": "하얀별동네",
                	"guildId": "0d20e4c11ca1cebc5fc695c25eb0f425",
                	"guildName": "ICO",
                	"timeline": {
                		"date": {
                			"start": "2023-06-10 01:44",
                			"end": "2023-07-10 01:44"
                		},
                		"next": "d1d15be7a890450dc62cbe598c66ff96c9786e8fb93ee3e7c22ec23bab235585ac1da8343979d3255cd8e22afd632f2b36fdd4befd73f0dc365fdba310a89a79b6ad584315b639098ca3e5877651cdd307a15db4d6c1d5b9d66e053077d71c5c",
                		"rows": [{
                			"code": 201,
                			"name": "레이드",
                			"date": "2023-07-09 20:02",
                			"data": {
                				"raidName": "무형의 시로코",
                				"raidPartyName": "류마#6 하드 딜러 버퍼 모셔요.",
                				"phaseName": "토벌",
                				"hard": true
                			}
                		}, {
                			"code": 201,
                			"name": "레이드",
                			"date": "2023-07-09 20:00",
                			"data": {
                				"raidName": "무형의 시로코",
                				"raidPartyName": "류마#6 하드 딜러 버퍼 모셔요.",
                				"phaseName": "추적",
                				"hard": true
                			}
                		}, {
                			"code": 209,
                			"name": "레기온 클리어",
                			"date": "2023-07-07 03:47",
                			"data": {
                				"regionName": "이스핀즈"
                			}
                		}, {
                			"code": 209,
                			"name": "레기온 클리어",
                			"date": "2023-07-07 00:27",
                			"data": {
                				"regionName": "차원회랑"
                			}
                		}, {
                			"code": 201,
                			"name": "레이드",
                			"date": "2023-07-02 21:25",
                			"data": {
                				"raidName": "기계 혁명",
                				"raidPartyName": "하드 4.7+ 버퍼 시나오칭35",
                				"modeName": "바칼 레이드",
                				"hard": true
                			}
                		}]
                	}
                }
                """;

        JSONArray jsonArray = new JSONObject(jsonText3)
                .getJSONObject("timeline")
                .optJSONArray("rows");

        HashMap<String, String> good = test(jsonArray);
        System.out.println(good);


    }

    HashMap<String, String> test(JSONArray jsonArray) {
        HashMap<String, String> regionMap = new HashMap<>();
        regionMap.put("이스핀즈", "ispins");
        regionMap.put("차원회랑", "dimension");
        HashMap<String, String> result = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int code = jsonObject.optInt("code");
            String date = jsonObject.getString("date");
            switch (code) {
                case 201:
                    if (jsonObject.getJSONObject("data").optString("raidName").equals("기계 혁명"))
                        result.put("bakal", date);
                    break;
                case 209:
                    String regionName = jsonObject.getJSONObject("data").optString("regionName");

                    for (Map.Entry<String, String> entry : regionMap.entrySet()) {
                        if (entry.getKey().equals(regionName)) {
                            result.put(entry.getValue(), date);
                            break;
                        }
                    }
                    break;
            }
        }

        return result;
    }
}
