package com.neo.neogame.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.neogame.api.model.CharacterDTO;
import com.neo.neogame.api.model.NewCharacterDTO;
import com.neo.neogame.domain.model.Job;
import com.neo.neogame.domain.model.Stats;
import com.neo.neogame.domain.model.Warrior;
import com.neo.neogame.domain.service.CharacterService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.neo.neogame.api.model.NewCharacterDTO.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharacterController.class)
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CharacterService characterService;

    @Test
    void shouldCreateCharacterWithValidJob() throws Exception {
        NewCharacterDTO newCharacterDTO = new NewCharacterDTO("Thatelch", Job.WARRIOR.toString());

        CharacterDTO characterDTO = CharacterDTO.builder()
                .id(UUID.randomUUID())
                .name(newCharacterDTO.getName())
                .job(newCharacterDTO.getJob())
                .hp(Warrior.hp)
                .strength(Warrior.strength)
                .dexterity(Warrior.dexterity)
                .intelligence(Warrior.intelligence)
                .stats(Stats.ALIVE.toString())
                .attack(9)
                .build();

        when(characterService.create(Mockito.any())).thenReturn(characterDTO);

        mockMvc.perform(post("/character")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCharacterDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(newCharacterDTO.getName()))
                .andExpect(jsonPath("$.job").value(newCharacterDTO.getJob()))
                .andExpect(jsonPath("$.hp").value(characterDTO.getHp()))
                .andExpect(jsonPath("$.strength").value(characterDTO.getStrength()))
                .andExpect(jsonPath("$.dexterity").value(characterDTO.getDexterity()))
                .andExpect(jsonPath("$.intelligence").value(characterDTO.getIntelligence()))
                .andExpect(jsonPath("$.stats").value(characterDTO.getStats()))
                .andExpect(jsonPath("$.attack").value(9));
    }

    @Test
    void shouldNotCreateCharacterWithInvalidJob() throws Exception {
        var input = """
                {
                    "name": "Thatelch",
                    "job": "ninja"
                }
                """;

        mockMvc.perform(post("/character")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value(400))
                .andExpect(jsonPath("$.messages", Matchers.hasItem(INVALID_JOB_MESSAGE)));
    }

    @Test
    void shouldNotCreateCharacterWithInvalidCharactersInName() throws Exception {
        var input = """
                {
                    "name": "John Doe",
                    "job": "mage"
                }
                """;

        mockMvc.perform(post("/character")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value(400))
                .andExpect(jsonPath("$.messages", Matchers.hasItem(NAME_PATTERN_MESSAGE)));
    }

    @Test
    void shouldNotCreateCharacterWithInvalidNameLength() throws Exception {
        var input = """
                {
                    "name": "Joe",
                    "job": "mage"
                }
                """;

        mockMvc.perform(post("/character")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value(400))
                .andExpect(jsonPath("$.messages", Matchers.hasItem(NAME_LENGTH_MESSAGE)));
    }
}
