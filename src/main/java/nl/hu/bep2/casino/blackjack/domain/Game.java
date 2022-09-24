package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.presentation.dto.ProgressDTO;
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

    public ProgressDTO getGameDTO(){

        ProgressDTO progressDTO = new ProgressDTO(this.username, this.bet, this.dealerHand, this.playerHand, this.state);

        return progressDTO;
    }

    public void startGame() throws GameAlreadyStartedException{
        if(this.state == GameState.PLAYING){
            throw new GameAlreadyStartedException("The game has already started");
        }

        this.state = GameState.PLAYING;

        this.gameDeck = new Deck();

        dealCards();
        checkGamestate();
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

    public void setDealerHand(ArrayList<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void playerHit(){
        this.playerHand.add(gameDeck.getNextCardFromDeck());
        checkGamestate();
    }

    public void dealerHit(){
        this.dealerHand.add(gameDeck.getNextCardFromDeck());
        checkGamestate();
    }

    public void stand(){
        //speler geeft op en dealer blijft hitten tot dood of 17

        while(this.calculateTotalValueHand(this.dealerHand)<17){
            this.dealerHit();
        }

    }
    public void surrender(){
        this.state = GameState.SURRENDERED;
    }

    public void doubleDown(){
        this.bet*=2;
        this.playerHand.add(gameDeck.getNextCardFromDeck());
        while(this.calculateTotalValueHand(this.dealerHand)<17){
            this.dealerHit();
        }
    }

    public int calculateTotalValueHand(ArrayList<Card> hand){
        int totalValue = 0;
        int aces = 0;

        for(Card card : hand){
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

    public void checkGamestate() {
        if (checkBlackjack(this.playerHand) && this.playerHand.size() == 2) {
            if (checkBlackjack(this.dealerHand)) {
                state = GameState.PUSH;
            } else {
                state = GameState.BLACKJACK;
            }
            return;
        }
        if (checkIfOver21(this.playerHand)) {
            state = GameState.BUST;
            return;
        }
        if (checkIfOver21(this.dealerHand)) {
            state = GameState.WON;
            return;
        }
        if (state == GameState.STAND) {
            if (calculateTotalValueHand(this.playerHand) < calculateTotalValueHand(this.dealerHand)) {
                state = GameState.LOST;
            } else if (calculateTotalValueHand(this.playerHand) == calculateTotalValueHand(this.dealerHand)) {
                state = GameState.PUSH;
            } else {
                state = GameState.WON;
            }
        }
    }

    public boolean checkIfOver21(ArrayList<Card> hand){
        return this.calculateTotalValueHand(hand) > 21;
    }

    public boolean checkBlackjack(ArrayList<Card> hand){
        int total = calculateTotalValueHand(hand);

        return total == 21;
    }

    public void dealCards(){
        //take 2 cards from deck en geef aan speler
        playerHand.add(gameDeck.getNextCardFromDeck());
        playerHand.add(gameDeck.getNextCardFromDeck());
        //take 2 cards from deck en geef aan dealer
        dealerHand.add(gameDeck.getNextCardFromDeck());
        dealerHand.add(gameDeck.getNextCardFromDeck());
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
