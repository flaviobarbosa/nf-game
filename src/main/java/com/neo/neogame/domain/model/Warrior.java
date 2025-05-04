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
}
