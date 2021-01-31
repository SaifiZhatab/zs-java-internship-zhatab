package com.zs.hobbies.service;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
class PersonServiceImplTest {

    private Cache lru = mock(Cache.class);
    private Connection connection = mock(Connection.class);
    private Validator validator = mock(Validator.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);

    private PersonService personService;
    private int personId;
    private String name, address, mobile;
    private Person person;

    @BeforeEach
    void setUp() {

        personService = new PersonServiceImpl(connection,lru);

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

        when(lru.get(anyString())).thenReturn(1);

        personService.insert(person);
    }
}