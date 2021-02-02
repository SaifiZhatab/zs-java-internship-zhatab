//package com.zs.hobbies.controller;
//
//import com.zs.hobbies.cache.Cache;
//import com.zs.hobbies.dto.Person;
//import com.zs.hobbies.service.PersonService;
//import com.zs.hobbies.validator.Validator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.times;
//
///**
// * This class is person Controller testing implementation using mockito
// */
//class PersonControllerTest {
//
//    /**
//     * create mock object for external usage object in Badminton controller
//     */
//    private Cache lru = mock(Cache.class);
//    private Connection connection = mock(Connection.class);
//    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
//    private Validator validator = mock(Validator.class);
//    private ResultSet resultSet = mock(ResultSet.class);
//    private PersonService personService  = mock(PersonService.class);
//
//    private PersonController personController;
//
//    /**
//     * create person object
//     */
//    private int personId;
//    private String name, address, mobile;
//    private Person person;
//
//    @BeforeEach
//    void setUp() {
//        personController = new PersonController(connection,lru);
//
//        personId = 1;
//        name = "Zhatab";
//        mobile = "8010311757";
//        address = "UP";
//        person = new Person(personId,name,mobile,address);
//    }
//
//    /**
//     * test insert function of person controller class
//     * @throws SQLException
//     */
//    @Test
//    void insert() throws SQLException {
//        /**
//         * set condition for  external usage of object
//         */
//        when(validator.validPerson(person)).thenReturn(true);
//        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
//        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);
//
//        /**
//         * call the function which you want to test
//         */
//        personController.insert(person);
//
//        /**
//         * verify the connection.prepareStatement(anyString().executeUpdate() execute 1 time when you insert badminton
//         */
//        verify(connection.prepareStatement(anyString()),times(1)).executeUpdate();
//    }
//}