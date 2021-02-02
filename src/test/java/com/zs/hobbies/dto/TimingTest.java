package com.zs.hobbies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a testing class for Timing dto class
 */
class TimingTest {

    private Time startTime;
    private Time endTime;
    private Date day;
    private Timing timing;

    @BeforeEach
    void setUp(){
        startTime = Time.valueOf("04:05:09");
        endTime = Time.valueOf("01:39:49");
        day = Date.valueOf("2020-04-05");

        timing = new Timing(startTime,endTime,day);
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getStartTime() {
        assertEquals(startTime,timing.getStartTime());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setStartTime() {
        Time start = Time.valueOf("14:05:09");
        timing.setStartTime(start);

        /**
         * check the expected or actual value is same
         */
        assertEquals(start,timing.getStartTime());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getEndTime() {
        assertEquals(endTime,timing.getEndTime());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setEndTime() {
        Time end = Time.valueOf("14:05:09");
        timing.setEndTime(end);

        /**
         * check the expected or actual value is same
         */
        assertEquals(end,timing.getEndTime());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getDay() {
        assertEquals(day,timing.getDay());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setDay() {
        Date changeDate = Date.valueOf("2018-02-15");
        timing.setDay(changeDate);

        /**
         * check the expected or actual value is same
         */
        assertEquals(changeDate,timing.getDay());
    }
}
