package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.constent.StringConstents;
import com.zs.hobbies.dao.VideoWatchingDao;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.VideoWatching;
import com.zs.hobbies.exception.ApplicationException;
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

    /**
     * This is constructor and it set the connection and lru object
     * @param con database connection
     * @param lru lru cache object
     */
    public VideoWatchingServiceImpl(Connection con,Cache lru) {
        logger = Logger.getLogger(Application.class.getName());
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
        validator.validVideoWatching(videoWatching);

        videoWatchingDao.insert(videoWatching);

        /**
         * put object in cache memory
         */
        lru.put(videoWatching.getPersonId() + StringConstents.VIDEOWATCHING + "LastTick" , videoWatching);

        /**
         * change longest streak in cache memory
         */
        Integer longestStreak = (Integer) lru.get(videoWatching.getPersonId() +StringConstents.VIDEOWATCHING + "LongestStreak");
        if(longestStreak != null) {
            lru.put(videoWatching.getPersonId() + StringConstents.VIDEOWATCHING + "LongestStreak" , null);
        }

        /**
         * change latest streak in cache memory
         */
        Integer latestStreak = (Integer) lru.get(videoWatching.getPersonId() + StringConstents.VIDEOWATCHING + "LatestStreak");
        if(latestStreak != null) {
            lru.put(videoWatching.getPersonId() + StringConstents.VIDEOWATCHING + "LatestStreak" , null);
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
        validator.validDate(date);

        ResultSet resultSet = videoWatchingDao.dateDetails(personId,date);

        try {
            /**
             * if details are not present at given date
             */
            if (!resultSet.next()) {
                return null;
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
    public int lastTick(int personId) {
        /**
         * check in the cache memory
         */
        Integer videoWatchingId = (Integer) lru.get(personId + StringConstents.VIDEOWATCHING + "LastTick");

        /**
         * present in cache memory
         */
        if(videoWatchingId != null) {
            return videoWatchingId;
        }

        ResultSet resultSet = videoWatchingDao.lastTick(personId);

        try {
            if (resultSet.next()) {
                videoWatchingId = resultSet.getInt("videoWatching_id");

                lru.put(personId + StringConstents.VIDEOWATCHING + "LastTick" , videoWatchingId);
            }
            return videoWatchingId;
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
        Integer longestStreak = (Integer) lru.get(personId + StringConstents.VIDEOWATCHING + "LongestStreak");

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
            lru.put(personId + StringConstents.VIDEOWATCHING + "LongestStreak", longestStreak);

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
        Integer latestStreak = (Integer) lru.get(personId + StringConstents.VIDEOWATCHING + "LatestStreak");

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
            lru.put(personId + StringConstents.VIDEOWATCHING + "LatestStreak", latestStreak);
            return latestStreak;
        }catch (SQLException e) {
            throw new ApplicationException(500, "Sorry, some internal error comes");
        }
    }

}
