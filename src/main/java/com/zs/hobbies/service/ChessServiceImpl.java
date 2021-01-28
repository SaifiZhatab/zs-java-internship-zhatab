package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.ChessDao;
import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.validator.Validator;

import java.io.IOException;
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
 * This class give service to Chess hobby
 */
public class ChessServiceImpl implements ChessService {
    private ChessDao chessDao;
    private Logger logger;
    private Cache lru;
    private SimilarRequirement similarRequirement;
    private Validator validator;
    /**
     * This is a constructor
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public ChessServiceImpl(Connection con,Cache lru) {
        logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully Chess service start ");

        this.lru = lru;
        chessDao = new ChessDao(con);
        similarRequirement = new SimilarRequirement();
        validator = new Validator();
    }

    /**
     * This function help you to insert Chess details into Chess database table
     * @param chess the chess object
     * @throws InvalidInputException custom exception
     */
    @Override
    public void insert(Chess chess) throws InvalidInputException {
        /**
         * check the given data is valid or not
         */
        validator.checkTime(chess.getTime().getStartTime(),chess.getTime().getEndTime());
        validator.checkDate(chess.getTime().getDay());
        validator.checkResult(chess.getResult());
        validator.checkNumOfMove(chess.getNumMoves());

        chessDao.insertChess(chess);

        /**
         * insert into cache
         */
        lru.put(chess.getPersonId() + "_chess", chess);

        /**
         * change longest streak in cache memory
         */
        Integer longestStreak = (Integer) lru.get(chess.getPersonId() + "_chess_longestStreak");
        if(longestStreak != null) {
            lru.put(chess.getPersonId() + "_chess_longestStreak" , null);
        }

        /**
         * change latest streak in cache memory
         */
        Integer latestStreak = (Integer) lru.get(chess.getPersonId() + "_chess_latestStreak");
        if(latestStreak != null) {
            lru.put(chess.getPersonId() + "_chess_latestStreak" , null);
        }
    }

    /**
     * This function help you to fetch the data on the basis of date
     * @param personId    the person id
     * @param date      date
     * @throws InvalidInputException
     */
    @Override
    public Set<Chess> dateDetails(int personId, Date date) throws InvalidInputException, SQLException {
        validator.checkDate(date);
        Set<Chess> setDetails = new HashSet<Chess>();

        ResultSet resultSet = chessDao.dateChessDetails(personId,date);

        /**
         * if details are not present of given date
         */
        if(!resultSet.next()) {
            throw new InvalidInputException(400,"Not present any entity");
        }

        while(resultSet.next()) {
            Timing timing = new Timing(resultSet.getTime("startTime"), resultSet.getTime("endTime"),
                    resultSet.getDate("day"));

            Chess chess = new Chess(resultSet.getInt("chess_id"),resultSet.getInt("personid"),
                    timing,resultSet.getInt("numMoves"),resultSet.getString("result"));

            setDetails.add(chess);
        }
        return setDetails;
    }

    /**
     * This function help you to find the last tick of Chess
     * @param personId    the person object
     * @throws SQLException
     * @return
     */
    @Override
    public Chess lastTick(int personId) throws SQLException {
        /**
         * check in cache memory
         */
        Chess chess = (Chess) lru.get(personId + "_chess");

        /**
         * if present in cache
         */
        if(chess != null) {
            return chess;
        }

        ResultSet resultSet = chessDao.lastTick(personId);

        if(resultSet.next()) {
            Timing timing = new Timing(resultSet.getTime("startTime"), resultSet.getTime("endTime"),
                    resultSet.getDate("day"));

            chess = new Chess(resultSet.getInt("chess_id"),resultSet.getInt("personid"),
                    timing,resultSet.getInt("numMoves"),resultSet.getString("result"));

        }else {
            logger.warning("No tick available for you");
        }
        return chess;
    }

    /**
     * This function help you to find the longest streak in chess
     * @param personId    the peron id
     * @throws SQLException
     */
    @Override
    public int longestStreak(int personId) throws SQLException {
        /**
         * check the longest streak is available or not in cache memory
         */
        Integer longestStreak = (Integer) lru.get(personId + "_chess_longestStreak");

        /**
         * if longest streak is available in cache then just return it.
         * if not available, then if condition doesn't execute
         */
        if(longestStreak != null){
            return longestStreak;
        }

        ResultSet resultSet = chessDao.longestChessStreak(personId);

        /**
         * use to store dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        /**
         * get all the details of person in chess table and perform operation on it.
         * To find the longest streak
         */
        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        longestStreak =  similarRequirement.longestStreak(days);

        /**
         * insert into cache
         */
        lru.put(personId + "_chess_longestStreak" , longestStreak);

        return  longestStreak;
    }

    /**
     * This function help you to find latest streak in chess
     * @param personId    the person id
     * @throws SQLException
     * @return
     */
    @Override
    public int latestStreak(int personId) throws SQLException {
        /**
         * check the latest streak is available in cache or not
         */
        Integer latestStreak = (Integer) lru.get(personId + "_chess_latestStreak");

        /**
         * if latest streak is available in cache, then just return it.
         * if not available, then if condition doesn't execute
         */
        if(latestStreak != null) {
            return latestStreak;
        }

        ResultSet resultSet = chessDao.longestChessStreak(personId);

        /**
         * use to store dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        /**
         * get all the details of person in chess table and perform operation on it.
         * To find the longest streak
         */
        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        latestStreak =  similarRequirement.latestStreak(days);

        /**
         * insert into cache
         */
        lru.put(personId + "_chess_latestStreak" , latestStreak);
        return latestStreak;
    }
}
