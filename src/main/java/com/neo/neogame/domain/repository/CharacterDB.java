package com.neo.neogame.domain.repository;

import com.neo.neogame.domain.model.Character;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterDB {

    private List<Character> characters = new ArrayList<>();

    public List<Character> getAll() {
        return new ArrayList<>(characters);
    }

    public void add(Character character) {
        characters.add(character);
    }
}
