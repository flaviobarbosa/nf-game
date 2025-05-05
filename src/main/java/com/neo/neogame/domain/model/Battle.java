package com.neo.neogame.domain.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Battle {

    private List<String> log = new ArrayList<>();

    public List<String> getLog() {
        return new ArrayList<>(log);
    }

    public void logBattleBegins(Character player1, Character player2) {
        log.add(String.format("Battle between %s (%s) - %d HP and %s (%s) - %d HP begins!",
                player1.getName(), player1.getJob().getName(), player1.getHp(), player2.getName(), player2.getJob().getName(), player2.getHp()));
    }

    public void logRound(Character first, int speedFirst, Character second, int speedSecond) {
        log.add(String.format("%s %d speed was faster than %s %d speed and will begin this round",
                first.getName(), speedFirst, second.getName(), speedSecond));
    }

    public void logAttack(Character attacker, Character defender, int damage, int newHp) {
        log.add(String.format("%s attacks %s for %d, %s has %d HP remaining",
                attacker.getName(), defender.getName(), damage, defender.getName(), newHp));
    }

    public void logVictory(Character winner) {
        log.add(String.format("%s wins the battle! %s still has %d HP remaining!",
                winner.getName(), winner.getName(), winner.getHp()));
    }
}
