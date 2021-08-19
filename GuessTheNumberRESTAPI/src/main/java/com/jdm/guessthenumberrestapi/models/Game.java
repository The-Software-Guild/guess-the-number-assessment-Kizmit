package com.jdm.guessthenumberrestapi.models;


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

}
