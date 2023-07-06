package com.party_grouping.service.mock_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MockTest {

    @Test
    void sss() {
        for (int j = 0; j < 3; j++) {
            LinkedList<Integer> integers = new LinkedList<>();

            for (int i = 0; i < 30; i++) {
                integers.add(i);
            }
            List<Integer> integerList = integers.stream().toList();

            System.out.println(integerList);
        }
    }
}
