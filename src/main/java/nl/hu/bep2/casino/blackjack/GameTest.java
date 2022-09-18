package nl.hu.bep2.casino.blackjack;

import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.enums.GameState;
import nl.hu.bep2.casino.blackjack.domain.exceptions.GameAlreadyStartedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void startGameTestState(){
        Game game = new Game();
        game.startGame();
        assertEquals(game.getState(), GameState.PLAYING);
    }

    @Test
    void startGameTestHands(){
        Game game = new Game();
        game.startGame();

        assertEquals(game.getDealerHand().size(), 2);
        assertEquals(game.getPlayerHand().size(), 2);
    }

    @Test
    void startGameTwiceThrowsException(){

        assertThrows(GameAlreadyStartedException.class, () -> {
            Game game = new Game();
            game.startGame();
            game.startGame();
        });
    }



}