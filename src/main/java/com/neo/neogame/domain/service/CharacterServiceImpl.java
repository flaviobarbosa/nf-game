package com.neo.neogame.domain.service;

import com.neo.neogame.api.model.CharacterDTO;
import com.neo.neogame.api.model.NewCharacterDTO;
import com.neo.neogame.domain.exception.InvalidJobException;
import com.neo.neogame.domain.model.Character;
import com.neo.neogame.domain.model.Mage;
import com.neo.neogame.domain.model.Thief;
import com.neo.neogame.domain.model.Warrior;
import com.neo.neogame.domain.repository.CharacterDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterDB db;

    @Override
    public CharacterDTO create(NewCharacterDTO newCharacterDTO) {
        Character character = createCharacter(newCharacterDTO);
        db.add(character);
        return toDTO(character);
    }

    //TODO create factory
    private Character createCharacter(NewCharacterDTO newCharacterDTO) {
        if(newCharacterDTO.getJob().equalsIgnoreCase("warrior")) {
            return new Warrior(newCharacterDTO.getName());
        } else if(newCharacterDTO.getJob().equalsIgnoreCase("mage")) {
            return new Mage(newCharacterDTO.getName());
        } else if(newCharacterDTO.getJob().equalsIgnoreCase("thief")) {
            return new Thief(newCharacterDTO.getName());
        } else {
            throw new InvalidJobException("The job " + newCharacterDTO.getJob() + " is invalid");
        }
    }

    //TODO create mapper
    private CharacterDTO toDTO(Character character) {
        return CharacterDTO.builder()
                .id(character.getId())
                .name(character.getName())
                .job(character.getJob().toString().toLowerCase())
                .hp(character.getHp())
                .strength(character.getStrength())
                .dexterity(character.getDexterity())
                .intelligence(character.getIntelligence())
                .stats(character.getStats().toString().toLowerCase())
                .build();
    }
}
