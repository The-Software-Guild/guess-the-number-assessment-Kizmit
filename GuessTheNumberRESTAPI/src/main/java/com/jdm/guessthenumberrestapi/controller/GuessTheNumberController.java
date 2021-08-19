package com.jdm.guessthenumberrestapi.controller;

import com.jdm.guessthenumberrestapi.models.Game;
import com.jdm.guessthenumberrestapi.models.Round;
import com.jdm.guessthenumberrestapi.service.GuessTheNumberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */

@RestController
@RequestMapping("/api/gtn")
public class GuessTheNumberController {
    
    private final GuessTheNumberService service;
    
    @Autowired
    public GuessTheNumberController(GuessTheNumberService service){
        this.service = service;
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/begin")
    public Game begin() {
        return service.createGame();
    }
    
    @GetMapping("/game")
    public List<Game> getGames(){
        return service.getAllGames();
    }
    
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable int gameId){
        Game game = service.getGame(gameId);
        if (game == null) {                                             
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);  //Not working properly, game not null? getting status 500
        }
        return ResponseEntity.ok(game);
    }
    
    @PostMapping("/guess")
    public Round guess(@RequestBody Round round){
        return service.guess(round.getGuess(), round.getGameId());
    }
    
    @GetMapping("/rounds/{gameId}")
    public List<Round> getRounds(@PathVariable int gameId){
        return service.getAllRounds(gameId);
    }
    

    

}
