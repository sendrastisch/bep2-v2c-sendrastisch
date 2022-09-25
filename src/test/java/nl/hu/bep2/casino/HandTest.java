package nl.hu.bep2.casino;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.enums.Suit;
import nl.hu.bep2.casino.blackjack.domain.enums.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void testHandValueMethod(){

        Card card = new Card(Suit.SPADE, Value.FIVE);
        Card card2 = new Card(Suit.SPADE, Value.FOUR);
        Hand hand = new Hand();

        hand.addCardToHand(card);
        hand.addCardToHand(card2);

        assertEquals(9, hand.calculateTotalValueHand());

    }

    @Test
    void testHandValueMethodWithAces(){
        Hand hand = new Hand();

        Card card = new Card(Suit.SPADE, Value.FIVE);
        Card card2 = new Card(Suit.SPADE, Value.SIX);
        Card card3 = new Card(Suit.SPADE, Value.ACE);

        hand.addCardToHand(card);
        hand.addCardToHand(card2);
        hand.addCardToHand(card3);

        assertEquals(12, hand.calculateTotalValueHand());
    }


}