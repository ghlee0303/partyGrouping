package com.party_grouping.service;

import com.party_grouping.dto.GroupDto;
import com.party_grouping.repository.GroupRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class GroupService {
    private final GroupRepo groupRepo;

    public GroupService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public Integer save(GroupDto groupDto) {
        return groupRepo.save(groupDto);
    }

    public Optional<GroupDto> groupDtoOpt(Integer groupId) {
        return groupRepo.findByIdOptDto(groupId);
    }
}
