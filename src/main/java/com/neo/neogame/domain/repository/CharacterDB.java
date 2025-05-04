package com.neo.neogame.domain.repository;

import com.neo.neogame.domain.model.Character;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CharacterDB {

    private List<Character> characters = new ArrayList<>();

    public List<Character> getAll() {
        return new ArrayList<>(characters);
    }

    public void add(Character character) {
        characters.add(character);
    }

    public Optional<Character> getById(UUID id) {
        return characters.stream()
                .filter(character -> character.getId().equals(id))
                .findFirst();
    }
}
