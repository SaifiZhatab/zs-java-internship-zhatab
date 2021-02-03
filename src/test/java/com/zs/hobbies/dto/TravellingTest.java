package com.zs.hobbies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a testing class for travelling dto class
 */
class TravellingTest {

    private int personId,travellingId;
    private Timing timing;
    private String startPoint;
    private String endPoint;
    private float distance;

    private Travelling travelling;

    @BeforeEach
    void setUp() {
        travellingId = 3;
        personId = 9;
        timing = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2015-04-05"));
        startPoint = "Delhi";
        endPoint = "Bangalore";
        distance = 2400f;

        travelling = new Travelling(travellingId,personId,timing,startPoint,endPoint,distance);
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getPersonId() {
        assertEquals(personId,travelling.getPersonId());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setPersonId() {
        travelling.setPersonId(3);

        /**
         * check the expected or actual value is same
         */
        assertEquals(3,travelling.getPersonId());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getTime() {
        assertEquals(timing , travelling.getTime());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setTime() {
        Timing time = new Timing(Time.valueOf("04:05:09"),Time.valueOf("01:39:49"), Date.valueOf("2020-04-05"));
        travelling.setTime(time);

        /**
         * check the expected or actual value is same
         */
        assertEquals(time, travelling.getTime());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getStartPoint() {
        assertEquals(startPoint,travelling.getStartPoint());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setStartPoint() {
        travelling.setStartPoint("Lucknow");

        /**
         * check the expected or actual value is same
         */
        assertEquals("Lucknow",travelling.getStartPoint());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getEndPoint() {
        assertEquals(endPoint,travelling.getEndPoint());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setEndPoint() {
        travelling.setEndPoint("Agra");

        /**
         * check the expected or actual value is same
         */
        assertEquals("Agra",travelling.getEndPoint());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getDistance() {
        assertEquals(distance,travelling.getDistance());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setDistance() {
        travelling.setDistance(12f);

        /**
         * check the expected or actual value is same
         */
        assertEquals(12f,travelling.getDistance());
    }
}