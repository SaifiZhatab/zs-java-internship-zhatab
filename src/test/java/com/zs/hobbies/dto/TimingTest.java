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
    Time start;
    Time end;
    Date day;
    Timing timing;

    @BeforeEach
    void setUp(){
        start = Time.valueOf("10:59:59");
        end = Time.valueOf("12:59:59");
        day = Date.valueOf("2015-04-05");

        timing = new Timing(start,end,day);
    }

    @Test
    void getStartTime() {
        assertEquals(start,timing.getStartTime());
    }

    @Test
    void setStartTime() {
    }

    @Test
    void getEndTime() {
        assertEquals(end,timing.getEndTime());
    }

    @Test
    void setEndTime() {

    }

    @Test
    void getDay() {
        assertEquals(day,timing.getDay());
    }

    @Test
    void setDay() {
    }
}
