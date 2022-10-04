package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.application.BlackjackService;
import nl.hu.bep2.casino.blackjack.application.exceptions.NoGamesFoundException;
import nl.hu.bep2.casino.blackjack.domain.exceptions.GameAlreadyStartedException;
import nl.hu.bep2.casino.blackjack.presentation.dto.ProgressDTO;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameData;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/games")
public class BlackjackController {

    private final BlackjackService blackjackService;

    public BlackjackController(BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }


    @GetMapping("/all")
    public ArrayList<ProgressDTO> getAllGames() {
        try {
            return blackjackService.findAllGames();
        } catch (NoGamesFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
    @PostMapping("/start")
    public ProgressDTO startGame(@Validated @RequestBody GameData gameData) {
        try {
            return blackjackService.startGame(gameData.username, gameData.bet);
        } catch (GameAlreadyStartedException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PatchMapping("/hit")
    public ProgressDTO hit() {
        return blackjackService.hit("sannie");
    }

    @PatchMapping("/double")
    public ProgressDTO doubleDown() {
        return blackjackService.doubleDown("doubleDown");
    }

    @PatchMapping("/surrender")
    public ProgressDTO surrender() {
        return blackjackService.surrender("surrender");
    }

    @PatchMapping("/stand")
    public ProgressDTO stand() {
        return blackjackService.stand("stand");
    }



}
