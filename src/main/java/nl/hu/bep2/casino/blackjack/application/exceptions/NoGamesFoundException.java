package nl.hu.bep2.casino.blackjack.application.exceptions;

public class NoGamesFoundException extends RuntimeException {
    public NoGamesFoundException(String message) {
        super(message);
    }
}
