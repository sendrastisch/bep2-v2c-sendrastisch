package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.domain.enums.Suit;
import nl.hu.bep2.casino.blackjack.domain.enums.Value;
import nl.hu.bep2.casino.blackjack.presentation.dto.CardDTO;

import javax.persistence.*;

@Entity
public class Card {

    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private Suit suit;
    @Enumerated(EnumType.STRING)
    private Value value;

    public Card(){

    }
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }



    public CardDTO getCardDTO(){
        return new CardDTO(this.suit, this.value);
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value=" + value +
                '}';
    }
}
