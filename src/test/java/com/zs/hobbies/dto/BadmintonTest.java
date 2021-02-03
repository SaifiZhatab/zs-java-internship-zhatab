package com.zs.hobbies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a testing class for Badminton dto class
 */
class BadmintonTest {

    private int badmintonId, personId;
    private Timing timing;
    private int numPlayers;
    private String result;
    private Badminton badminton;

    @BeforeEach
    void setUp() throws SQLException {
        personId = 9;
        timing = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2015-04-05"));
        numPlayers = 2;
        result = "Win";

        badminton = new Badminton(badmintonId,personId,timing,numPlayers,result);
    }


    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getNumPlayers() {
        assertEquals(numPlayers,badminton.getNumPlayers());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setNumPlayers() {
        badminton.setNumPlayers(5);

        /**
         * check the expected or actual value is same
         */
        assertEquals(5,badminton.getNumPlayers());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getResult() {
        assertEquals(result,badminton.getResult());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setResult() {
        badminton.setResult("draw");
        /**
         * check the expected or actual value is same
         */
        assertEquals("draw",badminton.getResult());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getPersonId() {
        assertEquals(personId,badminton.getPersonId());
    }


    /**
     * check this function is set the correct value or not
     */
    @Test
    void setPersonId() {
        badminton.setPersonId(3);
        /**
         * check the expected or actual value is same
         */
        assertEquals(3,badminton.getPersonId());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getTime() {
        assertEquals(timing,badminton.getTime());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setTime() {
        Timing time = new Timing(Time.valueOf("04:05:09"),Time.valueOf("01:39:49"), Date.valueOf("2020-04-05"));
        badminton.setTime(time);

        /**
         * check the expected or actual value is same
         */
        assertEquals(time,badminton.getTime());
    }
}