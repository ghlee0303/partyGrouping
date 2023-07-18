package com.party_grouping.service.mock_test;

import com.party_grouping.api.Api;
import com.party_grouping.api.CharacterYaml;
import com.party_grouping.api.DungeonYaml;
import com.party_grouping.repository.CharacterRepo;
import com.party_grouping.service.CharacterService;
import com.party_grouping.service.api.DnfApiService;
import com.party_grouping.service.inter.ApiService;
import com.party_grouping.service.mock.ApiRequestMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CharacterItemServiceMockTest {
    @Mock
    private CharacterRepo characterRepo;
    private final Api api = new ApiRequestMock();
    private final CharacterYaml characterYaml = new CharacterYaml();
    private final DungeonYaml dungeonYaml = new DungeonYaml();
    private final ApiService apiService = new DnfApiService(api, characterYaml, dungeonYaml);
    private CharacterService characterService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        characterService = new CharacterService(characterRepo, apiService);
    }

}
