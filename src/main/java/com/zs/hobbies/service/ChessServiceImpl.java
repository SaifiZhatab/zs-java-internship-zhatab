package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dao.ChessDao;
import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.cache.Node;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class give service to Chess hobby
 */
public class ChessServiceImpl implements ChessService {
    private ChessDao chessDao;
    private Logger logger;
    private LruService lru;
    private SimilarRequirement similarRequirement;
    /**
     * This is a constructor
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public ChessServiceImpl(Connection con,LruService lru) throws SQLException, ClassNotFoundException, IOException {
        logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully Chess service start ");

        this.lru = lru;
        chessDao = new ChessDao(con);
        similarRequirement = new SimilarRequirement();
    }

    /**
     * This function help you to insert Chess details into Chess database table
     * @param chess the chess object
     * @throws SQLException
     */
    @Override
    public void insert(Chess chess) throws SQLException {
        /**
         * if user doesn't give id, then it take automatically
         */
        if(chess.getId() == -1) {
            chess.setId(chessDao.findHigherKey());
        }

        int check = chessDao.insertChess(chess);

        if(check == 1) {
            logger.info("Successfully Chess enter in database");

            /**
             * insert hobby into the LRU cache
             */
            lru.put(String.valueOf(chess.getPerson().getId()) + "_chess",new Node(chess));
        }else {
            logger.warning("Some internally error comes.Please try again");
        }
    }

    /**
     * This function help you to fetch the data on the basis of date
     * @param person    the person object
     * @param date      date
     * @throws SQLException
     */
    @Override
    public void dateDetails(Person person, Date date) throws SQLException {
        ResultSet resultSet = chessDao.dateChessDetails(person,date);

        logger.info("This is all Chess details on " + date.toString());
        logger.info("startTime   :  EndTime   : Number of Moves   :   result");
        while(resultSet.next()){
            logger.info(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
                    + " "+  resultSet.getInt("nummoves") + " " + resultSet.getString("result")) ;
        }
    }

    /**
     * This function help you to find the last tick of Chess
     * @param person    the person object
     * @throws SQLException
     */
    @Override
    public void lastTick(Person person) throws SQLException {
        Node node = lru.get(String.valueOf(person.getId())  + "_chess");

        /**
         * when last tick present in lru cache
         */
        if(node != null && node.getChess() != null) {
            logger.info("This is the last tick of Chess");
            logger.info("Chess id : " + node.getChess().getId());

            return ;
        }

        ResultSet resultSet = chessDao.lastTick(person);

        if(resultSet.next()) {
            logger.info("This is the last tick of Chess  ");
            logger.info("Chess id : " + resultSet.getInt(1));
        }else {
            logger.warning("No tick available for you");
        }
    }

    /**
     * This function help you to find the longest streak in chess
     * @param person    the peron object
     * @throws SQLException
     */
    @Override
    public void longestStreak(Person person) throws SQLException {
        ResultSet resultSet = chessDao.longestChessStreak(person);

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
        logger.info("Longest Chess Streak for " + person.getName() + " : " + longestStreak);

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }

    /**
     * This function help you to find latest streak in chess
     * @param person    the person object
     * @throws SQLException
     */
    @Override
    public void latestStreak(Person person) throws SQLException {
        ResultSet resultSet = chessDao.longestChessStreak(person);

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
        logger.info("Latest Chess Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }
}
