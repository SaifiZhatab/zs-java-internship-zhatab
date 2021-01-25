package main.java.com.zs.hobbies;

import main.java.com.zs.hobbies.cache.LruService;
import main.java.com.zs.hobbies.cache.LruServiceImpl;
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
         * apply LRU cache on Hobby application
         */
        LruService lru = new LruServiceImpl(5);

        /**
         * is use to make the connection between java program and database
         */
        DataBase dataBase = new DataBase();



        /**
         * insert person in database table
         */
        Person person = new Person(13,"Rihan","9311851147","Dadri");
        PersonService personService = new PersonServiceImpl(dataBase.getCon(),lru);
        //personService.insert(person);


        /**
         * insert travel hobbies
         */
        Timing time = new Timing(Time.valueOf("09:09:39"),Time.valueOf("10:49:39"), Date.valueOf("2021-01-25"));
        Travelling travelling = new Travelling(16,person,time,"delhi","mumbi",5.6f);
        TravellingService travellingService = new TravellingServiceImpl(dataBase.getCon(),lru);
       // travellingService.insert(travelling);


        /**
         * insert chess hobbies
         */
        Chess chess = new Chess(17,person,time,3,"win");
        ChessService chessService = new ChessServiceImpl(dataBase.getCon(),lru);
        //chessService.insert(chess);


        /**
         * insert badminton hobbie
         */
        Badminton badminton = new Badminton(20,person,time, 8,"win");
        BadmintonService badmintonService = new BadmintonServiceImpl(dataBase.getCon(),lru);
        //badmintonService.insert(badminton);


        /**
         * insert Video Watching hobbies in table
         */
        VideoWatching videoWatching = new VideoWatching(14,person,time,"Lucifer morningstar");
        VideoWatchingService videoWatchingService = new VideoWatchingServiceImpl(dataBase.getCon(),lru);
        //videoWatchingService.insert(videoWatching);


      //lru.display();
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
