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
public class GuessTheNumberRoundDbDaoTest {
    @Autowired
    GuessTheNumberGameDao gameDao;
    
    @Autowired
    GuessTheNumberRoundDao roundDao;
    
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
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addRound method, of class GuessTheNumberRoundDbDao.
     */
    @Test
    public void testAddRound() {
    }

    /**
     * Test of getAllRounds method, of class GuessTheNumberRoundDbDao.
     */
    @Test
    public void testGetAllRounds() {
    }
    
}
