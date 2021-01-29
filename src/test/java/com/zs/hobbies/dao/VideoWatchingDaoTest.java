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
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);
        videoWatchingDao.insert(videoWatching);
        verify(connection.prepareStatement(anyString()) , times(1)).executeUpdate();
    }

    @Test
    void insertWithNullObject() throws SQLException {
        assertThrows(InvalidInputException.class,
                () -> {
                    videoWatchingDao.insert(null);
                });
    }

    @Test
    void insertException() throws SQLException {
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
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        videoWatchingDao.dateDetails(personId,date);

        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, videoWatchingDao.dateDetails(personId,date));
    }

    @Test
    void dateDetailsException() throws SQLException {
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenThrow(new SQLException());

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
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        videoWatchingDao.lastTick(personId);

        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, videoWatchingDao.lastTick(personId));
    }

    @Test
    void lastTickException() throws SQLException {
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenThrow(new SQLException());
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
        ResultSet resultSet = null;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        videoWatchingDao.longestStreak(personId);

        verify(connection.prepareStatement(anyString()) , times(1));

        /**
         * check the actual or expected value
         */
        assertEquals(resultSet, videoWatchingDao.longestStreak(personId));
    }


    @Test
    void longestStreakException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenThrow(new SQLException());

        assertThrows(ApplicationException.class,
                () -> {
                    videoWatchingDao.longestStreak(personId);
                });
    }
}