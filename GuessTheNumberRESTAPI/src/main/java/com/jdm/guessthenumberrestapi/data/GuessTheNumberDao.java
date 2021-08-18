package com.jdm.guessthenumberrestapi.data;

import com.jdm.guessthenumberrestapi.models.GuessTheNumber;

/**
 *
 * @author Joe
 */
public interface GuessTheNumberDao {
    
    /**
     * Adds the newly created game object to the database
     * Returns a copy of the created game for further use
     * @param game
     * @return GuessTheNumber object
     */
    public GuessTheNumber createGame(GuessTheNumber game);
    
    
     
}
