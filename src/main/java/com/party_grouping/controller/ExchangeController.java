package com.party_grouping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.ExchangeDto;
import com.party_grouping.exception.ApiException;
import com.party_grouping.exception.ErrorCode;
import com.party_grouping.request.ExchangeRequestDto;
import com.party_grouping.service.ExchangeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;
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
        ExchangeDto exchangeDto = exchangeService.save(exchangeDtoParsing(json));
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
    public ResponseEntity<ExchangeDto> searchExchange(@RequestParam(value = "exchangeKey", required = false) String StrExchangeKey) {
        Integer exchangeKey = validateExchangeKey(StrExchangeKey);
        if (exchangeKey == null)
            return ResponseEntity.notFound().build();

        ExchangeDto exchangeDto = exchangeService.findExchange(exchangeKey);                           // 교환 키로 검색 Integer

        if (exchangeDto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(exchangeDto);
    }

    @GetMapping("exchange_session")
    @ResponseBody
    public ResponseEntity<List<ExchangeDto>> searchExchangeListBySession(HttpSession session,
                                                                         @RequestParam("page") Integer page) {
        List<ExchangeDto> exchangeSession = exchangeService.findExchangeList(getSessionExchange(session));

        return ResponseEntity.ok(exchangeSession);
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

    private ExchangeRequestDto exchangeDtoParsing(String json) {
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.optJSONArray("apiIdList").length() != 4) {
            throw new ApiException(ErrorCode.PARTY_MEMBERS_NOT_ENOUGH);
        }

        return new ExchangeRequestDto(jsonObject);
    }

    private Integer validateExchangeKey(String exchangeKey) {
        Integer key = null;
        try {
            key = Integer.parseInt(exchangeKey);
        } catch (NumberFormatException e) {
            return null;
        }

        return key;
    }

    @SuppressWarnings("unchecked")
    private List<String> getSessionExchange2(HttpSession session) {
        List<String> exchangeSession = (List<String>) session.getAttribute("exchange");
        if (exchangeSession == null) {
            exchangeSession = new ArrayList<>();
        }
        return exchangeSession;
    }

    private void setSessionExchange2(HttpSession session, List<String> exchangeSession) {
        session.setAttribute("exchange", exchangeSession);
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
