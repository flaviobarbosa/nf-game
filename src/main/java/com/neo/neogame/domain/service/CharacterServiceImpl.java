package com.neo.neogame.domain.service;

import com.neo.neogame.api.model.CharacterDTO;
import com.neo.neogame.api.model.NewCharacterDTO;
import com.neo.neogame.api.model.TinyCharacterDTO;
import com.neo.neogame.domain.factory.CharacterFactory;
import com.neo.neogame.domain.mapper.CharacterMapper;
import com.neo.neogame.domain.model.Character;
import com.neo.neogame.domain.repository.CharacterDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterDB db;
    private final CharacterMapper characterMapper;
    private final CharacterFactory characterFactory;

    @Override
    public CharacterDTO create(NewCharacterDTO newCharacterDTO) {
        Character character = characterFactory.create(newCharacterDTO.getJob(), newCharacterDTO.getName());
        db.add(character);
        return characterMapper.toDTO(character);
    }

    @Override
    public List<TinyCharacterDTO> getAll() {
        return db.getAll()
                .stream()
                .map(character ->  characterMapper.toTinyDTO(character))
                .toList();
    }
}
