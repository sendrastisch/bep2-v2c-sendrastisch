package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.domain.enums.Suit;
import nl.hu.bep2.casino.blackjack.domain.enums.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;

@Entity
public class Deck {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private ArrayList<Card> gameDeck;

    public Deck() {
        createFullDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getGameDeck() { return gameDeck; }

    private void createFullDeck(){
        ArrayList<Card> deck = new ArrayList<>();
        for(Suit suit : Suit.values()){
            for(Value cardValue :Value.values()){
                deck.add(new Card(suit, cardValue));
            }
        }
        gameDeck = deck;
    }
    public Card getNextCardFromDeck(){
        return gameDeck.remove(0);
    }
    private void shuffleDeck(){
        Collections.shuffle(this.gameDeck);
    }

    public void addCardToDeck(Card card){
        this.gameDeck.add(card);
    }

    public int getDeckLength(){
        return this.gameDeck.size();
    }

    @Override
    public String toString() {
        String deckOutput = "";

        int i = 0;
        for(Card card: this.gameDeck){
            deckOutput+= "\n" + i + "-" + card.toString();
            i++;
        }
        return deckOutput;
    }
}
