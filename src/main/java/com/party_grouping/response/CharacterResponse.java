package com.party_grouping.response;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.CharacterItemDto;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterResponse {
    private CharacterDto character;
    private HashMap<String, String> dungeon;
}
