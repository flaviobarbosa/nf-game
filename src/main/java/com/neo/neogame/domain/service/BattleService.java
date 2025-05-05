package com.neo.neogame.domain.service;

import com.neo.neogame.domain.model.Battle;

import java.util.UUID;

public interface BattleService {

    Battle battle(UUID idPlayer1, UUID idPlayer2);

}
