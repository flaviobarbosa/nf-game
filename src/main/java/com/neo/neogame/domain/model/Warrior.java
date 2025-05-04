package com.neo.neogame.domain.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Warrior extends Character {

    public static final int hp = 20;
    public static final int strength = 10;
    public static final int dexterity = 5;
    public static final int intelligence = 5;

    public Warrior(String name) {
        super(UUID.randomUUID(), name, Job.WARRIOR, hp, strength, dexterity, intelligence, Stats.ALIVE);
    }

    @Override
    public int getAttack() {
        return (int) ((this.getStrength() * 0.8) + (this.getDexterity() * 0.2));
    }

    @Override
    public int getSpeed() {
        return (int) ((this.getDexterity() * 0.6) + (this.getIntelligence() * 0.2));
    }
}
