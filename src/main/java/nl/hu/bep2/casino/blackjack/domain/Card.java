package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.domain.enums.Suit;
import nl.hu.bep2.casino.blackjack.domain.enums.Value;

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value=" + value +
                '}';
    }
}
