package test.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.dao.BadmintonDao;
import main.java.com.zs.hobbies.util.DataBase;
import main.java.com.zs.hobbies.dto.Badminton;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.Timing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

class BadmintonServiceImplTest {
    @Mock
    DataBase db;

    @Mock
    BadmintonDao badmintonDao;

    Person person = new Person(9,"Rihan","9311851147","Dadri");
    Timing time = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2020-04-05"));
    Badminton badminton ;

    @BeforeEach
    void setUp() throws SQLException, IOException, ClassNotFoundException {
        db = new DataBase();
        badmintonDao = new BadmintonDao(db.getCon());
        badminton = new Badminton(5,person,time, 8,"win");
    }

    @Test
    void insert() {

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