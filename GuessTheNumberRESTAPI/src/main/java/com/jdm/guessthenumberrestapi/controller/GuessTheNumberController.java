package com.jdm.guessthenumberrestapi.controller;

import com.jdm.guessthenumberrestapi.models.GuessTheNumber;
import com.jdm.guessthenumberrestapi.service.GuessTheNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */

@RestController
@RequestMapping("/api/gtn")
public class GuessTheNumberController {
    
    private final GuessTheNumberService service;
    
    @Autowired
    public GuessTheNumberController(GuessTheNumberService service){
        this.service = service;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuessTheNumber create(@RequestBody GuessTheNumber game) {
        return service.createGame(game);
    }
}
