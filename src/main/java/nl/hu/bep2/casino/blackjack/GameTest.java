package nl.hu.bep2.casino.blackjack;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.enums.GameState;
import nl.hu.bep2.casino.blackjack.domain.enums.Suit;
import nl.hu.bep2.casino.blackjack.domain.enums.Value;
import nl.hu.bep2.casino.blackjack.domain.exceptions.GameAlreadyStartedException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    void testHandValueMethod(){
        Game game = new Game();
        ArrayList<Card> hand = new ArrayList<>();

        Card card = new Card(Suit.SPADE, Value.FIVE);
        Card card2 = new Card(Suit.SPADE, Value.FOUR);

        hand.add(card);
        hand.add(card2);

        assertEquals(9, game.calculateTotalValueHand(hand));

    }

    @Test
    void testHandValueMethodWithAces(){
        Game game = new Game();
        ArrayList<Card> hand = new ArrayList<>();

        Card card = new Card(Suit.SPADE, Value.FIVE);
        Card card2 = new Card(Suit.SPADE, Value.SIX);
        Card card3 = new Card(Suit.SPADE, Value.ACE);

        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        assertEquals(12, game.calculateTotalValueHand(hand));
    }

    @Test
    void testBlackjack(){
        Game game = new Game();

        ArrayList<Card> handPlayer = new ArrayList<>();
        ArrayList<Card> handDealer = new ArrayList<>();

        Card card = new Card(Suit.SPADE, Value.TEN);
        Card card2 = new Card(Suit.SPADE, Value.ACE);
        handPlayer.add(card);
        handPlayer.add(card2);

        Card card3 = new Card(Suit.SPADE, Value.TEN);
        Card card4 = new Card(Suit.SPADE, Value.TWO);
        handDealer.add(card3);
        handDealer.add(card4);

        game.setDealerHand(handDealer);
        game.setPlayerHand(handPlayer);

        assertTrue(game.checkBlackjackPlayer());
    }



}