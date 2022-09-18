package nl.hu.bep2.casino.blackjack.application.dto;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.enums.GameState;

import java.util.ArrayList;

public class GameDTO {
    public String username;
    public long bet;
    public ArrayList<Card> dealerHand;
    public ArrayList<Card> playerHand;
    public GameState state;

    public GameDTO(String username, long bet, ArrayList<Card> dealerHand, ArrayList<Card> playerHand, GameState state) {
        this.username = username;
        this.bet = bet;
        this.dealerHand = dealerHand;
        this.playerHand = playerHand;
        this.state = state;
    }
}
