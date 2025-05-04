package com.neo.neogame.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public abstract class Character {

    private UUID id;
    private String name;
    private Job job;
    private int hp;
    private int strength;
    private int dexterity;
    private int intelligence;
    private Stats stats;

    public abstract int getAttack();
    public abstract int getSpeed();

}
