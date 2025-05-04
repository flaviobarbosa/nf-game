package com.neo.neogame.domain.service;

import com.neo.neogame.api.model.CharacterDTO;
import com.neo.neogame.api.model.NewCharacterDTO;

public interface CharacterService {

    CharacterDTO create(NewCharacterDTO newCharacterDTO);

}
