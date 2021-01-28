package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dao.ChessDao;
import com.zs.hobbies.dto.Chess;
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
 * This class give service to Chess hobby
 */
public class ChessServiceImpl implements ChessService {
    private ChessDao chessDao;
    private Logger logger;
    private LruService lru;
    private SimilarRequirement similarRequirement;
    private Validator validator;
    /**
     * This is a constructor
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public ChessServiceImpl(Connection con,LruService lru) {
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
    }

    /**
     * This function help you to fetch the data on the basis of date
     * @param personId    the person id
     * @param date      date
     * @throws InvalidInputException
     */
    @Override
    public void dateDetails(int personId, Date date) throws InvalidInputException, SQLException {
        validator.checkDate(date);

        ResultSet resultSet = chessDao.dateChessDetails(personId,date);

        logger.info("This is all Chess details on " + date.toString());
        logger.info("startTime   :  EndTime   : Number of Moves   :   result");
        while(resultSet.next()){
            logger.info(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
                    + " "+  resultSet.getInt("nummoves") + " " + resultSet.getString("result")) ;
        }
    }

    /**
     * This function help you to find the last tick of Chess
     * @param personId    the person object
     * @throws SQLException
     */
    @Override
    public void lastTick(int personId) throws SQLException {
        Node node = lru.get(String.valueOf(personId)  + "_chess");

        /**
         * when last tick present in lru cache
         */
        if(node != null && node.getChess() != null) {
            logger.info("This is the last tick of Chess");
            logger.info("Chess id : " + node.getChess().getId());

            return ;
        }

        ResultSet resultSet = chessDao.lastTick(personId);

        if(resultSet.next()) {
            logger.info("This is the last tick of Chess  ");
            logger.info("Chess id : " + resultSet.getInt(1));
        }else {
            logger.warning("No tick available for you");
        }
    }

    /**
     * This function help you to find the longest streak in chess
     * @param personId    the peron id
     * @throws SQLException
     */
    @Override
    public void longestStreak(int personId) throws SQLException {
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
        int longestStreak = similarRequirement.longestStreak(days);
        logger.info("Longest Chess Streak for " + personId + " : " + longestStreak);

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }

    /**
     * This function help you to find latest streak in chess
     * @param personId    the person id
     * @throws SQLException
     */
    @Override
    public void latestStreak(int personId) throws SQLException {
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

        int longestStreak = similarRequirement.latestStreak(days);
        logger.info("Latest Chess Streak for " + personId + " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }
}
