package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.Application;
import main.java.com.zs.hobbies.cache.LruService;
import main.java.com.zs.hobbies.dao.VideoWatchingDataBase;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.VideoWatching;
import main.java.com.zs.hobbies.cache.Node;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class VideoWatchingServiceImpl implements VideoWatchingService {
    private VideoWatchingDataBase videoWatchingDataBase;
    private Logger logger;
    private LruService lru;
    private SimilarRequirement similarRequirement;

    public VideoWatchingServiceImpl(Connection con,LruService lru) throws SQLException, ClassNotFoundException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully VideoWatching service start ");
        videoWatchingDataBase = new VideoWatchingDataBase(con);

        this.lru = lru;
        similarRequirement = new SimilarRequirement();
    }

    @Override
    public void insert(VideoWatching videoWatching) throws SQLException {
        /**
         * if user doesn't give id, then it take automatically
         */
        if(videoWatching.getId() == -1) {
            videoWatching.setId(videoWatchingDataBase.findHigherKey());
        }

        lru.put(new Node(videoWatching));

        int check = videoWatchingDataBase.insertVideo(videoWatching);

        if(check == 1) {
            logger.info("Successfully Chess enter in database");
        }else {
            logger.warning("Some internally error comes.Please try again");
        }
    }

    @Override
    public void dateDetails(Person person, Date date) throws SQLException {
        ResultSet resultSet = videoWatchingDataBase.dateVideoWatchingDetails(person,date);

        logger.info("This is all VideoWatching details on " + date.toString());
        logger.info("startTime   :  EndTime   : Title  ");
        while(resultSet.next()){
            logger.info(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
                    + " "+  resultSet.getString("title") ) ;
        }
    }

    @Override
    public void lastTick(Person person) throws SQLException {
        ResultSet resultSet = videoWatchingDataBase.lastTick(person);

        if(resultSet.next()) {
            logger.info("This is the last tick of Video Watching ");

            logger.info("Date : " + resultSet.getDate("day").toString() );
            logger.info("Start time : " + resultSet.getTime("startTime"));
            logger.info("End time : " + resultSet.getTime("endTime"));
            logger.info("Title : " +  resultSet.getString("title"));
        }else {
            logger.warning("No tick available for you");
        }
    }

    @Override
    public void longestStreak(Person person) throws SQLException {
        ResultSet resultSet = videoWatchingDataBase.longestVideoWatchingStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
        int longestStreak = similarRequirement.longestStreak(days);
        logger.info("Longest Video Streak for " + person.getName() + " : " + longestStreak);

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }

    @Override
    public void latestStreak(Person person) throws SQLException {
        ResultSet resultSet = videoWatchingDataBase.longestVideoWatchingStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = similarRequirement.latestStreak(days);
        logger.info("Latest VideoWatching Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            logger.info(" day");
        }else {
            logger.info(" days");
        }
    }
}
