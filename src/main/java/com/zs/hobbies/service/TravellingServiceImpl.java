package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dao.TravellingDao;
import com.zs.hobbies.dto.Travelling;
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
 * This class give service to Travelling hobby
 */
public class TravellingServiceImpl implements TravellingService {
    private TravellingDao travellingDao;
    private Logger logger;
    private LruService lru;
    private SimilarRequirement similarRequirement;
    private Validator validator;

    /**
     * This is a constructor
     * This connect class with logger and initialize the Travelling Database also
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public TravellingServiceImpl(Connection con,LruService lru) throws SQLException, ClassNotFoundException, IOException {
        logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully Travelling service start ");

        this.lru = lru;
        travellingDao = new TravellingDao(con);
        validator = new Validator();
        similarRequirement = new SimilarRequirement();
    }

    /**
     * This function help you to insert the travelling data into travelling database table
     * @param travelling
     * @throws SQLException
     */
    @Override
    public void insert(Travelling travelling) throws InvalidInputException {
        /**
         * check the data is valid or not
         */
        validator.checkTime(travelling.getTime().getStartTime(),travelling.getTime().getEndTime());
        validator.checkDate(travelling.getTime().getDay());
        validator.checkPosition(travelling.getStartPoint());
        validator.checkPosition(travelling.getEndPoint());
        travellingDao.insertTravelling(travelling);

    }

    /**
     * This function help you to find the details on the basis of date
     * @param personId    the person id
     * @param date      date
     * @throws SQLException
     */
    @Override
    public void dateDetails(int personId, Date date) throws SQLException {
        /**
         * check date is valid or not
         */
        validator.checkDate(date);

        ResultSet resultSet = travellingDao.dateTravellingDetails(personId,date);

        logger.info("This is all Travelling details on " + date.toString());
        logger.info("startTime   :  EndTime   : startPoint   :   endPoint  : distance");
        while(resultSet.next()){
            logger.info(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
                    + " "+  resultSet.getString("startPoint") + " " + resultSet.getString("endPoint") +
                    " : " + resultSet.getFloat("distance")) ;
        }
    }

    /**
     * This function help you to find the last tick of person
     * if the last tick isn't present, then it nothing print
     * @param personId    the person id
     * @throws SQLException
     */
    @Override
    public void lastTick(int personId) throws SQLException {
        Node node = lru.get(String.valueOf(personId) + "_travelling");

        /**
         * if last tick of travelling present in the cache
         */
        if(node != null && node.getTravelling() != null) {
            logger.info("This is the last tick of travelling");
            logger.info("Travelling id : " + node.getTravelling().getId());

            return;
        }

        ResultSet resultSet = travellingDao.lastTick(personId);

        if(resultSet.next()) {
            logger.info("This is the last tick of Travelling ");
            logger.info(("Travelling id : " + resultSet.getInt(1)));
        }else {
            logger.warning("No tick available for you");
        }
    }

    /**
     * This function help you to find the longest streak of person travelling
     * if there is not streak, then it return 0
     * @param personId the person id
     * @throws SQLException
     */
    @Override
    public void longestStreak(int personId) throws SQLException {
        ResultSet resultSet = travellingDao.longestTravellingStreak(personId);

        /**
         * use to store all the data in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        /**
         * get all the details of person in travelling table and perform operation on it.
         * To find the longest streak
         */
        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = similarRequirement.longestStreak(days);
        logger.info("Longest Travelling Streak for " + personId + " : " + longestStreak);

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }

    /**
     * This function help you to find the latest streak of person in travelling
     * if there is no streak then it return 0
     * @param personId    the person id
     * @throws SQLException
     */
    @Override
    public void latestStreak(int personId) throws SQLException {
        ResultSet resultSet = travellingDao.longestTravellingStreak(personId);

        /**
         * use to store all the data in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        /**
         * get all the details of person in travelling table and perform operation on it.
         * To find the longest streak
         */
        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = similarRequirement.latestStreak(days);
        logger.info("Latest Travelling Streak for " + personId + " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }
}
