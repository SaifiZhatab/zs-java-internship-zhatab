package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.dto.VideoWatching;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class VideoWatchingDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insertVideo, dateVideoWatchingDetails, lastTick, longestVideoWatchingStreak, findHigherKey;

    public VideoWatchingDao(Connection con) throws SQLException, IOException {
         logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully VideoWatching database start ");

        this.con = con;

        insertVideo = con.prepareStatement("insert into VideoWatching values (?,?,?,?,?,?)");
        dateVideoWatchingDetails = con.prepareStatement("select * from VideoWatching where personid = ? and day = ?");
        lastTick = con.prepareStatement("select * from VideoWatching where personid = ? order by videoWatching_id desc LIMIT 1");
        longestVideoWatchingStreak = con.prepareStatement("select * from VideoWatching where personid = ? order by day");
        findHigherKey = con.prepareStatement("select videoWatching_id from VideoWatching order by videoWatching_id desc LIMIT 1");
    }

    /**
     * this function help you to insert the Video hobbies in database
     * @param videoWatching this is a video hobbies object
     * @return return status
     * @throws SQLException
     */
    public int insertVideo(VideoWatching videoWatching) throws SQLException {
        insertVideo.setInt(1,videoWatching.getId());
        insertVideo.setInt(2,videoWatching.getPerson().getId());
        insertVideo.setTime(3,videoWatching.getTime().getStartTime());
        insertVideo.setTime(4,videoWatching.getTime().getEndTime());
        insertVideo.setString(5,videoWatching.getTitle());
        insertVideo.setDate(6,videoWatching.getTime().getDay());

        return insertVideo.executeUpdate();
    }

    /**
     * This function help you to fetch the VideoWatching data at particular date
     * @param person    the person object
     * @param date      particular date
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet dateVideoWatchingDetails(Person person, Date date) throws SQLException {
        dateVideoWatchingDetails.setInt(1,person.getId());
        dateVideoWatchingDetails.setDate(2,date);

        return dateVideoWatchingDetails.executeQuery();
    }

    /**
     * This function help you to find the last tick of person
     * @param person    the person object
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet lastTick(Person person) throws SQLException {
        lastTick.setInt(1,person.getId());
        return lastTick.executeQuery();
    }

    /**
     * This function used for find the longest streak in the database
     * @param person    the person object
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet longestVideoWatchingStreak(Person person) throws SQLException {
        longestVideoWatchingStreak.setInt(1,person.getId());
        return longestVideoWatchingStreak.executeQuery();
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
