package nl.hu.bep2.casino.blackjack.presentation.dto;

public class GameData {
    public String username;
    public long bet;

    public GameData(String username, long bet) {
        this.username = username;
        this.bet = bet;
    }
}
