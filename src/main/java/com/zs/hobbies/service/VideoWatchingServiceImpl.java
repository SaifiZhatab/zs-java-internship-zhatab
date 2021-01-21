package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.database.DataBase;
import main.java.com.zs.hobbies.database.VideoWatchingDataBase;
import main.java.com.zs.hobbies.entity.Person;
import main.java.com.zs.hobbies.entity.VideoWatching;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class VideoWatchingServiceImpl implements VideoWatchingService {
    VideoWatchingDataBase videoWatchingDataBase;

    public VideoWatchingServiceImpl() throws SQLException, ClassNotFoundException {
        videoWatchingDataBase = new VideoWatchingDataBase();
    }

    @Override
    public void insertVideo(VideoWatching videoWatching) throws SQLException {
        int check = videoWatchingDataBase.insertVideo(videoWatching);

        if(check == 1) {
            System.out.println("Successfully Chess enter in database");
        }else {
            System.out.println("Some internally error comes.Please try again");
        }
    }

    @Override
    public void dateDetails(Person person, Date date) throws SQLException {
        ResultSet resultSet = videoWatchingDataBase.dateVideoWatchingDetails(person,date);

        System.out.println("This is all VideoWatching details on " + date.toString());
        System.out.println("startTime   :  EndTime   : Title  ");
        while(resultSet.next()){
            System.out.println(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
                    + " "+  resultSet.getString("title") ) ;
        }
    }

    @Override
    public void lastTick(Person person) throws SQLException {
        ResultSet resultSet = videoWatchingDataBase.lastTick(person);

        if(resultSet.next()) {
            System.out.println("This is the last tick of Video Watching ");

            System.out.println("Date : " + resultSet.getDate("day").toString() );
            System.out.println("Start time : " + resultSet.getTime("startTime"));
            System.out.println("End time : " + resultSet.getTime("endTime"));
            System.out.println("Title : " +  resultSet.getString("title"));
        }else {
            System.out.println("No tick available for you");
        }
    }

    @Override
    public void longestStreak(Person person) throws SQLException {
        ResultSet resultSet = videoWatchingDataBase.longestVideoWatchingStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
        int longestStreak = SimilarRequirement.longestStreak(days);
        System.out.print("Longest Video Streak for " + person.getName() + " : " + longestStreak);

        if(longestStreak == 1) {
            System.out.println(" day");
        }else {
            System.out.println(" days");
        }
    }

    @Override
    public void latestStreak(Person person) throws SQLException {
        ResultSet resultSet = videoWatchingDataBase.longestVideoWatchingStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = SimilarRequirement.latestStreak(days);
        System.out.print("Latest VideoWatching Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            System.out.println(" day");
        }else {
            System.out.println(" days");
        }
    }
}
