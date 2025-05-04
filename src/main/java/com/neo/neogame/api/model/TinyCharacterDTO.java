package com.neo.neogame.api.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class TinyCharacterDTO {

    private UUID id;
    private String name;
    private String job;
    private String stats;

}
