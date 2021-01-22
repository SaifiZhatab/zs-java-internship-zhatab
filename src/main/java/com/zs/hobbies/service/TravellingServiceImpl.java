package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.Application;
import main.java.com.zs.hobbies.dao.TravellingDataBase;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.Travelling;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class give service to Travelling hobby
 */
public class TravellingServiceImpl implements TravellingService {
    private TravellingDataBase travellingDataBase;
    private Logger logger;
    private SimilarRequirement similarRequirement;

    /**
     * This is a constructor
     * This connect class with logger and initialize the Travelling Database also
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public TravellingServiceImpl(Connection con) throws SQLException, ClassNotFoundException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully Travelling service start ");
        
        travellingDataBase = new TravellingDataBase(con);

        similarRequirement = new SimilarRequirement();
    }

    /**
     * This function help you to insert the travelling data into travelling database table
     * @param travelling
     * @throws SQLException
     */
    @Override
    public void insert(Travelling travelling) throws SQLException {
        int check = travellingDataBase.insertTravelling(travelling);

        if(check == 1) {
            logger.info("Successfully travelling hobbies enter in database");
        }else {
            logger.warning("Some internally error comes.Please try again");
        }
    }

    /**
     * This function help you to find the details on the basis of date
     * @param person        the person object
     * @param date      date
     * @throws SQLException
     */
    @Override
    public void dateDetails(Person person, Date date) throws SQLException {
        ResultSet resultSet = travellingDataBase.dateTravellingDetails(person,date);

        logger.info("This is all Travelling details on " + date.toString());
        logger.info("startTime   :  EndTime   : startPoint   :   endPoint  : distance");
        while(resultSet.next()){
            logger.info(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
                    + " "+  resultSet.getString("startPoint") + " " + resultSet.getString("endPoint") +
                    " : " + resultSet.getFloat("distance")) ;
        }
    }

    @Override
    public void lastTick(Person person) throws SQLException {
        ResultSet resultSet = travellingDataBase.lastTick(person);

        if(resultSet.next()) {
            logger.info("This is the last tick of Travelling ");

            logger.info("Date : " + resultSet.getDate("day").toString() );
            logger.info("Start Time : " + resultSet.getTime("startTime"));
            logger.info("End time : " +  resultSet.getTime("endTime") );
            logger.info("Start Location : " + resultSet.getString("startPoint"));
            logger.info("End Location : " +  resultSet.getString("endPoint"));
            logger.info("Total distance travel : " + resultSet.getFloat("distance"));
        }else {
            logger.warning("No tick available for you");
        }
    }

    @Override
    public void longestStreak(Person person) throws SQLException {
        ResultSet resultSet = travellingDataBase.longestTravellingStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
        int longestStreak = similarRequirement.longestStreak(days);
        logger.info("Longest Travelling Streak for " + person.getName() + " : " + longestStreak);

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }

    @Override
    public void latestStreak(Person person) throws SQLException {
        ResultSet resultSet = travellingDataBase.longestTravellingStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = similarRequirement.latestStreak(days);
        logger.info("Latest Travelling Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }
}
