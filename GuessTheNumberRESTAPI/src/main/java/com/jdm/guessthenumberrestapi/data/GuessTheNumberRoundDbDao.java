package com.jdm.guessthenumberrestapi.data;

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
public class GuessTheNumberRoundDbDao implements GuessTheNumberRoundDao {
    
    private final JdbcTemplate jdbc;

    @Autowired
    public GuessTheNumberRoundDbDao(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }
    
    @Override
    public Round addRound(String guess, String result, Timestamp guessTime, int gameId) {
        final String sql = "INSERT INTO round(gameId, guess, result, guessTime) VALUES(?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        Round round = new Round(guess, result, guessTime, gameId);

        jdbc.update((Connection conn) -> {

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
    public List<Round> getAllRounds(int gameId) {
        final String sql = "SELECT roundId, gameId, guess, guessTime, result FROM round WHERE gameId = ? ORDER BY guessTime ASC";
        return jdbc.query(sql, new GuessTheNumberRoundDbDao.RoundMapper(), gameId);
    }

    @Override
    public void deleteRoundById(int roundId) {
        final String DELETE_ROUND = "DELETE FROM round WHERE roundId = ?";
        jdbc.update(DELETE_ROUND, roundId);
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
