package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.service.BadmintonService;
import com.zs.hobbies.service.BadmintonServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
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
    private Person person;
    private Logger logger;


    BadmintonService badmintonService;
    Scanner in = new Scanner(System.in);

    public BadmintonController(Connection con, Cache lru) throws SQLException, IOException, ClassNotFoundException {
        badmintonService = new BadmintonServiceImpl(con,lru);
        logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This funciton help you to insert the badminton object in database
     * @throws ParseException
     * @throws SQLException
     */
    public void insert(Badminton badminton) throws ParseException, SQLException {
        badmintonService.insert(badminton);
    }

    /**
     * This function help you to find the longest streak in the badminton
     * @param personId  the person ID
     * @throws SQLException
     */
    public void longestStreak(int personId) throws SQLException {
        int longestStreak = badmintonService.longestStreak(personId);
        logger.info("Badminton longest streak : " + longestStreak);
    }

    /**
     * This function help you to find the latest streak in the badminton
     * @param personId  the person ID
     * @throws SQLException
     */
    public void latestStreak(int personId) throws SQLException {
        int latestStreak = badmintonService.latestStreak(personId);
        logger.info("Badminton latest streak : " + latestStreak);
    }

    /**
     * This function help you to find the last streak in the badminton
     * @param personId  the person ID
     * @throws SQLException
     */
    public void lastTick(int personId) throws SQLException {
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
     * @throws SQLException
     */
    public void searchByDate(int personId, Date date) throws SQLException {
        Set<Badminton> setDetails = badmintonService.dateDetails(personId,date);

        Iterator<Badminton> iterator = setDetails.iterator();

        while(iterator.hasNext()) {
            logger.info("Badminton id : " + iterator.next().getId());
        }
    }
}
