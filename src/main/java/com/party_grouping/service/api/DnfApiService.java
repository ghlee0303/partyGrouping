package com.party_grouping.service.api;

import com.party_grouping.api.Api;
import com.party_grouping.api.Buff;
import com.party_grouping.api.CharacterYaml;
import com.party_grouping.api.DungeonYaml;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.CharacterItemDto;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.party_grouping.service.inter.ApiService;
import com.party_grouping.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class DnfApiService implements ApiService {
    @Value("${dnf-api-key}")
    private String apiKey;
    private final Api api;
    private final CharacterYaml characterYaml;
    private final DungeonYaml dungeonYaml;

    @Autowired
    public DnfApiService(Api api, CharacterYaml characterYaml, DungeonYaml dungeonYaml) {
        this.api = api;
        this.characterYaml = characterYaml;
        this.dungeonYaml = dungeonYaml;
    }

    // 캐릭터 검색
    @Override
    public List<CharacterDto> callSearch(String name, String type) {
        String url = String.format("servers/%s/characters?characterName=%s&wordType=full&limit=8&apikey=%s",type, name, apiKey);
        List<CharacterDto> characterDtoList = parsingCharacterListJson(api.callRequest(url));
        String logging = "name : " + name + " / type : " + type;
        log.info("Search API [{}]", logging);

        return characterDtoList;
    }

    // 캐릭터 상세정보
    @Override
    public CharacterDto callCharacterStatus(String server, String apiId) {
        String url = String.format("servers/%s/characters/%s/status?apikey=%s", server, apiId, apiKey);

        String jsonString = api.callRequest(url);
        JSONObject jsonObject = new JSONObject(jsonString);

        if (jsonObject.getJSONArray("status").isEmpty()) {
            throw new ApiException(ErrorCode.CHARACTER_LOCK);
        }

        int fame = jsonObject.getJSONArray("status").getJSONObject(16).getInt("value");
        String adventureName = jsonObject.getString("adventureName");

        CharacterDto characterDto = setCharacterDto(jsonObject);
        characterDto.setFame(fame);
        characterDto.setAdventureName(adventureName);
        characterDto.setServer(server);
        characterDto.setBuff(callBuffSkill(server, apiId));

        String logging = "apiId : " + apiId + " / server : " + server;
        log.info("Status API [{}]", logging);

        return characterDto;
    }

    // 캐릭터 버프 스킬
    // 착용중인 버프 장비를 요청한 후 버프 스킬을 가져옴
    @Override
    public Buff callBuffSkill(String server, String apiId) {
        String url = String.format("servers/%s/characters/%s/skill/buff/equip/equipment?apikey=%s", server, apiId, apiKey);

        JSONObject buff = new JSONObject(api.callRequest(url))
                .getJSONObject("skill")
                .optJSONObject("buff");

        if (buff == null) {
            throw new ApiException(ErrorCode.CHARACTER_NONE_BUFF);
        }

        JSONObject skillInfo = buff.getJSONObject("skillInfo");

        String buffName = skillInfo.getString("name");
        String buffId = skillInfo.getString("skillId");
        int buffLevel = skillInfo.getJSONObject("option").getInt("level");

        return new Buff(buffLevel, buffName, buffId, characterYaml.getBuff());
    }

    // 캐릭터 아이템
    // 무기 강화/증폭, 시나오칭 유무, 악세 마부, 어벨 스증마부 합을 계산
    // 320작, 커스텀 갯수
    @Override
    public CharacterItemDto callItem(String server, String apiId) {
        String url = String.format("servers/%s/characters/%s/equip/equipment?apikey=%s", server, apiId, apiKey);

        JSONArray itemJsonArray = new JSONObject(api.callRequest(url))
                .getJSONArray("equipment");

        // 착용중인 장비가 13개가 되어야함
        if (itemJsonArray.length() != 13) {
            throw new ApiException(ErrorCode.ITEM_NOT_ENOUGH);
        }

        JSONObject weapon = itemJsonArray.getJSONObject(0);     // 무기
        JSONObject title = itemJsonArray.getJSONObject(1);      // 칭호
        JSONObject shoulder = itemJsonArray.getJSONObject(3);   // 어깨
        JSONObject belt = itemJsonArray.getJSONObject(6);       // 벨트
        JSONObject amulet = itemJsonArray.getJSONObject(7);     // 목걸이
        JSONObject wrist = itemJsonArray.getJSONObject(8);      // 팔찌
        JSONObject ring = itemJsonArray.getJSONObject(9);       // 반지
        JSONObject support = itemJsonArray.getJSONObject(10);   // 보조장비

        // 악세서리 마부
        List<Integer> accessoryEnchantList = calcAccessoryEnchant(
                amulet.optJSONObject("enchant"),
                wrist.optJSONObject("enchant"),
                ring.optJSONObject("enchant")
        );

        CharacterItemDto characterItemDto = CharacterItemDto.builder()
                .weaponReinforce(weapon.optInt("reinforce"))                // 무기 강화
                .weaponRefine(weapon.optInt("refine"))                      // 무기 재련
                .weaponAmp(findJsonString(weapon, "amplificationName"))           // 무기 증폭 여부
                .amulet(accessoryEnchantList.get(0))                            // 목걸이 마부
                .wrist(accessoryEnchantList.get(1))                             // 팔찌 마부
                .ring(accessoryEnchantList.get(2))                              // 반지 마부
                .siv(calcSupportEnchant(support.optJSONObject("enchant")))  // 시브마부  유무
                .creature(isEndCreature(callCreature(server, apiId)))  // 종결 크리쳐 유무
                .aurora(isEndAurora(callAurora(server, apiId)))        // 종결 오라 유무
                .title(isEndTitle(title.optString("itemName")))             // 종결 칭호 유무
                .enchantSkillBonus(calcEnchantSkillBonus(                       // 어벨 스증마부 합
                        shoulder.optJSONObject("enchant"),
                        belt.optJSONObject("enchant")))
                .build();

        String logging = "apiId : " + apiId + " / server : " + server;
        log.info("Item API [{}]", logging);

        return characterItemDto;
    }

    // 착용중인 크리쳐 정보
    @Override
    public String callCreature(String server, String characterApiId) {
        String url = String.format("servers/%s/characters/%s/equip/creature?apikey=%s", server, characterApiId, apiKey);

        JSONObject creatureJson = new JSONObject(api.callRequest(url))
                .optJSONObject("creature");
        if (creatureJson == null) {
            return null;
        }

        return creatureJson.getString("itemName");
    }

    // 착용중인 오라 정보
    @Override
    public String callAurora(String server, String characterApiId) {
        String url = String.format("servers/%s/characters/%s/equip/avatar?apikey=%s", server, characterApiId, apiKey);
        JSONArray avatarJson = new JSONObject(api.callRequest(url))
                .optJSONArray("avatar");

        for (int i = avatarJson.length() - 1; i>=0; i--) {
            JSONObject js = avatarJson.getJSONObject(i);
            if (js.getString("slotId").equals("AURORA"))
                return js.getString("itemName");
        }

        return null;
    }
    
    // 이번 주에 클리어한 던전(레기온, 레이드) 정보 요청
    @Override
    public HashMap<String, String> callWeeklyDungeon(String server, String characterApiId) {
        String dunDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(ApiUtils.getNowDunDate());
        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
        String url = String.format("servers/%s/characters/%s/timeline?code=209,201&startDate=%s&endDate=%s&apikey=%s",
                server, characterApiId, dunDate, now, apiKey);

        JSONArray timeLine = new JSONObject(api.callRequest(url))
                .getJSONObject("timeline")
                .optJSONArray("rows");

        HashMap<String, String> result = new HashMap<>();

        for (int i = 0; i < timeLine.length(); i++) {
            JSONObject rows = timeLine.getJSONObject(i);
            clsfcDungeon(rows, result);
        }

        return result;
    }

    // 던전 분류
    // 레기온, 레이드 등의 던전을 분류함
    private void clsfcDungeon(JSONObject rows, HashMap<String, String> result) {
        HashMap<String, String> regionMap = new HashMap<>();
        regionMap.put("이스핀즈", "ispins");
        regionMap.put("차원회랑", "dimension");

        HashMap<String, String> raidMap = new HashMap<>();
        raidMap.put("기계 혁명", "bakal");

        int code = rows.optInt("code");
        String date = rows.getString("date");
        JSONObject data = rows.getJSONObject("data");
        switch (code) {
            case 201 -> {
                String raidName = data.optString("raidName");
                putIfKeyExists(raidMap, raidName, result, date);
            }
            case 209 -> {
                String regionName = data.optString("regionName");
                putIfKeyExists(regionMap, regionName, result, date);
            }
        }
    }

    private void putIfKeyExists(Map<String, String> map, String key, Map<String, String> result, String value) {
        if (map.containsKey(key)) {
            result.put(map.get(key), value);
        }
    }

    private Integer calcEnchantSkillBonus(JSONObject shoulder, JSONObject belt) {
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

     // 악세서리 속성 강화 수치 int List
    private List<Integer> calcAccessoryEnchant(JSONObject amulet, JSONObject wrist, JSONObject ring) {
        String find = "status";
        List<JSONArray> jsonArrayList = Arrays.asList(
                findJsonArray(amulet, find),
                findJsonArray(wrist, find),
                findJsonArray(ring, find));
        List<Integer> resultList = new ArrayList<>();

        for (JSONArray jsonArray : jsonArrayList) {
            resultList.add(elementalValue(jsonArray));
        }

        return resultList;
    }

    // 속성 강화 수치
    private int elementalValue(JSONArray jsonArray) {
        if (jsonArray != null) {
            JSONObject enchantStatus = jsonArray.getJSONObject(0);
            String enchantName = enchantStatus.getString("name");
            return isElemental(enchantName) ? enchantStatus.getInt("value") : 0;
        }
        return 0;
    }

    // 속성 강화 판별
    private boolean isElemental(String enchantName) {
        return switch (enchantName) {
            case "모든 속성 강화", "화속성강화", "수속성강화", "명속성강화", "암속성강화" -> true;
            default -> false;
        };
    }

    private boolean calcSupportEnchant(JSONObject support) {
        JSONArray supportJsonArray = findJsonArray(support, "status");

        if (supportJsonArray == null || supportJsonArray.length() != 4) {
            return false;
        }

        JSONObject supportJsonObject = supportJsonArray.optJSONObject(3);
        String siv = supportJsonObject.getString("name");

        return siv.equals("피해 증가");
    }

    // 종결 칭호 여부
    private boolean isEndTitle(String title) {
        return title != null && ApiUtils.listContains(characterYaml.getTitle(), title);
    }

    private boolean isEndAurora(String aurora) {
        return aurora != null && ApiUtils.listContains(characterYaml.getAurora(), aurora);
    }

    private boolean isEndCreature(String creature) {
        return creature != null && ApiUtils.listContains(characterYaml.getCreature(), creature);
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
}
