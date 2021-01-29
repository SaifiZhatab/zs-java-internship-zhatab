package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Travelling;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Logger;

public class TravellingDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insert, dateDetails, lastTick, longestStreak;

    public TravellingDao(Connection con) {
         logger = Logger.getLogger(Application.class.getName());
        logger.info("Successfully Travelling database start ");
        this.con = con;
    }

    /**
     * this function help you to insert the travelling hobbies in database
     * @param travelling    this is a travel object
     * @return  return status of insert query
     */
    public void insert(Travelling travelling) {
        if(travelling == null) {
            throw new InvalidInputException(500,"Sorry, Null object pass in travelling database");
        }
        try{
            insert = con.prepareStatement("insert into travelling values (?,?,?,?,?,?,?,?)");
            insert.setInt(1,travelling.getId());
            insert.setInt(2,travelling.getPersonId());
            insert.setTime(3, travelling.getTime().getStartTime());
            insert.setTime(4,travelling.getTime().getEndTime());
            insert.setString(5,travelling.getStartPoint());
            insert.setString(6,travelling.getEndPoint());
            insert.setFloat(7,travelling.getDistance());
            insert.setDate(8,travelling.getTime().getDay());

            insert.executeUpdate();
            logger.info("Successfully insert travelling in database");
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry,some internal exception comes in travelling database");
        }
    }

    /**
     * This function help you to fetch the travelling data of person on particular date
     * @param personId    the person id
     * @param date      particular date
     * @return      return the fetch data
     */
    public ResultSet dateDetails(int personId, Date date) {
        try{
            dateDetails = con.prepareStatement("select * from Travelling where personid = ? and day = ?");
            dateDetails.setInt(1,personId);
            dateDetails.setDate(2,date);

            return dateDetails.executeQuery();
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry,some internal exception comes in travelling database");
        }
    }

    /**
     * This function help you to fetch the last tick of person
     * @param personId the person id
     * @return      return the fetch data
     */
    public ResultSet lastTick(int personId) {
        try{
            lastTick = con.prepareStatement("select * from Travelling where personid = ? order by travelling_id desc LIMIT 1");
            lastTick.setInt(1,personId);
            return lastTick.executeQuery();
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry,some internal exception comes in travelling database");
        }
    }

    /**
     * This function help you to find the longest Streak in travelling
     * @param personId    the person id
     * @return      return the fetch data
     */
    public ResultSet longestStreak(int personId) {
       try{
            longestStreak = con.prepareStatement("select * from Travelling where personid = ? order by day");
            longestStreak.setInt(1,personId);
            return longestStreak.executeQuery();
       }catch (SQLException e) {
           throw new ApplicationException(500,"Sorry,some internal exception comes in travelling database");
       }
    }

}
