package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.presentation.dto.CardDTO;
import nl.hu.bep2.casino.blackjack.presentation.dto.HandDTO;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hand {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    @JoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Card> hand = new ArrayList<>();

    public Hand() {

    }

    public HandDTO getHandDTO(){
        return new HandDTO(this.hand);
    }

    public int getSizeOfHand(){
        return hand.size();
    }

    public void addCardToHand(Card cardToAdd){
        this.hand.add(cardToAdd);
    }

    public int calculateTotalValueHand(){
        int totalValue = 0;
        int aces = 0;

        for(Card card : this.hand){
            switch(card.getValue()){
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;
            }
        }

        for(int i = 0; i < aces; i++){
            if(totalValue > 10){
                totalValue+=1;
            } else{
                totalValue+=11;
            }
        }

        return totalValue;
    }

    @Override
    public String toString() {
        String ding = "";

        for (Card card: hand) {
            ding = ding + card.toString();
        }
        return ding;
    }
}
