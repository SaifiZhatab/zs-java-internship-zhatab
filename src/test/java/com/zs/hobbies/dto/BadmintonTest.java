package com.zs.hobbies.dto;

import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.dto.Timing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a testing class for Badminton dto class
 */
class BadmintonTest {
    private Person person;
    private Timing timing;
    private int numPlayers;
    private String result;
    private Badminton badminton;

    @BeforeEach
    void setUp() throws SQLException {
        person = new Person(9,"Rahul","8235456789","MP");
        timing = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2015-04-05"));
        numPlayers = 2;
        result = "Win";

    }

    /**
     *  This function help you to check the person object available in badminton object
     */
    @Test
    void validPersonAvailable() throws SQLException {
        person = new Person(1,"zhatab" , "8024987656","UP");

        /**
         * insert person in badminton object
         */
        badminton = new Badminton(1,person.getId(),timing,numPlayers,result);

        /**
         * check the same object or data present in the badminton object
         */
        assertEquals(person.getId(),badminton.getPersonId());
    }

    @Test
    void setWhenAlreadyPersonPresent() throws SQLException {
        badminton = new Badminton(1,person.getId(),timing,numPlayers,result);

        /**
         * change the badminton person object and check it is successfully change or not
         */
        Person personChange = new Person(1,"zhatab" , "8024987656","UP");

        /**
         * change the person object in badminton
         */
        badminton.setPersonId(personChange.getId());

        /**
         * check the new object present or not in badminton object
         */
        assertEquals(personChange.getId(),badminton.getPersonId());
    }

    @Test
    void ValidTimeAvailable() throws SQLException {
        timing = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2015-04-05"));

        /**
         * insert person in badminton object
         */
        badminton = new Badminton(1,person.getId(),timing,numPlayers,result);

        /**
         * check the same object or data present in the badminton object
         */
        assertEquals(person.getId(),badminton.getPersonId());
    }

    @Test
    void setWhenAlreadyTimePresent() throws SQLException {
        badminton = new Badminton(1,person.getId(),timing,numPlayers,result);

        /**
         * change the badminton timing object and check it is successfully change or not
         */
        Timing changeTiming = new Timing(Time.valueOf("02:52:59"),Time.valueOf("12:59:59"), Date.valueOf("2020-07-25"));

        /**
         * change the person object in badminton
         */
        badminton.setTime(changeTiming);

        /**
         * check the new object present or not in badminton object
         */
        assertEquals(changeTiming,badminton.getTime());
    }

    @Test
    void getNumPlayers() {
        assertEquals(numPlayers,badminton.getNumPlayers());
    }

    @Test
    void setNumPlayers() {
    }

    @Test
    void getResult() {
        assertEquals(result,badminton.getResult());
    }

    @Test
    void setResult() {
    }
}