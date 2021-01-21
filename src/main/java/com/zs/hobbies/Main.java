package main.java.com.zs.hobbies;

import main.java.com.zs.hobbies.database.DataBase;
import main.java.com.zs.hobbies.entity.*;
import main.java.com.zs.hobbies.service.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class Main {

    public static void main(String st[]) throws SQLException, ClassNotFoundException {
        DataBase db = new DataBase();

        /**
         * insert person in database table
         */
        Person person = new Person(9,"Rahul","8235456789","MP");
        PersonService personService = new PersonServiceImpl();
       // personService.insertPerson(person);

        /**
         * insert travel hobbies
         */
        Timing time = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2015-04-05"));
        Travelling travelling = new Travelling(person,time,"delhi","mumbi",5.6f);
        TravellingService travellingService = new TravellingServiceImpl();
       // travellingService.insertTravelling(travelling);

        /**
         * insert chess hobbies
         */
        Chess chess = new Chess(person,time,5,"win");
        ChessService chessService = new ChessServiceImpl();
       // chessService.insertChess(chess);

        /**
         * insert badminton hobbie
         */
        Badminton badminton = new Badminton(person,time, 8,"win");
        BadmintonService badmintonService = new BadmintonServiceImpl();
       // badmintonService.insertBadminton(badminton);

        /**
         * insert Video Watching hobbies in table
         */
        VideoWatching videoWatching = new VideoWatching(person,time,"Lucifer morningstar");
        VideoWatchingService videoWatchingService = new VideoWatchingServiceImpl();
       // videoWatchingService.insertVideo(videoWatching);


        /**
         * find the hobbies on date
         */
//        badmintonService.dateDetails(person,  (java.sql.Date)Date.valueOf("2015-04-02"));
//        travellingService.dateDetails(person,  (java.sql.Date)Date.valueOf("2015-04-02"));
//        videoWatchingService.dateDetails(person,  (java.sql.Date)Date.valueOf("2015-04-02"));
//        chessService.dateDetails(person,  (java.sql.Date)Date.valueOf("2015-04-02"));


        /**
         * check last tick
         */
//        badmintonService.lastTick(person);
//        travellingService.lastTick(person);
//        videoWatchingService.lastTick(person);
//        chessService.lastTick(person);

        /**
         * longest streak
         */
//        badmintonService.longestStreak(person);
//        travellingService.longestStreak(person);
//        videoWatchingService.longestStreak(person);
//        chessService.longestStreak(person);

        /**
         * latest streak
         */
//        badmintonService.latestStreak(person);
//        travellingService.latestStreak(person);
//        videoWatchingService.latestStreak(person);
//        chessService.latestStreak(person);
    }
}
