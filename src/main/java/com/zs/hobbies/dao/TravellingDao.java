package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Travelling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Logger;

public class TravellingDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insertTravelling, dateTravellingDetails, lastTick, longestTravellingStreak;

    public TravellingDao(Connection con) {
         logger = Logger.getLogger(Application.class.getName());
        logger.info("Successfully Travelling database start ");
        this.con = con;
    }

    /**
     * this function help you to insert the travelling hobbies in database
     * @param travelling    this is a travel object
     * @return  return status of insert query
     * @throws SQLException
     */
    public void insertTravelling(Travelling travelling) {
        try{
            insertTravelling = con.prepareStatement("insert into travelling values (?,?,?,?,?,?,?,?)");
            insertTravelling.setInt(1,travelling.getId());
            insertTravelling.setInt(2,travelling.getPersonId());
            insertTravelling.setTime(3, travelling.getTime().getStartTime());
            insertTravelling.setTime(4,travelling.getTime().getEndTime());
            insertTravelling.setString(5,travelling.getStartPoint());
            insertTravelling.setString(6,travelling.getEndPoint());
            insertTravelling.setFloat(7,travelling.getDistance());
            insertTravelling.setDate(8,travelling.getTime().getDay());

            insertTravelling.executeUpdate();
            logger.info("Successfully insert travelling in database");
        }catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }

    /**
     * This function help you to fetch the travelling data of person on particular date
     * @param personId    the person id
     * @param date      particular date
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet dateTravellingDetails(int personId, Date date) {
        try{
            dateTravellingDetails = con.prepareStatement("select * from Travelling where personid = ? and day = ?");
            dateTravellingDetails.setInt(1,personId);
            dateTravellingDetails.setDate(2,date);

            return dateTravellingDetails.executeQuery();
        }catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return null;
    }

    /**
     * This function help you to fetch the last tick of person
     * @param personId the person id
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet lastTick(int personId) {
        try{
            lastTick = con.prepareStatement("select * from Travelling where personid = ? order by travelling_id desc LIMIT 1");
            lastTick.setInt(1,personId);
            return lastTick.executeQuery();
        }catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return null;
    }

    /**
     * This function help you to find the longest Streak in travelling
     * @param personId    the person id
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet longestTravellingStreak(int personId) {
       try{
            longestTravellingStreak = con.prepareStatement("select * from Travelling where personid = ? order by day");
            longestTravellingStreak.setInt(1,personId);
            return longestTravellingStreak.executeQuery();
       }catch (SQLException e) {
           logger.warning(e.getMessage());
       }
       return null;
    }

}
