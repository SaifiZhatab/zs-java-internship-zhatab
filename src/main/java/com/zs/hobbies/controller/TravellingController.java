package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.Travelling;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.service.TravellingService;
import com.zs.hobbies.service.TravellingServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
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

    public TravellingController(Connection con, LruService lru) throws SQLException, IOException, ClassNotFoundException {
        travellingService = new TravellingServiceImpl(con,lru);

       logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This funciton help you to insert the travelling object in database
     * @throws ParseException
     * @throws SQLException
     * @param travelling
     */
    public void insert(Travelling travelling) throws SQLException {
        travellingService.insert(travelling);
    }

    /**
     * This function help you to find the longest streak in the chess
     * @param personId  the person ID
     * @throws SQLException
     */
    public void longestStreak(int personId) throws SQLException {
        travellingService.longestStreak(personId);
    }

    /**
     * This function help you to find the latest streak in the chess
     * @param personId  the person ID
     * @throws SQLException
     */
    public void latestStreak(int personId) throws SQLException {
        travellingService.latestStreak(personId);
    }

    /**
     * This function help you to find the last streak in the chess
     * @param personId  the person ID
     * @throws SQLException
     */
    public void lastTick(int personId) throws SQLException {
        travellingService.lastTick(personId);
    }

    /**
     * This function help you to find the details according to details in the travelling
     * @param personId the person id
     * @param date the date
     * @throws SQLException
     */
    public void searchByDate(int personId, Date date) throws SQLException {
        travellingService.dateDetails(personId,date);
    }

}