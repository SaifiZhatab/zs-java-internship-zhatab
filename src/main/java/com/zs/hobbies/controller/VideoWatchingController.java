package com.zs.hobbies.controller;


import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.VideoWatching;
import com.zs.hobbies.service.VideoWatchingService;
import com.zs.hobbies.service.VideoWatchingServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Logger;

public class VideoWatchingController {
    private Person person;
    private Timing timing;
    private Logger logger;

    private VideoWatchingService videoWatchingService;

    public VideoWatchingController(Connection con, LruService lru) throws SQLException, IOException, ClassNotFoundException {
        videoWatchingService = new VideoWatchingServiceImpl(con,lru);
        logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This funciton help you to insert the badminton object in database
     * @throws ParseException
     * @throws SQLException
     */
    public void insert(VideoWatching videoWatching) throws ParseException, SQLException {
        videoWatchingService.insert(videoWatching);
    }

    /**
     * This function help you to find the longest streak in the video watching
     * @param personId  the person ID
     * @throws SQLException
     */
    public void longestStreak(int personId) throws SQLException {
        videoWatchingService.longestStreak(personId);
    }

    /**
     * This function help you to find the latest streak in the video watching
     * @param personId  the person ID
     * @throws SQLException
     */
    public void latestStreak(int personId) throws SQLException {
        videoWatchingService.latestStreak(personId);
    }

    /**
     * This function help you to find the last streak in the video watching
     * @param personId  the person ID
     * @throws SQLException
     */
    public void lastTick(int personId) throws SQLException {
        videoWatchingService.lastTick(personId);
    }

    /**
     * This function help you to find the details according to details in the Video watching
     * @param personId the person id
     * @param date the date
     * @throws SQLException
     */
    public void searchByDate(int personId, Date date) throws SQLException {
        videoWatchingService.dateDetails(personId,date);
    }
}
