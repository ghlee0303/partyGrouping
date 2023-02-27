package com.party_grouping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping("/")
    public String getMain() {
        String test = "1234";

        return test;
    }
}
