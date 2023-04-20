package com.party_grouping.service;

import com.party_grouping.api.ApiDnF;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.exception.ApiException;
import com.party_grouping.repository.CharacterRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
public class CharacterService {
    private final CharacterRepo characterRepo;
    private ApiDnF apiDnF;

    public CharacterService(CharacterRepo characterRepo, ApiDnF apiDnF) {
        this.characterRepo = characterRepo;
        this.apiDnF = apiDnF;
    }

    public Integer save(CharacterDto characterDto) {
        return characterRepo.save(characterDto);
    }

    public Optional<CharacterDto> characterDtoOpt(Integer characterId) {
        return characterRepo.findByIdOptDto(characterId);
    }

    public CharacterDto characterStatus(String server, String characterApiId) {
        CharacterDto characterDto = apiDnF.callCharacterStatus(server, characterApiId);

        characterRepo.characterStatus(characterDto);
        return characterDto;
    }

    public List<CharacterDto> characterSearch(String name) {
        List<CharacterDto> apiCharacterDtoList = apiDnF.callCharacter(name);

        for (CharacterDto apiCharacterDto : apiCharacterDtoList) {
            Optional<CharacterDto> characterDtoOpt = characterRepo.findByApiIdOptDto(apiCharacterDto.getApiId());
            characterDtoOpt.ifPresent(dbCharacterDto -> {
                apiCharacterDto.setFame(dbCharacterDto.getFame());
                apiCharacterDto.setAdventureName(dbCharacterDto.getAdventureName());
            });
        }
        return apiCharacterDtoList;
    }
}
