package com.zs.hobbies.service;

import com.zs.hobbies.dao.BadmintonDao;
import com.zs.hobbies.util.DataBase;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.dto.Timing;
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
        badminton = new Badminton(5,person.getId(),time, 8,"win");
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