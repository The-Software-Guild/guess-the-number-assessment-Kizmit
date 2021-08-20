/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdm.guessthenumberrestapi.service;

import com.jdm.guessthenumberrestapi.data.GuessTheNumberGameDao;
import com.jdm.guessthenumberrestapi.data.GuessTheNumberRoundDao;
import com.jdm.guessthenumberrestapi.models.Game;
import com.jdm.guessthenumberrestapi.models.Round;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Joe
 */

@SpringBootTest
public class GuessTheNumberServiceTest {
    
    @Autowired
    GuessTheNumberService service;
    
    @Autowired
    GuessTheNumberRoundDao roundDao;
    
    @Autowired
    GuessTheNumberGameDao gameDao;
    
    Game testGame;
    Game finishedGame;
    
    public GuessTheNumberServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        testGame = gameDao.createGame("1234");
        finishedGame = gameDao.createGame("5678");
        finishedGame.setFinished(true);
    }
    
    @AfterEach
    public void tearDown() {
        roundDao.getAllRounds(testGame.getGameId()).forEach(round -> {
            roundDao.deleteRoundById(round.getRoundId());
        });
        
        roundDao.getAllRounds(finishedGame.getGameId()).forEach(round -> {
            roundDao.deleteRoundById(round.getRoundId());
        });
        
        gameDao.getAllGames().forEach(game -> {
            gameDao.deleteGameById(game.getGameId());
        });
        

    }

    /**
     * Test of createGame method, of class GuessTheNumberService.
     */
    @Test
    public void testCreateGame() {
        Game game = service.createGame();
        assertNotNull(game);
        assertEquals(game.getAnswer(), "HIDDEN");
    }


    /**
     * Test of getGame method, of class GuessTheNumberService.
     */
    @Test
    public void testGetGame() {
        Game game = service.getGame(testGame.getGameId());
        List<Game> games = service.getAllGames();
        
        assertEquals(game.getAnswer(), "HIDDEN");
        assertEquals(games.get(0).getAnswer(), "HIDDEN");
    }

    /**
     * Test of guess method, of class GuessTheNumberService.
     */
    @Test
    public void testGuess() throws Exception {
        try{
            Round testRound1 = service.guess("5678", testGame.getGameId());
        
            assertNotNull(testRound1);
            assertEquals(testRound1.getResult(), "e:0:p:0");
            assertEquals(testRound1.getGameId(), testGame.getGameId());

            Round testRound2 = service.guess("1234", testGame.getGameId());
            assertNotNull(testRound2);
            assertEquals(testRound2.getResult(), "e:4:p:0");
            assertEquals(testRound2.getGameId(), testGame.getGameId());
        }
        catch(Exception e){
            fail("Exception was thrown");
        }
        
        try{
            Round testRound3 = service.guess("1234", 9999);
        }
        catch(Exception e){
            assertNotNull(e);
        }
        
        try{
            Round testRound4 = service.guess("1234", finishedGame.getGameId());
        }
        catch(Exception e){
            assertNotNull(e);
        }
        
        
        
    }

    
}
