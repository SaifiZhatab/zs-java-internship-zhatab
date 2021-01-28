package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dao.BadmintonDao;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.cache.Node;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.validator.Validator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * This class give service to Badminton
 */
public class BadmintonServiceImpl implements BadmintonService {
    private BadmintonDao badmintonDao;
    private Logger logger;
    private LruService lru;
    private SimilarRequirement similarRequirement;
    private Validator validator;

    /**
     * This is a constructor
     * This class set logger and initialize Badminton database
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public BadmintonServiceImpl(Connection con,LruService lru) {
        logger = Logger.getLogger(Application.class.getName());
        validator = new Validator();

        logger.info("Successfully Badminton Service class start ");
        this.lru = lru;
        badmintonDao = new BadmintonDao(con);

        similarRequirement = new SimilarRequirement();
    }

    /**
     * This function is helpful you to insert data in badminton table
     * @param badminton this is a badminton object
     * @throws SQLException
     */
    @Override
    public void insert(Badminton badminton) throws InvalidInputException {
        validator.checkTime(badminton.getTime().getStartTime(),badminton.getTime().getEndTime());
        validator.checkDate(badminton.getTime().getDay());
        validator.checkResult(badminton.getResult());

        badmintonDao.insertBadminton(badminton);
        logger.info("Successfully Badminton enter in database");

        /**
         * insert hobby into the LRU cache
         */
        lru.put(String.valueOf(badminton.getPersonId()) + "_badminton" , new Node(badminton));
    }

    /**
     * This function help you to find the details of person on particular date
     * @param personId    the person id
     * @param date      particular date
     * @throws SQLException
     */
    @Override
    public void dateDetails(int personId, Date date) throws InvalidInputException, SQLException {
        validator.checkDate(date);
        ResultSet resultSet = badmintonDao.dateBadmintonDetails(personId,date);

        logger.info("This is all badminton details on " + date.toString());

        logger.info("startTime : EndTime : Number of Player : result");
        while(resultSet.next()){
            logger.info(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
              + " "+  resultSet.getInt("numPlayers") + " " + resultSet.getString("result")) ;
        }
    }

    /**
     * This function help you to find the last tick of person in badminton table
     * @param personId    the person object
     * @throws SQLException
     */
    @Override
    public void lastTick(int personId) throws SQLException {
        Node node = lru.get(String.valueOf(personId) + "_badminton");

        /**
         * if the last tick present in cache
         */
        if(node != null && node.getBadminton() != null){
            logger.info("This is the last tick of badminton ");
            logger.info("Badminton id : " + node.getBadminton().getId());

            return;
        }

        ResultSet resultSet = badmintonDao.lastTick(personId);

        if(resultSet.next()) {
            logger.info("This   is the last tick of badminton ");
            logger.info("Badminton id : " + resultSet.getInt(1));

        }else {
            logger.warning("No tick available for you");
        }
    }

    /**
     * This function help you to find the longest streak in the badminton table
     * @param personId    the person object
     * @throws SQLException
     */
    @Override
    public void longestStreak(int personId) throws SQLException {
        ResultSet resultSet = badmintonDao.longestBadmintonStreak(personId);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        /**
         * get all the details of person in badminton table and perform operation on it.
         * To find the longest streak
         */
        int longestStreak = similarRequirement.longestStreak(days);

        logger.info("Longest Badminton Streak for " + personId + " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }

    /**
     * This function help you to find the latest streak
     * @param personId    the person object
     * @throws SQLException
     */
    @Override
    public void latestStreak(int personId) throws SQLException {
        ResultSet resultSet = badmintonDao.longestBadmintonStreak(personId);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        /**
         * get all the details of person in badminton table and perform operation on it.
         * To find the longest streak
         */
        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
        int longestStreak = similarRequirement.latestStreak(days);
        logger.info("Latest Badminton Streak for " + personId+ " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }

}
