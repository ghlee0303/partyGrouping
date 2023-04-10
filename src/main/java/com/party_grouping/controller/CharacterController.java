package com.party_grouping.controller;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.response.CustomResponse;
import com.party_grouping.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @GetMapping("character")
    public String getCharacter() {
        return "character";
    }

    @GetMapping("character_search")
    @ResponseBody
    public CustomResponse<List<CharacterDto>> getCharacterSearch(@RequestParam(value = "name") String name) {
        return new CustomResponse<>(characterService.saveByApiDnf("cain", name));
    }
}
