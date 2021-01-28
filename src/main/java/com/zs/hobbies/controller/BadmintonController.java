package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.service.BadmintonService;
import com.zs.hobbies.service.BadmintonServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;
import java.util.Scanner;
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

    public BadmintonController(Connection con,LruService lru) throws SQLException, IOException, ClassNotFoundException {
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
        badmintonService.longestStreak(personId);
    }

    /**
     * This function help you to find the latest streak in the badminton
     * @param personId  the person ID
     * @throws SQLException
     */
    public void latestStreak(int personId) throws SQLException {
        badmintonService.latestStreak(personId);
    }

    /**
     * This function help you to find the last streak in the badminton
     * @param personId  the person ID
     * @throws SQLException
     */
    public void lastTick(int personId) throws SQLException {
        badmintonService.lastTick(personId);
    }

    /**
     * This function help you to find the details according to details in the badminton
     * @param personId the person id
     * @param date the date
     * @throws SQLException
     */
    public void searchByDate(int personId, Date date) throws SQLException {
        badmintonService.dateDetails(personId,date);
    }
}
