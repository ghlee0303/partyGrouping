package com.party_grouping.service;

import com.party_grouping.dto.PartyAndCharacterDto;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.repository.PartyAndCharacterRepo;
import com.party_grouping.repository.PartyRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class PartyAndCharacterService {
    private final PartyAndCharacterRepo partyAndCharacterRepo;
    private final PartyRepo partyRepo;
    private final CharacterRepo characterRepo;

    public PartyAndCharacterService(PartyAndCharacterRepo partyAndCharacterRepo, PartyRepo partyRepo, CharacterRepo characterRepo) {
        this.partyAndCharacterRepo = partyAndCharacterRepo;
        this.partyRepo = partyRepo;
        this.characterRepo = characterRepo;
    }

    public Integer save (PartyAndCharacterDto partyAndCharacterDto) {
        return partyAndCharacterRepo.save(partyAndCharacterDto);
    }

    public Optional<PartyAndCharacterDto> partyAndCharacterDtoOpt(Integer partyAndCharacterId) {
        return partyAndCharacterRepo.findByIdOptDto(partyAndCharacterId);
    }
}
