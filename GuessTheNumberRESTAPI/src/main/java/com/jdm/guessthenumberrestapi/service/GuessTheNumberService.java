package com.jdm.guessthenumberrestapi.service;

import com.jdm.guessthenumberrestapi.models.Game;
import com.jdm.guessthenumberrestapi.models.Round;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jdm.guessthenumberrestapi.data.GuessTheNumberGameDao;
import com.jdm.guessthenumberrestapi.data.GuessTheNumberRoundDao;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */

@Component
public class GuessTheNumberService {
    
    private final GuessTheNumberGameDao gameDao;
    private final GuessTheNumberRoundDao roundDao;
    
    @Autowired
    public GuessTheNumberService(GuessTheNumberGameDao gameDao, GuessTheNumberRoundDao roundDao){
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    
    public Game createGame(){
        //Generate answer by creating a list of digits [0-10] and shuffle it
        ArrayList<Integer> numList = new ArrayList<>();
        for(int i = 0; i <= 9; i++){
            numList.add(i);
        }
        Collections.shuffle(numList);
        String answer = "" + numList.get(0) + numList.get(1) + numList.get(2) + numList.get(3);
        Game game = gameDao.createGame(answer);
        game.setAnswer("HIDDEN");
        return game;
    }

    //Determine whether or not to hide the answer
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        games.stream().filter(game -> (!game.isFinished())).forEachOrdered(game -> {
            game.setAnswer("HIDDEN");
        });
        return games;
    }

    //Determine whether or not to hide the answer
    public Game getGame(int gameId) {
        
        Game game = gameDao.getGame(gameId);
        if(!game.isFinished()){
            game.setAnswer("HIDDEN");
        }
        return game;
    }
    
    //Validate input
    //Check guess accuracy and calculate result
    public Round guess(String guess, int gameId) throws Exception {
        
        String result;
        Timestamp guessTime = Timestamp.valueOf(LocalDateTime.now());
        int e = 0;
        int p = 0;
        
        Game game = gameDao.getGame(gameId);
        if(game == null || game.isFinished()){ 
            throw new Exception();
        }
        else{
            
            String answer = game.getAnswer();
            
            if(answer.equals(guess)){
                result = "e:4:p:0";
                gameDao.finishGame(gameId);
                return roundDao.addRound(guess, result, guessTime, gameId);
            }
            
            else{  //Check partial match
                
                //Create char arrays for index by index comparison
                char[] guessArray = guess.toCharArray();
                char[] answerArray = answer.toCharArray();
                
                //Iterate through both char arrays, counting exact/partial matches
                for(int i = 0; i < guessArray.length; i++){
                    for(int j = 0; j < answerArray.length; j++){
                        if(guessArray[i] == answerArray[j] && i == j){
                            e++;
                        }
                        else if(guessArray[i] == answerArray[j] && i != j){
                            p++;
                        }
                    }
                }
                
                //Create result string from match counts
                result = "e:" + e + ":p:" + p;
            }
            
        }
        return roundDao.addRound(guess, result, guessTime, gameId);
    }
    
    //Pass-through
    public List<Round> getAllRounds(int gameId) {
        return roundDao.getAllRounds(gameId);
    }

}
