package test.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.dao.BadmintonDataBase;
import main.java.com.zs.hobbies.dao.DataBase;
import main.java.com.zs.hobbies.dto.Badminton;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.Timing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadmintonServiceImplTest {
    DataBase dataBase;
    BadmintonDataBase badmintonDataBase;
    Person person;
    Timing timing;

    @BeforeEach
    void setUp() throws SQLException, IOException, ClassNotFoundException {
        dataBase = new DataBase();
        badmintonDataBase = new BadmintonDataBase();

        person = new Person(9,"Aftab","8235456789","MP");
        timing = new Timing(Time.valueOf("11:59:59"),Time.valueOf("11:59:59"), Date.valueOf("2015-04-25"));
    }

    @Test
    void insertBadminton() throws SQLException {
        Badminton badminton = new Badminton(person,timing,2,"Win");
        assertEquals(1,badmintonDataBase.insertBadminton(badminton));
    }

    @Test
    void dateDetails() {
    }

    @Test
    void lastTick() {
    }

    @Test
    void longestStreak() {
    }

    @Test
    void latestStreak() {
    }
}