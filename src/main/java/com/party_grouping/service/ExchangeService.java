package com.party_grouping.service;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.party_grouping.repository.ExchangeRepo;
import com.party_grouping.request.CharacterRequest;
import com.party_grouping.request.ExchangeRequest;
import com.party_grouping.response.CharacterResponse;
import com.party_grouping.response.ExchangeResponse;
import com.party_grouping.util.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class ExchangeService {
    private final ExchangeRepo exchangeRepo;
    private final CharacterService characterService;

    @Autowired
    public ExchangeService(ExchangeRepo exchangeRepo, CharacterService characterService) {
        this.exchangeRepo = exchangeRepo;
        this.characterService = characterService;
    }

    @Transactional
    public ExchangeDto save(ExchangeRequest exchangeRequest) {
        if (ApiUtils.isDuplicate(exchangeRequest.getApiIdList())) {
            throw new ApiException(ErrorCode.PARTY_MEMBERS_DUPLICATE);
        }

        ExchangeDto exchangeDto = exchangeRepo.save(exchangeRequest);

        return exchangeDto;
    }

    public ExchangeDto findExchange(Integer exchangeKey) {
        return exchangeRepo.findExchangeByKey(exchangeKey).orElse(null);
    }

    public List<ExchangeDto> findExchangeList(List<Integer> id) {
        return exchangeRepo.findExchangeList(id);
    }

    public List<ExchangeDto> findExchangeByAdventure(String adventureName) {
        return exchangeRepo.findExchangeByAdventure(adventureName);
    }

    @Transactional
    public boolean deleteExchange(Integer id) {
        return exchangeRepo.deleteExchange(id);
    }

    @Transactional
    public Integer createExchangeKey(Integer id) {
        return exchangeRepo.setExchangeKey(id, generateExchangeKey());
    }

    public List<CharacterDto> exchangeReset(Integer id) {
        List<CharacterRequest> characterRequestList = new ArrayList<>();
        ExchangeDto exchangeDto = exchangeRepo.findExchange(id).orElse(null);

        if (exchangeDto == null) {
            return null;
        }

        addCharacterReqList(characterRequestList, exchangeDto.getCharacter1());
        addCharacterReqList(characterRequestList, exchangeDto.getCharacter2());
        addCharacterReqList(characterRequestList, exchangeDto.getCharacter3());
        addCharacterReqList(characterRequestList, exchangeDto.getCharacter4());

        return characterService.characterStatusList(true, characterRequestList);
    }

    public ExchangeResponse createResponse(ExchangeDto exchangeDto) {
        List<CharacterDto> characterDtoList = new ArrayList<>();
        characterDtoList.add(exchangeDto.getCharacter1());
        characterDtoList.add(exchangeDto.getCharacter2());
        characterDtoList.add(exchangeDto.getCharacter3());
        characterDtoList.add(exchangeDto.getCharacter4());

        List<CharacterResponse> characterResponseList = characterService.createResponseList(characterDtoList);
        return ExchangeResponse
                .builder()
                .id(exchangeDto.getId())
                .exchangeKey(exchangeDto.getExchangeKey())
                .adventureName(exchangeDto.getAdventureName())
                .characterList(characterResponseList)
                .build();
    }

    private void addCharacterReqList(List<CharacterRequest> characterRequestList, CharacterDto characterDto) {
        characterRequestList.add(new CharacterRequest(characterDto.getApiId(), characterDto.getServer()));
    }

    private int generateExchangeKey() {
        Random random = new Random();
        int exchangeKey = 0;

        while (exchangeRepo.isKeyUnique(exchangeKey)) {
            exchangeKey = random.nextInt(9000) + 1000;
        }

        return exchangeKey;
    }

    private void validateAdventure(ExchangeRequest requestDto) {


    }
}
