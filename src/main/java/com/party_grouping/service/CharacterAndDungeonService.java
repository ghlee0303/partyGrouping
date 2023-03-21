package com.party_grouping.service;

import com.party_grouping.dto.CharacterAndDungeonDto;
import com.party_grouping.repository.CharacterAndDungeonRepo;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.repository.DungeonRepo;
import com.party_grouping.request.CADRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public Integer save(CADRequestDto cadRequestDto) {
        if (cadRequestDto.isClear()) {
            cadRequestDto.setClearDate(LocalDateTime.now());
        } else {
            cadRequestDto.setClearDate(null);
        }
        return characterAndDungeonRepo.save(cadRequestDto);
    }

    public Optional<CharacterAndDungeonDto> characterAndDungeonDtoOpt(Integer characterAndDungeonId) {
        return characterAndDungeonRepo.findByIdOptDto(characterAndDungeonId);
    }
}
