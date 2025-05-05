package com.neo.neogame.domain.service;

import com.neo.neogame.domain.exception.InvalidBattleException;
import com.neo.neogame.domain.model.Battle;
import com.neo.neogame.domain.model.Character;
import com.neo.neogame.domain.model.Stats;
import com.neo.neogame.domain.repository.CharacterDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BattleServiceImplTest {

    @InjectMocks
    private BattleServiceImpl sut;

    @Mock
    private CharacterDB characterDB;

    @Mock
    private Random random;

    @Mock
    private Battle battle;

    @Mock
    private Character player1;

    @Mock
    private Character player2;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldThrowErrorWhenBothPlayersNotFound() {
        UUID idPlayer1 = UUID.randomUUID();
        UUID idPlayer2 = UUID.randomUUID();

        when(characterDB.getById(idPlayer1)).thenReturn(Optional.empty());
        when(characterDB.getById(idPlayer2)).thenReturn(Optional.empty());

        InvalidBattleException ex = assertThrows(
                InvalidBattleException.class,
                () -> sut.battle(idPlayer1, idPlayer2)
        );

        assertEquals("Player1 and Player2 were not found", ex.getMessage());
    }

    @Test
    void shouldThrowErrorWhenPlayer1NotFound() {
        UUID idPlayer1 = UUID.randomUUID();
        UUID idPlayer2 = UUID.randomUUID();

        when(characterDB.getById(idPlayer1)).thenReturn(Optional.empty());
        when(characterDB.getById(idPlayer2)).thenReturn(Optional.of(player2));

        InvalidBattleException ex = assertThrows(
                InvalidBattleException.class,
                () -> sut.battle(idPlayer1, idPlayer2)
        );

        assertEquals("Player1 was not found", ex.getMessage());
    }

    @Test
    void shouldThrowErrorWhenPlayer2NotFound() {
        UUID idPlayer1 = UUID.randomUUID();
        UUID idPlayer2 = UUID.randomUUID();

        when(characterDB.getById(idPlayer1)).thenReturn(Optional.of(player1));
        when(characterDB.getById(idPlayer2)).thenReturn(Optional.empty());

        InvalidBattleException ex = assertThrows(
                InvalidBattleException.class,
                () -> sut.battle(idPlayer1, idPlayer2)
        );

        assertEquals("Player2 was not found", ex.getMessage());
    }

    @Test
    void shouldThrowErrorWhenBothPlayersAreDead() {
        UUID idPlayer1 = UUID.randomUUID();
        UUID idPlayer2 = UUID.randomUUID();

        when(characterDB.getById(idPlayer1)).thenReturn(Optional.of(player1));
        when(characterDB.getById(idPlayer2)).thenReturn(Optional.of(player2));

        when(player1.isDead()).thenReturn(true);
        when(player2.isDead()).thenReturn(true);

        InvalidBattleException ex = assertThrows(
                InvalidBattleException.class,
                () -> sut.battle(idPlayer1, idPlayer2)
        );

        assertEquals("Player1 and Player2 are dead", ex.getMessage());
    }

    @Test
    void shouldThrowErrorWhenPlayer1IsDead() {
        UUID idPlayer1 = UUID.randomUUID();
        UUID idPlayer2 = UUID.randomUUID();

        when(characterDB.getById(idPlayer1)).thenReturn(Optional.of(player1));
        when(characterDB.getById(idPlayer2)).thenReturn(Optional.of(player2));

        when(player1.isDead()).thenReturn(true);
        when(player2.isDead()).thenReturn(false);

        InvalidBattleException ex = assertThrows(
                InvalidBattleException.class,
                () -> sut.battle(idPlayer1, idPlayer2)
        );

        assertEquals("Player1 is dead", ex.getMessage());
    }

    @Test
    void shouldThrowErrorWhenPlayer2IsDead() {
        UUID idPlayer1 = UUID.randomUUID();
        UUID idPlayer2 = UUID.randomUUID();

        when(characterDB.getById(idPlayer1)).thenReturn(Optional.of(player1));
        when(characterDB.getById(idPlayer2)).thenReturn(Optional.of(player2));

        when(player1.isDead()).thenReturn(false);
        when(player2.isDead()).thenReturn(true);

        InvalidBattleException ex = assertThrows(
                InvalidBattleException.class,
                () -> sut.battle(idPlayer1, idPlayer2)
        );

        assertEquals("Player2 is dead", ex.getMessage());
    }

    @Test
    void shouldDeclarePlayer1Winner() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        when(characterDB.getById(id1)).thenReturn(Optional.of(player1));
        when(characterDB.getById(id2)).thenReturn(Optional.of(player2));

        when(random.nextInt(anyInt()))
                .thenReturn(5)  // speed player1
                .thenReturn(1)  // speed player2
                .thenReturn(50); // attack damage

        doAnswer(invocation -> {
            when(player2.isAlive()).thenReturn(false);
            when(player2.isDead()).thenReturn(true);
            return null;
        }).when(player2).setStats(Stats.DEAD);

        when(player1.isAlive()).thenReturn(true);
        when(player2.isAlive()).thenReturn(true);

        when(player2.getHp()).thenReturn(0);

        when(player1.getAttack()).thenReturn(100);

        when(player1.getSpeed()).thenReturn(10);
        when(player2.getSpeed()).thenReturn(10);

        Battle result = sut.battle(id1, id2);

        verify(battle).logVictory(player1);
        assertEquals(battle, result);
    }

    @Test
    void shouldDeclarePlayer2Winner() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        when(characterDB.getById(id1)).thenReturn(Optional.of(player1));
        when(characterDB.getById(id2)).thenReturn(Optional.of(player2));

        when(random.nextInt(anyInt()))
                .thenReturn(1)
                .thenReturn(5)
                .thenReturn(50);

        doAnswer(invocation -> {
            when(player1.isAlive()).thenReturn(false);
            when(player1.isDead()).thenReturn(true);
            return null;
        }).when(player1).setStats(Stats.DEAD);

        when(player1.isAlive()).thenReturn(true);
        when(player2.isAlive()).thenReturn(true);
        when(player1.getHp()).thenReturn(0);

        when(player1.getHp()).thenReturn(10);

        when(player2.getAttack()).thenReturn(100);

        when(player1.getSpeed()).thenReturn(10);
        when(player2.getSpeed()).thenReturn(10);

        Battle result = sut.battle(id1, id2);

        verify(battle).logVictory(player2);
        assertEquals(battle, result);
    }
}
