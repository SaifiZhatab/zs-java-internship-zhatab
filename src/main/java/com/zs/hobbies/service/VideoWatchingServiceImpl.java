package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.VideoWatchingDao;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.VideoWatching;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.validator.Validator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;


/**
 * This class give service to Video watching hobby
 */
public class VideoWatchingServiceImpl implements VideoWatchingService {
    private VideoWatchingDao videoWatchingDao;
    private Logger logger;
    private Cache lru;
    private SimilarRequirement similarRequirement;
    private Validator validator;

    public VideoWatchingServiceImpl(Connection con,Cache lru) {
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
     */
    @Override
    public void insert(VideoWatching videoWatching) {
        /**
         * check the given object data is valid or not
         */
        validator.checkTime(videoWatching.getTime().getStartTime(),videoWatching.getTime().getEndTime());
        validator.checkDate(videoWatching.getTime().getDay());

        videoWatchingDao.insert(videoWatching);

        /**
         * put object in cache memory
         */
        lru.put(videoWatching.getPersonId() + "_videoWatching" , videoWatching);

        /**
         * change longest streak in cache memory
         */
        Integer longestStreak = (Integer) lru.get(videoWatching.getPersonId() + "_videoWatching_longestStreak");
        if(longestStreak != null) {
            lru.put(videoWatching.getPersonId() + "_videoWatching_longestStreak" , null);
        }

        /**
         * change latest streak in cache memory
         */
        Integer latestStreak = (Integer) lru.get(videoWatching.getPersonId() + "_videoWatching_latestStreak");
        if(latestStreak != null) {
            lru.put(videoWatching.getPersonId() + "_videoWatching_latestStreak" , null);
        }
    }

    /**
     * This function help you to find the details of person on particular date
     * @param personId    the person id
     * @param date      particular date
     */
    @Override
    public Set<VideoWatching> dateDetails(int personId, Date date) {
        /**
         * check date is valid or not
         */
        validator.checkDate(date);

        ResultSet resultSet = videoWatchingDao.dateDetails(personId,date);

        try {
            /**
             * if details are not present of given date
             */
            if (!resultSet.next()) {
                throw new InvalidInputException(400, "Not present any entity");
            }

            /**
             * use to store the objects
             */
            Set<VideoWatching> setDetails = new HashSet<VideoWatching>();

            /**
             * query result
             */
            while (resultSet.next()) {
                Timing timing = new Timing(resultSet.getTime("startTime"), resultSet.getTime("endTime"),
                        resultSet.getDate("day"));

                VideoWatching videoWatching = new VideoWatching(resultSet.getInt("videoWatching_id"), resultSet.getInt("personid"),
                        timing, resultSet.getString("title"));

                setDetails.add(videoWatching);
            }
            return setDetails;
        }catch (SQLException e) {
            throw new ApplicationException(500, "Sorry, some internal error comes");
        }
    }

    /**
     * This function help you to find the last tick of person in video watching table
     * @param personId    the person id
     * @return
     */
    @Override
    public VideoWatching lastTick(int personId) {
        /**
         * check in the cache memory
         */
        VideoWatching videoWatching = (VideoWatching) lru.get(personId + "_videoWatching");

        /**
         * present in cache memory
         */
        if(videoWatching != null) {
            return videoWatching;
        }

        ResultSet resultSet = videoWatchingDao.lastTick(personId);

        try {
            if (resultSet.next()) {
                Timing timing = new Timing(resultSet.getTime("startTime"), resultSet.getTime("endTime"),
                        resultSet.getDate("day"));

                videoWatching = new VideoWatching(resultSet.getInt("videoWatching_id"), resultSet.getInt("personid"),
                        timing, resultSet.getString("title"));
            } else {
                logger.warning("No tick available for you");
            }
            return videoWatching;
        }catch (SQLException e) {
            throw new ApplicationException(500, "Sorry, some internal error comes");
        }
    }

    /**
     * This function help you to find the longest streak in the video watching table
     * @param personId    the person id
     */
    @Override
    public int longestStreak(int personId) {
        /**
         * check the longest streak is available or not in cache memory
         */
        Integer longestStreak = (Integer) lru.get(personId + "_videoWatching_longestStreak");

        /**
         * if longest streak is available in cache then just return it.
         * if not available, then if condition doesn't execute
         */
        if(longestStreak != null){
            return longestStreak;
        }

        ResultSet resultSet = videoWatchingDao.longestStreak(personId);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        try {
            /**
             * get all the details of person in video watching table and perform operation on it.
             * To find the longest streak
             */
            while (resultSet.next()) {
                days.add(resultSet.getDate("day").toString());
            }

            /**
             * get all the details of person in badminton table and perform operation on it.
             * To find the longest streak
             */
            longestStreak = similarRequirement.longestStreak(days);

            /**
             * insert into cache
             */
            lru.put(personId + "_videoWatching_longestStreak", longestStreak);

            return longestStreak;
        }catch (SQLException e) {
            throw new ApplicationException(500, "Sorry, some internal error comes");
        }
    }

    /**
     * This function help you to find the latest streak
     * @param personId    the person id
     * @return
     */
    @Override
    public int latestStreak(int personId) {
        /**
         * check the latest streak is available in cache or not
         */
        Integer latestStreak = (Integer) lru.get(personId + "_videoWatching_latestStreak");

        /**
         * if latest streak is available in cache, then just return it.
         * if not available, then if condition doesn't execute
         */
        if(latestStreak != null) {
            return latestStreak;
        }
        ResultSet resultSet = videoWatchingDao.longestStreak(personId);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        try {
            /**
             * get all the details of person in video watching table and perform operation on it.
             * To find the longest streak
             */
            while (resultSet.next()) {
                days.add(resultSet.getDate("day").toString());
            }

            latestStreak = similarRequirement.latestStreak(days);

            /**
             * insert into cache
             */
            lru.put(personId + "_videoWatching_latestStreak", latestStreak);
            return latestStreak;
        }catch (SQLException e) {
            throw new ApplicationException(500, "Sorry, some internal error comes");
        }
    }

}
