package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.Travelling;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.service.TravellingService;
import com.zs.hobbies.service.TravellingServiceImpl;

import java.sql.Connection;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This is a travelling Controller class
 * that call the travelling service call and using service interact with database
 */
public class TravellingController {
    private Person person;
    private TravellingService travellingService;
    private Logger logger;

    Scanner in = new Scanner(System.in);

    public TravellingController(Connection con, Cache lru) {
        travellingService = new TravellingServiceImpl(con,lru);

       logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This funciton help you to insert the travelling object in database
     * @param travelling
     */
    public void insert(Travelling travelling){
        travellingService.insert(travelling);
    }

    /**
     * This function help you to find the longest streak in the chess
     * @param personId  the person ID
     */
    public void longestStreak(int personId) {
        int longestStreak = travellingService.longestStreak(personId);
        logger.info("Travelling longest streak : " + longestStreak);
    }

    /**
     * This function help you to find the latest streak in the chess
     * @param personId  the person ID
     */
    public void latestStreak(int personId) {
        int latestStreak = travellingService.latestStreak(personId);
        logger.info("Travelling latest streak : " + latestStreak);
    }

    /**
     * This function help you to find the last streak in the chess
     * @param personId  the person ID
     */
    public void lastTick(int personId) {
        Travelling travelling = (Travelling) travellingService.lastTick(personId);
        if(travelling != null) {
            logger.info("Travelling last tick id : " + travelling.getId());
        }else {
            logger.warning("No last tick present");
        }
    }

    /**
     * This function help you to find the details according to details in the travelling
     * @param personId the person id
     * @param date the date
     */
    public void searchByDate(int personId, Date date) {
        Set<Travelling> setDetails = travellingService.dateDetails(personId,date);

        Iterator<Travelling> iterator = setDetails.iterator();

        while(iterator.hasNext()) {
            logger.info("Travelling id : " + iterator.next().getId());
        }

    }

}