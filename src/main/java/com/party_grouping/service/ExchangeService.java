package com.party_grouping.service;

import com.party_grouping.api.ApiDnF;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.exception.ApiException;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.repository.ExchangeRepo;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Transactional
public class ExchangeService {
    private final ExchangeRepo exchangeRepo;
    private final CharacterRepo characterRepo;
    private final ApiDnF apiDnF;

    public ExchangeService(ExchangeRepo exchangeRepo, CharacterRepo characterRepo, ApiDnF apiDnF) {
        this.exchangeRepo = exchangeRepo;
        this.characterRepo = characterRepo;
        this.apiDnF = apiDnF;
    }

    public String save(List<String> apiIdList) {
        List<CharacterDto> characterDtoList = new ArrayList<>();
        CharacterDto buffer = null;
        for (String apiId: apiIdList) {
            CharacterDto characterDto = characterRepo.findByApiIdOptDto(apiId)
                    .orElseThrow(() -> { throw new ApiException("해당 캐릭터가 존재하지 않습니다.", 404); });

            if (characterDto.isBuffer()) {
                buffer = characterDto;
                continue;
            }
            characterDtoList.add(characterDto);
        }
        characterDtoList.add(buffer);

        ExchangeDto exchangeDto = ExchangeDto.builder()
                .character1(characterDtoList.get(0))
                .character2(characterDtoList.get(1))
                .character3(characterDtoList.get(2))
                .character4(characterDtoList.get(3))
                .build();

        return exchangeRepo.save(exchangeDto);
    }

}
