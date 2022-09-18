package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.application.dto.GameDTO;
import nl.hu.bep2.casino.blackjack.domain.enums.GameState;
import nl.hu.bep2.casino.blackjack.domain.exceptions.GameAlreadyStartedException;

import java.util.ArrayList;

public class Game {
    private long id;
    private String username;
    private long bet;
    private GameState state;

    private Deck gameDeck;
    private ArrayList<Card> dealerHand;
    private ArrayList<Card> playerHand;
    public Game(){
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();
    }

    public GameDTO getGameDTO(){
        GameDTO gameDTO = new GameDTO(this.username, this.bet, this.dealerHand, this.playerHand, this.state);
        return gameDTO;
    }

    public GameState getState() {
        return state;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void startGame() throws GameAlreadyStartedException{
        if(this.state == GameState.PLAYING){
            throw new GameAlreadyStartedException("The game has already started");
        }

        this.state = GameState.PLAYING;

        this.gameDeck = new Deck();
        gameDeck.createFullDeck();
        gameDeck.shuffleDeck();

        dealCards();
        checkBlackjack();
    }

    public void checkBlackjack(){

    }

    public void dealCards(){
        //take 2 cards from deck en geef aan speler
        playerHand.add(gameDeck.getNextCardFromDeck());
        playerHand.add(gameDeck.getNextCardFromDeck());

        dealerHand.add(gameDeck.getNextCardFromDeck());
        dealerHand.add(gameDeck.getNextCardFromDeck());

    }

    public void dealerHit(){

    }

    public void userMoves(String move){
        switch (move) {
            case "hit":
                //do hit thing
                break;
            case "dd":
                //do double down thing
                break;
            case "surrender":
                //do surrender thing
                break;
            case "stand":
                //do stand thing
                break;
        }
    }

    public long calculatePayout(){
        long payout = 0;

        switch(state){
            case WON:
               payout = this.bet * 2;
               break;
            case LOST:
                payout = 0;
            case SURRENDERED:
                payout = (long) (this.bet * 0.5);
                break;
            case PUSH:
                break;
            case BLACKJACK:
                payout = (long) (this.bet * 1.5);
        }
        return payout;
    }

    @Override
    public String toString() {
        return "Game{" +
                "dealerHand=" + dealerHand +
                ", playerHand=" + playerHand +
                '}';
    }
}
