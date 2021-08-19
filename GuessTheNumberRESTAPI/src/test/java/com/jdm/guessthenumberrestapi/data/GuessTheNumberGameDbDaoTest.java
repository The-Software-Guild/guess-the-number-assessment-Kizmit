/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdm.guessthenumberrestapi.data;

import com.jdm.guessthenumberrestapi.TestApplicationConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Joe
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GuessTheNumberGameDbDaoTest {
    @Autowired
    GuessTheNumberGameDao gameDao;
    
    @Autowired
    GuessTheNumberRoundDao roundDao;
    
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
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createGame method, of class GuessTheNumberGameDbDao.
     */
    @Test
    public void testCreateGame() {
    }

    /**
     * Test of getAllGames method, of class GuessTheNumberGameDbDao.
     */
    @Test
    public void testGetAllGames() {
    }

    /**
     * Test of getGame method, of class GuessTheNumberGameDbDao.
     */
    @Test
    public void testGetGame() {
    }

    /**
     * Test of finishGame method, of class GuessTheNumberGameDbDao.
     */
    @Test
    public void testFinishGame() {
    }
    
}
