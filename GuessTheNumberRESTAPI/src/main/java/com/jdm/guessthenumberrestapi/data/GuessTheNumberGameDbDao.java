/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdm.guessthenumberrestapi.data;

import com.jdm.guessthenumberrestapi.models.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */

@Profile("database")
@Repository
public class GuessTheNumberGameDbDao implements GuessTheNumberGameDao {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuessTheNumberGameDbDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game createGame(String answer) {
        final String sql = "INSERT INTO guessgame(answer, finished) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        Game game = new Game(answer);

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            statement.setBoolean(2, game.isFinished());
            return statement;
        }
        , keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return game;    
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT gameId, finished, answer FROM guessgame";
        return jdbcTemplate.query(sql, new GuessTheNumberGameDbDao.GameMapper());
    }

    @Override
    public Game getGame(int gameId) {
        final String sql = "SELECT gameId, finished, answer FROM guessgame WHERE gameId = ?";
        return jdbcTemplate.queryForObject(sql, new GuessTheNumberGameDbDao.GameMapper(), gameId);
    }
    
    @Override
    public void finishGame(int gameId) {
        Game game = getGame(gameId);
        game.setFinished(true);
        final String sql = "UPDATE guessgame SET finished = ?, answer = ? WHERE gameId = ?";
        jdbcTemplate.update(sql, game.isFinished(), game.getAnswer(), game.getGameId()); 
    }
    
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game(rs.getInt("gameId"), rs.getBoolean("finished"), rs.getString("answer"));
            return game;
        }
    }
}
