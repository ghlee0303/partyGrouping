package com.party_grouping.service;

import com.party_grouping.dto.DungeonDto;
import com.party_grouping.repository.DungeonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DungeonService {
    private final DungeonRepo dungeonRepo;

    @Autowired
    public DungeonService(DungeonRepo dungeonRepo) {
        this.dungeonRepo = dungeonRepo;
    }

    public Integer save(DungeonDto dungeonDto) {
        return dungeonRepo.save(dungeonDto);
    }

    public Optional<DungeonDto> dungeonDtoOpt(Integer dungeonId) {
        return dungeonRepo.findByIdOptDto(dungeonId);
    }

    public List<DungeonDto> dungeonDtoList() {
        return dungeonRepo.findListDto();
    }
}
