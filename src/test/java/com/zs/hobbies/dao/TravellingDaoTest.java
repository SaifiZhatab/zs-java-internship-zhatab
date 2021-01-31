package com.zs.hobbies.dao;

import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.Travelling;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * This class is TravellingDao test implementation
 */
class TravellingDaoTest {

    private Connection connection = mock(Connection.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private TravellingDao travellingDao;

    private int travellingId,personId;
    private Time startTime ,endTime;
    private Date date;
    private String startPoint , endPoint;
    private Float distance;

    private Travelling travelling;
    private Timing timing;


    @BeforeEach
    void setUp() {
        travellingDao = new TravellingDao(connection);

        travellingId = 1;
        personId = 1;
        startTime = Time.valueOf("10:45:31");
        endTime = Time.valueOf("12:20:31");
        date =  Date.valueOf("2021-01-01");
        startPoint = "Up";
        endPoint = "MP";
        distance = 1.5f;

        timing = new Timing(startTime,endTime,date);

        travelling = new Travelling(travellingId,personId,timing,startPoint,endPoint,distance);
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
        travellingDao.insert(travelling);

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
                    travellingDao.insert(null);
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
                    travellingDao.insert(travelling);
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
        travellingDao.dateDetails(personId,date);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, travellingDao.dateDetails(personId,date));
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
                    travellingDao.dateDetails(personId,date);
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
        travellingDao.lastTick(personId);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, travellingDao.lastTick(personId));
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
                    travellingDao.lastTick(personId);
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
        travellingDao.longestStreak(personId);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the method return correct value or not
         */
        assertEquals(resultSet, travellingDao.longestStreak(personId));
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
                    travellingDao.longestStreak(personId);
                });
    }
}