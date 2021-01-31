package com.zs.hobbies.controller;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.service.BadmintonService;
import com.zs.hobbies.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class BadmintonControllerTest {

    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private Validator validator = mock(Validator.class);
    private ResultSet resultSet = mock(ResultSet.class);
    private BadmintonService badmintonService = mock(BadmintonService.class);

    private BadmintonController badmintonController;

    private int badmintonId,personId;
    private Time startTime ,endTime;
    private Date date;
    private int numOfPlayer ;
    private String result;
    private Badminton badminton;
    private Timing timing;

    @BeforeEach
    void setUp() {
        badmintonController = new BadmintonController(connection,lru);

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

    @Test
    void insert() throws SQLException {
        when(validator.validBadminton(badminton)).thenReturn(true);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        badmintonController.insert(badminton);
        verify(connection.prepareStatement(anyString()),times(1)).executeUpdate();
    }

    @Test
    void longestStreak() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(badmintonService.longestStreak(anyInt())).thenReturn(3);
        badmintonController.longestStreak(personId);

       assertEquals(3,badmintonService.longestStreak(personId));

        verify(badmintonService,times(1)).longestStreak(personId);
    }

    @Test
    void latestStreak() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(badmintonService.latestStreak(anyInt())).thenReturn(3);
        badmintonController.latestStreak(personId);
        assertEquals(3,badmintonService.latestStreak(personId));

        verify(badmintonService,times(1)).latestStreak(personId);
    }

    @Test
    void lastTick() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(badmintonService.lastTick(anyInt())).thenReturn(2);

        badmintonController.lastTick(personId);

        assertEquals(2,badmintonService.lastTick(personId));

        verify(badmintonService,times(1)).lastTick(personId);
    }


    @Test
    void searchByDateWithZeroSize() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        Set<Badminton> setDetails = new TreeSet<Badminton>();
        when(badmintonService.dateDetails(personId,date)).thenReturn(setDetails);
        badmintonController.searchByDate(personId,date);

        assertEquals(setDetails,badmintonService.dateDetails(personId,date));
    }

    @Test
    void searchByDateWithSize() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        Iterator<Badminton> iterator = mock(Iterator.class);
        Set<Badminton> setDetails = new HashSet<Badminton>();
        setDetails.add(badminton);

        when(badmintonService.dateDetails(anyInt(),any())).thenReturn(setDetails);
        when(iterator.hasNext()).thenReturn(true);
        when(iterator.next()).thenReturn(setDetails.iterator().next());
        when(iterator.next().getId()).thenReturn(setDetails.iterator().next().getId());

        badmintonController.searchByDate(personId,date);
        assertEquals(setDetails,badmintonService.dateDetails(personId,date));
    }
}