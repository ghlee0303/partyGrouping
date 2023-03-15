package com.party_grouping.controller;

import com.party_grouping.dto.*;
import com.party_grouping.entity.PartyAndCharacterEntity;
import com.party_grouping.response.CustomResponse;
import com.party_grouping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class mainController {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private DungeonService dungeonService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private PartyService partyService;
    @Autowired
    private PartyAndCharacterService partyAndCharacterService;

    @GetMapping("/")
    public String getMain() {

        return "index";
    }
    @GetMapping("data_insert")
    public String getData_Insert(Model model) {
        model.addAttribute("group_data_list", groupService.groupDtoList());
        model.addAttribute("dungeon_data_list", dungeonService.dungeonDtoList());
        model.addAttribute("party_data_list", partyService.partyDtoList());
        model.addAttribute("character_data_list", characterService.characterDtoList());

        return "dataInsert";
    }

    @GetMapping("pac_list")
    @ResponseBody
    public List<List<PartyAndCharacterDto>> getPAC_List(@RequestParam(value = "partyId") Integer partyId) {
        return partyAndCharacterService.doublePACListDto(partyId);
    }

    @PostMapping("character_insert")
    @ResponseBody
    public CustomResponse<String> postCharacter_Insert(@RequestBody CharacterDto characterDto) {
        System.out.println("캐릭터");
        characterService.save(characterDto);
        return new CustomResponse<>(characterDto.getName());
    }

    @PostMapping("dungeon_insert")
    @ResponseBody
    public CustomResponse<String> postDungeon_Insert(@RequestBody DungeonDto dungeonDto) {
        System.out.println("던전");
        dungeonService.save(dungeonDto);
        return new CustomResponse<>(dungeonDto.getName());
    }

    @PostMapping("group_insert")
    @ResponseBody
    public CustomResponse<String> postGroup_Insert(@RequestBody GroupDto groupDto) {
        System.out.println("그룹");
        groupService.save(groupDto);
        return new CustomResponse<>(groupDto.getName());
    }

    @PostMapping("party_insert")
    @ResponseBody
    public CustomResponse<String> postCharacter_Insert(@RequestBody PartyDto partyDto) {
        System.out.println("파티");
        partyService.save(partyDto);
        return new CustomResponse<>(partyDto.getName());
    }

    @PostMapping("pac_insert")
    @ResponseBody
    public CustomResponse<Integer> postPAC_Insert(@RequestBody PartyAndCharacterDto partyAndCharacterDto) {
        System.out.println("파티원 등록");
        partyAndCharacterService.save(partyAndCharacterDto);
        return new CustomResponse<>(partyAndCharacterDto.getId());
    }
}
