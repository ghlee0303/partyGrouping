package com.party_grouping.service;

import com.party_grouping.data.CharacterNode;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.CharacterItemDto;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.request.CharacterRequest;
import com.party_grouping.service.inter.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

        // 버퍼는 아이템 정보를 사용하지 않음
        if (!characterDto.isBuffer()) {
            characterDto.setItem(dnfApiService.callItem(server, characterApiId));
        }
        characterRepo.characterStatus(characterDto);

        return characterDto;
    }

    public List<CharacterDto> characterSearch(String name, String type) {
        if (type.equals("adventure")) {
            return characterRepo.findByAdventureDto(name);
        }

        return dnfApiService.callSearch(name, type);
    }

    public List<CharacterDto> characterStatusList(boolean apiUse, List<CharacterRequest> characterRequestList) {
        List<CharacterDto> characterDtoList;

        if (apiUse) {
            characterDtoList = characterStatusFromAPI(characterRequestList);
        } else {
            characterDtoList = sortList(characterRepo.findStatusList(characterRequestList), characterRequestList);
        }

        return characterDtoList;
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

    public CharacterDto findCharacter(String server, String characterApiId) {
        return characterRepo.findCharacterDto(server, characterApiId)
                .orElseThrow(() -> { throw new ApiException(ErrorCode.CHARACTER_NOT_FOUND); });
    }
}
