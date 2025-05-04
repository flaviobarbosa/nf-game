package com.neo.neogame.domain.factory;

import com.neo.neogame.api.model.NewCharacterDTO;
import com.neo.neogame.domain.exception.InvalidJobException;
import com.neo.neogame.domain.model.Character;
import com.neo.neogame.domain.model.Mage;
import com.neo.neogame.domain.model.Thief;
import com.neo.neogame.domain.model.Warrior;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.neo.neogame.api.model.NewCharacterDTO.INVALID_JOB_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CharacterFactoryTest {

    private final CharacterFactory factory = new CharacterFactory();

    @Test
    void shouldCreateWarriorWhenJobIsWarrior() {
        Character character = factory.create("warrior", "Thatelch");
        assertTrue(character instanceof Warrior);
        assertEquals("Thatelch", character.getName());
    }

    @Test
    void shouldCreateMageWhenJobIsMage() {
        Character character = factory.create("mage", "John");
        assertTrue(character instanceof Mage);
        assertEquals("John", character.getName());
    }

    @Test
    void shouldCreateThiefWhenJobIsThief() {
        Character character = factory.create("thief", "Garrett");
        assertTrue(character instanceof Thief);
        assertEquals("Garrett", character.getName());
    }

    @Test
    void shouldThrowExceptionForInvalidJob() {
        InvalidJobException exception = assertThrows(
                InvalidJobException.class,
                () -> factory.create("knight", "Arthur")
        );
        assertEquals(INVALID_JOB_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenJobIsNull() {
        InvalidJobException exception = assertThrows(
                InvalidJobException.class,
                () -> factory.create(null, "Arthur")
        );
        assertEquals(INVALID_JOB_MESSAGE, exception.getMessage());
    }
}
