package com.zs.hobbies.service;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.BadmintonDao;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class is badminton service testing implementation
 */
class BadmintonServiceImplTest {

    /**
     * create mock object for external usage object in Badminton service
     */
    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private Validator validator = mock(Validator.class);
    private  BadmintonDao badmintonDao = mock(BadmintonDao.class);

    private BadmintonService badmintonService;

    private int badmintonId,personId;
    private Date date;
    private Timing timing;
    private int numPlayers ;
    private String result;
    private Badminton badminton;

    @BeforeEach
    void setUp() {
        /**
         * initialise badminton service object with mock object
         */
        badmintonService  = new BadmintonServiceImpl();

        badmintonId = 1;
        personId =2;
        date = Date.valueOf("2021-01-01");
        timing = new Timing(Time.valueOf("10:45:31"),Time.valueOf("12:20:31"), date);
        numPlayers = 3;
        result = "win";

        badminton = mock(Badminton.class);

        badminton = new Badminton(badmintonId,personId,timing,numPlayers,result);
    }

    /**
     * test insert function of service class
     * @throws SQLException
     */
    @Test
    void insert() throws SQLException {
        /**
         * set condition for  external usage of object
         */
        when(validator.validBadminton(badminton)).thenReturn(true);
        doNothing().when(badmintonDao).insert(badminton);

        when(lru.get(anyString())).thenReturn(1);

        /**
         * call the function which you want to test
         */
        badmintonService.insert(any());
    }

    /**
     * this function check the date details function in service class
     * @throws SQLException
     */
    @Test
    void dateDetails() throws SQLException {
        /**
         * set condition for  external usage of object
         */

        /**
         * call the function which you want to test
         */
        badmintonService.dateDetails(personId,date);
    }

    /**
     * test date details function when this function throw exception
     * @throws SQLException
     */
    @Test
    void dateDetailsException() throws SQLException {
        /**
         * call the function which you want to test
         */


        /**
         * check Application exception will throw or not
         */
        assertThrows(ApplicationException.class,
                ()->{
                    badmintonService.dateDetails(personId,date);
                });
    }

    /**
     * test last tick when it's available in cache memory
     */
    @Test
    void lastTickAvailableInCache() {
        /**
         * call the function which you want to test
         */
        when(lru.get(anyString())).thenReturn(1);

        badmintonService.lastTick(personId);
    }

    /**
     * test last tick it check in database without exception
     * @throws SQLException
     */
    @Test
    void lastTickNotAvailableWithoutException() throws SQLException {
        /**
         * call the function which you want to test
         */


        badmintonService.lastTick(personId);
    }

    /**
     * test last tick it check in database with exception
     * @throws SQLException
     */
    @Test
    void lastTickNotAvailableWithException() throws SQLException {
        /**
         * call the function which you want to test

         * check Application exception will throw or not
         */
        assertThrows(ApplicationException.class,
                ()->{
                    badmintonService.lastTick(personId);
                });
    }

    /**
     * test longest streak available in cache when it's available in cache memory
     */
    @Test
    void longestStreakAvailableInCache() {
        /**
         * call the function which you want to test
         */
        when(lru.get(anyString())).thenReturn(1);

        badmintonService.longestStreak(personId);
    }

    /**
     * test longest streak it check in database without exception
     * @throws SQLException
     */
    @Test
    void longestStreakNotAvailableWithoutException() throws SQLException {

        badmintonService.longestStreak(personId);
    }

    /**
     * test longest streak it check in database with exception
     * @throws SQLException
     */
    @Test
    void longestStreakNotAvailableWithException() throws SQLException {

        /**
         * check Application exception will throw or not
         */
        assertThrows(ApplicationException.class,
                ()->{
                    badmintonService.longestStreak(personId);
                });
    }

    /**
     * test latest streak when it's available in cache memory
     */
    @Test
    void latestStreakAvailableInCache() {
        /**
         * call the function which you want to test
         */
        when(lru.get(anyString())).thenReturn(1);

        badmintonService.latestStreak(personId);
    }

    /**
     * test latest streak it check in database without exception
     * @throws SQLException
     */
    @Test
    void latestStreakNotAvailableWithoutException() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(lru.get(anyString())).thenReturn(null);
        badmintonService.latestStreak(personId);

        when(badmintonDao.longestStreak(anyInt())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
//        when(resultSet.getInt());
    }

    /**
     * test latest streak it check in database with exception
     * @throws SQLException
     */
    @Test
    void latestStreakNotAvailableWithException() throws SQLException {

        /**
         * check Application exception will throw or not
         */
        assertThrows(ApplicationException.class,
                ()->{
                    badmintonService.latestStreak(personId);
                });
    }
}