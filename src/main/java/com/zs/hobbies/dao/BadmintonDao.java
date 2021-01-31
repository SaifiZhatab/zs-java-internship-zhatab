package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;

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
    private PreparedStatement insert, dateDetails, longestStreak, lastTick;


    public BadmintonDao(Connection con) {
       logger = Logger.getLogger(Application.class.getName());
        this.con = con;
    }
    /**
     * this function help you to insert the Badminton hobbies in database
     * when you pass null object, then it check and return
     * @param badminton this is a badminton object
     * @return   return status
     */
    public void insert(Badminton badminton) {
        if(badminton == null) {
            throw new InvalidInputException(400,"Sorry, Null object pass in badminton database");
        }
        try {
            insert = con.prepareStatement("insert into Badminton values (?,?,?,?,?,?,?)");
            insert.setInt(1, badminton.getId());
            insert.setInt(2, badminton.getPersonId());
            insert.setTime(3, badminton.getTime().getStartTime());
            insert.setTime(4, badminton.getTime().getEndTime());
            insert.setInt(5, badminton.getNumPlayers());
            insert.setString(6, badminton.getResult());
            insert.setDate(7, badminton.getTime().getDay());
            insert.executeUpdate();
            insert.close();
        }catch (SQLException e){
            throw new ApplicationException(500,"Sorry,some internal exception comes in badminton database");
        }
    }

    /**
     * this function send all the person badminton details
     * @param personId
     * @return return result set of data
     */
    public ResultSet longestStreak(int personId) {
        try {
            longestStreak = con.prepareStatement("select * from Badminton where personid = ? order by day");
            longestStreak.setInt(1, personId);
            return longestStreak.executeQuery();
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry,some internal exception comes in badminton database");
        }
    }

    /**
     * this class help you to find the person details on a particular date
     * @param personId    the person id
     * @param date      specific date
     * @return      return the set of data
     */
    public ResultSet dateDetails(int personId, Date date) {
        try{
            dateDetails = con.prepareStatement("select * from Badminton where personid = ? and day=?");
            dateDetails.setInt(1,personId);
            dateDetails.setDate(2,date);
            return dateDetails.executeQuery();
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry,some internal exception comes in badminton database");
        }
    }

    /**
     * This class help you to fetch the last tick details
     * @param personId    the person id
     * @return      return the last tick details
     */
    public ResultSet lastTick(int personId) {
        try{
            lastTick = con.prepareStatement("select * from Badminton where personid = ? order by badminton_id desc LIMIT 1");
            lastTick.setInt(1,personId );
            return lastTick.executeQuery();
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry,some internal exception comes in badminton database");
        }
    }
}
