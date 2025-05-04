package com.neo.neogame.domain.service;

import com.neo.neogame.api.model.CharacterDTO;
import com.neo.neogame.api.model.NewCharacterDTO;
import com.neo.neogame.api.model.TinyCharacterDTO;

import java.util.List;
import java.util.UUID;

public interface CharacterService {

    CharacterDTO create(NewCharacterDTO newCharacterDTO);

    List<TinyCharacterDTO> getAll();

    CharacterDTO getDetails(UUID id);
}
