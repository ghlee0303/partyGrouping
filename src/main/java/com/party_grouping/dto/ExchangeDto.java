package com.party_grouping.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDto extends BaseDto {
    @JsonIgnore
    protected Integer id;

    protected String persistentKey;
    protected Integer exchangeKey;

    protected CharacterDto character1;
    protected CharacterDto character2;
    protected CharacterDto character3;
    protected CharacterDto character4;
}
