package nl.hu.bep2.casino;

import nl.hu.bep2.casino.blackjack.Card;
import nl.hu.bep2.casino.blackjack.Deck;
import nl.hu.bep2.casino.blackjack.Suit;
import nl.hu.bep2.casino.blackjack.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class CasinoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CasinoApplication.class, args);

        Deck deck = new Deck(new ArrayList<>());
        deck.createFullDeck();
        deck.shuffleDeck();

        System.out.println(deck);


    }
    //sannie sannieword
}
