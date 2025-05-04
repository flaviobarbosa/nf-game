package com.neo.neogame.domain.mapper;

import com.neo.neogame.api.model.CharacterDTO;
import com.neo.neogame.api.model.TinyCharacterDTO;
import com.neo.neogame.domain.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    public CharacterDTO toDTO(Character character) {
        return CharacterDTO.builder()
                .id(character.getId())
                .name(character.getName())
                .job(character.getJob().toString().toLowerCase())
                .hp(character.getHp())
                .strength(character.getStrength())
                .dexterity(character.getDexterity())
                .intelligence(character.getIntelligence())
                .stats(character.getStats().toString().toLowerCase())
                .attack(character.getAttack())
                .speed(character.getSpeed())
                .build();
    }

    public TinyCharacterDTO toTinyDTO(Character character) {
        return TinyCharacterDTO.builder()
                .id(character.getId())
                .name(character.getName())
                .job(character.getJob().toString().toLowerCase())
                .stats(character.getStats().toString().toLowerCase())
                .build();
    }
}
