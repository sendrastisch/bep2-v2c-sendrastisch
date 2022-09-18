package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.application.dto.GameDTO;
import nl.hu.bep2.casino.chips.application.ChipsService;
import org.springframework.stereotype.Service;

@Service
public class BlackjackService {
    private final ChipsService chipsService;

    public BlackjackService(ChipsService chipsService) {
        this.chipsService = chipsService;
    }

    public GameDTO startGame(String playerName, long bet){
        //neem aantal chips op ter hoogte van bet
        chipsService.withdrawChips(playerName, bet);

        //maak nieuw spel aan
        Game game = new Game();
        game.startGame();

        //sla spel op (later)

        //stort chips op basis van payout
        chipsService.depositChips(playerName, game.calculatePayout());

        return game.getGameDTO();
    }


}
