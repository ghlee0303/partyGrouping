package com.party_grouping.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ConfigurationProperties("dungeon")
@Getter
@Setter
public class DungeonYaml {
    private HashMap<String, String> region;
    private HashMap<String, String> raid;
}
