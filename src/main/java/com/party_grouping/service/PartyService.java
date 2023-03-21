package com.party_grouping.service;

import com.party_grouping.dto.PartyDto;
import com.party_grouping.entity.PartyEntity;
import com.party_grouping.repository.PartyRepo;
import com.party_grouping.request.PartyRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class PartyService {
    private final PartyRepo partyRepo;

    public PartyService(PartyRepo partyRepo) {
        this.partyRepo = partyRepo;
    }

    public Integer save(PartyRequestDto partyRequestDto) {
        return partyRepo.save(partyRequestDto);
    }

    public Optional<PartyDto> partyDtoOpt(Integer partyId) {
        return partyRepo.findByIdOptDto(partyId);
    }

    public List<PartyDto> partyDtoList() {
        return partyRepo.findListDto();
    }
}
