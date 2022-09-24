package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.enums.GameState;
import nl.hu.bep2.casino.blackjack.presentation.dto.ProgressDTO;
import nl.hu.bep2.casino.chips.application.ChipsService;
import org.springframework.stereotype.Service;

@Service
public class BlackjackService {
    private final ChipsService chipsService;

    public BlackjackService(ChipsService chipsService) {
        this.chipsService = chipsService;
    }

    public ProgressDTO startGame(String playerName, long bet){
        //neem aantal chips op ter hoogte van bet
        chipsService.withdrawChips(playerName, bet);

        //maak nieuw spel aan
        Game game = new Game();
        game.startGame();

        //sla spel op (later)

        //stort chips op basis van payout
        chipsService.depositChips(playerName, game.calculatePayout());

        System.out.println(game.getGameDTO().dealerHand);

        return game.getGameDTO();
    }

    //uitgecomment want dit werkt nog niet. heb nog geen database.

    public ProgressDTO hit(String playerName){
//
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NoGamesFoundException("Wrong id."));
//        game.playerHit();
//        //sla spel op
//        //geef chips indien gewonnen
//        chipsService.depositChips(playerName, game.calculatePayout());

        return new ProgressDTO(playerName, 10, null, null, GameState.PLAYING);
    }

    public ProgressDTO stand(String playerName){
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NoGamesFoundException("Wrong id."));
//        game.stand();
//
//        //sla spel op
//
//        //geef chips indien gewonnen
//        chipsService.depositChips(playerName, game.calculatePayout());

        return new ProgressDTO(playerName, 10, null, null, null);
    }

    public ProgressDTO surrender(String playerName){
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NoGamesFoundException("Wrong id."));
//        game.surrender();
//
//        //sla spel op
//
//        //geef chips indien gewonnen
//        chipsService.depositChips(playerName, game.calculatePayout());

        return new ProgressDTO(playerName, 10, null, null, null);
    }
//
    public ProgressDTO doubleDown(String playerName){
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NoGamesFoundException("Wrong id."));
//        game.doubleDown();

        //sla spel op

        //geef chips indien gewonnen
//        chipsService.depositChips(playerName, game.calculatePayout());

        return new ProgressDTO(playerName, 10, null, null, null);
    }


}
