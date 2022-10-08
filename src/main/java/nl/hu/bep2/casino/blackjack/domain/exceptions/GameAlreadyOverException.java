package nl.hu.bep2.casino.blackjack.domain.exceptions;

public class GameAlreadyOverException extends RuntimeException {
    public GameAlreadyOverException(String message){super(message);}

}
