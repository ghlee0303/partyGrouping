package com.party_grouping.service;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.repository.CharacterRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class CharacterService {
    private final CharacterRepo characterRepo;

    public CharacterService(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    public Integer save(CharacterDto characterDto) {
        return characterRepo.save(characterDto);
    }

    public Optional<CharacterDto> characterDtoOpt(Integer characterId) {
        return characterRepo.findByIdOptDto(characterId);
    }

}
