package com.zs.hobbies.dao;

import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.VideoWatching;
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
 * This class is VideoWatchingDao test implementation
 */
class VideoWatchingDaoTest {

    private Connection connection = mock(Connection.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private VideoWatchingDao videoWatchingDao;

    private int videoWatchingId,personId;
    private Time startTime ,endTime;
    private Date date;
    private String title;
    private VideoWatching videoWatching;
    private Timing timing;

    @BeforeEach
    void setUp() {
        videoWatchingDao = new VideoWatchingDao(connection);

        videoWatchingId = 1;
        personId = 1;
        startTime = Time.valueOf("10:45:31");
        endTime = Time.valueOf("12:20:31");
        date =  Date.valueOf("2021-01-01");
        title = "lucifer morningstar";

        timing = new Timing(startTime,endTime,date);

        videoWatching = new VideoWatching(videoWatchingId,personId,timing,title);
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
        videoWatchingDao.insert(videoWatching);

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
                    videoWatchingDao.insert(null);
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
                    videoWatchingDao.insert(videoWatching);
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
        videoWatchingDao.dateDetails(personId,date);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, videoWatchingDao.dateDetails(personId,date));
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
                    videoWatchingDao.dateDetails(personId,date);
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
        videoWatchingDao.lastTick(personId);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, videoWatchingDao.lastTick(personId));
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
                    videoWatchingDao.lastTick(personId);
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
        videoWatchingDao.longestStreak(personId);

        /**
         * check connection.prepareStatement(anyString()).executeQuery() run only 1 time
         */
        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the method return correct value or not
         */
        assertEquals(resultSet, videoWatchingDao.longestStreak(personId));
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
                    videoWatchingDao.longestStreak(personId);
                });
    }
}