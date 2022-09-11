package nl.hu.bep2.casino.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> gameDeck;

    public Deck(ArrayList<Card> gameDeck) {
        this.gameDeck = new ArrayList<Card>();
    }

    public ArrayList<Card> getGameDeck() { return gameDeck; }

    public void createFullDeck(){
        for(Suit suit : Suit.values()){
            for(Value cardValue :Value.values()){
                this.gameDeck.add(new Card(suit, cardValue));
            }
        }
    }
    public Card getNextCardFromDeck(){

        Card card = this.gameDeck.get(0);
        gameDeck.remove(card);

        return card;
    }
    public void shuffleDeck(){
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
