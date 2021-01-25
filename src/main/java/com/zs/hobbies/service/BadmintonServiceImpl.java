package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.Application;
import main.java.com.zs.hobbies.cache.LruService;
import main.java.com.zs.hobbies.dao.BadmintonDataBase;
import main.java.com.zs.hobbies.dto.Badminton;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.cache.Node;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class give service to Badminton
 */
public class BadmintonServiceImpl implements BadmintonService {
    private BadmintonDataBase badmintonDataBase;
    private Logger logger;
    private LruService lru;
    private SimilarRequirement similarRequirement;

    /**
     * This is a constructor
     * This class set logger and initialize Badminton database
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public BadmintonServiceImpl(Connection con,LruService lru) throws SQLException, ClassNotFoundException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully Badminton Service class start ");
        this.lru = lru;
        badmintonDataBase = new BadmintonDataBase(con);

        similarRequirement = new SimilarRequirement();
    }

    /**
     * This function is helpful you to insert data in badminton table
     * @param badminton
     * @throws SQLException
     */
    @Override
    public void insert(Badminton badminton) throws SQLException {
        /**
         * if user doesn't give id, then it take automatically
         */
        if(badminton.getId() == -1) {
            badminton.setId(badmintonDataBase.findHigherKey());
        }

        Node node = new Node();
        lru.put(node);

        int check = badmintonDataBase.insertBadminton(badminton);

        if(check == 1) {
            logger.info("Successfully Badminton enter in database");
        }else {
            logger.warning("Some internally error comes.Please try again");
        }
    }

    /**
     * This function help you to find the details of person on particular date
     * @param person    the person object
     * @param date      particular date
     * @throws SQLException
     */
    @Override
    public void dateDetails(Person person, Date date) throws SQLException {
        ResultSet resultSet = badmintonDataBase.dateBadmintonDetails(person,date);

        logger.info("This is all badminton details on " + date.toString());

        logger.info("startTime : EndTime : Number of Player : result");
        while(resultSet.next()){
            logger.info(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
              + " "+  resultSet.getInt("numPlayers") + " " + resultSet.getString("result")) ;
        }
    }

    /**
     * This function help you to find the last tick of person in badminton table
     * @param person    the person object
     * @throws SQLException
     */
    @Override
    public void lastTick(Person person) throws SQLException {
        ResultSet resultSet = badmintonDataBase.lastTick(person);

        if(resultSet.next()) {
            logger.info("This is the last tick of badminton ");

            logger.info("Date : " + resultSet.getDate("day").toString() );
            logger.info("Start time : " + resultSet.getTime("startTime"));
            logger.info("End time : " + resultSet.getTime("endTime"));
            logger.info("Number of players : " + resultSet.getInt("numPlayers"));
            logger.info("Result : " + resultSet.getString("result") );

        }else {
            logger.warning("No tick available for you");
        }

    }

    /**
     * This function help you to find the longest streak in the badminton table
     * @param person    the person object
     * @throws SQLException
     */
    @Override
    public void longestStreak(Person person) throws SQLException {
        ResultSet resultSet = badmintonDataBase.longestBadmintonStreak(person);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = similarRequirement.longestStreak(days);
        logger.info("Longest Badminton Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }

    /**
     * This function help you to find the latest streak
     * @param person    the person object
     * @throws SQLException
     */
    @Override
    public void latestStreak(Person person) throws SQLException {
        ResultSet resultSet = badmintonDataBase.longestBadmintonStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
        int longestStreak = similarRequirement.latestStreak(days);
        logger.info("Latest Badminton Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }

}
