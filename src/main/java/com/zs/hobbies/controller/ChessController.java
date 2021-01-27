package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.service.ChessService;
import com.zs.hobbies.service.ChessServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This is a Chess Controller class
 * that call the chess service call and using service interact with database
 */
public class ChessController {
    private Person person;
    private TimingController timingController;
    private Logger logger;

    private ChessService chessService;
    Scanner in = new Scanner(System.in);

    public ChessController(Connection con, LruService lru) throws SQLException, IOException, ClassNotFoundException {
        chessService = new ChessServiceImpl(con,lru);
        timingController = new TimingController();

        logger = Logger.getLogger(Application.class.getName());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * This function check the number of move is valid or not
     * if the number of move greater than 0 and less or equal to 100, then return true else return false
     * @param move   the number of move
     * @return  true / false
     */
    boolean checkNumOfMove(int move) {
        if(move >=0 && move <= 100) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * This function check the result is valid or not
     * the only valid string is win/draw/losse
     * if the string is either win,draw or losse, then it return true else return false
     * @param result  the result string
     * @return true/false
     */
    boolean checkResult(String result) {
        if(result.compareToIgnoreCase("win")==0 || result.compareToIgnoreCase("draw")==0
            || result.compareToIgnoreCase("loose")==0 ) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * This funciton help you to insert the chess object in database
     * @throws ParseException
     * @throws SQLException
     */
    public void insert() throws ParseException, SQLException {
        Chess chess;
        Timing timing;
        int numOfMove;
        String result;
        timing = timingController.getTime();
        logger.info("Enter number of player in badminton : ");
        numOfMove = in.nextInt();

        if(!checkNumOfMove(numOfMove)) {
            throw new InvalidInputException(400,"Wrong number of moves details given by user");
        }

        logger.info("Enter the result of game ");
        result = in.next();

        if(!checkResult(result)) {
            throw new InvalidInputException(400,"Wrong result status given by user");
        }

        chess = new Chess(person,timing,numOfMove,result);

        chessService.insert(chess);
    }
    public void insert(Person person) throws ParseException, SQLException {
        insert();
        this.person = person;
    }
}
