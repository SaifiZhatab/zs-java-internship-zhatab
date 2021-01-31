package com.zs.hobbies.service;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.ChessDao;
import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class is badminton service testing implementation
 */
class ChessServiceImplTest {
    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private Validator validator = mock(Validator.class);
    private ChessDao chessDao = mock(ChessDao.class);
    private ResultSet resultSet = mock(ResultSet.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private SimilarRequirement similarRequirement = mock(SimilarRequirement.class);

    private ChessService chessService;

    private int chessId,personId;
    private Date date;
    private Timing timing;
    private int numOfMoves ;
    private String result;
    private Chess chess;

    @BeforeEach
    void setUp() {
        chessService = new ChessServiceImpl(connection,lru);

        chessId = 1;
        personId =2;
        date = Date.valueOf("2021-01-01");
        timing = new Timing(Time.valueOf("10:45:31"),Time.valueOf("12:20:31"), date);
        numOfMoves = 3;
        result = "win";

        chess = new Chess(chessId,personId,timing,numOfMoves,result);
    }

    /**
     * insert function testing
     * @throws SQLException
     */
    @Test
    void insert() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(validator.validChess(chess)).thenReturn(true);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);
        when(lru.get(anyString())).thenReturn(1);

        /**
         * call method which you want to test
         */
        chessService.insert(chess);
    }

    @Test
    void dateDetails() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(resultSet.getTime("startTime")).thenReturn(Time.valueOf("10:45:31"));
        when(resultSet.getTime("endTime")).thenReturn(Time.valueOf("12:20:31"));
        when(resultSet.getDate("day")).thenReturn(Date.valueOf("2021-01-01"));
        when(resultSet.getInt("chess_id")).thenReturn(1);
        when(resultSet.getInt("personid")).thenReturn(2);
        when(resultSet.getInt("numMoves")).thenReturn(4);
        when(resultSet.getString("result")).thenReturn("win");
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(validator.validDate(any())).thenReturn(true);

        /**
         * call method which you want to test
         */
        chessService.dateDetails(personId,date);
    }

    @Test
    void dateDetailsException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        when(resultSet.getTime("startTime")).thenReturn(Time.valueOf("10:45:31"));
        when(resultSet.getTime("endTime")).thenReturn(Time.valueOf("12:20:31"));
        when(resultSet.getDate("day")).thenReturn(Date.valueOf("2021-01-01"));
        when(resultSet.getInt("chess_id")).thenReturn(1);
        when(resultSet.getInt("personid")).thenReturn(2);
        when(resultSet.getInt("numMoves")).thenReturn(4);
        when(resultSet.getString("result")).thenReturn("win");

        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(validator.validDate(any())).thenReturn(true);

        assertThrows(ApplicationException.class,
                ()->{
                    chessService.dateDetails(personId,date);
                });
    }

    @Test
    void lastTickAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        chessService.lastTick(personId);
    }

    @Test
    void lastTickNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("chess_id")).thenReturn(1);

        chessService.lastTick(personId);
    }

    @Test
    void lastTickNotAvailableWithException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(resultSet.getInt("chess_id")).thenReturn(1);

        assertThrows(ApplicationException.class,
                ()->{
                    chessService.lastTick(personId);
                });
    }

    @Test
    void longestStreakAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);
        chessService.longestStreak(personId);
    }

    @Test
    void longestStreakNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        chessService.longestStreak(personId);
    }

    @Test
    void longestStreakNotAvailableWithException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(resultSet.getInt("day")).thenReturn(1);

        assertThrows(ApplicationException.class,
                ()->{
                    chessService.longestStreak(personId);
                });
    }

    @Test
    void latestStreakAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        chessService.latestStreak(personId);
    }

    @Test
    void latestStreakNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        chessService.latestStreak(personId);
    }

    @Test
    void latestStreakNotAvailableWithException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        assertThrows(ApplicationException.class,
                ()->{
                    chessService.latestStreak(personId);
                });
    }
}