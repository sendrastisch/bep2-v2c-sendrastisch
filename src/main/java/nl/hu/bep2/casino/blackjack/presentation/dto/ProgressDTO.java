package nl.hu.bep2.casino.blackjack.presentation.dto;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.enums.GameState;

import java.util.ArrayList;

public class ProgressDTO {
    public long id;

    public String username;
    public long bet;
    public HandDTO dealerHand;
    public HandDTO playerHand;
    public GameState state;

    public ProgressDTO(long id, String username, long bet, HandDTO dealerHand, HandDTO playerHand, GameState state) {
        this.id = id;
        this.username = username;
        this.bet = bet;
        this.dealerHand = dealerHand;
        this.playerHand = playerHand;
        this.state = state;
    }

}
