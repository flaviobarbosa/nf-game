package com.neo.neogame.api.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class CharacterDTO {

    private UUID id;
    private String name;
    private String job;
    private int hp;
    private int strength;
    private int dexterity;
    private int intelligence;
    private String stats;
    private int attack;

}
