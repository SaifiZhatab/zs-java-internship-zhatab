package main.java.com.zs.hobbies.database;

import main.java.com.zs.hobbies.entity.Person;
import main.java.com.zs.hobbies.entity.VideoWatching;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoWatchingDataBase {
    private PreparedStatement insertVideo, dateVideoWatchingDetails, lastTick, longestVideoWatchingStreak;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public VideoWatchingDataBase() throws ClassNotFoundException, SQLException {
        insertVideo = DataBase.con.prepareStatement("insert into VideoWatching values (?,?,?,?,?,?)");
        dateVideoWatchingDetails = DataBase.con.prepareStatement("select * from VideoWatching where personid = ? and day = ?");
        lastTick = DataBase.con.prepareStatement("select * from VideoWatching where personid = ? order by videoWatching_id desc LIMIT 1");
        longestVideoWatchingStreak = DataBase.con.prepareStatement("select * from VideoWatching where personid = ? order by day");
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

    public ResultSet dateVideoWatchingDetails(Person person, Date date) throws SQLException {
        dateVideoWatchingDetails.setInt(1,person.getId());
        dateVideoWatchingDetails.setDate(2,date);

        return dateVideoWatchingDetails.executeQuery();
    }

    public ResultSet lastTick(Person person) throws SQLException {
        lastTick.setInt(1,person.getId());
        return lastTick.executeQuery();
    }

    public ResultSet longestVideoWatchingStreak(Person person) throws SQLException {
        longestVideoWatchingStreak.setInt(1,person.getId());
        return longestVideoWatchingStreak.executeQuery();
    }
}
