package com.zs.hobbies.controller;


import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.VideoWatching;
import com.zs.hobbies.service.VideoWatchingService;
import com.zs.hobbies.service.VideoWatchingServiceImpl;

import java.sql.Connection;
import java.sql.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class VideoWatchingController {
    private Logger logger;

    private VideoWatchingService videoWatchingService;

    public VideoWatchingController(Connection con, Cache lru) {
        videoWatchingService = new VideoWatchingServiceImpl(con,lru);
        logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This function help you to insert the badminton object in database
     */
    public void insert(VideoWatching videoWatching) {
        videoWatchingService.insert(videoWatching);
    }

    /**
     * This function help you to find the longest streak in the video watching
     * @param personId  the person ID
     */
    public void longestStreak(int personId) {
        int longestStreak = videoWatchingService.longestStreak(personId);
        logger.info("Video latest streak : " + longestStreak);
    }

    /**
     * This function help you to find the latest streak in the video watching
     * @param personId  the person ID
     */
    public void latestStreak(int personId) {
        int latestStreak = videoWatchingService.latestStreak(personId);

        logger.info("Video latest streak : " + latestStreak);
    }

    /**
     * This function help you to find the last streak in the video watching
     * @param personId  the person ID
     */
    public void lastTick(int personId) {
        Integer videoWatchingId = (Integer) videoWatchingService.lastTick(personId);
        if(videoWatchingId != null) {
            logger.info("Last tick video id : " + videoWatchingId);
        }
    }

    /**
     * This function help you to find the details according to details in the Video watching
     * @param personId the person id
     * @param date the date
     */
    public void searchByDate(int personId, Date date) {
        Set<VideoWatching> setDetails = videoWatchingService.dateDetails(personId,date);

        if(setDetails == null) {
            logger.warning("No details present at this date");
            return;
        }

        Iterator<VideoWatching> iterator = setDetails.iterator();
        while(iterator.hasNext()) {
            logger.info("Video watching id : " + iterator.next().getId());
        }
    }
}
