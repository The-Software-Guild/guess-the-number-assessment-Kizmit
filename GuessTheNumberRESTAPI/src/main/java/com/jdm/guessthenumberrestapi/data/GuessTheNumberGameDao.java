package com.jdm.guessthenumberrestapi.data;

import com.jdm.guessthenumberrestapi.models.Game;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author Joe
 */
public interface GuessTheNumberGameDao {
    
    /**
     * Adds the service layer generated fields to a new game object and adds it to the database.
     * Returns a copy of the created game for further use
     * @param answer
     * @return Game object
     */
    public Game createGame(String answer);

    /**
     * Returns a list of all of the games created
     * @return List<Game>
     */
    public List<Game> getAllGames();

    public Game getGame(int gameId) throws EmptyResultDataAccessException;

    public void finishGame(int gameId); 
     
}
