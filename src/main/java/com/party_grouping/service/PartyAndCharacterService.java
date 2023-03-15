package com.party_grouping.service;

import com.party_grouping.dto.PartyAndCharacterDto;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.repository.PartyAndCharacterRepo;
import com.party_grouping.repository.PartyRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    public Integer save(PartyAndCharacterDto partyAndCharacterDto) {
        // 같은 partyNumber 에 4명만
        // 같은 partyNumber 에 중복 character 가 있는지 체크
        // partyNumber 1~8 인지 체크
        System.out.println(partyAndCharacterDto.getPartyNumber());
        if (partyAndCharacterDto.getPartyNumber() == null) {
            partyAndCharacterDto.setPartyNumber(1);
        }
        return partyAndCharacterRepo.save(partyAndCharacterDto);
    }

    public Optional<PartyAndCharacterDto> partyAndCharacterDtoOpt(Integer partyAndCharacterId) {
        return partyAndCharacterRepo.findByIdOptDto(partyAndCharacterId);
    }

    public List<List<PartyAndCharacterDto>> doublePACListDto(Integer partyId) {
        List<List<PartyAndCharacterDto>> doublePACList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            doublePACList.add(new ArrayList<>());
        }

        partyAndCharacterRepo.findByPartyListDto(partyId).forEach(partyAndCharacterDto -> {
            System.out.println(partyAndCharacterDto.getPartyNumber());
            doublePACList.get(partyAndCharacterDto.getPartyNumber()).add(partyAndCharacterDto);
        });

        return doublePACList;
    }

}
