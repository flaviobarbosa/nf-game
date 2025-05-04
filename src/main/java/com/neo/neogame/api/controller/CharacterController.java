package com.neo.neogame.api.controller;

import com.neo.neogame.api.model.CharacterDTO;
import com.neo.neogame.api.model.NewCharacterDTO;
import com.neo.neogame.domain.service.CharacterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterDTO create(@RequestBody @Valid NewCharacterDTO newCharacterDTO) {
        return characterService.create(newCharacterDTO);
    }

}
