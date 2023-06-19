package com.party_grouping.response;

import com.party_grouping.dto.CharacterDto;
import com.party_grouping.dto.CharacterItemDto;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterResponse {
    private CharacterDto characterDto;
    private CharacterItemDto characterItemDto;
}
