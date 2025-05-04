package com.neo.neogame.domain.service;

import com.neo.neogame.api.model.CharacterDTO;
import com.neo.neogame.api.model.NewCharacterDTO;
import com.neo.neogame.domain.factory.CharacterFactory;
import com.neo.neogame.domain.mapper.CharacterMapper;
import com.neo.neogame.domain.model.Character;
import com.neo.neogame.domain.model.Job;
import com.neo.neogame.domain.model.Stats;
import com.neo.neogame.domain.model.Warrior;
import com.neo.neogame.domain.repository.CharacterDB;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CharacterServiceImplTest {

    @InjectMocks
    private CharacterServiceImpl sut;

    @Mock
    private CharacterFactory characterFactory;

    @Mock
    private CharacterDB characterDB;

    @Mock
    private CharacterMapper characterMapper;

    @Test
    void shouldCreateCharacter() {
        NewCharacterDTO newCharacterDTO = new NewCharacterDTO("Thatelch", "warrior");
        Character character = new Warrior("Thatelch");
        CharacterDTO characterDTO = CharacterDTO.builder()
                .id(UUID.randomUUID())
                .name("Thatelch")
                .job(Job.WARRIOR.getName())
                .hp(Warrior.hp)
                .strength(Warrior.strength)
                .dexterity(Warrior.dexterity)
                .intelligence(Warrior.intelligence)
                .stats(Stats.ALIVE.toString())
                .build();

        when(characterFactory.create("warrior", "Thatelch")).thenReturn(character);
        when(characterMapper.toDTO(character)).thenReturn(characterDTO);

        CharacterDTO result = sut.create(newCharacterDTO);

        assertEquals("Thatelch", result.getName());
        assertEquals("warrior", result.getJob());
        assertEquals(Stats.ALIVE.toString(), result.getStats());

        verify(characterFactory, times(1)).create("warrior", "Thatelch");
        verify(characterDB, times(1)).add(character);
        verify(characterMapper, times(1)).toDTO(character);

    }

}
