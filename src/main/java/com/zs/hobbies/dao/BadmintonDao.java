package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Badminton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Logger;

/**
 * this class help to Badminton service class to communicate with database
 */
public class BadmintonDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insertBadminton, dateBadmintonDetails, longestBadmintonStreak, lastTick;


    public BadmintonDao(Connection con) {
       logger = Logger.getLogger(Application.class.getName());
        logger.info("Successfully Badminton database start ");

        this.con = con;
    }
    /**
     * this function help you to insert the Badminton hobbies in database
     * @param badminton this is a badminton object
     * @return   return status
     * @throws SQLException
     */
    public void insertBadminton(Badminton badminton) {
        try {
            insertBadminton = con.prepareStatement("insert into Badminton values (?,?,?,?,?,?,?)");
            insertBadminton.setInt(1, badminton.getId());
            insertBadminton.setInt(2, badminton.getPersonId());
            insertBadminton.setTime(3, badminton.getTime().getStartTime());
            insertBadminton.setTime(4, badminton.getTime().getEndTime());
            insertBadminton.setInt(5, badminton.getNumPlayers());
            insertBadminton.setString(6, badminton.getResult());
            insertBadminton.setDate(7, badminton.getTime().getDay());
            insertBadminton.executeUpdate();
            logger.info("Successfully insert Badminton in database");
            insertBadminton.close();
        }catch (SQLException e){
            logger.warning(e.getMessage());
        }
    }

    /**
     * this function send all the person badminton details
     * @param personId
     * @return
     * @throws SQLException
     */
    public ResultSet longestBadmintonStreak(int personId) {
        try {
            longestBadmintonStreak = con.prepareStatement("select * from Badminton where personid = ? order by day");

            longestBadmintonStreak.setInt(1, personId);
            return longestBadmintonStreak.executeQuery();
        }catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return null;
    }

    /**
     * this class help you to find the person details on a particular date
     * @param personId    the person id
     * @param date      specific date
     * @return      return the set of data
     * @throws SQLException
     */
    public ResultSet dateBadmintonDetails(int personId, Date date) {
        try{
            dateBadmintonDetails = con.prepareStatement("select * from Badminton where personid = ? and day=?");
            dateBadmintonDetails.setInt(1,personId);
            dateBadmintonDetails.setDate(2,date);
            return dateBadmintonDetails.executeQuery();
        }catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return null;
    }

    /**
     * This class help you to fetch the last tick details
     * @param personId    the person id
     * @return      return the last tick details
     * @throws SQLException
     */
    public ResultSet lastTick(int personId) {
        try{
            lastTick = con.prepareStatement("select * from Badminton where personid = ? order by badminton_id desc LIMIT 1");
            lastTick.setInt(1,personId);
            return lastTick.executeQuery();
        }catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return null;
    }
}
