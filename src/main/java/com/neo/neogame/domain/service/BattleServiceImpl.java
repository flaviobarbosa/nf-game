package com.neo.neogame.domain.service;

import com.neo.neogame.domain.exception.InvalidBattleException;
import com.neo.neogame.domain.model.Battle;
import com.neo.neogame.domain.model.Character;
import com.neo.neogame.domain.model.Stats;
import com.neo.neogame.domain.repository.CharacterDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BattleServiceImpl implements BattleService {

    private final CharacterDB characterDB;
    private final Random random;
    private final Battle battle;

    @Override
    public Battle battle(UUID idPlayer1, UUID idPlayer2) {
        Optional<Character> player1 = characterDB.getById(idPlayer1);
        Optional<Character> player2 = characterDB.getById(idPlayer2);

        validatePlayers(player1, player2);

        start(player1.get(), player2.get());

        return battle;
    }

    private void validatePlayers(Optional<Character> player1, Optional<Character> player2) {
        if(player1.isEmpty() && player2.isEmpty()) {
            throw new InvalidBattleException("Player1 and Player2 were not found");
        }

        if(player1.isEmpty()) {
            throw new InvalidBattleException("Player1 was not found");
        }

        if(player2.isEmpty()) {
            throw new InvalidBattleException("Player2 was not found");
        }

        if(player1.get().isDead() && player2.get().isDead()) {
            throw new InvalidBattleException("Player1 and Player2 are dead");
        }

        if(player1.get().isDead()) {
            throw new InvalidBattleException("Player1 is dead");
        }

        if(player2.get().isDead()) {
            throw new InvalidBattleException("Player2 is dead");
        }
    }

    private void start(Character player1, Character player2) {
        battle.logBattleBegins(player1, player2);

        while (player1.isAlive() && player2.isAlive()) {
            playRound(player1, player2);
        }
    }

    private void playRound(Character player1, Character player2) {
        int speedPlayer1, speedPlayer2;

        do {
            speedPlayer1 = random.nextInt(player1.getSpeed() + 1);
            speedPlayer2 = random.nextInt(player2.getSpeed() + 1);
        } while (speedPlayer1 == speedPlayer2);

        Character first, second;
        int speedFirst, speedSecond;

        if(speedPlayer1 > speedPlayer2) {
            first = player1;
            speedFirst = speedPlayer1;

            second = player2;
            speedSecond = speedPlayer2;
        } else {
            first = player2;
            speedFirst = speedPlayer2;

            second = player1;
            speedSecond = speedPlayer1;
        }

        battle.logRound(first, speedFirst, second, speedSecond);

        attack(first, second);

        if(second.isDead()) {
            battle.logVictory(first);
        } else {
            attack(second, first);

            if(first.isDead()) {
                battle.logVictory(second);
            }
        }
    }

    private void attack(Character attacker, Character defender) {
        int damage = random.nextInt(attacker.getAttack());
        int newHp = Math.max(0, defender.getHp() - damage);
        defender.setHp(newHp);

        battle.logAttack(attacker, defender, damage, newHp);

        if (newHp == 0) {
            defender.setStats(Stats.DEAD);
        }
    }
}
