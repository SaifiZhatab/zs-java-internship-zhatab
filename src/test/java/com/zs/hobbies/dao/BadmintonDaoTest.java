package com.zs.hobbies.dao;

import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Timing;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * This class is BadmintonDao test implementation
 */
public class BadmintonDaoTest {

     Connection connection = mock(Connection.class);
     PreparedStatement preparedStatement = mock(PreparedStatement.class);

    @InjectMocks
    private BadmintonDao badmintonDao ;

    private int badmintonId,personId;
    private Time startTime ,endTime;
    private Date date;
    private int numOfPlayer ;
    private String result;
    private Badminton badminton;
    private Timing timing;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        /**
         * create object with mock connection
         */
        badmintonDao = new BadmintonDao(connection);

        /**
         * initialise object values
         */
        badmintonId = 1;
        personId = 1;
        startTime = Time.valueOf("10:45:31");
        endTime = Time.valueOf("12:20:31");
        date =  Date.valueOf("2021-01-01");
        numOfPlayer = 4;
        result = "Win";

        timing = new Timing(startTime,endTime,date);
        badminton = new Badminton(badmintonId,personId,timing,numOfPlayer,result);
    }

    /**
     * insert the object when data is not present in database
     * @throws SQLException
     */
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

        badmintonDao.insert(badminton);

        /**
         * verify the connection.prepareStatement(anyString()) will execute only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1)).executeUpdate();

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

        badmintonDao.longestStreak(personId);

        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, badmintonDao.longestStreak(personId));
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

        badmintonDao.dateDetails(personId,date);

        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, badmintonDao.dateDetails(personId,date));
    }

    @Test
    public void dateDetailsWithNullObject() throws SQLException {
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        badmintonDao.dateDetails(personId,null);

        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, badmintonDao.longestStreak(personId));
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

        badmintonDao.lastTick(personId);

        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, badmintonDao.longestStreak(personId));
    }
}