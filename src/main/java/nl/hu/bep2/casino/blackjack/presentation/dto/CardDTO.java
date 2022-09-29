package nl.hu.bep2.casino.blackjack.presentation.dto;

import nl.hu.bep2.casino.blackjack.domain.enums.Suit;
import nl.hu.bep2.casino.blackjack.domain.enums.Value;

public class CardDTO{
        private Suit suit;
        private Value value;

        public CardDTO(Suit suit, Value value) {
                this.suit = suit;
                this.value = value;
        }

        public Suit getSuit() {
                return suit;
        }

        public Value getValue() {
                return value;
        }

        public void setSuit(Suit suit) {
                this.suit = suit;
        }

        public void setValue(Value value) {
                this.value = value;
        }
}
