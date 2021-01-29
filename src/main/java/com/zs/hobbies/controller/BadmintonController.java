package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.service.BadmintonService;
import com.zs.hobbies.service.BadmintonServiceImpl;

import java.sql.Connection;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This is a Badminton Controller class
 * that call the badminton service call and using service interact with database
 */
public class BadmintonController {
    private Logger logger;
    BadmintonService badmintonService;
    Scanner in = new Scanner(System.in);

    public BadmintonController(Connection con, Cache lru) {
        badmintonService = new BadmintonServiceImpl(con,lru);
        logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This funciton help you to insert the badminton object in database
     */
    public void insert(Badminton badminton) {
        badmintonService.insert(badminton);
    }

    /**
     * This function help you to find the longest streak in the badminton
     * @param personId  the person ID
     */
    public void longestStreak(int personId) {
        int longestStreak = badmintonService.longestStreak(personId);
        logger.info("Badminton longest streak : " + longestStreak);
    }

    /**
     * This function help you to find the latest streak in the badminton
     * @param personId  the person ID
     */
    public void latestStreak(int personId) {
        int latestStreak = badmintonService.latestStreak(personId);
        logger.info("Badminton latest streak : " + latestStreak);
    }

    /**
     * This function help you to find the last streak in the badminton
     * @param personId  the person ID
     */
    public void lastTick(int personId) {
        Badminton badminton = (Badminton) badmintonService.lastTick(personId);
        if(badminton != null) {
            logger.info("Badminton last tick id : " + badminton.getId());
        }else {
            logger.warning("Not present last tick");
        }
    }

    /**
     * This function help you to find the details according to details in the badminton
     * @param personId the person id
     * @param date the date
     */
    public void searchByDate(int personId, Date date) {
        Set<Badminton> setDetails = badmintonService.dateDetails(personId,date);

        if(setDetails == null) {
            logger.warning("No details present at this date");
            return;
        }

        Iterator<Badminton> iterator = setDetails.iterator();
        while(iterator.hasNext()) {
            logger.info("Badminton id : " + iterator.next().getId());
        }
    }
}
