package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.application.exceptions.NoGamesFoundException;
import nl.hu.bep2.casino.blackjack.data.GameRepository;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.enums.GameState;
import nl.hu.bep2.casino.blackjack.presentation.dto.ProgressDTO;
import nl.hu.bep2.casino.chips.application.ChipsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BlackjackService {
    private final ChipsService chipsService;
    private final GameRepository gameRepository;

    public BlackjackService(ChipsService chipsService, GameRepository gameRepository) {
        this.chipsService = chipsService;
        this.gameRepository = gameRepository;
    }

    public ProgressDTO startGame(String playerName, long bet){
        //neem aantal chips op ter hoogte van bet
        chipsService.withdrawChips(playerName, bet);

        //maak nieuw spel aan
        Game game = new Game();
        game.startGame(playerName, bet);

        gameRepository.save(game);

        //stort chips op basis van payout
        if(game.checkBlackjack(game.getPlayerHand())){
            chipsService.depositChips(playerName, game.calculatePayout());
        }

        return game.getGameDTO();
    }

    public ArrayList<ProgressDTO> findAllGames() {
        List<Game> games = gameRepository.findAll();
        ArrayList<ProgressDTO> dto = new ArrayList<>();

        if (games.isEmpty()) {
            throw new NoGamesFoundException("No games are found.");
        } else {
            for (Game g : games) {
                dto.add(g.getGameDTO());
            }
        }
        return dto;
    }

    public ProgressDTO findGameById(long id){
        Game game = gameRepository.findById(id).orElseThrow(() -> new NoGamesFoundException("No game is found."));

        return game.getGameDTO();
    }

    public ProgressDTO findGameByUsername(String username){
        Game game = gameRepository.findByUsername(username).orElseThrow(()-> new NoGamesFoundException("No game is found. "));
        return game.getGameDTO();
    }

    public void deleteAllGames(){
        gameRepository.deleteAll();
    }

    public void deleteGameById(long id){
        gameRepository.deleteById(id);
    }

    public ProgressDTO hit(String playerName){

        Game game = gameRepository.findByUsername(playerName).orElseThrow(() -> new NoGamesFoundException("No games found with this username."));
        game.playerHit();

        gameRepository.save(game);
        //sla spel op
        //geef chips indien gewonnen
        chipsService.depositChips(playerName, game.calculatePayout());

        return game.getGameDTO();
    }

    public ProgressDTO stand(String playerName){
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NoGamesFoundException("Wrong id."));
//        game.stand();
//
//        //sla spel op
//
//        //geef chips indien gewonnen
//        chipsService.depositChips(playerName, game.calculatePayout());

        return new ProgressDTO(0, playerName, 10, null, null, null);
    }

    public ProgressDTO surrender(String playerName){
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NoGamesFoundException("Wrong id."));
//        game.surrender();
//
//        //sla spel op
//
//        //geef chips indien gewonnen
//        chipsService.depositChips(playerName, game.calculatePayout());

        return new ProgressDTO(0, playerName, 10, null, null, null);
    }
//
    public ProgressDTO doubleDown(String playerName){
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NoGamesFoundException("Wrong id."));
//        game.doubleDown();

        //sla spel op

        //geef chips indien gewonnen
//        chipsService.depositChips(playerName, game.calculatePayout());

        return new ProgressDTO(0, playerName, 10, null, null, null);
    }


}
