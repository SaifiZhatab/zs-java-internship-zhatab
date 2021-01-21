package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.Controller;
import main.java.com.zs.hobbies.dao.ChessDataBase;
import main.java.com.zs.hobbies.dto.Chess;
import main.java.com.zs.hobbies.dto.Person;

import java.io.FileInputStream;
import java.io.IOException;
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
    ChessDataBase chessDataBase ;
    private Logger logger;

    /**
     * This is a constructor
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public ChessServiceImpl() throws SQLException, ClassNotFoundException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Controller.class.getName());

        logger.info("Successfully Chess service start ");

        chessDataBase = new ChessDataBase();
    }

    /**
     * This function help you to insert Chess details into Chess database table
     * @param chess the chess object
     * @throws SQLException
     */
    @Override
    public void insertChess(Chess chess) throws SQLException {
        int check = chessDataBase.insertChess(chess);

        if(check == 1) {
            logger.info("Successfully Chess enter in database");
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
        ResultSet resultSet = chessDataBase.dateChessDetails(person,date);

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
        ResultSet resultSet = chessDataBase.lastTick(person);

        if(resultSet.next()) {
            logger.info("This is the last tick of Chess  ");

            logger.info("Date : " + resultSet.getDate("day").toString() );
            logger.info("Start time : " + resultSet.getTime("startTime"));
            logger.info("End time : " + resultSet.getTime("endTime"));
            logger.info("Number of player : " + resultSet.getInt("numMoves"));
            logger.info("Result : " + resultSet.getString("result") );
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
        ResultSet resultSet = chessDataBase.longestChessStreak(person);

        /**
         * use to store dates in sorted order
         */
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
        int longestStreak = SimilarRequirement.longestStreak(days);
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
        ResultSet resultSet = chessDataBase.longestChessStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = SimilarRequirement.latestStreak(days);
        logger.info("Latest Chess Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }
}
