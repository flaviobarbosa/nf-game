package com.neo.neogame.domain.model;

public enum Stats {

    ALIVE("alive"),
    DEAD("dead");

    private String description;

    Stats(String description) {
        this.description = description;
    }
}
