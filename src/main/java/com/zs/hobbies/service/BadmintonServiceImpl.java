package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.database.BadmintonDataBase;
import main.java.com.zs.hobbies.database.DataBase;
import main.java.com.zs.hobbies.entity.Badminton;
import main.java.com.zs.hobbies.entity.Person;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BadmintonServiceImpl implements BadmintonService {
    BadmintonDataBase badmintonDataBase;

    public BadmintonServiceImpl() throws SQLException, ClassNotFoundException {
        badmintonDataBase = new BadmintonDataBase();
    }

    @Override
    public void insertBadminton(Badminton badminton) throws SQLException {
        int check = badmintonDataBase.insertBadminton(badminton);

        if(check == 1) {
            System.out.println("Successfully Badminton enter in database");
        }else {
            System.out.println("Some internally error comes.Please try again");
        }

    }

    @Override
    public void dateDetails(Person person, Date date) throws SQLException {
        ResultSet resultSet = badmintonDataBase.dateBadmintonDetails(person,date);

        System.out.println("This is all badminton details on " + date.toString());
        System.out.println("startTime : EndTime : Number of Player : result");
        while(resultSet.next()){
            System.out.println(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
              + " "+  resultSet.getInt("numPlayers") + " " + resultSet.getString("result")) ;
        }
    }

    @Override
    public void lastTick(Person person) throws SQLException {
        ResultSet resultSet = badmintonDataBase.lastTick(person);

        if(resultSet.next()) {
            System.out.println("This is the last tick of badminton ");

            System.out.println("Date : " + resultSet.getDate("day").toString() );
            System.out.println("Start time : " + resultSet.getTime("startTime"));
            System.out.println("End time : " + resultSet.getTime("endTime"));
            System.out.println("Number of players : " + resultSet.getInt("numPlayers"));
            System.out.println("Result : " + resultSet.getString("result") );

        }else {
            System.out.println("No tick available for you");
        }

    }

    @Override
    public void longestStreak(Person person) throws SQLException {
        ResultSet resultSet = badmintonDataBase.longestBadmintonStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = SimilarRequirement.longestStreak(days);
        System.out.print("Longest Badminton Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            System.out.println(" day");
        }else {
            System.out.println(" days");
        }
    }

    @Override
    public void latestStreak(Person person) throws SQLException {
        ResultSet resultSet = badmintonDataBase.longestBadmintonStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
        int longestStreak = SimilarRequirement.latestStreak(days);
        System.out.print("Latest Badminton Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            System.out.print(" day");
        }else {
            System.out.println(" days");
        }
    }
}
