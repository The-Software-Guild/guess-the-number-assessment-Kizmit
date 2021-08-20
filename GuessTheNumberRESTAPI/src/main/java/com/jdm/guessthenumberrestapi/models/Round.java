package com.jdm.guessthenumberrestapi.models;

import java.sql.Timestamp;
import java.util.Objects;


/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class Round {
    int gameId, roundId;
    Timestamp guessTime;
    String result, guess;

    public Round(String guess, String result, Timestamp guessTime, int gameId) {
        this.gameId = gameId;
        this.guessTime = guessTime;
        this.result = result;
        this.guess = guess;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public Timestamp getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(Timestamp guessTime) {
        this.guessTime = guessTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.gameId;
        hash = 97 * hash + this.roundId;
        hash = 97 * hash + Objects.hashCode(this.result);
        hash = 97 * hash + Objects.hashCode(this.guess);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.roundId != other.roundId) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        return true;
    }
    
    
}
