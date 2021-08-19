package com.jdm.guessthenumberrestapi.data;

import com.jdm.guessthenumberrestapi.models.Game;
import com.jdm.guessthenumberrestapi.models.Round;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Joe
 */
public interface GuessTheNumberDao {
    
    /**
     * Adds the service layer generated fields to a new game object and adds it to the database.
     * Returns a copy of the created game for further use
     * @param answer
     * @param finished
     * @return Game object
     */
    public Game createGame(String answer);

    /**
     * Returns a list of all of the games created
     * @return List<Game>
     */
    public List<Game> getAllGames();

    public Game getGame(int gameId);

    public Round addRound(String guess, String result, Timestamp guessTime, int gameId);

    public void finishGame(int gameId);

    public List<Round> getAllRounds(int gameId);
    
    
     
}
