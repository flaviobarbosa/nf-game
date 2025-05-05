package com.neo.neogame.api.model;

import com.neo.neogame.domain.model.Character;
import lombok.Data;

import java.util.UUID;

@Data
public class BattleDTO {

    private UUID player1;
    private UUID player2;

}
