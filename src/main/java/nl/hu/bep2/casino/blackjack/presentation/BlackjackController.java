package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.application.BlackjackService;
import nl.hu.bep2.casino.blackjack.application.exceptions.NoGamesFoundException;
import nl.hu.bep2.casino.blackjack.domain.exceptions.GameAlreadyOverException;
import nl.hu.bep2.casino.blackjack.presentation.dto.ProgressDTO;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameData;
import nl.hu.bep2.casino.security.domain.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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

//    @GetMapping("/{id}" )
//    public ProgressDTO findGameById(@PathVariable long id){
//        try{
//            return blackjackService.findGameById(id);
//        }catch(NoGamesFoundException exception){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
//        }
//    }

    @GetMapping("/username")
    public ProgressDTO findByUsername(Authentication authentication){
        try{
            UserProfile profile = (UserProfile) authentication.getPrincipal();
            return blackjackService.findGameByUsername(profile.getUsername());
        } catch(NoGamesFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping("/start")
    public ProgressDTO startGame(@Validated @RequestBody GameData gameData) {
        return blackjackService.startGame(gameData.username, gameData.bet);
    }
    @PatchMapping("/hit")
    public ProgressDTO hit(Authentication authentication) {
        try{
            UserProfile profile = (UserProfile) authentication.getPrincipal();
            return blackjackService.hit(profile.getUsername());
        } catch(GameAlreadyOverException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PatchMapping("/double")
    public ProgressDTO doubleDown(Authentication authentication) {
        try{
            UserProfile profile = (UserProfile) authentication.getPrincipal();
            return blackjackService.doubleDown(profile.getUsername());
        } catch(GameAlreadyOverException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PatchMapping("/surrender")
    public ProgressDTO surrender(Authentication authentication) {
        try{
            UserProfile profile = (UserProfile) authentication.getPrincipal();
            return blackjackService.surrender(profile.getUsername());
        } catch(GameAlreadyOverException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PatchMapping("/stand")
    public ProgressDTO stand(Authentication authentication) {
        try{
            UserProfile profile = (UserProfile) authentication.getPrincipal();
            return blackjackService.stand(profile.getUsername());
        } catch(GameAlreadyOverException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/all")
    public void deleteAllGames(){blackjackService.deleteAllGames();}

    @DeleteMapping("/{id}")
    public void deleteGameById(@PathVariable long id){blackjackService.deleteGameById(id);}



}
