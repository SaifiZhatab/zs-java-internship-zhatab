package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.dao.BadmintonDao;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Timing;
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
 * This class give service to Badminton
 */
public class BadmintonServiceImpl implements BadmintonService {
    private BadmintonDao badmintonDao;
    private Logger logger;
    private Cache lru;
    private SimilarRequirement similarRequirement;
    private Validator validator;

    /**
     * This is a constructor
     * This class set logger and initialize Badminton database
     */
    public BadmintonServiceImpl(Connection con,Cache lru) {
        logger = Logger.getLogger(Application.class.getName());
        validator = new Validator();

        logger.info("Successfully Badminton Service class start ");
        this.lru = lru;
        badmintonDao = new BadmintonDao(con);

        similarRequirement = new SimilarRequirement();
    }

    /**
     * This function is helpful you to insert data in badminton table
     * @param badminton this is a badminton object
     * @throws InvalidInputException
     */
    @Override
    public void insert(Badminton badminton) throws InvalidInputException {
        /**
         * check the details is valid or not
         * start time will always less than end time and end time will always less than or equal to current time
         * date will always less than or equal to current date
         */
        validator.checkTime(badminton.getTime().getStartTime(),badminton.getTime().getEndTime());
        validator.checkDate(badminton.getTime().getDay());
        validator.checkResult(badminton.getResult());

        badmintonDao.insertBadminton(badminton);
        logger.info("Successfully Badminton enter in database");

        /**
         * insert hobby into the LRU Cache
         */
       lru.put(badminton.getPersonId() + "_badminton" , badminton);

        /**
         * change longest streak in cache memory
         */
        Integer longestStreak = (Integer) lru.get(badminton.getPersonId() + "_badminton_longestStreak");
        if(longestStreak != null) {
            lru.put(badminton.getPersonId() + "_badminton_longestStreak" , null);
        }

        /**
         * change latest streak in cache memory
         */
        Integer latestStreak = (Integer) lru.get(badminton.getPersonId() + "_badminton_latestStreak");
        if(latestStreak != null) {
            lru.put(badminton.getPersonId() + "_badminton_latestStreak" , null);
        }
    }

    /**
     * This function help you to find the details of person on particular date
     * and return set of badminton
     * @param personId the person id
     * @param date the date
     * @throws InvalidInputException  custom exception
     * @throws SQLException
     * return return set of badminton
     */
    @Override
    public Set<Badminton> dateDetails(int personId, Date date) throws InvalidInputException, SQLException {
        /**
         * check the date is valid or not
         * start time will always less than end time and end time will always less than or equal to current time
         * date will always less than or equal to current date
         */
        validator.checkDate(date);
        ResultSet resultSet = badmintonDao.dateBadmintonDetails(personId,date);

        /**
         * if details are not present of given date
         */
        if(!resultSet.next()) {
            throw new InvalidInputException(400,"Not present any entity");
        }

        Set<Badminton> setDetails = new HashSet<Badminton>();

        /**
         * insert all details in set and return
         */
        while(resultSet.next()){
            Timing timing = new Timing(resultSet.getTime("startTime"),resultSet.getTime("endTime"),
                    resultSet.getDate("day"));

            Badminton badminton = new Badminton(resultSet.getInt("badminton_id"),resultSet.getInt("personid"),
                    timing, resultSet.getInt("numPlayers"),resultSet.getString("result"));

            setDetails.add(badminton);
        }
        return setDetails;
    }

    /**
     * This function help you to find the last tick of person in badminton table
     * @param personId    the person object
     * @throws SQLException
     * @return
     */
    @Override
    public Badminton lastTick(int personId) throws SQLException {
        /**
         * check in cache memory key is present or not
         */
        Badminton badminton = (Badminton) lru.get(personId + "_badminton");

        /**
         * if key present in cache memory
         */
        if(badminton != null) {
            return badminton;
        }

        ResultSet resultSet = badmintonDao.lastTick(personId);

        if(resultSet.next()) {
            Timing timing = new Timing(resultSet.getTime("startTime"),resultSet.getTime("endTime"),
                    resultSet.getDate("day"));

            badminton = new Badminton(resultSet.getInt("badminton_id"),resultSet.getInt("personid"),
                    timing, resultSet.getInt("numPlayers"),resultSet.getString("result"));

            /**
             * if not available in cache, then insert it
             */
            lru.put(personId + "_badminton" , badminton);
        }else {
            logger.warning("No tick available for you");
        }
        return badminton;
    }

    /**
     * This function help you to find the longest streak in the badminton table
     * @param personId    the person object
     * @throws SQLException
     */
    @Override
    public int longestStreak(int personId) throws SQLException {
        /**
         * check the longest streak is available or not in cache memory
         */
        Integer longestStreak = (Integer) lru.get(personId + "_badminton_longestStreak");

        /**
         * if longest streak is available in cache then just return it.
         * if not available, then if condition doesn't execute
         */
        if(longestStreak != null){
            return longestStreak;
        }

        ResultSet resultSet = badmintonDao.longestBadmintonStreak(personId);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        /**
         * get all the details of person in badminton table and perform operation on it.
         * To find the longest streak
         */
        longestStreak =  similarRequirement.longestStreak(days);

        /**
         * insert into cache
         */
        lru.put(personId + "_badminton_longestStreak" , longestStreak);

        return  longestStreak;
    }

    /**
     * This function help you to find the latest streak
     * @param personId    the person object
     * @throws SQLException
     * @return
     */
    @Override
    public int latestStreak(int personId) throws SQLException {
        /**
         * check the latest streak is available in cache or not
         */
        Integer latestStreak = (Integer) lru.get(personId + "_badminton_latestStreak");

        /**
         * if latest streak is available in cache, then just return it.
         * if not available, then if condition doesn't execute
         */
        if(latestStreak != null) {
            return latestStreak;
        }

        ResultSet resultSet = badmintonDao.longestBadmintonStreak(personId);

        /**
         * use to store all dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        /**
         * get all the details of person in badminton table and perform operation on it.
         * To find the longest streak
         */
        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
       latestStreak =  similarRequirement.latestStreak(days);

        /**
         * insert into cache
         */
        lru.put(personId + "_badminton_latestStreak" , latestStreak);
        return latestStreak;
    }
}
