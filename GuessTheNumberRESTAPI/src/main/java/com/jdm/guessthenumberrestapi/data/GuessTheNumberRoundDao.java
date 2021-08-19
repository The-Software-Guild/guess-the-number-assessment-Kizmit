/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdm.guessthenumberrestapi.data;

import com.jdm.guessthenumberrestapi.models.Round;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Joe
 */
public interface GuessTheNumberRoundDao {
    
    public Round addRound(String guess, String result, Timestamp guessTime, int gameId);
    
    public List<Round> getAllRounds(int gameId);
    
}
