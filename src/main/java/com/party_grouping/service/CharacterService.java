package com.party_grouping.service;

import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.repository.CharacterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CharacterService {
    public CharacterRepo characterRepo;

    public CharacterService(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    public Integer save() {
        CharacterEntity character = new CharacterEntity("test111123", 41254);
        int characterId = characterRepo.save(character);
        System.out.println(characterId);
        return characterId;
    }

    public void findByIdOpt() {

    }
}
