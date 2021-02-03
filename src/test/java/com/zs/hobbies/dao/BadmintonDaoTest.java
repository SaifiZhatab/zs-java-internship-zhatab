package com.zs.hobbies.dao;

import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


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
    void insertWithNotNullBadmintonObject() throws SQLException {
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

    /**
     * when you try to insert object in dao, then it return ApplicationException
     */
    @Test
    void insertException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenThrow(new SQLException());

        /**
         * check the insert(null) give InvalidInputException
         */
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
        /**
         * set the external object to mock object
         */
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        /**
         * call method which you want to test
         */
        badmintonDao.longestStreak(personId);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1)).executeQuery();

        /**
         * check the method return correct value or not
         */
        assertEquals(resultSet, badmintonDao.longestStreak(personId));
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
                    badmintonDao.longestStreak(personId);
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
        badmintonDao.dateDetails(personId,date);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1)).executeQuery();

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, badmintonDao.dateDetails(personId,date));
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
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenThrow(new SQLException());

        /**
         * check the exception arise or not for this test case
         */
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
        /**
         * set the external object to mock object
         */
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        /**
         * call method which you want to test
         */
        badmintonDao.lastTick(personId);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, badmintonDao.longestStreak(personId));
    }

    /**
     * last tick function call when exception throw
     * @throws SQLException
     */
    @Test
    void lastTickExceptionThrow() throws SQLException {
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
                    badmintonDao.lastTick(personId);
                });
    }
}