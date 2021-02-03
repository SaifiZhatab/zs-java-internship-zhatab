package com.zs.hobbies.service;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.TravellingDao;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.Travelling;
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
 * This class is travelling service testing implementation
 */
class TravellingServiceImplTest {

    /**
     * create mock object for external usage object in travelling service
     */
    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private Validator validator = mock(Validator.class);
    private TravellingDao travellingDao = mock(TravellingDao.class);
    private ResultSet resultSet = mock(ResultSet.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private SimilarRequirement similarRequirement = mock(SimilarRequirement.class);

    private TravellingService travellingService;

    private int travellingId,personId;
    private Time startTime ,endTime;
    private Date date;
    private String startPoint , endPoint;
    private Float distance;
    private Travelling travelling;
    private Timing timing;


    @BeforeEach
    void setUp() {
        /**
         * initialise travelling service object with mock object
         */
        travellingService = new TravellingServiceImpl();

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

    /**
     * insert function testing
     * @throws SQLException
     */
    @Test
    void insert() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(validator.validTravelling(travelling)).thenReturn(true);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        when(lru.get(anyString())).thenReturn(1);

        travellingService.insert(travelling);
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

        travellingService.dateDetails(personId,date);
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
        when(resultSet.getInt("travelling_id")).thenReturn(1);
        when(resultSet.getInt("personid")).thenReturn(2);
        when(resultSet.getString("startPoint")).thenReturn("UP");
        when(resultSet.getString("endPoint")).thenReturn("MP");
        when(resultSet.getFloat("distance")).thenReturn(1200f);

        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(validator.validDate(any())).thenReturn(true);

        /**
         * check Application exception will throw or not
         */
        assertThrows(ApplicationException.class,
                ()->{
                    travellingService.dateDetails(personId,date);
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

        travellingService.lastTick(personId);
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
        when(resultSet.getInt("travelling_id")).thenReturn(1);

        travellingService.lastTick(personId);
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
        when(resultSet.getInt("travelling_id")).thenReturn(1);

        /**
         * check Application exception will throw or not
         */
        assertThrows(ApplicationException.class,
                ()->{
                    travellingService.lastTick(personId);
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

        travellingService.longestStreak(personId);
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

        travellingService.longestStreak(personId);
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
                    travellingService.longestStreak(personId);
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

        travellingService.latestStreak(personId);
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

        travellingService.latestStreak(personId);
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
                    travellingService.latestStreak(personId);
                });
    }
}