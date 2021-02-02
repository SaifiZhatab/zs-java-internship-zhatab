package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.constent.StringConstents;
import com.zs.hobbies.dao.ChessDao;
import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.util.DataBase;
import com.zs.hobbies.util.ResourceLoader;
import com.zs.hobbies.validator.Validator;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * This class give service to Chess hobby
 */
public class ChessServiceImpl implements ChessService {
    private ChessDao chessDao;
    private Logger logger;
    private Cache lru;
    private SimilarRequirement similarRequirement;
    private Validator validator;

    /**
     * This is constructor and it set the connection and lru object
     */
    public ChessServiceImpl() {
        logger = Logger.getLogger(Application.class.getName());
        validator = new Validator();
        this.lru = new Cache(ResourceLoader.getCacheSize());
        chessDao = new ChessDao(DataBase.getCon());
        similarRequirement = new SimilarRequirement();
    }

    /**
     * This function help you to insert Chess details into Chess database table
     * @param chess the chess object
     */
    @Override
    public void insert(Chess chess) {
        /**
         * check the given data is valid or not
         */
        validator.validChess(chess);
        chessDao.insert(chess);

        /**
         * insert into cache
         */
        lru.put(chess.getPersonId() + StringConstents.CHESS + "LastTick", chess.getId());

        /**
         * change longest streak in cache memory
         */
        Integer longestStreak = (Integer) lru.get(chess.getPersonId() + StringConstents.CHESS + "LongestStreak");
        if(longestStreak != null) {
            lru.put(chess.getPersonId() + StringConstents.CHESS + "LongestStreak" , null);
        }

        /**
         * change latest streak in cache memory
         */
        Integer latestStreak = (Integer) lru.get(chess.getPersonId() + StringConstents.CHESS + "LatestStreak");
        if(latestStreak != null) {
            lru.put(chess.getPersonId() + StringConstents.CHESS + "LatestStreak" , null);
        }
    }

    /**
     * This function help you to fetch the data on the basis of date
     * @param personId    the person id
     * @param date      date
     */
    @Override
    public Set<Chess> dateDetails(int personId, Date date) {
        validator.validDate(date);
        Set<Chess> setDetails = new HashSet<Chess>();

        ResultSet resultSet = chessDao.dateDetails(personId,date);

        try {
            /**
             * if details are not present at given date
             */
            while (resultSet.next()) {
                Timing timing = new Timing(resultSet.getTime("startTime"), resultSet.getTime("endTime"),
                        resultSet.getDate("day"));

                Chess chess = new Chess(resultSet.getInt("chess_id"), resultSet.getInt("personid"),
                        timing, resultSet.getInt("numMoves"), resultSet.getString("result"));

                setDetails.add(chess);
            }
            return setDetails;
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry, some internal error comes");
        }
    }

    /**
     * This function help you to find the last tick of Chess
     * @param personId    the person object
     * @return
     */
    @Override
    public Integer lastTick(int personId) {
        /**
         * check in cache memory
         */
        Integer chessId = (Integer) lru.get(personId + StringConstents.CHESS + "LastTick");

        /**
         * if present in cache
         */
        if(chessId != null) {
            return chessId;
        }

        ResultSet resultSet = chessDao.lastTick(personId);
        try {
            if (resultSet.next()) {
                chessId = resultSet.getInt("chess_id");

                lru.put(personId + StringConstents.CHESS + "LastTick",chessId);
            }
            return chessId;
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry, some internal error comes");
        }
    }

    /**
     * This function help you to find the longest streak in chess
     * @param personId    the peron id
     */
    @Override
    public int longestStreak(int personId) {
        /**
         * check the longest streak is available or not in cache memory
         */
        Integer longestStreak = (Integer) lru.get(personId + StringConstents.CHESS + "LongestStreak");

        /**
         * if longest streak is available in cache then just return it.
         * if not available, then if condition doesn't execute
         */
        if(longestStreak != null){
            return longestStreak;
        }

        ResultSet resultSet = chessDao.longestStreak(personId);

        /**
         * use to store dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        try {
            /**
             * get all the details of person in chess table and perform operation on it.
             * To find the longest streak
             */
            while (resultSet.next()) {
                days.add(resultSet.getDate("day").toString());
            }

            longestStreak = similarRequirement.longestStreak(days);

            /**
             * insert into cache
             */
            lru.put(personId + StringConstents.CHESS + "LongestStreak", longestStreak);

            return longestStreak;
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry, some internal error comes");
        }
    }

    /**
     * This function help you to find latest streak in chess
     * @param personId    the person id
     * @return
     */
    @Override
    public int latestStreak(int personId) {
        /**
         * check the latest streak is available in cache or not
         */
        Integer latestStreak = (Integer) lru.get(personId + StringConstents.CHESS + "LatestStreak");

        /**
         * if latest streak is available in cache, then just return it.
         * if not available, then if condition doesn't execute
         */
        if(latestStreak != null) {
            return latestStreak;
        }

        ResultSet resultSet = chessDao.longestStreak(personId);

        /**
         * use to store dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        try {
            /**
             * get all the details of person in chess table and perform operation on it.
             * To find the longest streak
             */
            while (resultSet.next()) {
                days.add(resultSet.getDate("day").toString());
            }

            latestStreak = similarRequirement.latestStreak(days);

            /**
             * insert into cache
             */
            lru.put(personId + StringConstents.CHESS + "LatestStreak", latestStreak);
            return latestStreak;
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry, some internal error comes");
        }
    }
}
