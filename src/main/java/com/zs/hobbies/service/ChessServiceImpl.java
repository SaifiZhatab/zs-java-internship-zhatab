package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.database.ChessDataBase;
import main.java.com.zs.hobbies.database.DataBase;
import main.java.com.zs.hobbies.entity.Chess;
import main.java.com.zs.hobbies.entity.Person;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ChessServiceImpl implements ChessService {
    ChessDataBase chessDataBase ;

    public ChessServiceImpl() throws SQLException, ClassNotFoundException {
        chessDataBase = new ChessDataBase();
    }

    @Override
    public void insertChess(Chess chess) throws SQLException {
        int check = chessDataBase.insertChess(chess);

        if(check == 1) {
            System.out.println("Successfully Chess enter in database");
        }else {
            System.out.println("Some internally error comes.Please try again");
        }
    }

    @Override
    public void dateDetails(Person person, Date date) throws SQLException {
        ResultSet resultSet = chessDataBase.dateChessDetails(person,date);

        System.out.println("This is all Chess details on " + date.toString());
        System.out.println("startTime   :  EndTime   : Number of Moves   :   result");
        while(resultSet.next()){
            System.out.println(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
                    + " "+  resultSet.getInt("nummoves") + " " + resultSet.getString("result")) ;
        }
    }

    @Override
    public void lastTick(Person person) throws SQLException {
        ResultSet resultSet = chessDataBase.lastTick(person);

        if(resultSet.next()) {
            System.out.println("This is the last tick of Chess  ");

            System.out.println("Date : " + resultSet.getDate("day").toString() );
            System.out.println("Start time : " + resultSet.getTime("startTime"));
            System.out.println("End time : " + resultSet.getTime("endTime"));
            System.out.println("Number of player : " + resultSet.getInt("numMoves"));
            System.out.println("Result : " + resultSet.getString("result") );
        }else {
            System.out.println("No tick available for you");
        }
    }

    @Override
    public void longestStreak(Person person) throws SQLException {
        ResultSet resultSet = chessDataBase.longestChessStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
        int longestStreak = SimilarRequirement.longestStreak(days);
        System.out.print("Longest Chess Streak for " + person.getName() + " : " + longestStreak);

        if(longestStreak == 1) {
            System.out.println(" day");
        }else {
            System.out.println(" days");
        }
    }

    @Override
    public void latestStreak(Person person) throws SQLException {
        ResultSet resultSet = chessDataBase.longestChessStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = SimilarRequirement.latestStreak(days);
        System.out.print("Latest Chess Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            System.out.println(" day");
        }else {
            System.out.println(" days");
        }
    }
}
