package main.java.com.zs.hobbies;

import main.java.com.zs.hobbies.dao.DataBase;
import main.java.com.zs.hobbies.dto.*;
import main.java.com.zs.hobbies.service.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Application {
    private static Logger logger;

    public static void main(String st[]) throws SQLException, ClassNotFoundException, IOException {
        /**
         * initialize logger
         */
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Application.class.getName());
        logger.info("Successfully logger start");

        /**
         * is use to make the connection between java program and database
         */
        DataBase dataBase = new DataBase();

        /**
         * insert person in database table
         */
        Person person = new Person(9,"Rihan","9311851147","Dadri");
        PersonService personService = new PersonServiceImpl(dataBase.getCon());
       // personService.insert(person);

        /**
         * insert travel hobbies
         */
        Timing time = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2020-04-05"));
        Travelling travelling = new Travelling(person,time,"delhi","mumbi",5.6f);
        TravellingService travellingService = new TravellingServiceImpl(dataBase.getCon());
        //travellingService.insert(travelling);

        /**
         * insert chess hobbies
         */
        Chess chess = new Chess(person,time,5,"win");
        ChessService chessService = new ChessServiceImpl(dataBase.getCon());
        //chessService.insert(chess);

        /**
         * insert badminton hobbie
         */
        Badminton badminton = new Badminton(person,time, 8,"win");
        BadmintonService badmintonService = new BadmintonServiceImpl(dataBase.getCon());
       // badmintonService.insert(badminton);

        /**
         * insert Video Watching hobbies in table
         */
        VideoWatching videoWatching = new VideoWatching(person,time,"Lucifer morningstar");
        VideoWatchingService videoWatchingService = new VideoWatchingServiceImpl(dataBase.getCon());
        //videoWatchingService.insert(videoWatching);


        /**
         * find the hobbies on date
         */
//        badmintonService.dateDetails(person, Date.valueOf("2015-04-02"));
//        travellingService.dateDetails(person, Date.valueOf("2015-04-02"));
//        videoWatchingService.dateDetails(person, Date.valueOf("2015-04-02"));
//        chessService.dateDetails(person, Date.valueOf("2015-04-02"));


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

        dataBase.getCon().close();
    }
}
