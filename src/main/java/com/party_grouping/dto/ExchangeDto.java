package com.party_grouping.dto;

import com.party_grouping.entity.CharacterEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDto extends BaseDto {
    protected Integer id;

    protected String exchangeKey;

    protected CharacterDto character1;
    protected CharacterDto character2;
    protected CharacterDto character3;
    protected CharacterDto character4;
}
