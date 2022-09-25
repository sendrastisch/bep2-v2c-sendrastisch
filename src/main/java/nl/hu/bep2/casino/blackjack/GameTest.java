package nl.hu.bep2.casino.blackjack;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.enums.GameState;
import nl.hu.bep2.casino.blackjack.domain.enums.Suit;
import nl.hu.bep2.casino.blackjack.domain.enums.Value;
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

        assertEquals(game.getDealerHand().getSizeOfHand(), 2);
        assertEquals(game.getPlayerHand().getSizeOfHand(), 2);
    }

    @Test
    void startGameTwiceThrowsException(){

        assertThrows(GameAlreadyStartedException.class, () -> {
            Game game = new Game();
            game.startGame();
            game.startGame();
        });
    }
    @Test
    void testBlackjack(){
        Game game = new Game();

        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        Card card = new Card(Suit.SPADE, Value.TEN);
        Card card2 = new Card(Suit.SPADE, Value.ACE);

        playerHand.addCardToHand(card);
        playerHand.addCardToHand(card2);

        Card card3 = new Card(Suit.SPADE, Value.TEN);
        Card card4 = new Card(Suit.SPADE, Value.THREE);
        dealerHand.addCardToHand(card3);
        dealerHand.addCardToHand(card4);

        game.setPlayerHand(playerHand);
        game.setDealerHand(dealerHand);

        assertTrue(game.checkBlackjack(playerHand));
    }



}