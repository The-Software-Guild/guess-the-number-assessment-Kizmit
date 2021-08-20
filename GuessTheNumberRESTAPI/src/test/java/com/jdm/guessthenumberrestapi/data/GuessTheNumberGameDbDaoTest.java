package com.jdm.guessthenumberrestapi.data;

import com.jdm.guessthenumberrestapi.models.Game;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 *
 * @author Joe
 */

@SpringBootTest
public class GuessTheNumberGameDbDaoTest {
    
    @Autowired
    GuessTheNumberGameDao gameDao;
    
    
    public GuessTheNumberGameDbDaoTest() { 
    }
    
   
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Game> games = gameDao.getAllGames();
        games.forEach(game -> { 
            gameDao.deleteGameById(game.getGameId());
        });
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createGame method, of class GuessTheNumberGameDbDao.
     */
    @Test
    public void testCreateGetGame() {
        Game testGame1 = new Game("1234");
        Game testGame2 = new Game("5678");
        
        
        Game returnedTG1 = gameDao.createGame("1234");
        Game returnedTG2 = gameDao.createGame("5678");
        
        testGame1.setGameId(returnedTG1.getGameId());
        testGame2.setGameId(returnedTG2.getGameId());
        
        Game returnedById1 = gameDao.getGame(returnedTG1.getGameId());
        Game returnedById2 = gameDao.getGame(returnedTG2.getGameId());
        
        List<Game> games = gameDao.getAllGames();
        
        assertEquals(2, games.size());
        assertEquals(games.get(0), returnedTG1);
        assertEquals(games.get(1), returnedTG2);
        assertEquals(returnedTG1, returnedById1);
        assertEquals(returnedTG2, returnedById2);
        assertEquals(returnedTG1, testGame1);
        assertEquals(returnedTG2, testGame2);
    }

    /**
     * Test of finishGame method, of class GuessTheNumberGameDbDao.
     */
    @Test
    public void testFinishGame() {
        Game returnedTG1 = gameDao.createGame("1234");
        Game returnedTG2 = gameDao.createGame("5678");
        
        assertNotNull(returnedTG1);
        assertNotNull(returnedTG2);
        
        gameDao.finishGame(returnedTG1.getGameId());
        gameDao.finishGame(returnedTG2.getGameId());
        
        returnedTG1 = gameDao.getGame(returnedTG1.getGameId());
        returnedTG2 = gameDao.getGame(returnedTG2.getGameId());
        
        assertNotNull(returnedTG1);
        assertNotNull(returnedTG2);
        assertTrue(returnedTG1.isFinished());
        assertTrue(returnedTG2.isFinished());
    }
    
}
