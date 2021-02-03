package com.zs.hobbies.dao;

import com.zs.hobbies.dto.Person;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class is PersonDao test implementation
 */
class PersonDaoTest {

    private Connection connection = mock(Connection.class);
    private PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private PersonDao personDao;

    private int personId;
    private String name, address, mobile;
    private Person person;

    @BeforeEach
    void setUp() {
        personDao = new PersonDao(connection);

        personId = 1;
        name = "Zhatab";
        mobile = "8010311757";
        address = "UP";

        person = new Person(personId,name,mobile,address);
    }

    /**
     * test person dao insert object
     * @throws SQLException
     */
    @Test
    void insertWithNotNUllObject() throws SQLException {
        /**
         * when connection.prepareStatement(anyString()) instruction come, then it return preparedStatement
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        /**
         * call that method which you want to test
         */
        personDao.insert(person);

        /**
         * verify the connection.prepareStatement(anyString()) will execute only 1 time
         */
        verify(connection.prepareStatement(anyString()),times(1)).executeUpdate();
    }

    /**
     * when you try to insert null object in dao, then it return InvalidInputException
     */
    @Test
    void insertWithNullObject() {
        /**
         * check the insert(null) give InvalidInputException
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    personDao.insert(null);
                });
    }

    /**
     * when you try to insert object in dao, then it return ApplicationException
     */
    @Test
    void insertException() throws SQLException {
        /**
         * set the external object to mock object
         */
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenThrow(new SQLException());

        /**
         * check the insert(null) give InvalidInputException
         */
        assertThrows(ApplicationException.class,
                () -> {
                    personDao.insert(person);
                });
    }
}