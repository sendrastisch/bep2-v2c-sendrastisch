package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.domain.exceptions.GameAlreadyOverException;
import nl.hu.bep2.casino.blackjack.presentation.dto.ProgressDTO;
import nl.hu.bep2.casino.blackjack.domain.enums.GameState;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private long bet;
    @Enumerated(EnumType.STRING)
    private GameState state;

    @ManyToOne
    @JoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Deck gameDeck;

    @ManyToOne
    @JoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Hand dealerHand;

    @ManyToOne    @JoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)

    private Hand playerHand;

    public Game(){

    }

    public void startGame(String username, long bet){

        this.state = GameState.PLAYING;
        this.username = username;
        this.bet = bet;
        this.gameDeck = new Deck();
        this.dealerHand = new Hand();
        this.playerHand = new Hand();

        dealCards();
        checkGamestate();
    }

    public ProgressDTO getGameDTO(){
        return new ProgressDTO(this.id, this.username, this.bet, this.dealerHand.getHandDTO(), this.playerHand.getHandDTO(), this.state);
    }

    public GameState getState() {
        return state;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public void setDealerHand(Hand dealerHand) {
        this.dealerHand = dealerHand;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public void playerHit(){
        if(this.state != GameState.PLAYING){
            throw new GameAlreadyOverException("The game is already over! :(");
        }
        this.playerHand.addCardToHand(gameDeck.getNextCardFromDeck());
        checkGamestate();
    }

    public void dealerHit(){
        this.dealerHand.addCardToHand(gameDeck.getNextCardFromDeck());
        checkGamestate();
    }
    public void stand(){
        //speler geeft op en dealer blijft hitten tot dood of 17

        while(dealerHand.calculateTotalValueHand()<17){
            this.dealerHit();
        }

    }
    public void surrender(){
        this.state = GameState.SURRENDERED;
    }
    public void doubleDown(){
        this.bet*=2;
        this.playerHand.addCardToHand(gameDeck.getNextCardFromDeck());
        while(dealerHand.calculateTotalValueHand()<17){
            this.dealerHit();
        }
    }
    public void checkGamestate() {
        if (checkBlackjack(playerHand) && playerHand.getSizeOfHand() >= 2) {
            if (checkBlackjack(dealerHand)) {
                state = GameState.PUSH;
            } else {
                state = GameState.BLACKJACK;
            }
            return;
        }
        if (checkIfOver21(playerHand)) {
            state = GameState.BUST;
            return;
        }
        if (checkIfOver21(dealerHand)) {
            state = GameState.WON;
            return;
        }
        if (state == GameState.STAND) {
            if (playerHand.calculateTotalValueHand() < dealerHand.calculateTotalValueHand()) {
                state = GameState.LOST;
            } else if (playerHand.calculateTotalValueHand() == dealerHand.calculateTotalValueHand()) {
                state = GameState.PUSH;
            } else {
                state = GameState.WON;
            }
        }
    }

    public boolean checkIfOver21(Hand hand){
        return hand.calculateTotalValueHand() > 21;
    }

    public boolean checkBlackjack(Hand hand){
        int total = hand.calculateTotalValueHand();

        return total == 21;
    }

    public void dealCards(){
        //take 2 cards from deck en geef aan speler
        playerHand.addCardToHand(gameDeck.getNextCardFromDeck());
        playerHand.addCardToHand(gameDeck.getNextCardFromDeck());
        //take 2 cards from deck en geef aan dealer
        dealerHand.addCardToHand(gameDeck.getNextCardFromDeck());
        dealerHand.addCardToHand(gameDeck.getNextCardFromDeck());

    }

    public long calculatePayout(){
        long payout = 0;

        switch(this.state){
            case WON:
               payout = this.bet * 2;
               break;
            case LOST:
            case PUSH:
                break;
            case SURRENDERED:
                payout = (long) (this.bet * 0.5);
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
