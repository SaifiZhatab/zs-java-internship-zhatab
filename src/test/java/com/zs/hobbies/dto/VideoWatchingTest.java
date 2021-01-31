package com.zs.hobbies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void getPersonId() {
        assertEquals(personId , videoWatching.getPersonId());
    }

    @Test
    void setPersonId() {
        videoWatching.setPersonId(4);
        assertEquals(4 , videoWatching.getPersonId());
    }

    @Test
    void getTime() {
        assertEquals(timing, videoWatching.getTime());
    }

    @Test
    void setTime() {
        Timing time = new Timing(Time.valueOf("04:05:09"),Time.valueOf("01:39:49"), Date.valueOf("2020-04-05"));
        videoWatching.setTime(time);
        assertEquals(time, videoWatching.getTime());
    }

    @Test
    void getTitle() {
        assertEquals(title , videoWatching.getTitle());
    }

    @Test
    void setTitle() {
        videoWatching.setTitle("The boys");
        assertEquals("The boys" , videoWatching.getTitle());
    }
}