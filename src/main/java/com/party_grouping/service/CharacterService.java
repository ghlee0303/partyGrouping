package com.party_grouping.service;

import com.party_grouping.api.ApiDnF;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.repository.CharacterRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class CharacterService {
    private final CharacterRepo characterRepo;
    private ApiDnF APIDnF;

    public CharacterService(CharacterRepo characterRepo, ApiDnF APIDnF) {
        this.characterRepo = characterRepo;
        this.APIDnF = APIDnF;
    }

    public Integer save(CharacterDto characterDto) {
        return characterRepo.save(characterDto);
    }

    public Optional<CharacterDto> characterDtoOpt(Integer characterId) {
        return characterRepo.findByIdOptDto(characterId);
    }

    public List<CharacterDto> characterDtoList() {
        return characterRepo.findListDto();
    }

    public List<CharacterDto> saveByApiDnf(String server, String name) {
        List<CharacterDto> dnfCharacterDtoList = APIDnF.callCharacter(server, name);
        characterRepo.apiDnfSave(dnfCharacterDtoList);

        return dnfCharacterDtoList;
    }
}
