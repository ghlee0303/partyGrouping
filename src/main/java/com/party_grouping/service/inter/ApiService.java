package com.party_grouping.service.inter;

import com.party_grouping.api.Buff;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.CharacterItemDto;

import java.util.HashMap;
import java.util.List;

public interface ApiService {
    List<CharacterDto> callSearch(String name, String type);
    CharacterDto callCharacterStatus(String server, String characterApiId);
    Buff callBuffSkill(String server, String characterApiId);
    CharacterItemDto callItem(String server, String characterApiId);
    String callCreature(String server, String characterApiId);
    String callAurora(String server, String characterApiId);
    HashMap<String, String> callWeeklyDungeon(String server, String characterApiId);
}
