package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.VideoWatching;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Logger;

public class VideoWatchingDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insert, dateDetails, lastTick, longestStreak;

    public VideoWatchingDao(Connection con) {
        logger = Logger.getLogger(Application.class.getName());
        this.con = con;
    }

    /**
     * this function help you to insert the Video hobbies in database
     * @param videoWatching this is a video hobbies object
     * @return return status
     */
    public void insert(VideoWatching videoWatching) {
        if(videoWatching == null) {
            throw new InvalidInputException(500,"Sorry, Null object pass in video watching database");
        }

        try {
            insert = con.prepareStatement("insert into VideoWatching values (?,?,?,?,?,?)");
            insert.setInt(1, videoWatching.getId());
            insert.setInt(2, videoWatching.getPersonId());
            insert.setTime(3, videoWatching.getTime().getStartTime());
            insert.setTime(4, videoWatching.getTime().getEndTime());
            insert.setString(5, videoWatching.getTitle());
            insert.setDate(6, videoWatching.getTime().getDay());

            insert.executeUpdate();
            logger.info("Successfully insert videoWatching in database");
        }catch (SQLException e){
            throw new InvalidInputException(500,"Sorry,you use duplicate key");
        }
    }

    /**
     * This function help you to fetch the VideoWatching data at particular date
     * @param personId    the person id
     * @param date      particular date
     * @return      return the fetch data
     */
    public ResultSet dateDetails(int personId, Date date) {
        try{
            dateDetails = con.prepareStatement("select * from VideoWatching where personid = ? and day = ?");
            dateDetails.setInt(1,personId);
            dateDetails.setDate(2,date);
            return dateDetails.executeQuery();
        }catch (SQLException e){
            throw new ApplicationException(500,"Sorry,some internal exception comes in video watching database");
        }
    }

    /**
     * This function help you to find the last tick of person
     * @param personId    the person id
     * @return      return the fetch data
     */
    public ResultSet lastTick(int personId) {
        try{
            lastTick = con.prepareStatement("select * from VideoWatching where personid = ? order by videoWatching_id desc LIMIT 1");
            lastTick.setInt(1,personId);
            return lastTick.executeQuery();
        }catch (SQLException e){
            throw new ApplicationException(500,"Sorry,some internal exception comes in video watching database");
        }
    }

    /**
     * This function used for find the longest streak in the database
     * @param personId    the person id
     * @return      return the fetch data
     */
    public ResultSet longestStreak(int personId) {
        try{
            longestStreak = con.prepareStatement("select * from VideoWatching where personid = ? order by day");
            longestStreak.setInt(1,personId);
            return longestStreak.executeQuery();
        }catch (SQLException e){
            throw new ApplicationException(500,"Sorry,some internal exception comes in video watching database");
        }
    }

}
