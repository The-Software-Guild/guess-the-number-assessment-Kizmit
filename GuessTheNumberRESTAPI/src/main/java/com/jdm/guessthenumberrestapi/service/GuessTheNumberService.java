/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdm.guessthenumberrestapi.service;

import com.jdm.guessthenumberrestapi.data.GuessTheNumberDao;
import com.jdm.guessthenumberrestapi.models.GuessTheNumber;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class GuessTheNumberService {
    
    private final GuessTheNumberDao dao;
    
    @Autowired
    public GuessTheNumberService(GuessTheNumberDao dao){
        this.dao = dao;
    }
    
    public GuessTheNumber createGame(GuessTheNumber game){
        //Generate answer
        //create game obj with generated answer and status
        return null;

    }
    
    public void makeGuess(){
        //Validate format of guess
        //handle the guess
    }
}
