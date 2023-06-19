package com.party_grouping.service;

import com.party_grouping.dto.CharacterItemDto;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.party_grouping.repository.CharacterItemRepo;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.service.inter.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CharacterItemService {
    private final CharacterItemRepo characterItemRepo;
    private ApiService apiService;

    @Autowired
    public CharacterItemService(CharacterItemRepo characterItemRepo, ApiService apiService) {
        this.characterItemRepo = characterItemRepo;
        this.apiService = apiService;
    }

}
