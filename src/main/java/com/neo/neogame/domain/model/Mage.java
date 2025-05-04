package com.neo.neogame.domain.model;

import java.util.UUID;

public class Mage extends Character {

    private static final int hp = 12;
    private static final int strength = 5;
    private static final int dexterity = 6;
    private static final int intelligence = 10;

    public Mage(String name) {
        super(UUID.randomUUID(), name, Job.MAGE, hp, strength, dexterity, intelligence, Stats.ALIVE);
    }

}
