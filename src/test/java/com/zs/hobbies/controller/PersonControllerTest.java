package com.zs.hobbies.controller;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.service.ChessService;
import com.zs.hobbies.service.PersonService;
import com.zs.hobbies.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PersonControllerTest {

    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private Validator validator = mock(Validator.class);
    private ResultSet resultSet = mock(ResultSet.class);
    private PersonService personService  = mock(PersonService.class);

    private PersonController personController;

    private int personId;
    private String name, address, mobile;
    private Person person;

    @BeforeEach
    void setUp() {
        personController = new PersonController(connection,lru);

        personId = 1;
        name = "Zhatab";
        mobile = "8010311757";
        address = "UP";
        person = new Person(personId,name,mobile,address);
    }

    @Test
    void insert() throws SQLException {
        when(validator.validPerson(person)).thenReturn(true);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        personController.insert(person);
        verify(connection.prepareStatement(anyString()),times(1)).executeUpdate();
    }
}