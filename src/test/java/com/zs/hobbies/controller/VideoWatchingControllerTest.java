package com.zs.hobbies.controller;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.VideoWatching;
import com.zs.hobbies.service.VideoWatchingService;
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

class VideoWatchingControllerTest {

    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private Validator validator = mock(Validator.class);
    private ResultSet resultSet = mock(ResultSet.class);
    private VideoWatchingService videoWatchingService = mock(VideoWatchingService.class);

    private VideoWatchingController videoWatchingController;

    private int videoWatchingId,personId;
    private Time startTime ,endTime;
    private Date date;
    private String title;
    private VideoWatching videoWatching;
    private Timing timing;

    @BeforeEach
    void setUp() {
        videoWatchingController = new VideoWatchingController(connection,lru);

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

        videoWatchingController.insert(videoWatching);
        verify(connection.prepareStatement(anyString()),times(1)).executeUpdate();
    }

    @Test
    void longestStreak() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(videoWatchingService.longestStreak(anyInt())).thenReturn(3);
        videoWatchingController.longestStreak(personId);

        assertEquals(3,videoWatchingService.longestStreak(personId));

        verify(videoWatchingService,times(1)).longestStreak(personId);
    }

    @Test
    void latestStreak() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(videoWatchingService.latestStreak(anyInt())).thenReturn(3);
        videoWatchingController.latestStreak(personId);
        assertEquals(3,videoWatchingService.latestStreak(personId));

        verify(videoWatchingService,times(1)).latestStreak(personId);
    }

    @Test
    void lastTick() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(videoWatchingService.lastTick(anyInt())).thenReturn(2);

        videoWatchingController.lastTick(personId);

        assertEquals(2,videoWatchingService.lastTick(personId));

        verify(videoWatchingService,times(1)).lastTick(personId);
    }


    @Test
    void searchByDateWithZeroSize() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        Set<Badminton> setDetails = new TreeSet<Badminton>();
        when(videoWatchingService.dateDetails(personId,date)).thenReturn(setDetails);
        videoWatchingController.searchByDate(personId,date);

        assertEquals(setDetails,videoWatchingService.dateDetails(personId,date));
    }

    @Test
    void searchByDateWithSize() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        Iterator<VideoWatching> iterator = mock(Iterator.class);
        Set<VideoWatching> setDetails = new HashSet<VideoWatching>();
        setDetails.add(videoWatching);

        when(videoWatchingService.dateDetails(anyInt(),any())).thenReturn(setDetails);
        when(iterator.hasNext()).thenReturn(true);
        when(iterator.next()).thenReturn(setDetails.iterator().next());
        when(iterator.next().getId()).thenReturn(setDetails.iterator().next().getId());

        videoWatchingController.searchByDate(personId,date);
        assertEquals(setDetails,videoWatchingService.dateDetails(personId,date));
    }
}