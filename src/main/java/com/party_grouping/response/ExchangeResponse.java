package com.party_grouping.response;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeResponse {
    private Integer id;

    private Integer exchangeKey;
    private String adventureName;
    private List<CharacterResponse> characterList;
}
