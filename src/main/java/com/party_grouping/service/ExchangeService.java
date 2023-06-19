package com.party_grouping.service;

import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.party_grouping.repository.ExchangeRepo;
import com.party_grouping.request.ExchangeRequestDto;
import com.party_grouping.util.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.*;

@Transactional
@Service
public class ExchangeService {
    private final ExchangeRepo exchangeRepo;

    @Autowired
    public ExchangeService(ExchangeRepo exchangeRepo) {
        this.exchangeRepo = exchangeRepo;
    }

    @Transactional
    public String save(ExchangeRequestDto exchangeRequestDto) {
        if (ApiUtils.isDuplicate(exchangeRequestDto.getApiIdList())) {
            throw new ApiException(ErrorCode.PARTY_MEMBERS_DUPLICATE);
        }

        return exchangeRepo.save(exchangeRequestDto, generatePersistentKey());
    }

    @Transactional
    public void deleteExchange(String persistentKey) {
        exchangeRepo.deleteExchange(persistentKey);
    }

    public List<ExchangeDto> findByPersistentKeyList(List<String> persistentKeyList) {
        return exchangeRepo.findByPersistentKeyList(persistentKeyList);
    }

    @Transactional
    public int createExchangeKey(String persistentKey) {
        int exchangeKey = generateExchangeKey();
        exchangeRepo.updateExchangeKey(persistentKey, exchangeKey);
        return exchangeKey;
    }


    private String generatePersistentKey() {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int LENGTH = 9;
        String persistentKey = null;

        while (exchangeRepo.isKeyUnique(persistentKey)) {
            StringBuilder builder = new StringBuilder();
            SecureRandom random = new SecureRandom();
            while (builder.length() < LENGTH) {
                int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(index));
            }
            persistentKey = builder.toString();
        }

        return persistentKey;
    }

    private int generateExchangeKey() {
        Random random = new Random();
        int exchangeKey = 0;

        while (exchangeRepo.isKeyUnique(exchangeKey)) {
            exchangeKey = random.nextInt(9000) + 1000;
        }

        return exchangeKey;
    }
}
