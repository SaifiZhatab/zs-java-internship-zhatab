//package com.zs.hobbies.controller;
//
//import com.zs.hobbies.cache.Cache;
//import com.zs.hobbies.dto.Badminton;
//import com.zs.hobbies.dto.Chess;
//import com.zs.hobbies.dto.Timing;
//import com.zs.hobbies.service.ChessService;
//import com.zs.hobbies.validator.Validator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.PreparedStatement;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.sql.Time;
//
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//import java.util.TreeSet;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyInt;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.times;
//
///**
// * This class is Chess Controller testing implementation using mockito
// */
//class ChessControllerTest {
//
//    /**
//     * create mock object for external usage object in Badminton controller
//     */
//    private Cache lru = mock(Cache.class);
//    private Connection connection = mock(Connection.class);
//    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
//    private Validator validator = mock(Validator.class);
//    private ResultSet resultSet = mock(ResultSet.class);
//    private ChessService chessService  = mock(ChessService.class);
//
//    private ChessController chessController;
//
//    /**
//     * create chess object
//     */
//    private int chessId,personId;
//    private Date date;
//    private Timing timing;
//    private int numOfMoves ;
//    private String result;
//    private Chess chess;
//
//    @BeforeEach
//    void setUp() {
//        /**
//         * initialise chess controller object with mock object
//         */
//        chessController = new ChessController(connection,lru);
//
//        chessId = 1;
//        personId =2;
//        date = Date.valueOf("2021-01-01");
//        timing = new Timing(Time.valueOf("10:45:31"),Time.valueOf("12:20:31"), date);
//        numOfMoves = 3;
//        result = "win";
//        chess = new Chess(chessId,personId,timing,numOfMoves,result);
//
//    }
//
//    /**
//     * test insert function of chess controller class
//     * @throws SQLException
//     */
//    @Test
//    void insert() throws SQLException {
//        /**
//         * set condition for  external usage of object
//         */
//        when(validator.validChess(chess)).thenReturn(true);
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);
//
//        /**
//         * call the function which you want to test
//         */
//        chessController.insert(chess);
//
//        /**
//         * verify the connection.prepareStatement(anyString().executeUpdate() execute 1 time when you insert badminton
//         */
//        verify(connection.prepareStatement(anyString()),times(1)).executeUpdate();
//    }
//
//    /**
//     * test longestStreak function of chess controller class
//     * @throws SQLException
//     */
//    @Test
//    void longestStreak() throws SQLException {
//        /**
//         * set condition for external usage of object
//         */
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
//        when(chessService.longestStreak(anyInt())).thenReturn(3);
//
//        /**
//         * call the function which you want to test
//         */
//        chessController.longestStreak(personId);
//
//        /**
//         * check the function return correct value or not
//         */
//        assertEquals(3,chessService.longestStreak(personId));
//
//        /**
//         * verify the badmintonService.longestStreak() execute 1 time when you insert badminton
//         */
//        verify(chessService,times(1)).longestStreak(personId);
//    }
//
//    /**
//     * test longestStreak function of chess controller class
//     * @throws SQLException
//     */
//    @Test
//    void latestStreak() throws SQLException {
//        /**
//         * set condition for external usage of object
//         */
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
//        when(chessService.latestStreak(anyInt())).thenReturn(3);
//
//        /**
//         * call the function which you want to test
//         */
//        chessController.latestStreak(personId);
//
//        /**
//         * check the function return correct value or not
//         */
//        assertEquals(3,chessService.latestStreak(personId));
//
//        /**
//         * verify the badmintonService.longestStreak() execute 1 time when you insert badminton
//         */
//        verify(chessService,times(1)).latestStreak(personId);
//    }
//
//
//    /**
//     * test longestStreak function of chess controller class
//     * @throws SQLException
//     */
//    @Test
//    void lastTick() throws SQLException {
//        /**
//         * set condition for external usage of object
//         */
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
//        when(chessService.lastTick(anyInt())).thenReturn(2);
//
//        /**
//         * call the function which you want to test
//         */
//        chessController.lastTick(personId);
//
//        /**
//         * check the function return correct value or not
//         */
//        assertEquals(2,(int)chessService.lastTick(personId));
//
//        /**
//         * verify the badmintonService.longestStreak() execute 1 time when you insert badminton
//         */
//        verify(chessService,times(1)).lastTick(personId);
//    }
//
//
//    /**
//     * test date details function when there is no data available in database i.e zero size Set pass in function
//     * @throws SQLException
//     */
//    @Test
//    void searchByDateWithZeroSize() throws SQLException {
//        /**
//         * set condition for external usage of object
//         */
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
//
//        Set<Badminton> setDetails = new TreeSet<Badminton>();
//        when(chessService.dateDetails(personId,date)).thenReturn(setDetails);
//
//        /**
//         * call the function which you want to test
//         */
//        chessController.searchByDate(personId,date);
//
//        /**
//         * check the function return correct value or not
//         */
//        assertEquals(setDetails,chessService.dateDetails(personId,date));
//    }
//
//    /**
//     * test date details function when you pass some value set in parameter
//     * @throws SQLException
//     */
//    @Test
//    void searchByDateWithSize() throws SQLException {
//        /**
//         * set condition for external usage of object
//         */
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
//
//        Iterator<Chess> iterator = mock(Iterator.class);
//        Set<Chess> setDetails = new HashSet<Chess>();
//        setDetails.add(chess);
//
//        when(chessService.dateDetails(anyInt(),any())).thenReturn(setDetails);
//        when(iterator.hasNext()).thenReturn(true);
//        when(iterator.next()).thenReturn(setDetails.iterator().next());
//        when(iterator.next().getId()).thenReturn(setDetails.iterator().next().getId());
//
//        /**
//         * call the function which you want to test
//         */
//        chessController.searchByDate(personId,date);
//
//        /**
//         * check the function return correct value or not
//         */
//        assertEquals(setDetails,chessService.dateDetails(personId,date));
//    }
//}