/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
