package com.zs.hobbies.service;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.TravellingDao;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.Travelling;
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

class TravellingServiceImplTest {

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
        travellingService = new TravellingServiceImpl(connection,lru);

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
    void insert() throws SQLException {
        when(validator.validTravelling(travelling)).thenReturn(true);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        when(lru.get(anyString())).thenReturn(1);

        travellingService.insert(travelling);
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

        travellingService.dateDetails(personId,date);
    }

    @Test
    void dateDetailsException() throws SQLException {
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

        assertThrows(ApplicationException.class,
                ()->{
                    travellingService.dateDetails(personId,date);
                });
    }

    @Test
    void lastTickAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        travellingService.lastTick(personId);
    }

    @Test
    void lastTickNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("travelling_id")).thenReturn(1);

        travellingService.lastTick(personId);
    }

    @Test
    void lastTickNotAvailableWithException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenThrow(new SQLException()).thenReturn(false);
        when(resultSet.getInt("travelling_id")).thenReturn(1);

        assertThrows(ApplicationException.class,
                ()->{
                    travellingService.lastTick(personId);
                });
    }

    @Test
    void longestStreakAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        travellingService.longestStreak(personId);
    }

    @Test
    void longestStreakNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        travellingService.longestStreak(personId);
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
                    travellingService.longestStreak(personId);
                });
    }

    @Test
    void latestStreakAvailableInCache() {
        when(lru.get(anyString())).thenReturn(1);

        travellingService.latestStreak(personId);
    }

    @Test
    void latestStreakNotAvailableWithoutException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(lru.get(anyString())).thenReturn(null);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getDate("day")).thenReturn(date);

        travellingService.latestStreak(personId);
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
                    travellingService.latestStreak(personId);
                });
    }
}