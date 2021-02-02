package com.zs.hobbies.dao;

import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


/**
 * This class is ChessDao test implementation
 */
class ChessDaoTest {

    private Connection connection = mock(Connection.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private ChessDao chessDao;

    private int chessId,personId;
    private Time startTime ,endTime;
    private Date date;
    private int numOfMoves ;
    private String result;
    private Chess chess;
    private Timing timing;

    @BeforeEach
    void setUp() throws SQLException {
        /**
         * create object with mock connection
         */
        chessDao = new ChessDao(connection);

        /**
         * initialise object values
         */
        chessId = 1;
        personId = 1;
        startTime = Time.valueOf("10:45:31");
        endTime = Time.valueOf("12:20:31");
        date =  Date.valueOf("2021-01-01");
        numOfMoves = 4;
        result = "Win";

        timing = new Timing(startTime,endTime,date);
        chess = new Chess(chessId,personId,timing,numOfMoves,result);
    }

    @Test
    void insertObject() throws SQLException {
        /**
         * when connection.prepareStatement(anyString()) instruction come, then it return preparedStatement
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        /**
         * when connection.prepareStatement(anyString()).executeUpdate() instruction come,then it return 1
         */
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        /**
         * call that method which you want to test
         */
        chessDao.insert(chess);

        /**
         * verify the connection.prepareStatement(anyString()) will execute only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1)).executeUpdate();
    }

    /**
     * when you try to insert null object in dao, then it return InvalidInputException
     */
    @Test
    void insertWithNullObject() throws SQLException {
        /**
         * check the insert(null) give InvalidInputException
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    chessDao.insert(null);
                });
    }

    /**
     * when you try to insert object in dao, then it return ApplicationException
     */
    @Test
    void insertException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenThrow(new SQLException());

        assertThrows(ApplicationException.class,
                () -> {
                    chessDao.insert(chess);
                });
    }

    /**
     * check the date details function of badminton dao class
     * @throws SQLException
     */
    @Test
    void dateDetails() throws SQLException {
        /**
         * set the external object to mock object
         */
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        /**
         * call method which you want to test
         */
        chessDao.dateDetails(personId,date);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, chessDao.dateDetails(personId,date));
    }

    /**
     * check date details for exception
     * @throws SQLException
     */
    @Test
    void dateDetailsException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenThrow(new SQLException());

        /**
         * check the exception arise or not for this test case
         */
        assertThrows(ApplicationException.class,
                () -> {
                    chessDao.dateDetails(personId,date);
                });
    }

    /**
     * check the last tick function of badminton dao
     * @throws SQLException
     */
    @Test
    void lastTick() throws SQLException {
        /**
         * set the external object to mock object
         */
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        /**
         * call method which you want to test
         */
        chessDao.lastTick(personId);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, chessDao.lastTick(personId));
    }

    /**
     * last tick function call when exception throw
     * @throws SQLException
     */
    @Test
    void lastTickException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenThrow(new SQLException());

        /**
         * check the exception arise or not for this test case
         */
        assertThrows(ApplicationException.class,
                () -> {
                    chessDao.lastTick(personId);
                });
    }

    /**
     * check longest streak function
     * @throws SQLException
     */
    @Test
    void longestStreak() throws SQLException {
        /**
         * set the external object to mock object
         */
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        /**
         * call method which you want to test
         */
        chessDao.longestStreak(personId);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the method return correct value or not
         */
        assertEquals(resultSet, chessDao.longestStreak(personId));
    }

    /**
     * test longestStreak function when this function give exception
     * @throws SQLException
     */
    @Test
    void longestStreakException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenThrow(new SQLException());

        /**
         * check the exception arise or not for this test case
         */
        assertThrows(ApplicationException.class,
            () -> {
                chessDao.longestStreak(personId);
            });
    }
}