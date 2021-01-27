package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.dto.Travelling;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TravellingDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insertTravelling, dateTravellingDetails, lastTick, longestTravellingStreak, findHigherKey;

    public TravellingDao(Connection con) throws SQLException, IOException {
         logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully Travelling database start ");

        this.con = con;
        insertTravelling = con.prepareStatement("insert into travelling values (?,?,?,?,?,?,?,?)");
        dateTravellingDetails = con.prepareStatement("select * from Travelling where personid = ? and day = ?");
        lastTick = con.prepareStatement("select * from Travelling where personid = ? order by travelling_id desc LIMIT 1");
        longestTravellingStreak = con.prepareStatement("select * from Travelling where personid = ? order by day");
        findHigherKey = con.prepareStatement("select travelling_id from Travelling order by travelling_id desc LIMIT 1 ");
    }

    /**
     * this function help you to insert the travelling hobbies in database
     * @param travelling    this is a travel object
     * @return  return status of insert query
     * @throws SQLException
     */
    public int insertTravelling(Travelling travelling) throws SQLException {
        insertTravelling.setInt(1,travelling.getId());
        insertTravelling.setInt(2,travelling.getPerson().getId());
        insertTravelling.setTime(3, travelling.getTime().getStartTime());
        insertTravelling.setTime(4,travelling.getTime().getEndTime());
        insertTravelling.setString(5,travelling.getStartPoint());
        insertTravelling.setString(6,travelling.getEndPoint());
        insertTravelling.setFloat(7,travelling.getDistance());
        insertTravelling.setDate(8,travelling.getTime().getDay());

        return insertTravelling.executeUpdate();
    }

    /**
     * This function help you to fetch the travelling data of person on particular date
     * @param person    the person object
     * @param date      particular date
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet dateTravellingDetails(Person person, Date date) throws SQLException {
        dateTravellingDetails.setInt(1,person.getId());
        dateTravellingDetails.setDate(2,date);

        return dateTravellingDetails.executeQuery();
    }

    /**
     * This function help you to fetch the last tick of person
     * @param person the person object
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet lastTick(Person person) throws SQLException {
        lastTick.setInt(1,person.getId());
        return lastTick.executeQuery();
    }

    /**
     * This function help you to find the longest Streak in travelling
     * @param person    the person object
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet longestTravellingStreak(Person person) throws SQLException {
        longestTravellingStreak.setInt(1,person.getId());
        return longestTravellingStreak.executeQuery();
    }

    /**
     * This class help you to find the unique key that will not present in database table
     * @return   return the unique key of table
     * @throws SQLException
     */
    public int findHigherKey() throws SQLException {
        ResultSet resultSet = findHigherKey.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }else {
            return 1;
        }
    }
}
