package com.neo.neogame.api.controller;

import com.neo.neogame.domain.model.Battle;
import com.neo.neogame.domain.service.BattleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BattleController.class)
class BattleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BattleService battleService;

    @Test
    void shouldStartBattle() throws Exception {
        UUID idPlayer1 = UUID.randomUUID();
        UUID idPlayer2 = UUID.randomUUID();

        Battle battle = new Battle();

        when(battleService.battle(idPlayer1, idPlayer2)).thenReturn(battle);

        String json = String.format("""
                {
                    "player1": "%s",
                    "player2": "%s"
                }
                """, idPlayer1, idPlayer2);

        mockMvc.perform(post("/battles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.log").exists());
    }
}
