package com.party_grouping.service;

import com.party_grouping.service.api.DnfApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DnfApiServiceTest {
    @Autowired
    private DnfApiService dnfApiService;

    @Test
    void test() {
        System.out.println(dnfApiService.callWeeklyDungeon("cain", "68642c1010daed6d46b78a80b7d0fe2d"));
    }
}
