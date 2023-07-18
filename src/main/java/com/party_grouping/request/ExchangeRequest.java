package com.party_grouping.request;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRequest {
    private String server;
    private String adventureName;
    private List<String> apiIdList;
}
