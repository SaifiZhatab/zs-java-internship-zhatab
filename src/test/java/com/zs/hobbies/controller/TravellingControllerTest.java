//package com.zs.hobbies.controller;
//
//import com.zs.hobbies.cache.Cache;
//import com.zs.hobbies.dto.Badminton;
//import com.zs.hobbies.dto.Timing;
//import com.zs.hobbies.dto.Travelling;
//import com.zs.hobbies.service.TravellingService;
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
// * This class is Travelling Controller testing implementation using mockito
// */
//class TravellingControllerTest {
//
//    /**
//     * create mock object for external usage object in Badminton controller
//     */
//    private Cache lru = mock(Cache.class);
//    private Connection connection = mock(Connection.class);
//    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
//    private Validator validator = mock(Validator.class);
//    private ResultSet resultSet = mock(ResultSet.class);
//    private TravellingService travellingService = mock(TravellingService.class);
//
//    private TravellingController travellingController;
//
//    /**
//     * create travelling object
//     */
//    private int travellingId,personId;
//    private Time startTime ,endTime;
//    private Date date;
//    private String startPoint , endPoint;
//    private Float distance;
//    private Travelling travelling;
//    private Timing timing;
//
//    @BeforeEach
//    void setUp() {
//        /**
//         * initialise travelling controller object with mock object
//         */
//        travellingController = new TravellingController(connection,lru);
//
//        travellingId = 1;
//        personId = 1;
//        startTime = Time.valueOf("10:45:31");
//        endTime = Time.valueOf("12:20:31");
//        date =  Date.valueOf("2021-01-01");
//        startPoint = "Up";
//        endPoint = "MP";
//        distance = 1.5f;
//
//        timing = new Timing(startTime,endTime,date);
//        travelling = new Travelling(travellingId,personId,timing,startPoint,endPoint,distance);
//
//    }
//
//    /**
//     * test insert function of travelling controller class
//     * @throws SQLException
//     */
//    @Test
//    void insert() throws SQLException {
//        /**
//         * set condition for  external usage of object
//         */
//        when(validator.validTravelling(travelling)).thenReturn(true);
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);
//
//        /**
//         * call the function which you want to test
//         */
//        travellingController.insert(travelling);
//
//        /**
//         * verify the connection.prepareStatement(anyString().executeUpdate() execute 1 time when you insert badminton
//         */
//        verify(connection.prepareStatement(anyString()),times(1)).executeUpdate();
//    }
//
//    /**
//     * test longestStreak function of travelling controller class
//     * @throws SQLException
//     */
//    @Test
//    void longestStreak() throws SQLException {
//        /**
//         * set condition for external usage of object
//         */
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
//        when(travellingService.longestStreak(anyInt())).thenReturn(3);
//
//        /**
//         * call the function which you want to test
//         */
//        travellingController.longestStreak(personId);
//
//        /**
//         * check the function return correct value or not
//         */
//        assertEquals(3,travellingService.longestStreak(personId));
//
//        /**
//         * verify the badmintonService.longestStreak() execute 1 time when you insert badminton
//         */
//        verify(travellingService,times(1)).longestStreak(personId);
//    }
//
//    /**
//     * test longestStreak function of travelling controller class
//     * @throws SQLException
//     */
//    @Test
//    void latestStreak() throws SQLException {
//        /**
//         * set condition for external usage of object
//         */
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
//        when(travellingService.latestStreak(anyInt())).thenReturn(3);
//
//        /**
//         * call the function which you want to test
//         */
//        travellingController.latestStreak(personId);
//
//        /**
//         * check the function return correct value or not
//         */
//        assertEquals(3,travellingService.latestStreak(personId));
//
//        /**
//         * verify the badmintonService.longestStreak() execute 1 time when you insert badminton
//         */
//        verify(travellingService,times(1)).latestStreak(personId);
//    }
//
//
//    /**
//     * test longestStreak function of travelling controller class
//     * @throws SQLException
//     */
//    @Test
//    void lastTick() throws SQLException {
//        /**
//         * set condition for external usage of object
//         */
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeQuery()).thenReturn(resultSet);
//        when(travellingService.lastTick(anyInt())).thenReturn(2);
//
//        /**
//         * call the function which you want to test
//         */
//        travellingController.lastTick(personId);
//
//        /**
//         * check the function return correct value or not
//         */
//        assertEquals(2,(int)travellingService.lastTick(personId));
//
//        /**
//         * verify the badmintonService.longestStreak() execute 1 time when you insert badminton
//         */
//        verify(travellingService,times(1)).lastTick(personId);
//    }
//
//
//    /**
//     * test date details function when there is no data available in database i.e zero size Set pass in fucntion
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
//        when(travellingService.dateDetails(personId,date)).thenReturn(setDetails);
//
//        /**
//         * call the function which you want to test
//         */
//        travellingController.searchByDate(personId,date);
//
//        /**
//         * check the function return correct value or not
//         */
//        assertEquals(setDetails,travellingService.dateDetails(personId,date));
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
//        Iterator<Travelling> iterator = mock(Iterator.class);
//        Set<Travelling> setDetails = new HashSet<Travelling>();
//        setDetails.add(travelling);
//
//        when(travellingService.dateDetails(anyInt(),any())).thenReturn(setDetails);
//        when(iterator.hasNext()).thenReturn(true);
//        when(iterator.next()).thenReturn(setDetails.iterator().next());
//        when(iterator.next().getId()).thenReturn(setDetails.iterator().next().getId());
//
//        /**
//         * call the function which you want to test
//         */
//        travellingController.searchByDate(personId,date);
//
//        /**
//         * check the function return correct value or not
//         */
//        assertEquals(setDetails,travellingService.dateDetails(personId,date));
//    }
//}