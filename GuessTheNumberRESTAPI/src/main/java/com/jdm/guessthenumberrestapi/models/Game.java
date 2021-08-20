package com.jdm.guessthenumberrestapi.models;

import java.util.Objects;


/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class Game {
    private int gameId;
    private boolean finished;
    private String answer;

    public Game(int gameId, boolean finished, String answer) {
        this.gameId = gameId;
        this.finished = finished;
        this.answer = answer;
    }

    public Game(String answer) {
        this.answer = answer;
        this.finished = false; //Default false
    }
   

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.gameId;
        hash = 23 * hash + (this.finished ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.answer);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.finished != other.finished) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

}
