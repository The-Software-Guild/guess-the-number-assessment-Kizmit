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
    
    public void deleteRoundById(int roundId);
}
