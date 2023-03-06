package com.party_grouping.service;

import com.party_grouping.dto.GroupAndCharacterDto;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.repository.GroupAndCharacterRepo;
import com.party_grouping.repository.GroupRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class GroupAndCharacterService {
    private final GroupAndCharacterRepo groupAndCharacterRepo;
    private final GroupRepo groupRepo;
    private final CharacterRepo characterRepo;

    public GroupAndCharacterService(GroupAndCharacterRepo groupAndCharacterRepo, GroupRepo groupRepo, CharacterRepo characterRepo) {
        this.groupAndCharacterRepo = groupAndCharacterRepo;
        this.groupRepo = groupRepo;
        this.characterRepo = characterRepo;
    }

    public Integer save(GroupAndCharacterDto groupAndCharacterDto) {
        return groupAndCharacterRepo.save(groupAndCharacterDto);
    }

    public Optional<GroupAndCharacterDto> groupAndCharacterDtoOpt(Integer groupAndCharacterId){
        return groupAndCharacterRepo.findByIdOptDto(groupAndCharacterId);
    }
}
