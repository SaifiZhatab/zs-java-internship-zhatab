package com.zs.hobbies.dao;

import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.dto.Timing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


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
    void insertWithoutNullObject() throws SQLException {
        /**
         * when connection.prepareStatement(anyString()) instruction come, then it return preparedStatement
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        /**
         * when connection.prepareStatement(anyString()).executeUpdate() instruction come,then it return 1
         */
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        chessDao.insert(chess);

        /**
         * verify the connection.prepareStatement(anyString()) will execute only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));
    }

    /**
     * check insert function in ChessDao when you pass null object
     * @throws SQLException
     */
    @Test
    public void insertWithNullObject() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        chessDao.insert(null);

        verify(connection.prepareStatement(anyString()), times(1));
    }

    /**
     * check the deta details function of badminton dao class
     * @throws SQLException
     */
    @Test
    public void dateDetails() throws SQLException {
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        chessDao.dateDetails(personId,date);

        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, chessDao.dateDetails(personId,date));
    }

    /**
     * check the last tick function of badminton dao
     * @throws SQLException
     */
    @Test
    void lastTick() throws SQLException {
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        chessDao.lastTick(personId);

        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, chessDao.lastTick(personId));
    }

    /**
     * check longest streak function
     * @throws SQLException
     */
    @Test
    public void longestStreak() throws SQLException {
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        chessDao.longestStreak(personId);

        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, chessDao.longestStreak(personId));
    }
}