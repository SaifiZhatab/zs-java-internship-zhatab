package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.constent.StringConstents;
import com.zs.hobbies.dao.BadmintonDao;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Timing;
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
 * This class give service to Badminton
 */
public class BadmintonServiceImpl implements BadmintonService {
    private BadmintonDao badmintonDao;
    private Logger logger;
    private Cache lru;
    private SimilarRequirement similarRequirement;
    private Validator validator;

    /**
     * This is constructor and it set the connection and lru object
     * @param con database connection
     * @param lru lru cache object
     */
    public BadmintonServiceImpl(Connection con,Cache lru) {
        logger = Logger.getLogger(Application.class.getName());
        validator = new Validator();

        this.lru = lru;
        badmintonDao = new BadmintonDao(con);
        similarRequirement = new SimilarRequirement();
    }

    /**
     * This function is helpful you to insert data in badminton table
     * @param badminton this is a badminton object
     */
    @Override
    public void insert(Badminton badminton) {
        /**
         * check the badminton object details
         */
        validator.validateBadminton(badminton);

        badmintonDao.insert(badminton);
        logger.info("Successfully Badminton enter in database");

        /**
         * insert hobby into the LRU Cache
         */
       lru.put(badminton.getPersonId() + StringConstents.BADMINTON + "LastTick" , badminton.getId());

        /**
         * change longest streak in cache memory
         */
        Integer longestStreak = (Integer) lru.get(badminton.getPersonId() + StringConstents.BADMINTON + "LongestStreak");
        if(longestStreak != null) {
            lru.put(badminton.getPersonId() + StringConstents.BADMINTON + "LongestStreak" , null);
        }

        /**
         * change latest streak in cache memory
         */
        Integer latestStreak = (Integer) lru.get(badminton.getPersonId() + StringConstents.BADMINTON + "LatestStreak");
        if(latestStreak != null) {
            lru.put(badminton.getPersonId() + StringConstents.BADMINTON +  "LatestStreak" , null);
        }
    }

    /**
     * This function help you to find the details of person on particular date
     * and return set of badminton
     * @param personId the person id
     * @param date the date
     * return return set of badminton
     */
    @Override
    public Set<Badminton> dateDetails(int personId, Date date) {
        /**
         * check the date is valid or not
         * start time will always less than end time and end time will always less than or equal to current time
         * date will always less than or equal to current date
         */
        validator.validDate(date);
        ResultSet resultSet = badmintonDao.dateDetails(personId,date);

        try {

            /**
             * if details are not present at given date
             */
            if (!resultSet.next()) {
                return null;
            }

            Set<Badminton> setDetails = new HashSet<Badminton>();

            /**
             * insert all details in set and return
             */
            while (resultSet.next()) {
                Timing timing = new Timing(resultSet.getTime("startTime"), resultSet.getTime("endTime"),
                        resultSet.getDate("day"));

                Badminton badminton = new Badminton(resultSet.getInt("badminton_id"), resultSet.getInt("personid"),
                        timing, resultSet.getInt("numPlayers"), resultSet.getString("result"));

                setDetails.add(badminton);
            }
            return setDetails;
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry, some internal error comes in badminton service");
        }
    }

    /**
     * This function help you to find the last tick of person in badminton table
     * @param personId    the person object
     * @return
     */
    @Override
    public int lastTick(int personId) {

        /**
         * check in cache memory key is present or not
         */
        Integer badmintonId = (Integer) lru.get(personId + StringConstents.BADMINTON + "LastTick");

        /**
         * if key present in cache memory
         */
        if (badmintonId != null) {
            return badmintonId;
        }

        ResultSet resultSet = badmintonDao.lastTick(personId);

        try{
            if (resultSet.next()) {
                badmintonId = resultSet.getInt("badminton_id");
                /**
                 * if not available in cache, then insert it
                 */
                lru.put(personId + StringConstents.BADMINTON + "LastTick", badmintonId);
            }
            return badmintonId;
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry, some internal error comes in badminton service");
        }
    }

    /**
     * This function help you to find the longest streak in the badminton table
     * @param personId    the person object
     */
    @Override
    public int longestStreak(int personId) {

        /**
         * check the longest streak is available or not in cache memory
         */
        Integer longestStreak = (Integer) lru.get(personId + StringConstents.BADMINTON + "LongestStreak");

        /**
         * if longest streak is available in cache then just return it.
         * if not available, then if condition doesn't execute
         */
        if (longestStreak != null) {
            return longestStreak;
        }

        ResultSet resultSet = badmintonDao.longestStreak(personId);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        try{
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
            lru.put(personId + StringConstents.BADMINTON + "LongestStreak", longestStreak);

            return longestStreak;
        }catch (SQLException e){
            throw new ApplicationException(500, "Sorry, come internal error come");
        }
    }

    /**
     * This function help you to find the latest streak
     * @param personId    the person object
     * @return
     */
    @Override
    public int latestStreak(int personId) {
        /**
         * check the latest streak is available in cache or not
         */
        Integer latestStreak = (Integer) lru.get(personId + StringConstents.BADMINTON + "LatestStreak");

        /**
         * if latest streak is available in cache, then just return it.
         * if not available, then if condition doesn't execute
         */
        if(latestStreak != null) {
            return latestStreak;
        }

        ResultSet resultSet = badmintonDao.longestStreak(personId);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        try {
            /**
             * get all the details of person in badminton table and perform operation on it.
             * To find the longest streak
             */
            while (resultSet.next()) {
                days.add(resultSet.getDate("day").toString());
            }
            latestStreak = similarRequirement.latestStreak(days);

            /**
             * insert into cache
             */
            lru.put(personId + StringConstents.BADMINTON + "LatestStreak", latestStreak);
            return latestStreak;
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry, some internal error comes");
        }
    }
}
