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
import static org.mockito.Mockito.*;

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

    @Test
    void insertWithNotNUllObject() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenReturn(1);

        personDao.insert(person);

        verify(connection.prepareStatement(anyString()),times(1)).executeUpdate();
    }

    @Test
    void insertWithNullObject() {
        assertThrows(InvalidInputException.class,
                () -> {
                    personDao.insert(null);
                });
    }

    @Test
    void insertException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.prepareStatement(anyString()).executeUpdate()).thenThrow(new SQLException());

        assertThrows(ApplicationException.class,
                () -> {
                    personDao.insert(person);
                });
    }
}