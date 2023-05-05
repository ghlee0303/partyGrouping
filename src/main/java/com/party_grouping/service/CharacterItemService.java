package com.party_grouping.service;

import com.party_grouping.api.ApiDnF;
import com.party_grouping.dto.CharacterItemDto;
import com.party_grouping.entity.CharacterItemEntity;
import com.party_grouping.repository.CharacterItemRepo;
import com.party_grouping.repository.CharacterRepo;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CharacterItemService {
    private final CharacterRepo characterRepo;
    private final CharacterItemRepo characterItemRepo;
    private ApiDnF apiDnF;

    public CharacterItemService(CharacterRepo characterRepo, CharacterItemRepo characterItemRepo) {
        this.characterRepo = characterRepo;
        this.characterItemRepo = characterItemRepo;
    }

    public void test(String server, String characterApiId) {
        CharacterItemDto characterItemDto = apiDnF.callItem(server, characterApiId);

    }
}
