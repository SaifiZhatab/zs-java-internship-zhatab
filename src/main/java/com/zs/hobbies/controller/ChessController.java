package main.java.com.zs.hobbies.controller;

import main.java.com.zs.hobbies.cache.LruService;
import main.java.com.zs.hobbies.dto.Chess;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.Timing;
import main.java.com.zs.hobbies.exception.InvalidInputException;
import main.java.com.zs.hobbies.service.ChessService;
import main.java.com.zs.hobbies.service.ChessServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class ChessController {
    private Person person;
    private TimingController timingController;

    private ChessService chessService;
    Scanner in = new Scanner(System.in);

    public ChessController(Connection con, LruService lru) throws SQLException, IOException, ClassNotFoundException {
        chessService = new ChessServiceImpl(con,lru);
        timingController = new TimingController();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    boolean checkNumOfMove(int move) {
        if(move >=0 && move <= 100) {
            return true;
        }else {
            return false;
        }
    }
    boolean checkResult(String result) {
        if(result.compareToIgnoreCase("win")==0 || result.compareToIgnoreCase("draw")==0
            || result.compareToIgnoreCase("loose")==0 ) {
            return true;
        }else {
            return false;
        }
    }
    public void insert() throws ParseException, SQLException {
        Chess chess;
        Timing timing;
        int numOfMove;
        String result;
        timing = timingController.getTime();

        System.out.print("Enter number of player in badminton : ");
        numOfMove = in.nextInt();

        if(!checkNumOfMove(numOfMove)) {
            throw new InvalidInputException(400,"Wrong number of moves details given by user");
        }

        System.out.print("Enter the result of game ");
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
