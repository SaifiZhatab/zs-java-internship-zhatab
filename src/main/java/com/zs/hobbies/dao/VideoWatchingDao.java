package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.VideoWatching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Logger;

public class VideoWatchingDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insertVideo, dateVideoWatchingDetails, lastTick, longestVideoWatchingStreak;

    public VideoWatchingDao(Connection con) {
        logger = Logger.getLogger(Application.class.getName());
        logger.info("Successfully VideoWatching database start ");
        this.con = con;
    }

    /**
     * this function help you to insert the Video hobbies in database
     * @param videoWatching this is a video hobbies object
     * @return return status
     * @throws SQLException
     */
    public void insertVideo(VideoWatching videoWatching) {
        try {
            insertVideo = con.prepareStatement("insert into VideoWatching values (?,?,?,?,?,?)");
            insertVideo.setInt(1, videoWatching.getId());
            insertVideo.setInt(2, videoWatching.getPersonId());
            insertVideo.setTime(3, videoWatching.getTime().getStartTime());
            insertVideo.setTime(4, videoWatching.getTime().getEndTime());
            insertVideo.setString(5, videoWatching.getTitle());
            insertVideo.setDate(6, videoWatching.getTime().getDay());

            insertVideo.executeUpdate();
            logger.info("Successfully insert videoWatching in database");
        }catch (SQLException e){
            logger.warning(e.getMessage());
        }
    }

    /**
     * This function help you to fetch the VideoWatching data at particular date
     * @param personId    the person id
     * @param date      particular date
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet dateVideoWatchingDetails(int personId, Date date) {
        try{
            dateVideoWatchingDetails = con.prepareStatement("select * from VideoWatching where personid = ? and day = ?");
            dateVideoWatchingDetails.setInt(1,personId);
            dateVideoWatchingDetails.setDate(2,date);
            return dateVideoWatchingDetails.executeQuery();
        }catch (SQLException e){
            logger.warning(e.getMessage());
        }
        return null;
    }

    /**
     * This function help you to find the last tick of person
     * @param personId    the person id
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet lastTick(int personId) {
        try{
            lastTick = con.prepareStatement("select * from VideoWatching where personid = ? order by videoWatching_id desc LIMIT 1");
            lastTick.setInt(1,personId);
            return lastTick.executeQuery();
        }catch (SQLException e){
            logger.warning(e.getMessage());
        }
        return null;
    }

    /**
     * This function used for find the longest streak in the database
     * @param personId    the person id
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet longestVideoWatchingStreak(int personId) {
        try{
            longestVideoWatchingStreak = con.prepareStatement("select * from VideoWatching where personid = ? order by day");
            longestVideoWatchingStreak.setInt(1,personId);
            return longestVideoWatchingStreak.executeQuery();
        }catch (SQLException e){
            logger.warning(e.getMessage());
        }
        return null;
    }
}
