package com.party_grouping.repository;

import com.party_grouping.config.SpringConfig;
import com.party_grouping.config.TestConfig;
import com.party_grouping.dto.DungeonDto;
import com.party_grouping.dto.GroupDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(SpringConfig.class)
public class groupRepoTest {
    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private DungeonRepo dungeonRepo;

    @Test
    void test1() {
        List<GroupDto> groupDtoList = groupRepo.findListDto();
        System.out.println(groupDtoList);

        List<DungeonDto> dungeonDtoList = dungeonRepo.findListDto();
        System.out.println(dungeonDtoList);

        DungeonDto dungeonDto = dungeonRepo.findByIdOptDto(1).get();
        System.out.println(dungeonDto.getName());
    }

    @Test
    void test2() {
        LocalDateTime localDateTime = getLastThursdayOfWeek(LocalDateTime.now().minusDays(3));
        LocalDateTime localDateTimeTest = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(localDateTimeTest);
        System.out.println(localDateTime.isBefore(localDateTimeTest));
    }

    public static LocalDateTime getLastThursdayOfWeek(LocalDateTime date) {
        LocalDateTime lastThursday;
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int daysToAdd = dayOfWeek.getValue() >= DayOfWeek.THURSDAY.getValue() ? 0 : -7;
        lastThursday = date.plusDays(daysToAdd).with(DayOfWeek.THURSDAY).with(LocalTime.of(10, 0, 0));

        return lastThursday;
    }
}
