package com.party_grouping.controller;

import com.party_grouping.exception.ApiException;
import com.party_grouping.service.ExchangeService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;
    HttpHeaders header;

    @GetMapping
    public String getExchange() {
        return null;
    }

    @PostMapping("exchange")
    @ResponseBody
    public ResponseEntity<String> postExchange(@RequestBody String json) {
        JSONArray jsonArray = new JSONArray(json);
        if (jsonArray.length() != 4) {
            throw new ApiException("멤버가 4명이 되어야 합니다.", 404);
        }

        List<String> apiIdList = Arrays.asList(jsonArray.getString(0)
                , jsonArray.getString(1)
                , jsonArray.getString(2)
                , jsonArray.getString(3));

        if (apiIdList.size() != apiIdList.stream().distinct().count()) {
            throw new ApiException("멤버가 중복되었습니다.", 404);
        }

        System.out.println(apiIdList);
        exchangeService.save(apiIdList);
        return new ResponseEntity<>("ok", header, HttpStatus.OK);
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }
}
