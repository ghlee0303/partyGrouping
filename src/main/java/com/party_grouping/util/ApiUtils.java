package com.party_grouping.util;

import jakarta.servlet.http.Cookie;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ApiUtils {

    public static String getCookieOpt(Cookie[] cookies, String cookieName) {
        return Arrays.stream(cookies)
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    public static LocalDateTime getLastThursdayOfWeek() {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime lastThursday;
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int daysToAdd = dayOfWeek.getValue() >= DayOfWeek.THURSDAY.getValue() ? 0 : -7;
        lastThursday = date.plusDays(daysToAdd).with(DayOfWeek.THURSDAY).with(LocalTime.of(10, 0, 0));

        return lastThursday;
    }

    public static long daysBetween(LocalDateTime date1, LocalDateTime date2) {
        Duration duration = Duration.between(date1.toLocalDate().atStartOfDay(), date2.toLocalDate().atStartOfDay());
        return Math.abs(duration.toDays());
    }

    public static boolean isDuplicate(List<String> stringList) {
        Set<String> dup = new HashSet<>(stringList);

        return dup.size() != stringList.size();
    }

    public static boolean listContains(List<String> list, String element) {
        return list.stream().anyMatch(element::contains);
    }
}
