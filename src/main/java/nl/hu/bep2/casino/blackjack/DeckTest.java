package nl.hu.bep2.casino.blackjack;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @org.junit.jupiter.api.Test
    //Test whether createFullDeck creates a deck with 52 cards
    void createFullDeck() {
        Deck deck = new Deck(new ArrayList<>());
        deck.createFullDeck();

        assertEquals(52, deck.getDeckLength());
    }
}