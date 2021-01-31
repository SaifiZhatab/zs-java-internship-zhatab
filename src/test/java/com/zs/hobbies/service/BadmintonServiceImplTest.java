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
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * This class is badminton service testing implementation
 */
class BadmintonServiceImplTest {

    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private Validator validator = mock(Validator.class);
    private BadmintonDao badmintonDao = mock(BadmintonDao.class);
    private ResultSet resultSet = mock(ResultSet.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private SimilarRequirement similarRequirement = mock(SimilarRequirement.class);

    private BadmintonService badmintonService;

    private int badmintonId,personId;
    private Date date;
    private Timing timing;
    private int numPlayers ;
    private String result;
    private Badminton badminton;

    @BeforeEach
    void setUp() {
        badmintonService = new BadmintonServiceImpl(connection,lru);

        badmintonId = 1;
        personId =2;
        date = Date.valueOf("2021-01-01");
        timing = new Timing(Time.valueOf("10:45:31"),Time.valueOf("12:20:31"), date);
        numPlayers = 3;
        result = "win";

        badminton = new Badminton(badmintonId,personId,timing,numPlayers,result);
    }

    @Test
    void insert() throws SQLException {
        when(validator.validBadminton(badminton)).thenReturn(true);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        when(lru.get(anyString())).thenReturn(1);

        badmintonService.insert(badminton);

    }

    @Test
    void dateDetails() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        when(resultSet.getTime("startTime")).thenReturn(Time.valueOf("10:45:31"));
        when(resultSet.getTime("endTime")).thenReturn(Time.valueOf("12:20:31"));
        when(resultSet.getDate("day")).thenReturn(Date.valueOf("2021-01-01"));
        when(resultSet.getInt("badminton_id")).thenReturn(1);
        when(resultSet.getInt("personid")).thenReturn(2);
        when(resultSet.getInt("numPlayers")).thenReturn(4);
        when(resultSet.getString("result")).thenReturn("win");

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(validator.validDate(any())).thenReturn(true);

        badmintonService.dateDetails(personId,date);
    }

    @Test
    void dateDetailsException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        when(resultSet.getTime("startTime")).thenReturn(Time.valueOf("10:45:31"));
        when(resultSet.getTime("endTime")).thenReturn(Time.valueOf("12:20:31"));
        when(resultSet.getDate("day")).thenReturn(Date.valueOf("2021-01-01"));
        when(resultSet.getInt("badminton_id")).thenReturn(1);
        when(resultSet.getInt("personid")).thenReturn(2);
        when(resultSet.getInt("numPlayers")).thenReturn(4);
        when(resultSet.getString("result")).thenReturn("win");

        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(validator.validDate(any())).thenReturn(true);

        assertThrows(ApplicationException.class,
                ()->{
                    badmintonService.dateDetails(personId,date);
                });
    }

    @Test
    void lastTickAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        badmintonService.lastTick(personId);
    }

    @Test
    void lastTickNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("badminton_id")).thenReturn(1);

        badmintonService.lastTick(personId);
    }

    @Test
    void lastTickNotAvailableWithException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(resultSet.getInt("badminton_id")).thenReturn(1);

        assertThrows(ApplicationException.class,
                ()->{
                    badmintonService.lastTick(personId);
                });
    }

    @Test
    void longestStreakAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        badmintonService.longestStreak(personId);
    }

    @Test
    void longestStreakNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        badmintonService.longestStreak(personId);
    }

    @Test
    void longestStreakNotAvailableWithException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(resultSet.getInt("badminton_id")).thenReturn(1);

        assertThrows(ApplicationException.class,
                ()->{
                    badmintonService.longestStreak(personId);
                });
    }

    @Test
    void latestStreakAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        badmintonService.latestStreak(personId);
    }

    @Test
    void latestStreakNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        badmintonService.latestStreak(personId);
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
                    badmintonService.latestStreak(personId);
                });
    }
}