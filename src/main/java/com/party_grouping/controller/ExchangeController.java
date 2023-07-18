package com.party_grouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.party_grouping.request.ExchangeRequest;
import com.party_grouping.response.CharacterResponse;
import com.party_grouping.response.ExchangeResponse;
import com.party_grouping.service.CharacterService;
import com.party_grouping.service.ExchangeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private ObjectMapper mapper;

    @GetMapping("exchange")
    public String getExchange(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        return "exchange";
    }

    @GetMapping("exchange_manage")
    public String getExchangeManage(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Model model) {
        return "exchange_manage";
    }

    @PostMapping("exchange_manage")
    @ResponseBody
    public ResponseEntity<Integer> uploadExchange(HttpSession session, @RequestBody String json) {
        List<Integer> exchangeSession = getSessionExchange(session);
        ExchangeDto exchangeDto = exchangeService.save(parsingExchangeRequest(json));
        exchangeSession.add(exchangeDto.getId());
        setSessionExchange(session, exchangeSession);

        return ResponseEntity.ok(exchangeDto.getId());
    }

    @DeleteMapping("exchange_manage")
    @ResponseBody
    public ResponseEntity<Boolean> deleteExchange(HttpSession session, @RequestParam("id") Integer id) {
        List<Integer> exchangeSession = getSessionExchange(session);

        // 세션에 값이 있는지 먼저 검증함
        if (!exchangeSession.contains(id)) {
            return ResponseEntity.notFound().build();
        }

        // 세션에 값이 있다면 DB에 교환파티 삭제 요청 및 세션에서도 삭제
        boolean result = exchangeService.deleteExchange(id);
        exchangeSession.remove(id);

        if (!result) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(true);
    }

    @GetMapping("exchange_search")
    @ResponseBody
    public ResponseEntity<List<ExchangeResponse>> searchExchange(@RequestParam(value = "search") String search,
                                                            @RequestParam(value = "type") String type) {    // adventure = 모험단 검색,
                                                                                                            // number = 파티번호 검색
        List<ExchangeDto> exchangeDtoList;

        switch (type) {
            case "adventure" -> exchangeDtoList = exchangeService.findExchangeByAdventure(search);
            case "number" -> {
                Integer exchangeKey = validateExchangeKey(search);
                exchangeDtoList = Collections.singletonList(exchangeService.findExchange(exchangeKey));
            }
            default -> {
                return ResponseEntity.notFound().build();
            }
        }

        List<ExchangeResponse> responseList = exchangeDtoList.stream()
                .map(exchangeService::createResponse).toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("exchange_session")
    @ResponseBody
    public ResponseEntity<List<ExchangeResponse>> searchExchangeListBySession(HttpSession session,
                                                                         @RequestParam("page") Integer page) {
        List<ExchangeResponse> responseList = exchangeService.findExchangeList(getSessionExchange(session)).stream()
                .map(exchangeService::createResponse).toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("exchange_key")
    @ResponseBody
    public ResponseEntity<Integer> generateExchangeKey(@RequestParam("id") Integer id) {
        Integer result = exchangeService.createExchangeKey(id);

        return ResponseEntity.ok(result);
    }

    @GetMapping("exchange_refresh")
    @ResponseBody
    public List<CharacterDto> refreshExchange(@RequestParam("id") Integer id) {
        return exchangeService.exchangeReset(id);
    }

    @GetMapping("exchange_test")
    @ResponseBody
    public ResponseEntity<ExchangeResponse> getExchangeTest() {

        return ResponseEntity.ok(null);
    }

    private ExchangeRequest parsingExchangeRequest(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray apiIdListArray = jsonObject.optJSONArray("apiIdList");

        if (apiIdListArray == null || apiIdListArray.length() != 4) {
            throw new ApiException(ErrorCode.PARTY_MEMBERS_NOT_ENOUGH);
        }

        List<String> apiIdList = new ArrayList<>();

        for (int i = 0; i < apiIdListArray.length(); i++) {
            String apiId = apiIdListArray.getString(i);
            apiIdList.add(apiId);
        }

        return ExchangeRequest.builder()
                .server(jsonObject.getString("server"))
                .adventureName(jsonObject.getString("adventureName"))
                .apiIdList(apiIdList)
                .build();
    }

    private Integer validateExchangeKey(String exchangeKey) {
        if (exchangeKey.isEmpty()) {
            return null;
        }
        
        try {
            return Integer.parseInt(exchangeKey);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @SuppressWarnings("unchecked")
    private List<Integer> getSessionExchange(HttpSession session) {
        List<Integer> exchangeSession = (List<Integer>) session.getAttribute("exchange");
        if (exchangeSession == null) {
            exchangeSession = new ArrayList<>();
        }
        return exchangeSession;
    }

    private void setSessionExchange(HttpSession session, List<Integer> exchangeSession) {
        session.setAttribute("exchange", exchangeSession);
    }

}
