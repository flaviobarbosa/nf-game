package com.neo.neogame.domain.model;

public enum Job {

    WARRIOR("warrior"),
    THIEF("thief"),
    MAGE("mage");

    private String name;

    Job(String name) {
        this.name = name;
    }
}
