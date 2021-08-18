package com.jdm.guessthenumberrestapi.data;

import com.jdm.guessthenumberrestapi.models.GuessTheNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
@Profile("database")
@Repository
public class GuessTheNumberDatabaseDao implements GuessTheNumberDao {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuessTheNumberDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GuessTheNumber createGame(GuessTheNumber game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
