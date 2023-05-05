package com.party_grouping.api;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.CharacterItemDto;

import java.util.List;

public interface ApiCharacter {
    CharacterDto callCharacterStatus(String server, String characterApiId);
    List<CharacterDto> callCharacter(String name, String type);
    CharacterItemDto callItem(String server, String characterApiId);
}
