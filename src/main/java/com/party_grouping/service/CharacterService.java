package com.party_grouping.service;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.request.CharacterRequest;
import com.party_grouping.response.CharacterResponse;
import com.party_grouping.service.inter.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Transactional
@Service
public class CharacterService {
    private final CharacterRepo characterRepo;
    private final ApiService dnfApiService;

    @Autowired
    public CharacterService(CharacterRepo characterRepo, ApiService dnfApiService) {
        this.characterRepo = characterRepo;
        this.dnfApiService = dnfApiService;
    }

    @Transactional
    public Integer save(CharacterDto characterDto) {
        return characterRepo.save(characterDto);
    }

    @Transactional
    public CharacterDto characterStatus(String server, String characterApiId) {
        CharacterDto characterDto = dnfApiService.callCharacterStatus(server, characterApiId);

        // 딜러만 아이템 정보를 사용
        if (!characterDto.isBuffer()) {
            characterDto.setItem(dnfApiService.callItem(server, characterApiId));
        }
        characterRepo.characterStatus(characterDto);

        return characterDto;
    }

    public List<CharacterDto> characterSearch(String name, String type) {
        if (type.equals("adventure")) {
            return characterRepo.findCharacterByAdventure(name);
        }

        return dnfApiService.callSearch(name, type);
    }

    public Optional<CharacterDto> findCharacter(String server, String characterApiId) {
        return characterRepo.findCharacterDto(server, characterApiId);
    }

    public List<CharacterDto> characterStatusList(boolean apiUse, List<CharacterRequest> characterRequestList) {
        if (apiUse) {
            return characterStatusFromAPI(characterRequestList);
        } else {
            return sortList(characterRepo.findStatusList(characterRequestList), characterRequestList);
        }
    }

    public List<CharacterDto> characterAdventure(String adventureName) {
        return characterRepo.findCharacterByAdventure(adventureName);
    }

    public CharacterResponse createResponse(CharacterDto characterDto) {
        return CharacterResponse.builder()
                .character(characterDto)
                .dungeon(dnfApiService.callWeeklyDungeon(characterDto.getServer(), characterDto.getApiId()))
                .build();
    }

    public List<CharacterResponse> createResponseList(List<CharacterDto> characterDtoList) {
        List<CharacterResponse> responses = new ArrayList<>();

        for (CharacterDto dto : characterDtoList) {
            responses.add(CharacterResponse.builder()
                    .character(dto)
                    .dungeon(dnfApiService.callWeeklyDungeon(dto.getServer(), dto.getApiId()))
                    .build());
        }

        return responses;
    }

    private List<CharacterDto> characterStatusFromAPI(List<CharacterRequest> characterRequestList) {
        List<CharacterDto> characterDtoList = new ArrayList<>();

        for (CharacterRequest req : characterRequestList) {
            String apiId = req.getApiId();
            String server = req.getServer();
            CharacterDto characterDto = characterStatus(server, apiId);
            characterDtoList.add(characterDto);
        }

        return characterDtoList;
    }

    private List<CharacterDto> sortList(List<CharacterDto> characterDtoList, List<CharacterRequest> characterRequestList) {
        List<CharacterDto> result = new ArrayList<>();

        for (CharacterRequest req : characterRequestList) {
            String apiId = req.getApiId();
            String server = req.getServer();
            characterDtoList.stream()
                    .filter(dto -> dto.getApiId().equals(apiId) && dto.getServer().equals(server))
                    .findFirst()
                    .ifPresent(result::add);
        }

        return result;
    }
}
