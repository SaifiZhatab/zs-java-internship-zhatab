package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dao.VideoWatchingDao;
import com.zs.hobbies.dto.VideoWatching;
import com.zs.hobbies.cache.Node;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.validator.Validator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;


/**
 * This class give service to Video watching hobby
 */
public class VideoWatchingServiceImpl implements VideoWatchingService {
    private VideoWatchingDao videoWatchingDao;
    private Logger logger;
    private LruService lru;
    private SimilarRequirement similarRequirement;
    private Validator validator;

    public VideoWatchingServiceImpl(Connection con,LruService lru) throws SQLException, ClassNotFoundException, IOException {
        logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully VideoWatching service start ");
        videoWatchingDao = new VideoWatchingDao(con);

        this.lru = lru;
        similarRequirement = new SimilarRequirement();
        validator = new Validator();
    }

    /**
     * This function is helpful you to insert data in video watching table
     * @param videoWatching this is a videoWatching object
     * @throws SQLException
     */
    @Override
    public void insert(VideoWatching videoWatching) throws InvalidInputException {
        /**
         * check the given object data is valid or not
         */
        validator.checkTime(videoWatching.getTime().getStartTime(),videoWatching.getTime().getEndTime());
        validator.checkDate(videoWatching.getTime().getDay());

        videoWatchingDao.insertVideo(videoWatching);
    }

    /**
     * This function help you to find the details of person on particular date
     * @param personId    the person id
     * @param date      particular date
     * @throws SQLException
     */
    @Override
    public void dateDetails(int personId, Date date) throws SQLException {
        /**
         * check date is valid or not
         */
        validator.checkDate(date);

        ResultSet resultSet = videoWatchingDao.dateVideoWatchingDetails(personId,date);

        logger.info("This is all VideoWatching details on " + date.toString());
        logger.info("startTime   :  EndTime   : Title  ");
        while(resultSet.next()){
            logger.info(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
                    + " "+  resultSet.getString("title") ) ;
        }
    }

    /**
     * This function help you to find the last tick of person in video watching table
     * @param personId    the person id
     * @throws SQLException
     */
    @Override
    public void lastTick(int personId) throws SQLException {
        Node node = lru.get(String.valueOf(personId)  + "_videoWatching");

        /**
         * when videoWatching present in lru cache
         */
        if(node != null && node.getVideoWatching() != null) {
            logger.info("This is video Watching class in cache");
            logger.info("Video Watching id :" + node.getVideoWatching().getId());

            return;
        }

        ResultSet resultSet = videoWatchingDao.lastTick(personId);

        if(resultSet.next()) {
            logger.info("This is the last tick of Video Watching ");
            logger.info("Video Watching id : " + resultSet.getInt(1));
        }else {
            logger.warning("No tick available for you");
        }
    }

    /**
     * This function help you to find the longest streak in the video watching table
     * @param personId    the person id
     * @throws SQLException
     */
    @Override
    public void longestStreak(int personId) throws SQLException {
        ResultSet resultSet = videoWatchingDao.longestVideoWatchingStreak(personId);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        /**
         * get all the details of person in video watching table and perform operation on it.
         * To find the longest streak
         */
        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
        int longestStreak = similarRequirement.longestStreak(days);
        logger.info("Longest Video Streak for " + personId + " : " + longestStreak);

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }

    /**
     * This function help you to find the latest streak
     * @param personId    the person id
     * @throws SQLException
     */
    @Override
    public void latestStreak(int personId) throws SQLException {
        ResultSet resultSet = videoWatchingDao.longestVideoWatchingStreak(personId);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        /**
         * get all the details of person in video watching table and perform operation on it.
         * To find the longest streak
         */
        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = similarRequirement.latestStreak(days);
        logger.info("Latest VideoWatching Streak for " + personId + " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }
}
