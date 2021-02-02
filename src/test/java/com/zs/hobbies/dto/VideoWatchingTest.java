package com.zs.hobbies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a testing class for video watching dto class
 */
class VideoWatchingTest {

    private int personId,videoWatchingId;
    private Timing timing;
    private String title;
    private VideoWatching videoWatching;

    @BeforeEach
    void setUp() {
        videoWatchingId = 2;
        personId = 9;
        timing = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2015-04-05"));
        title = "Lucifer morningstar";

        videoWatching = new VideoWatching(videoWatchingId,personId,timing,title);
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getPersonId() {
        assertEquals(personId , videoWatching.getPersonId());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setPersonId() {
        videoWatching.setPersonId(4);

        /**
         * check the expected or actual value is same
         */
        assertEquals(4 , videoWatching.getPersonId());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getTime() {
        assertEquals(timing, videoWatching.getTime());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setTime() {
        Timing time = new Timing(Time.valueOf("04:05:09"),Time.valueOf("01:39:49"), Date.valueOf("2020-04-05"));
        videoWatching.setTime(time);

        /**
         * check the expected or actual value is same
         */
        assertEquals(time, videoWatching.getTime());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getTitle() {
        assertEquals(title , videoWatching.getTitle());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setTitle() {
        videoWatching.setTitle("The boys");

        /**
         * check the expected or actual value is same
         */
        assertEquals("The boys" , videoWatching.getTitle());
    }
}