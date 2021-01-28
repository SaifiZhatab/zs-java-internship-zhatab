package com.zs.hobbies.dao;

import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.util.DataBase;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class BadmintonDaoTest {


    DataBase dataBase = mock(DataBase.class);
    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock (PreparedStatement.class);
    BadmintonDao badmintonDao = mock(BadmintonDao.class);

    private int badmintonId,personId;
    Time startTime ,endTime;
    Date date;
    int numOfPlayer ;
    String result;
    Badminton badminton;
    Timing timing;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);

        badmintonDao = new BadmintonDao(connection);

        assertNotNull(badmintonDao);

        badmintonId = 1;
        personId = 1;
        startTime = Time.valueOf("10:45:31");
        endTime = Time.valueOf("12:20:31");
        date =  Date.valueOf("2021-01-01");
        numOfPlayer = 4;
        result = "Win";

        timing = new Timing(startTime,endTime,date);

        badminton = new Badminton(badmintonId,personId,timing,numOfPlayer,result);
    }

    /**
     * insert the object when data is not present in database
     */
    @Test
    void insertBadmintonWhenDataNotPresent() throws SQLException {
        assertNotNull(badmintonDao);
        badmintonDao.insertBadminton(badminton);

      //  verify(badmintonDao,times(1));
    }

    @Test
    void checkBadmintonDetails() {
        assertEquals(badmintonId,badminton.getId());
        assertEquals(personId,badminton.getPersonId());

     //   verify(badmintonDao,times(1)).insertBadminton(badminton);
    }
    @Test
    void longestBadmintonStreak() {

    }

    @Test
    void dateBadmintonDetails() {
    }

    @Test
    void lastTick() {
    }
}