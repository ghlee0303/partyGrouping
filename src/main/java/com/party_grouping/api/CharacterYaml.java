package com.party_grouping.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@ConfigurationProperties("character")
@Getter
@Setter
public class CharacterYaml {
    private HashMap<String, Integer> buff;
    private List<String> buffer;
    private List<String> elemental;
    private List<String> title;
    private List<String> aurora;
    private List<String> creature;
}
