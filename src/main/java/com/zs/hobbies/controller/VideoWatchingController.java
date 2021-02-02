package com.zs.hobbies.controller;


import com.zs.hobbies.Application;
import com.zs.hobbies.dto.VideoWatching;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.service.VideoWatchingService;
import com.zs.hobbies.service.VideoWatchingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

@RestController
public class VideoWatchingController {
    private Logger logger;
    private VideoWatchingService videoWatchingService;

    public VideoWatchingController() {
        logger = Logger.getLogger(Application.class.getName());
        videoWatchingService = new VideoWatchingServiceImpl();
    }

    /**
     * This function help you to insert the badminton object in database
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/VideoWatching/insert")
    public void insert(VideoWatching videoWatching) {
        videoWatchingService.insert(videoWatching);
    }

    /**
     * This function help you to find the longest streak in the video watching
     * @param personId  the person ID
     */
    @GetMapping("/VideoWatching/longestStreak/{personId}")
    public String longestStreak(int personId) {
        try{
            int longestStreak = videoWatchingService.longestStreak(personId);
            return "This is the longest video watching streak for " + personId + " : " + longestStreak;
        }catch (InvalidInputException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }catch (ApplicationException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }
    }

    /**
     * This function help you to find the latest streak in the video watching
     * @param personId  the person ID
     */
    @GetMapping("/VideoWatching/latestStreak/{personId}")
    public String latestStreak(int personId) {
        try{
            int latestStreak = videoWatchingService.latestStreak(personId);
            return "This is the latest video watching streak for " + personId + " : " + latestStreak;
        }catch (InvalidInputException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }catch (ApplicationException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }
    }

    /**
     * This function help you to find the last streak in the video watching
     * @param personId  the person ID
     */
    @GetMapping("/VideoWatching/lastTick/{personId}")
    public String lastTick(int personId) {
        try{
            Integer lastTick = (Integer) videoWatchingService.lastTick(personId);
            if(lastTick == null) {
                lastTick = 0;
            }
            return "This is the last video watching tick for " + personId + " : " + lastTick;
        }catch (InvalidInputException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }catch (ApplicationException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }
    }

    /**
     * This function help you to find the details according to details in the Video watching
     * @param personId the person id
     * @param date the date
     */
    public void searchByDate(int personId, Date date) {
        Set<VideoWatching> setDetails = videoWatchingService.dateDetails(personId,date);

        if(setDetails.size() == 0) {
            logger.warning("No details present at this date");
            return;
        }

        Iterator<VideoWatching> iterator = setDetails.iterator();
        while(iterator.hasNext()) {
            logger.info("Video watching id : " + iterator.next().getId());
        }
    }
}
