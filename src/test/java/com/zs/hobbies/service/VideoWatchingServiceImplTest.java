package com.zs.hobbies.service;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.VideoWatchingDao;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.VideoWatching;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class is video watching service testing implementation
 */
class VideoWatchingServiceImplTest {

    /**
     * create mock object for external usage object in travelling service
     */
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
        /**
         * initialise video watching service object with mock object
         */
        videoWatchingService = new VideoWatchingServiceImpl();

        videoWatchingId = 1;
        personId = 1;
        startTime = Time.valueOf("10:45:31");
        endTime = Time.valueOf("12:20:31");
        date =  Date.valueOf("2021-01-01");
        title = "lucifer morningstar";

        timing = new Timing(startTime,endTime,date);
        videoWatching = new VideoWatching(videoWatchingId,personId,timing,title);
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
        when(validator.validVideoWatching(videoWatching)).thenReturn(true);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        when(lru.get(anyString())).thenReturn(1);

        videoWatchingService.insert(videoWatching);
    }

    /**
     * this function check the date details function in service class
     * @throws SQLException
     */
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
        when(resultSet.getInt("travelling_id")).thenReturn(1);
        when(resultSet.getInt("personid")).thenReturn(2);
        when(resultSet.getString("startPoint")).thenReturn("UP");
        when(resultSet.getString("endPoint")).thenReturn("MP");
        when(resultSet.getFloat("distance")).thenReturn(1200f);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(validator.validDate(any())).thenReturn(true);

        videoWatchingService.dateDetails(personId,date);
    }

    /**
     * test date details function when this function throw exception
     * @throws SQLException
     */
    @Test
    void dateDetailsException() throws SQLException {
        /**
         * set the external object to mock object
         */
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

        /**
         * check Application exception will throw or not
         */
        assertThrows(ApplicationException.class,
                ()->{
                    videoWatchingService.dateDetails(personId,date);
                });
    }

    /**
     * test last tick it check in database without exception
     * @throws SQLException
     */
    @Test
    void lastTickAvailableInCache() {
        /**
         * set the external object to mock object
         */
        when(lru.get(anyString())).thenReturn(1);

        videoWatchingService.lastTick(personId);
    }

    /**
     * test last tick it check in database without exception
     * @throws SQLException
     */
    @Test
    void lastTickNotAvailableWithoutException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("videoWatching_id")).thenReturn(1);

        videoWatchingService.lastTick(personId);
    }

    /**
     * test last tick it check in database with exception
     * @throws SQLException
     */
    @Test
    void lastTickNotAvailableWithException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(resultSet.getInt("videoWatching_id")).thenReturn(1);

        /**
         * check Application exception will throw or not
         */
        assertThrows(ApplicationException.class,
                ()->{
                    videoWatchingService.lastTick(personId);
                });
    }

    /**
     * test longest streak available in cache when it's available in cache memory
     */
    @Test
    void longestStreakAvailableInCache() {
        /**
         * set the external object to mock object
         */
        when(lru.get(anyString())).thenReturn(1);

        videoWatchingService.longestStreak(personId);
    }

    /**
     * test longest streak it check in database without exception
     * @throws SQLException
     */
    @Test
    void longestStreakNotAvailableWithoutException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        videoWatchingService.longestStreak(personId);
    }

    /**
     * test longest streak it check in database with exception
     * @throws SQLException
     */
    @Test
    void longestStreakNotAvailableWithException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(resultSet.getInt("day")).thenReturn(1);

        /**
         * check Application exception will throw or not
         */
        assertThrows(ApplicationException.class,
                ()->{
                    videoWatchingService.longestStreak(personId);
                });
    }

    /**
     * test latest streak when it's available in cache memory
     */
    @Test
    void latestStreakAvailableInCache() {
        /**
         * set the external object to mock object
         */
        when(lru.get(anyString())).thenReturn(1);

        videoWatchingService.latestStreak(personId);
    }

    /**
     * test latest streak it check in database without exception
     * @throws SQLException
     */
    @Test
    void latestStreakNotAvailableWithoutException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        videoWatchingService.latestStreak(personId);
    }

    /**
     * test latest streak it check in database with exception
     * @throws SQLException
     */
    @Test
    void latestStreakNotAvailableWithException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        /**
         * check Application exception will throw or not
         */
        assertThrows(ApplicationException.class,
                ()->{
                    videoWatchingService.latestStreak(personId);
                });
    }
}