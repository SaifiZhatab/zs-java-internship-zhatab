package com.zs.hobbies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void getPersonId() {
        assertEquals(personId,travelling.getPersonId());
    }

    @Test
    void setPersonId() {
        travelling.setPersonId(3);
        assertEquals(3,travelling.getPersonId());
    }

    @Test
    void getTime() {
        assertEquals(timing , travelling.getTime());
    }

    @Test
    void setTime() {
        Timing time = new Timing(Time.valueOf("04:05:09"),Time.valueOf("01:39:49"), Date.valueOf("2020-04-05"));
        travelling.setTime(time);

        assertEquals(time, travelling.getTime());
    }

    @Test
    void getStartPoint() {
        assertEquals(startPoint,travelling.getStartPoint());
    }

    @Test
    void setStartPoint() {
        travelling.setStartPoint("Lucknow");
        assertEquals("Lucknow",travelling.getStartPoint());
    }

    @Test
    void getEndPoint() {
        assertEquals(endPoint,travelling.getEndPoint());
    }

    @Test
    void setEndPoint() {
        travelling.setEndPoint("Agra");
        assertEquals("Agra",travelling.getEndPoint());
    }

    @Test
    void getDistance() {
        assertEquals(distance,travelling.getDistance());
    }

    @Test
    void setDistance() {
        travelling.setDistance(12f);
        assertEquals(12f,travelling.getDistance());
    }
}