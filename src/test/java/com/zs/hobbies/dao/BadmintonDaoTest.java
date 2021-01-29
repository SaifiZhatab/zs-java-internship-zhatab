package com.zs.hobbies.dao;

import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * This class is BadmintonDao test implementation
 */
public class BadmintonDaoTest {

     Connection connection = mock(Connection.class);
     PreparedStatement preparedStatement = mock(PreparedStatement.class);


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
    void insertNotNullBadmintonObject() throws SQLException {
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
     * when you try to insert null object in dao, then it return InvalidInputException
     */
    @Test
    void insertNullObject() {
        /**
         * check the insert(null) give InvalidInputException
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    badmintonDao.insert(null);
                });
    }
    
    @Test
    void insertException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenThrow(new SQLException());

        assertThrows(ApplicationException.class,
                ()->{
                    badmintonDao.insert(badminton);
                });
    }

    /**
     * check longest streak function
     * @throws SQLException
     */
    @Test
    void longestStreak() throws SQLException {
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        badmintonDao.longestStreak(personId);

        verify(connection.prepareStatement(anyString()) , times(1)).executeQuery();

        assertEquals(resultSet, badmintonDao.longestStreak(personId));
    }


    @Test
    void longestStreakException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenThrow(new SQLException());

        assertThrows(ApplicationException.class,
                () -> {
                    badmintonDao.longestStreak(personId);
                });
    }
    /**
     * check the deta details function of badminton dao class
     * @throws SQLException
     */
    @Test
    void dateDetails() throws SQLException {
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        badmintonDao.dateDetails(personId,date);

        verify(connection.prepareStatement(anyString()) , times(1)).executeQuery();

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, badmintonDao.dateDetails(personId,date));
    }

    @Test
    void dateDetailsException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenThrow(new SQLException());
        assertThrows(ApplicationException.class,
                () -> {
                    badmintonDao.dateDetails(personId,date);
                });
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

    @Test
    void lastTickExceptionThrow() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenThrow(new SQLException());
        assertThrows(ApplicationException.class,
                () -> {
                    badmintonDao.lastTick(personId);
                });
    }
}