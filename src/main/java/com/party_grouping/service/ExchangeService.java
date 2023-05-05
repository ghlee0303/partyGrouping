package com.party_grouping.service;

import com.party_grouping.api.ApiDnF;
import com.party_grouping.entity.CharacterEntity;
import com.party_grouping.exception.ApiException;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.repository.ExchangeRepo;
import com.party_grouping.request.ExchangeRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public String save(ExchangeRequestDto exchangeRequestDto) {
        String server = exchangeRequestDto.getServer();
        List<CharacterEntity> characterEntityList = new ArrayList<>();

        for (String apiId: exchangeRequestDto.getApiIdList()) {
            characterEntityList.add(characterRepo.findByApiIdOptEntity(server, apiId)
                    .orElseThrow(() -> { throw new ApiException("해당 캐릭터를 찾을 수 없습니다.", 400); }));
        }

        return exchangeRepo.save(characterEntityList);
    }
}
