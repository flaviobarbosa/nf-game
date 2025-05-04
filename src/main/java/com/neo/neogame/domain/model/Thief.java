package com.neo.neogame.domain.model;

import java.util.UUID;

public class Thief extends Character {

    private static final int hp = 15;
    private static final int strength = 4;
    private static final int dexterity = 10;
    private static final int intelligence = 4;

    public Thief(String name) {
        super(UUID.randomUUID(), name, Job.THIEF, hp, strength, dexterity, intelligence, Stats.ALIVE);
    }

}
