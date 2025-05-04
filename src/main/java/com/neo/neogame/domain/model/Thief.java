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

    @Override
    public int getAttack() {
        return (int) ((this.getStrength() * 0.25) + (this.getDexterity()) + (this.getIntelligence() * 0.25));
    }

    @Override
    public int getSpeed() {
        return (int) (this.getDexterity() * 0.8);
    }
}
