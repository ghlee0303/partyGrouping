package com.party_grouping.controller;

import com.party_grouping.service.CharacterService;
import com.party_grouping.service.DungeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private DungeonService dungeonService;

    @GetMapping("/")
    public String getMain() {

        return "index";
    }

}
