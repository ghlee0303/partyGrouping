package com.party_grouping.controller;

import com.party_grouping.dto.*;
import com.party_grouping.entity.PartyAndCharacterEntity;
import com.party_grouping.request.CADRequestDto;
import com.party_grouping.request.PACRequestDto;
import com.party_grouping.request.PartyRequestDto;
import com.party_grouping.response.CustomResponse;
import com.party_grouping.response.dto.PACResponseDto;
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
    @Autowired
    private CharacterAndDungeonService characterAndDungeonService;

    @GetMapping("/")
    public String getMain() {
        return "index";
    }

    @GetMapping("data_insert")
    public String getData_Insert(Model model) {
        model.addAttribute("group_data_list", groupService.groupDtoList());
        model.addAttribute("dungeon_data_list", dungeonService.dungeonDtoList());
        model.addAttribute("party_data_list", partyService.partyDtoList());

        return "dataInsert";
    }

    @GetMapping("pac_list")
    @ResponseBody
    public CustomResponse<List<List<PACResponseDto>>> getPAC_List(@RequestParam(value = "partyId") Integer partyId) {
        return new CustomResponse<>(partyAndCharacterService.doublePACResponseDtoList(partyId));
    }

    @PostMapping("character_insert")
    @ResponseBody
    public CustomResponse<Integer> postCharacter_Insert(@RequestBody CharacterDto characterDto) {
        System.out.println("캐릭터");
        characterService.save(characterDto);
        return new CustomResponse<>(characterDto.getId());
    }

    @PostMapping("dungeon_insert")
    @ResponseBody
    public CustomResponse<Integer> postDungeon_Insert(@RequestBody DungeonDto dungeonDto) {
        System.out.println("던전");
        dungeonService.save(dungeonDto);
        return new CustomResponse<>(dungeonDto.getId());
    }

    @PostMapping("group_insert")
    @ResponseBody
    public CustomResponse<Integer> postGroup_Insert(@RequestBody GroupDto groupDto) {
        System.out.println("그룹");
        groupService.save(groupDto);
        return new CustomResponse<>(groupDto.getId());
    }

    @PostMapping("party_insert")
    @ResponseBody
    public CustomResponse<Integer> postCharacter_Insert(@RequestBody PartyRequestDto partyRequestDto) {
        System.out.println("파티");
        Integer partyId = partyService.save(partyRequestDto);
        return new CustomResponse<>(partyId);
    }

    @PostMapping("pac_insert")
    @ResponseBody
    public CustomResponse<Integer> postPAC_Insert(@RequestBody PACRequestDto pacRequestDto) {
        System.out.println("파티원 등록");
        Integer saveId = partyAndCharacterService.save(pacRequestDto);
        return new CustomResponse<>(saveId);
    }

    @PostMapping("cad_insert")
    @ResponseBody
    public CustomResponse<Integer> postCAD_Insert(@RequestBody CADRequestDto cadRequestDto) {
        System.out.println("던전 클리어 여부");
        Integer cadId = characterAndDungeonService.save(cadRequestDto);

        return new CustomResponse<>(cadId);
    }
}
