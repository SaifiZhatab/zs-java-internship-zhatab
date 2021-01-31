package com.zs.hobbies.service;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.TravellingDao;
import com.zs.hobbies.dao.VideoWatchingDao;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.Travelling;
import com.zs.hobbies.dto.VideoWatching;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VideoWatchingServiceImplTest {

    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private Validator validator = mock(Validator.class);
    private VideoWatchingDao videoWatchingDao = mock(VideoWatchingDao.class);
    private ResultSet resultSet = mock(ResultSet.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private SimilarRequirement similarRequirement = mock(SimilarRequirement.class);

    private VideoWatchingService videoWatchingService;

    private int videoWatchingId,personId;
    private Time startTime ,endTime;
    private Date date;
    private String title;
    private VideoWatching videoWatching;
    private Timing timing;


    @BeforeEach
    void setUp() {
        videoWatchingService = new VideoWatchingServiceImpl(connection,lru);

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
    void insert() throws SQLException {
        when(validator.validVideoWatching(videoWatching)).thenReturn(true);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        when(lru.get(anyString())).thenReturn(1);

        videoWatchingService.insert(videoWatching);
    }

    @Test
    void dateDetails() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        when(resultSet.getTime("startTime")).thenReturn(Time.valueOf("10:45:31"));
        when(resultSet.getTime("endTime")).thenReturn(Time.valueOf("12:20:31"));
        when(resultSet.getDate("day")).thenReturn(Date.valueOf("2021-01-01"));
        when(resultSet.getInt("travelling_id")).thenReturn(1);
        when(resultSet.getInt("personid")).thenReturn(2);
        when(resultSet.getString("startPoint")).thenReturn("UP");
        when(resultSet.getString("endPoint")).thenReturn("MP");
        when(resultSet.getFloat("distance")).thenReturn(1200f);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(validator.validDate(any())).thenReturn(true);

        videoWatchingService.dateDetails(personId,date);
    }

    @Test
    void dateDetailsException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        when(resultSet.getTime("startTime")).thenReturn(Time.valueOf("10:45:31"));
        when(resultSet.getTime("endTime")).thenReturn(Time.valueOf("12:20:31"));
        when(resultSet.getDate("day")).thenReturn(Date.valueOf("2021-01-01"));
        when(resultSet.getInt("videoWatching_id")).thenReturn(1);
        when(resultSet.getInt("personid")).thenReturn(2);
        when(resultSet.getString("title")).thenReturn("lucifer");

        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(validator.validDate(any())).thenReturn(true);

        assertThrows(ApplicationException.class,
                ()->{
                    videoWatchingService.dateDetails(personId,date);
                });
    }

    @Test
    void lastTickAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        videoWatchingService.lastTick(personId);
    }

    @Test
    void lastTickNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("videoWatching_id")).thenReturn(1);

        videoWatchingService.lastTick(personId);
    }

    @Test
    void lastTickNotAvailableWithException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(resultSet.getInt("videoWatching_id")).thenReturn(1);

        assertThrows(ApplicationException.class,
                ()->{
                    videoWatchingService.lastTick(personId);
                });
    }

    @Test
    void longestStreakAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        videoWatchingService.longestStreak(personId);
    }

    @Test
    void longestStreakNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        videoWatchingService.longestStreak(personId);
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
                    videoWatchingService.longestStreak(personId);
                });
    }

    @Test
    void latestStreakAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        videoWatchingService.latestStreak(personId);
    }

    @Test
    void latestStreakNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        videoWatchingService.latestStreak(personId);
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
                    videoWatchingService.latestStreak(personId);
                });
    }
}