package com.zs.hobbies.controller;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.VideoWatching;
import com.zs.hobbies.service.ChessService;
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

class ChessControllerTest {

    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private Validator validator = mock(Validator.class);
    private ResultSet resultSet = mock(ResultSet.class);
    private ChessService chessService  = mock(ChessService.class);

    private ChessController chessController;

    private int chessId,personId;
    private Date date;
    private Timing timing;
    private int numOfMoves ;
    private String result;
    private Chess chess;

    @BeforeEach
    void setUp() {
        chessController = new ChessController(connection,lru);

        chessId = 1;
        personId =2;
        date = Date.valueOf("2021-01-01");
        timing = new Timing(Time.valueOf("10:45:31"),Time.valueOf("12:20:31"), date);
        numOfMoves = 3;
        result = "win";
        chess = new Chess(chessId,personId,timing,numOfMoves,result);

    }

    @Test
    void insert() throws SQLException {
        when(validator.validChess(chess)).thenReturn(true);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        chessController.insert(chess);
        verify(connection.prepareStatement(anyString()),times(1)).executeUpdate();
    }

    @Test
    void longestStreak() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(chessService.longestStreak(anyInt())).thenReturn(3);
        chessController.longestStreak(personId);

        assertEquals(3,chessService.longestStreak(personId));

        verify(chessService,times(1)).longestStreak(personId);
    }

    @Test
    void latestStreak() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(chessService.latestStreak(anyInt())).thenReturn(3);
        chessController.latestStreak(personId);
        assertEquals(3,chessService.latestStreak(personId));

        verify(chessService,times(1)).latestStreak(personId);
    }

    @Test
    void lastTick() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
        when(chessService.lastTick(anyInt())).thenReturn(2);

        chessController.lastTick(personId);

        assertEquals(2,chessService.lastTick(personId));

        verify(chessService,times(1)).lastTick(personId);
    }


    @Test
    void searchByDateWithZeroSize() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        Set<Badminton> setDetails = new TreeSet<Badminton>();
        when(chessService.dateDetails(personId,date)).thenReturn(setDetails);
        chessController.searchByDate(personId,date);

        assertEquals(setDetails,chessService.dateDetails(personId,date));
    }

    @Test
    void searchByDateWithSize() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);

        Iterator<Chess> iterator = mock(Iterator.class);
        Set<Chess> setDetails = new HashSet<Chess>();
        setDetails.add(chess);

        when(chessService.dateDetails(anyInt(),any())).thenReturn(setDetails);
        when(iterator.hasNext()).thenReturn(true);
        when(iterator.next()).thenReturn(setDetails.iterator().next());
        when(iterator.next().getId()).thenReturn(setDetails.iterator().next().getId());

        chessController.searchByDate(personId,date);
        assertEquals(setDetails,chessService.dateDetails(personId,date));
    }
}