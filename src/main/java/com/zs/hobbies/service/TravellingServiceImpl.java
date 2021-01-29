package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.TravellingDao;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.dto.Travelling;
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
 * This class give service to Travelling hobby
 */
public class TravellingServiceImpl implements TravellingService {
    private TravellingDao travellingDao;
    private Logger logger;
    private Cache lru;
    private SimilarRequirement similarRequirement;
    private Validator validator;

    /**
     * This is constructor and it set the connection and lru object
     * @param con database connection
     * @param lru lru cache object
     */
    public TravellingServiceImpl(Connection con,Cache lru) {
        logger = Logger.getLogger(Application.class.getName());

        this.lru = lru;
        travellingDao = new TravellingDao(con);
        validator = new Validator();
        similarRequirement = new SimilarRequirement();
    }

    /**
     * This function help you to insert the travelling data into travelling database table
     * @param travelling
     */
    @Override
    public void insert(Travelling travelling) {
        /**
         * check the data is valid or not
         */
        validator.checkTime(travelling.getTime().getStartTime(),travelling.getTime().getEndTime());
        validator.checkDate(travelling.getTime().getDay());
        validator.checkPosition(travelling.getStartPoint());
        validator.checkPosition(travelling.getEndPoint());
        travellingDao.insert(travelling);

        /**
         * put object in cache memory
         */
        lru.put(travelling.getPersonId() + "_travelling" , travelling);

        /**
         * change longest streak in cache memory
         */
        Integer longestStreak = (Integer) lru.get(travelling.getPersonId() + "_travelling_longestStreak");
        if(longestStreak != null) {
            lru.put(travelling.getPersonId() + "_travelling_longestStreak" , null);
        }

        /**
         * change latest streak in cache memory
         */
        Integer latestStreak = (Integer) lru.get(travelling.getPersonId() + "_travelling_latestStreak");
        if(latestStreak != null) {
            lru.put(travelling.getPersonId() + "_travelling_latestStreak" , null);
        }
    }

    /**
     * This function help you to find the details on the basis of date
     * @param personId    the person id
     * @param date      date
     */
    @Override
    public Set<Travelling> dateDetails(int personId, Date date) {
        /**
         * check date is valid or not
         */
        validator.checkDate(date);

        ResultSet resultSet = travellingDao.dateDetails(personId,date);

        try {
            /**
             * if details are not present at given date
             */
            if (!resultSet.next()) {
                return null;
            }

            /**
             * setDetails use to store the object
             */
            Set<Travelling> setDetails = new HashSet<Travelling>();

            /**
             * query result
             */
            while (resultSet.next()) {
                Timing timing = new Timing(resultSet.getTime("startTime"), resultSet.getTime("endTime"),
                        resultSet.getDate("day"));

                Travelling travelling = new Travelling(resultSet.getInt("travelling_id"), resultSet.getInt("personid"),
                        timing, resultSet.getString("startPoint"), resultSet.getString("endPoint"),
                        resultSet.getFloat("distance"));

                setDetails.add(travelling);

            }
            return setDetails;
        }catch (SQLException e) {
            throw new ApplicationException(500, "Sorry, some internal error comes");
        }
    }

    /**
     * This function help you to find the last tick of person
     * if the last tick isn't present, then it nothing print
     * @param personId    the person id
     * @return
     */
    @Override
    public Travelling lastTick(int personId) {
        /**
         * check in cache memeory
         */
        Travelling travelling = (Travelling) lru.get(personId + "_travelling");

        /**
         * present in cache memory
         */
        if(travelling != null) {
            return travelling;
        }

        ResultSet resultSet = travellingDao.lastTick(personId);

        try {
            if (resultSet.next()) {
                Timing timing = new Timing(resultSet.getTime("startTime"), resultSet.getTime("endTime"),
                        resultSet.getDate("day"));

                travelling = new Travelling(resultSet.getInt("travelling_id"), resultSet.getInt("personid"),
                        timing, resultSet.getString("startPoint"), resultSet.getString("endPoint"),
                        resultSet.getFloat("distance"));

            }
            return travelling;
        }catch (SQLException e) {
            throw new ApplicationException(500, "Sorry, some internal error comes");
        }
    }

    /**
     * This function help you to find the longest streak of person travelling
     * if there is not streak, then it return 0
     * @param personId the person id
     */
    @Override
    public int longestStreak(int personId) {
        /**
         * check the longest streak is available or not in cache memory
         */
        Integer longestStreak = (Integer) lru.get(personId + "_travelling_longestStreak");

        /**
         * if longest streak is available in cache then just return it.
         * if not available, then if condition doesn't execute
         */
        if(longestStreak != null){
            return longestStreak;
        }

        ResultSet resultSet = travellingDao.longestStreak(personId);

        /**
         * use to store all the data in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        try {
            /**
             * get all the details of person in travelling table and perform operation on it.
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
            lru.put(personId + "_travelling_longestStreak", longestStreak);

            return longestStreak;
        }catch (SQLException e) {
            throw new ApplicationException(500, "Sorry, some internal error comes");
        }
    }

    /**
     * This function help you to find the latest streak of person in travelling
     * if there is no streak then it return 0
     * @param personId    the person id
     * @return
     */
    @Override
    public int latestStreak(int personId) {
        /**
         * check the latest streak is available in cache or not
         */
        Integer latestStreak = (Integer) lru.get(personId + "_travelling_latestStreak");

        /**
         * if latest streak is available in cache, then just return it.
         * if not available, then if condition doesn't execute
         */
        if(latestStreak != null) {
            return latestStreak;
        }

        ResultSet resultSet = travellingDao.longestStreak(personId);

        /**
         * use to store all the data in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        try {
            /**
             * get all the details of person in travelling table and perform operation on it.
             * To find the longest streak
             */
            while (resultSet.next()) {
                days.add(resultSet.getDate("day").toString());
            }

            latestStreak = similarRequirement.latestStreak(days);

            /**
             * insert into cache
             */
            lru.put(personId + "_travelling_latestStreak", latestStreak);
            return latestStreak;
        }catch (SQLException e) {
            throw new ApplicationException(500, "Sorry, some internal error comes");
        }
    }
}
