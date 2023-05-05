package com.party_grouping.api;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.CharacterItemDto;
import com.party_grouping.exception.ApiException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiDnF implements ApiCharacter {
    @Value("${dnf-api-key}")
    private static String apiKey;
    private final WebClient webClient;

    public ApiDnF() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.neople.co.kr/df/")
                .build();
    }

    @Override
    public CharacterDto callCharacterStatus(String server, String characterApiId) {
        String url = String.format("servers/%s/characters/%s/status?apikey=%s", server, characterApiId, apiKey);
        Mono<String> characterStatus = requestAPIDnF(url)
                .bodyToMono(String.class);

        JSONObject jsonObject = new JSONObject(characterStatus.block());

        if (jsonObject.getJSONArray("status").isEmpty()) {
            throw new ApiException("캐릭터가 잠금 상태입니다.", 404);
        }

        int fame = jsonObject.getJSONArray("status").getJSONObject(16).getInt("value");
        String adventureName = jsonObject.getString("adventureName");

        CharacterDto characterDto = setCharacterDto(jsonObject);
        characterDto.setFame(fame);
        characterDto.setAdventureName(adventureName);
        characterDto.setServer(server);
        characterDto.setBuff(callBuffSkill(server, characterApiId));

        return characterDto;
    }

    @Override
    public List<CharacterDto> callCharacter(String name, String type) {
        String url = String.format("/servers/%s/characters?characterName=%s&wordType=full&limit=8&apikey=%s",type, name, apiKey);
        Mono<String> characterData = requestAPIDnF(url)
                .bodyToMono(String.class);

        return parsingCharacterListJson(characterData.block());
    }

    @Override
    public CharacterItemDto callItem(String server, String characterApiId) {
        String url = String.format("servers/%s/characters/%s/equip/equipment?apikey=%s", server, characterApiId, apiKey);
        Mono<String> itemData = requestAPIDnF(url)
                .bodyToMono(String.class);

        JSONArray itemJsonArray = new JSONObject(itemData.block())
                .getJSONArray("equipment");

        if (itemJsonArray.length() != 13) {
            throw new ApiException("장착한 장비의 갯수가 13개가 되지 않습니다.", 404);
        }

        JSONObject weapon = itemJsonArray.getJSONObject(0);     // 무기
        JSONObject title = itemJsonArray.getJSONObject(1);      // 칭호
        JSONObject shoulder = itemJsonArray.getJSONObject(3);   // 어깨
        JSONObject belt = itemJsonArray.getJSONObject(6);       // 벨트
        JSONObject amulet = itemJsonArray.getJSONObject(7);     // 목걸이
        JSONObject wrist = itemJsonArray.getJSONObject(8);      // 팔찌
        JSONObject ring = itemJsonArray.getJSONObject(9);       // 반지
        JSONObject support = itemJsonArray.getJSONObject(10);   // 보조장비

        List<Integer> accessoryEnchantList = accessoryEnchantCalc(
                amulet.optJSONObject("enchant"),
                wrist.optJSONObject("enchant"),
                ring.optJSONObject("enchant")
        );

        return CharacterItemDto.builder()
                .weaponReinforce(weapon.getInt("reinforce"))
                .weaponRefine(weapon.getInt("refine"))
                .weaponAmp(weapon.get("amplificationName").toString())
                .title(title.getString("itemName"))
                .enchantSkillBonus(enchantSkillBonusCalc(
                        shoulder.optJSONObject("enchant"),
                        belt.optJSONObject("enchant")))
                .amulet(accessoryEnchantList.get(0))
                .wrist(accessoryEnchantList.get(1))
                .ring(accessoryEnchantList.get(2))
                .siv(supportEnchantCalc(support.optJSONObject("enchant")))
                .build();
    }

    private Buff callBuffSkill(String server, String characterApiId) {
        String url = String.format("servers/%s/characters/%s/skill/buff/equip/equipment?apikey=%s", server, characterApiId, apiKey);
        Mono<String> characterData = requestAPIDnF(url)
                .bodyToMono(String.class);

        JSONObject buff = new JSONObject(characterData.block())
                .getJSONObject("skill")
                .optJSONObject("buff");

        if (buff == null) {
            throw new ApiException("버프강화를 선택해주세요.", 404);
        }

        JSONObject skillInfo = buff
                .getJSONObject("skillInfo");

        String buffName = skillInfo.getString("name");
        String buffId = skillInfo.getString("skillId");
        int buffLevel = skillInfo.getJSONObject("option").getInt("level");

        return new Buff(buffLevel, buffName, buffId);
    }

    private Integer enchantSkillBonusCalc(JSONObject shoulder, JSONObject belt) {
        String find = "explain";
        String enchantShoulder = findJsonString(shoulder, find);
        String enchantBelt = findJsonString(belt, find);
        int calc = 0;

        calc = calc + skillBonusValue(enchantShoulder);
        calc = calc + skillBonusValue(enchantBelt);

        return calc;
    }

    private int skillBonusValue(String enchant) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(enchant);

        if (matcher.find()) {
            String number = matcher.group();
            return Integer.parseInt(number);
        }

        return 0;
     }

    private List<Integer> accessoryEnchantCalc(JSONObject amulet, JSONObject wrist, JSONObject ring) {
        String find = "status";
        List<JSONArray> jsonArrayList = Arrays.asList(
                findJsonArray(amulet, find),
                findJsonArray(wrist, find),
                findJsonArray(ring, find));
        List<Integer> resultList = new ArrayList<>();

        for (JSONArray jsonArray : jsonArrayList) {
            if (jsonArray != null) {
                JSONObject enchantStatus = jsonArray.getJSONObject(0);
                String enchantName = enchantStatus.getString("name");
                if (isElemental(enchantName)) {
                    resultList.add(enchantStatus.getInt("value"));
                }
            } else {
                resultList.add(0);
            }
        }

        return resultList;
    }

    private boolean isElemental(String enchantName) {
        return switch (enchantName) {
            case "모든 속성 강화", "화속성강화", "수속성강화", "명속성강화", "암속성강화" -> true;
            default -> false;
        };
    }

    private boolean supportEnchantCalc(JSONObject support) {
        System.out.println("/////////");
        JSONArray supportJsonArray = findJsonArray(support, "status");
        System.out.println(supportJsonArray);

        if (supportJsonArray == null || supportJsonArray.length() != 4) {
            return false;
        }

        JSONObject supportJsonObject = supportJsonArray.optJSONObject(3);
        System.out.println(supportJsonObject);
        String siv = supportJsonObject.getString("name");
        System.out.println(siv);

        return siv.equals("피해 증가");
    }

    private JSONArray findJsonArray(JSONObject jsonObject, String find) {
        return jsonObject == null ? null : jsonObject.optJSONArray(find);
    }

    private String findJsonString(JSONObject jsonObject, String find) {
        return jsonObject == null ? "" : jsonObject.optString(find);
    }

    private List<CharacterDto> parsingCharacterListJson(String response) {
        List<CharacterDto> characterDtoList = new ArrayList<>();

        JSONArray jsonArray = new JSONObject(response)
                .getJSONArray("rows");

        if (jsonArray.isEmpty()) {
            throw new ApiException("검색 결과가 없습니다.", 404);
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            characterDtoList.add(setCharacterDto(jsonArray.getJSONObject(i)));
        }

        return characterDtoList;
    }

    private CharacterDto setCharacterDto(JSONObject json) {
        return CharacterDto.builder()
                .name(json.getString("characterName"))
                .level(json.getInt("level"))
                .apiId(json.getString("characterId"))
                .server(json.optString("serverId"))
                .jobName(json.getString("jobName"))
                .jobGrowName(json.getString("jobGrowName"))
                .jobId(json.getString("jobId"))
                .jobGrowId(json.getString("jobGrowId"))
                .build();
    }

    private WebClient.ResponseSpec requestAPIDnF(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode() == HttpStatus.BAD_REQUEST) {
                        throw new ApiException("요청에 대한 유효성 검증 실패 또는 필수 파라미터 에러", response.statusCode().value());
                    } else if (response.statusCode() == HttpStatus.UNAUTHORIZED) {
                        throw new ApiException("인증 오류", response.statusCode().value());
                    } else if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        throw new ApiException("존재하지 않은 리소스 또는 페이지", response.statusCode().value());
                    } else {
                        throw new ApiException("클라이언트 에러 발생", response.statusCode().value());
                    }
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    if (response.statusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                        throw new ApiException("서비스 이용 불가", response.statusCode().value());
                    } else {
                        throw new ApiException("서버 에러 발생", response.statusCode().value());
                    }
                });
    }

}
