package com.party_grouping.service;

import com.party_grouping.dto.PartyDto;
import com.party_grouping.entity.PartyEntity;
import com.party_grouping.repository.PartyRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class PartyService {
    private final PartyRepo partyRepo;

    public PartyService(PartyRepo partyRepo) {
        this.partyRepo = partyRepo;
    }

    public Integer save(PartyDto partyDto) {
        return partyRepo.save(partyDto);
    }

    public Optional<PartyDto> partyDtoOpt(Integer partyId) {
        return partyRepo.findByIdOptDto(partyId);
    }
}
