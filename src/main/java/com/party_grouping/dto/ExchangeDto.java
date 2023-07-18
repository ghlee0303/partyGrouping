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
    protected Integer id;

    protected Integer exchangeKey;
    protected String adventureName;

    protected CharacterDto character1;
    protected CharacterDto character2;
    protected CharacterDto character3;
    protected CharacterDto character4;
}
