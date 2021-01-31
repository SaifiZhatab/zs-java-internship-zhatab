package com.zs.hobbies.controller;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.Travelling;
import com.zs.hobbies.service.BadmintonService;
import com.zs.hobbies.service.TravellingService;
import com.zs.hobbies.service.TravellingServiceImpl;
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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class TravellingControllerTest {

    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private Validator validator = mock(Validator.class);
    private ResultSet resultSet = mock(ResultSet.class);
    private TravellingService travellingService = mock(TravellingService.class);

    private TravellingController travellingController;

    private int travellingId,personId;
    private Time startTime ,endTime;
    private Date date;
    private String startPoint , endPoint;
    private Float distance;
    private Travelling travelling;
    private Timing timing;

    @BeforeEach
    void setUp() {
        travellingController = new TravellingController(connection,lru);

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

        travellingController.insert(travelling);
        verify(connection.prepareStatement(anyString()),times(1)).executeUpdate();
    }

    @Test
    void longestStreak() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(travellingService.longestStreak(anyInt())).thenReturn(3);
        travellingController.longestStreak(personId);

        assertEquals(3,travellingService.longestStreak(personId));

        verify(travellingService,times(1)).longestStreak(personId);
    }

    @Test
    void latestStreak() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(travellingService.latestStreak(anyInt())).thenReturn(3);
        travellingController.latestStreak(personId);
        assertEquals(3,travellingService.latestStreak(personId));

        verify(travellingService,times(1)).latestStreak(personId);
    }

    @Test
    void lastTick() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(travellingService.lastTick(anyInt())).thenReturn(2);

        travellingController.lastTick(personId);

        assertEquals(2,travellingService.lastTick(personId));

        verify(travellingService,times(1)).lastTick(personId);
    }


    @Test
    void searchByDateWithZeroSize() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        Set<Badminton> setDetails = new TreeSet<Badminton>();
        when(travellingService.dateDetails(personId,date)).thenReturn(setDetails);
        travellingController.searchByDate(personId,date);

        assertEquals(setDetails,travellingService.dateDetails(personId,date));
    }

    @Test
    void searchByDateWithSize() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        Iterator<Travelling> iterator = mock(Iterator.class);
        Set<Travelling> setDetails = new HashSet<Travelling>();
        setDetails.add(travelling);

        when(travellingService.dateDetails(anyInt(),any())).thenReturn(setDetails);
        when(iterator.hasNext()).thenReturn(true);
        when(iterator.next()).thenReturn(setDetails.iterator().next());
        when(iterator.next().getId()).thenReturn(setDetails.iterator().next().getId());

        travellingController.searchByDate(personId,date);
        assertEquals(setDetails,travellingService.dateDetails(personId,date));
    }
}