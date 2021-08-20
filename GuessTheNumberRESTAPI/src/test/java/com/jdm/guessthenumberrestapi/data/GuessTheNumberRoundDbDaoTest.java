package com.jdm.guessthenumberrestapi.data;

import com.jdm.guessthenumberrestapi.models.Game;
import com.jdm.guessthenumberrestapi.models.Round;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Joe
 */

@SpringBootTest
public class GuessTheNumberRoundDbDaoTest {
    

    @Autowired
    GuessTheNumberRoundDao roundDao;
    
    @Autowired
    GuessTheNumberGameDao gameDao;
    
    Game testGame1, testGame2;
    
    public GuessTheNumberRoundDbDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {

    }
    
    @BeforeEach
    public void setUp() {
        testGame1 = gameDao.createGame("1234");
        testGame2 = gameDao.createGame("5678");
    }
    
    @AfterEach
    public void tearDown() {
        List<Round> roundsG1 = roundDao.getAllRounds(testGame1.getGameId());
        roundsG1.forEach(round -> { 
            roundDao.deleteRoundById(round.getRoundId());
        });
        
        List<Round> roundsG2 = roundDao.getAllRounds(testGame2.getGameId());
        roundsG2.forEach(round -> { 
            roundDao.deleteRoundById(round.getRoundId());
        });
        
        List<Game> games = gameDao.getAllGames();
        games.forEach(game -> { 
            gameDao.deleteGameById(game.getGameId());
        });
    }

    /**
     * Test of addRound method, of class GuessTheNumberRoundDbDao.
     */
    @Test
    public void testAddGetRound() {
        Round testRound1G1 = roundDao.addRound("1423", "e:1:p:3", Timestamp.valueOf(LocalDateTime.now()), testGame1.getGameId());
        Round testRound2G1 = roundDao.addRound("1234", "e:4:p:0", Timestamp.valueOf(LocalDateTime.now()), testGame1.getGameId());
        Round testRound1G2 = roundDao.addRound("1234", "e:0:p:0", Timestamp.valueOf(LocalDateTime.now()), testGame2.getGameId());
        Round testRound2G2 = roundDao.addRound("5678", "e:4:p:0", Timestamp.valueOf(LocalDateTime.now()), testGame2.getGameId());
        
        assertNotNull(testRound1G1);
        assertNotNull(testRound2G1);
        assertNotNull(testRound1G2);
        assertNotNull(testRound2G2);
        
        List<Round> roundsG1 = roundDao.getAllRounds(testGame1.getGameId());
        List<Round> roundsG2 = roundDao.getAllRounds(testGame2.getGameId());

        assertEquals(roundsG1.size(), 2);
        assertEquals(roundsG2.size(), 2);
        assertEquals(testRound1G1, roundsG1.get(0));
        assertEquals(testRound2G1, roundsG1.get(1));
        assertEquals(testRound1G2, roundsG2.get(0));
        assertEquals(testRound2G2, roundsG2.get(1));
        
    }

    
}
