package com.party_grouping.service;

import com.party_grouping.dto.PartyAndCharacterDto;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.repository.PartyAndCharacterRepo;
import com.party_grouping.repository.PartyRepo;
import com.party_grouping.request.PACRequestDto;
import com.party_grouping.response.dto.PACResponseDto;
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

    public Integer save(PACRequestDto pacRequestDto) {
        // 같은 partyNumber 에 4명만
        // 같은 partyNumber 에 중복 character 가 있는지 체크
        // partyNumber 1~8 인지 체크
        System.out.println(pacRequestDto.getPartyNumber());
        if (pacRequestDto.getPartyNumber() == null) {
            pacRequestDto.setPartyNumber(1);
        }

        return partyAndCharacterRepo.save(pacRequestDto);
    }

    public Optional<PartyAndCharacterDto> partyAndCharacterDtoOpt(Integer partyAndCharacterId) {
        return partyAndCharacterRepo.findByIdOptDto(partyAndCharacterId);
    }

    // 파티 번호 1~8에 맞춰 이중리스트 PACDto를 구성
    public List<List<PartyAndCharacterDto>> doublePACListDto(Integer partyId) {
        List<List<PartyAndCharacterDto>> doublePACList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            doublePACList.add(new ArrayList<>());
        }

        partyAndCharacterRepo.findByPartyListDto(partyId).forEach(partyAndCharacterDto -> {
            doublePACList.get(partyAndCharacterDto.getPartyNumber() - 1).add(partyAndCharacterDto);
        });

        return doublePACList;
    }

    public List<PACResponseDto> pacResponseDtoList(Integer characterId) {
        return partyAndCharacterRepo.findByPartyIdWithClearDateListResDto(characterId);
    }

    public List<List<PACResponseDto>> doublePACResponseDtoList(Integer partyId) {
        List<List<PACResponseDto>> doublePACResList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            doublePACResList.add(new ArrayList<>());
        }

        partyAndCharacterRepo.findByPartyIdWithClearDateListResDto(partyId).forEach(partyAndCharacterDto -> {
            doublePACResList.get(partyAndCharacterDto.getPartyNumber() - 1).add(partyAndCharacterDto);
        });

        return doublePACResList;
    }
}
