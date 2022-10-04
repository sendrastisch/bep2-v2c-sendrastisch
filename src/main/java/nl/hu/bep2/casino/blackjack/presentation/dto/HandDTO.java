package nl.hu.bep2.casino.blackjack.presentation.dto;

import nl.hu.bep2.casino.blackjack.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class HandDTO {
    private ArrayList<CardDTO> hand;

    public HandDTO(List<Card> cards) {
        ArrayList<CardDTO> cardDTOS = new ArrayList<>();

        for (Card card:cards) {
            cardDTOS.add(card.getCardDTO());
        }

        this.hand = cardDTOS;
    }

    public ArrayList<CardDTO> getHand() {
        return hand;
    }

    public void setHand(ArrayList<CardDTO> hand) {
        this.hand = hand;
    }
}
