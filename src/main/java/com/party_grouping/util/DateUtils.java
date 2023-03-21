package com.party_grouping.util;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtils {
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
}
