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
    private TimingController timingController;
    private Logger logger;

    Scanner in = new Scanner(System.in);

    public TravellingController(Connection con, LruService lru) throws SQLException, IOException, ClassNotFoundException {
        travellingService = new TravellingServiceImpl(con,lru);
        timingController = new TimingController();

       logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This function check the given position is valid or not
     * return true if the length of the position string is greater than 0 and less than 500
     * else return false
     * @param position  the position string
     * @return  true/false
     */
    boolean checkPosition(String position) {
        if(position.length() >0 && position.length() < 500) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * This funciton help you to insert the travelling object in database
     * @throws ParseException
     * @throws SQLException
     */
    public void insert() throws ParseException {
        Timing timing;
        Travelling travelling;

        timing = timingController.getTime();

        String startPoint,endPoint;
        float distance;

        logger.info("Enter the starting position ");
        startPoint = in.next();

        if(!checkPosition(startPoint)) {
            throw new InvalidInputException(400,"wrong start position given by user");
        }

        logger.info("Enter the end position ");
        endPoint = in.next();

        if(!checkPosition(endPoint)) {
            throw new InvalidInputException(400,"wrong start position given by user");
        }

        logger.info("Enter the total travelling distance ");
        distance = in.nextFloat();

        if(distance <= 0.0f) {
            throw new InvalidInputException(400,"Wrong distance given by user ");
        }

    }
}
