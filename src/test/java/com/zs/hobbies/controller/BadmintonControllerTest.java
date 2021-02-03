//package com.zs.hobbies.controller;
//
//import com.zs.hobbies.cache.Cache;
//import com.zs.hobbies.dto.Badminton;
//import com.zs.hobbies.dto.Timing;
//import com.zs.hobbies.service.BadmintonServiceImpl;
//import com.zs.hobbies.validator.Validator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
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
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
///**
// * This class is badminton Controller testing implementation using mockito
// */
//class BadmintonControllerTest {
//
//    /**
//     * create mock object for external usage object in Badminton controller
//     */
//    private Cache lru = mock(Cache.class);
//    private Connection connection = mock(Connection.class);
//    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
//    private Validator validator = mock(Validator.class);
//    private ResultSet resultSet = mock(ResultSet.class);
//    //private badmintonServiceImplImpl badmintonServiceImpl = mock(badmintonServiceImplImpl.class);
//
//    private BadmintonController badmintonController;
//
//    BadmintonServiceImpl badmintonServiceImpl = mock(BadmintonServiceImpl.class);
//
//    /*
//     * create badminton object
//     */
//    private int badmintonId,personId;
//    private Time startTime ,endTime;
//    private Date date;
//    private int numOfPlayer ;
//    private String result;
//    private Badminton badminton;
//    private Timing timing;
//
//    @BeforeEach
//    void setUp() {
//        /**
//         * initialise badminton controller object with mock object
//         */
//        badmintonController = new BadmintonController();
//
//        badmintonId = 1;
//        personId = 1;
//        startTime = Time.valueOf("10:45:31");
//        endTime = Time.valueOf("12:20:31");
//        date =  Date.valueOf("2021-01-01");
//        numOfPlayer = 4;
//        result = "Win";
//
//        timing = new Timing(startTime,endTime,date);
//        badminton = new Badminton(badmintonId,personId,timing,numOfPlayer,result);
//
//    }
//
//    /**
//     * test insert function of badminton controller class
//     * @throws SQLException
//     */
//    @Test
//    void insert() throws SQLException {
//        /**
//         * set condition for  external usage of object
//         */
////        when(validator.validBadminton(badminton)).thenReturn(true);
////        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
////        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);
//
//        doNothing().when(badmintonServiceImpl).insert(badminton);
//        /**
//         * call the function which you want to test
//         */
//       // badmintonController.insert(badminton);
//
//        /**
//         * verify the connection.prepareStatement(anyString().executeUpdate() execute 1 time when you insert badminton
//         */
//        verify(badmintonServiceImpl,times(1)).insert(badminton);
//    }
//
////    /**
////     * test longestStreak function of badminton controller class
////     * @throws SQLException
////     */
////    @Test
////    void longestStreak() throws SQLException {
////        /**
////         * set condition for external usage of object
////         */
////        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
////        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
////        //when(badmintonServiceImpl.longestStreak(anyInt())).thenReturn(3);
////
////        /**
////         * call the function which you want to test
////         */
////        badmintonController.longestStreak(personId);
////
////        /**
////         * check the function return correct value or not
////         */
////        //assertEquals(3,badmintonServiceImpl.longestStreak(personId));
////
////        /**
////         * verify the badmintonServiceImpl.longestStreak() execute 1 time when you insert badminton
////         */
////        verify(badmintonServiceImpl,times(1)).longestStreak(personId);
////    }
////
////    /**
////     * test longestStreak function of badminton controller class
////     * @throws SQLException
////     */
////    @Test
////    void latestStreak() throws SQLException {
////        /**
////         * set condition for external usage of object
////         */
////        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
////        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
////        when(badmintonServiceImpl.latestStreak(anyInt())).thenReturn(3);
////
////        /**
////         * call the function which you want to test
////         */
////        badmintonController.latestStreak(personId);
////
////        /**
////         * check the function return correct value or not
////         */
////        assertEquals(3,badmintonServiceImpl.latestStreak(personId));
////
////        /**
////         * verify the badmintonServiceImpl.longestStreak() execute 1 time when you insert badminton
////         */
////        verify(badmintonServiceImpl,times(1)).latestStreak(personId);
////    }
////
////
////    /**
////     * test longestStreak function of badminton controller class
////     * @throws SQLException
////     */
////    @Test
////    void lastTick() throws SQLException {
////        /**
////         * set condition for external usage of object
////         */
////        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
////        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
////        when(badmintonServiceImpl.lastTick(anyInt())).thenReturn(2);
////
////        /**
////         * call the function which you want to test
////         */
////        badmintonController.lastTick(personId);
////
////        /**
////         * check the function return correct value or not
////         */
////        assertEquals(2,badmintonServiceImpl.lastTick(personId));
////
////        /**
////         * verify the badmintonServiceImpl.longestStreak() execute 1 time when you insert badminton
////         */
////        verify(badmintonServiceImpl,times(1)).lastTick(personId);
////    }
////
////
////    /**
////     * test date details function when there is no data available in database i.e zero size Set pass in fucntion
////     * @throws SQLException
////     */
////    @Test
////    void searchByDateWithZeroSize() throws SQLException {
////        /**
////         * set condition for external usage of object
////         */
////        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
////        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
////
////        Set<Badminton> setDetails = new TreeSet<Badminton>();
////        when(badmintonServiceImpl.dateDetails(personId,date)).thenReturn(setDetails);
////
////        /**
////         * call the function which you want to test
////         */
////        badmintonController.searchByDate(personId,date);
////
////        /**
////         * check the function return correct value or not
////         */
////        assertEquals(setDetails,badmintonServiceImpl.dateDetails(personId,date));
////    }
////
////    /**
////     * test date details function when you pass some value set in parameter
////     * @throws SQLException
////     */
////    @Test
////    void searchByDateWithSize() throws SQLException {
////        /**
////         * set condition for external usage of object
////         */
////        Set<Badminton> setDetails = new HashSet<Badminton>();
////        setDetails.add(badminton);
////
////        when(badmintonServiceImpl.dateDetails(personId,date)).thenReturn(setDetails);
////        /**
////         * call the function which you want to test
////         */
////        badmintonController.searchByDate(personId,date);
////
////        /**
////         * check the function return correct value or not
////         */
////        assertEquals(setDetails,badmintonServiceImpl.dateDetails(personId,date));
////    }
//}