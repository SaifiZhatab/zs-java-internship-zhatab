package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.service.ChessService;
import com.zs.hobbies.service.ChessServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This is a Chess Controller class
 * that call the chess service call and using service interact with database
 */
public class ChessController {
    private Person person;
    private Logger logger;

    private ChessService chessService;
    Scanner in = new Scanner(System.in);

    public ChessController(Connection con, Cache lru) throws SQLException, IOException, ClassNotFoundException {
        chessService = new ChessServiceImpl(con,lru);

        logger = Logger.getLogger(Application.class.getName());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }



    /**
     * This function help you to insert the chess object in database
     * @throws ParseException
     * @throws SQLException
     */
    public void insert(Chess chess) throws ParseException, SQLException {
        chessService.insert(chess);
    }

    /**
     * This function help you to find the longest streak in the chess
     * @param personId  the person ID
     * @throws SQLException
     */
    public void longestStreak(int personId) throws SQLException {
        int longestStreak = chessService.longestStreak(personId);
        logger.info("Chess longest streak : " + longestStreak);
    }

    /**
     * This function help you to find the latest streak in the chess
     * @param personId  the person ID
     * @throws SQLException
     */
    public void latestStreak(int personId) throws SQLException {
        int latestStreak = chessService.latestStreak(personId);
        logger.info("Chess latest Streak : " + latestStreak);
    }

    /**
     * This function help you to find the last streak in the chess
     * @param personId  the person ID
     * @throws SQLException
     */
    public void lastTick(int personId) throws SQLException {
        Chess chess = (Chess) chessService.lastTick(personId);

        if(chess != null) {
            logger.info("Chess last tick id : " + chess.getId());
        }else {
            logger.warning("No last tick present");
        }
    }

    /**
     * This function help you to find the details according to details in the chess
     * @param personId  the person ID
     * @throws SQLException
     */
    public void searchByDate(int personId, Date date) throws SQLException {
        Set<Chess> setDetails = chessService.dateDetails(personId,date);

        Iterator<Chess> iterator = setDetails.iterator();

        while(iterator.hasNext()) {
            logger.info("Chess id : " + iterator.next().getId());
        }
    }
}
