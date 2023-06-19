package com.party_grouping.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.party_grouping.request.ExchangeRequestDto;
import com.party_grouping.service.CharacterItemService;
import com.party_grouping.service.CharacterService;
import com.party_grouping.service.ExchangeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.*;

@Controller
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private CharacterItemService characterItemService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private ObjectMapper mapper;
    private HttpHeaders header;
    private static final String COOKIE_NAME = "exchangeKey";

    public ExchangeController() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    }
    @GetMapping("test")
    public String test() {
        return "test";
    }

    @GetMapping("exchange")
    public String getExchange(HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {
        return "exchange";
    }

    @PostMapping("exchange")
    @ResponseBody
    public ResponseEntity<String> postExchange(@RequestBody String json) {
        String persistentKey = exchangeService.save(exchangeDtoParsing(json));
        System.out.println(persistentKey);

        return new ResponseEntity<>(persistentKey, header, HttpStatus.OK);
    }

    @DeleteMapping("exchange")
    @ResponseBody
    public ResponseEntity<Boolean> deleteExchange(@RequestParam("persistentKey") String persistentKey) {
        exchangeService.deleteExchange(persistentKey);

        return new ResponseEntity<>(true, header, HttpStatus.OK);
    }

    @PostMapping("exchange_list")
    @ResponseBody
    public ResponseEntity<List<ExchangeDto>> postExchangeList(@RequestBody List<String> persistentKeyList) {
        List<ExchangeDto> exchangeDtoList = exchangeService.findByPersistentKeyList(persistentKeyList);

        return new ResponseEntity<>(exchangeDtoList, header, HttpStatus.OK);
    }

    @GetMapping("exchange_key")
    @ResponseBody
    public ResponseEntity<Integer> getExchangeKey(@RequestParam("persistentKey") String persistentKey) {
        return new ResponseEntity<>(exchangeService.createExchangeKey(persistentKey), header, HttpStatus.OK);
    }

    private ExchangeRequestDto exchangeDtoParsing(String json) {
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.optJSONArray("apiIdList").length() != 4) {
            throw new ApiException(ErrorCode.PARTY_MEMBERS_NOT_ENOUGH);
        }

        return new ExchangeRequestDto(jsonObject);
    }


}
