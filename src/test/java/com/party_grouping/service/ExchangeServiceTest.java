package com.party_grouping.service;

import com.party_grouping.dto.ExchangeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ExchangeServiceTest {
    @Autowired
    private ExchangeService exchangeService;

    @Test
    void test() {
        List<String> list = new ArrayList<>();
        list.add("5EB32FMHB");
        List<ExchangeDto> dtoList = exchangeService.findByPersistentKeyList(list);

        System.out.println(dtoList);

    }

}
