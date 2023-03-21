package com.party_grouping.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartyRequestDto {
    @JsonProperty("party_dungeon_id")
    private Integer dungeon_id;
    @JsonProperty("party_group_id")
    private Integer group_id;
    @JsonProperty("party_entry_time")
    private LocalDateTime entryTime;
    @JsonProperty("party_name")
    private String name;
}
