package nl.hu.bep2.casino.blackjack;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    //Test whether createFullDeck creates a deck with 52 cards
    void createFullDeck() {
        Deck deck = new Deck();
        deck.createFullDeck();

        assertEquals(52, deck.getDeckLength());
    }

    @Test
    //Test of methode kaart teruggeeft.
    void getCardFromDeck(){
        Deck deck = new Deck();
        deck.createFullDeck();
        deck.shuffleDeck();

        assertInstanceOf(Card.class, deck.getNextCardFromDeck());
    }

    @Test
    void checkIfMethodRemovesCardFromDeck(){
        Deck deck = new Deck();
        deck.createFullDeck();
        deck.shuffleDeck();
        Card card = deck.getNextCardFromDeck();

        assertFalse(deck.getGameDeck().contains(card));

    }
}