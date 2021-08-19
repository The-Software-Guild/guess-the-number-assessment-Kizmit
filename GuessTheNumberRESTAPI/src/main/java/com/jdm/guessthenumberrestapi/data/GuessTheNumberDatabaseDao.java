package com.jdm.guessthenumberrestapi.data;

import com.jdm.guessthenumberrestapi.models.Game;
import com.jdm.guessthenumberrestapi.models.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
public class GuessTheNumberDatabaseDao implements GuessTheNumberDao {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GuessTheNumberDatabaseDao(JdbcTemplate jdbcTemplate) {
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
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game getGame(int gameId) {
        final String sql = "SELECT gameId, finished, answer FROM guessgame WHERE gameId = ?";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
    }

    @Override
    public Round addRound(String guess, String result, Timestamp guessTime, int gameId) {
        final String sql = "INSERT INTO round(gameId, guess, result, guessTime) VALUES(?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        Round round = new Round(guess, result, guessTime, gameId);

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGameId());
            statement.setString(2, round.getGuess());
            statement.setString(3, round.getResult());
            statement.setTimestamp(4, round.getGuessTime());
            return statement;
        }
        , keyHolder);

        round.setRoundId(keyHolder.getKey().intValue());
        
        return round;
    }

    @Override
    public void finishGame(int gameId) {
        Game game = getGame(gameId);
        game.setFinished(true);
        final String sql = "UPDATE guessgame SET finished = ?, answer = ? WHERE gameId = ?";
        jdbcTemplate.update(sql, game.isFinished(), game.getAnswer(), game.getGameId()); 
    }

    @Override
    public List<Round> getAllRounds(int gameId) {
        final String sql = "SELECT roundId, gameId, guess, guessTime, result FROM round WHERE gameId = ? ORDER BY guessTime ASC";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game(rs.getInt("gameId"), rs.getBoolean("finished"), rs.getString("answer"));
            return game;
        }
    }
    
        private static final class RoundMapper implements RowMapper<Round> {

        //String guess, String result, Timestamp guessTime, int gameId
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round(rs.getString("guess"), rs.getString("result"), rs.getTimestamp("guessTime"), rs.getInt("gameId"));
            round.setRoundId(rs.getInt("roundId"));
            return round;
        }
    }
}
