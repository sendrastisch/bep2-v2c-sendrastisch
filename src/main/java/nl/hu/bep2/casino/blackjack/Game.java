package nl.hu.bep2.casino.blackjack;

import java.util.ArrayList;

public class Game {
    private long id;
    private String username;
    private int bet;

    private GameState state;
    private ArrayList<Card> dealerHand;
    private ArrayList<Card> playerHand;

    public Game(long id, String username, int bet, ArrayList<Card> dealerHand, ArrayList<Card> playerHand) {
        this.id = id;
        this.username = username;
        this.bet = bet;
        this.dealerHand = dealerHand;
        this.playerHand = playerHand;
    }

    public void calculatePayout(){
        switch(state){
            case WON:
               this.bet *= 2;
                break;
            case BUST:
            case LOST:
                this.bet = 0;
            case SURRENDERED:
                this.bet *= 0.5;
                break;
            case PUSH:
                break;
            case BLACKJACK:
                this.bet *= 1.5;
        }
    }

}
