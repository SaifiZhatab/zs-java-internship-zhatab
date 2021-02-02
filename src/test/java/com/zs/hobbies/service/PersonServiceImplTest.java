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
 * This class is badminton service testing implementation
 */
class PersonServiceImplTest {

    /**
     * create mock object for external usage object in chess service
     */
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
        /**
         * initialise service service object with mock object
         */
        personService = new PersonServiceImpl();

        personId = 1;
        name = "Zhatab";
        mobile = "8010311757";
        address = "UP";
        person = new Person(personId,name,mobile,address);
    }

    /**
     * insert function testing
     * @throws SQLException
     */
    @Test
    void insert() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(validator.validPerson(person)).thenReturn(true);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        when(lru.get(anyString())).thenReturn(1);

        personService.insert(person);
    }
}