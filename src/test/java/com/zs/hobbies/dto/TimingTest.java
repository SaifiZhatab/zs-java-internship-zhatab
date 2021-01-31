package com.zs.hobbies.dto;

import com.zs.hobbies.dto.Timing;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void getStartTime() {
        assertEquals(startTime,timing.getStartTime());
    }

    @Test
    void setStartTime() {
        Time start = Time.valueOf("14:05:09");
        timing.setStartTime(start);
        assertEquals(start,timing.getStartTime());
    }

    @Test
    void getEndTime() {
        assertEquals(endTime,timing.getEndTime());
    }

    @Test
    void setEndTime() {
        Time end = Time.valueOf("14:05:09");
        timing.setEndTime(end);
        assertEquals(end,timing.getEndTime());
    }

    @Test
    void getDay() {
        assertEquals(day,timing.getDay());
    }

    @Test
    void setDay() {
        Date changeDate = Date.valueOf("2018-02-15");
        timing.setDay(changeDate);
        assertEquals(changeDate,timing.getDay());
    }
}
