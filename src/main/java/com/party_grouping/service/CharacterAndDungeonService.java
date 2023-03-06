package com.party_grouping.service;

import com.party_grouping.dto.CharacterAndDungeonDto;
import com.party_grouping.repository.CharacterAndDungeonRepo;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.repository.DungeonRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class CharacterAndDungeonService {
    private final CharacterAndDungeonRepo characterAndDungeonRepo;
    private final CharacterRepo characterRepo;
    private final DungeonRepo dungeonRepo;

    public CharacterAndDungeonService(CharacterAndDungeonRepo characterAndDungeonRepo, CharacterRepo characterRepo, DungeonRepo dungeonRepo) {
        this.characterAndDungeonRepo = characterAndDungeonRepo;
        this.characterRepo = characterRepo;
        this.dungeonRepo = dungeonRepo;
    }

    public Integer save(CharacterAndDungeonDto characterAndDungeonDto) {
        return characterAndDungeonRepo.save(characterAndDungeonDto);
    }

    public Optional<CharacterAndDungeonDto> characterAndDungeonDtoOpt(Integer characterAndDungeonId) {
        return characterAndDungeonRepo.findByIdOptDto(characterAndDungeonId);
    }
}
