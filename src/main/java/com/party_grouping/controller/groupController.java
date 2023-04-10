package com.party_grouping.controller;

import com.party_grouping.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class groupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("group_list")
    public String getGroupList(Model model) {
        model.addAttribute("group_data_list", groupService.groupDtoList());

        return "group_list";
    }

    @GetMapping("group/{id}")
    public String getGroupById() {
        return "group";
    }
}
