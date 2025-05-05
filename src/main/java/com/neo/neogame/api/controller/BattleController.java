package com.neo.neogame.api.controller;

import com.neo.neogame.api.model.BattleDTO;
import com.neo.neogame.domain.model.Battle;
import com.neo.neogame.domain.service.BattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/battles")
@RequiredArgsConstructor
public class BattleController {

    private final BattleService battleService;

    @PostMapping
    public Battle battle(@RequestBody BattleDTO battle) {
        return battleService.battle(battle.getPlayer1(), battle.getPlayer2());
    }
}
