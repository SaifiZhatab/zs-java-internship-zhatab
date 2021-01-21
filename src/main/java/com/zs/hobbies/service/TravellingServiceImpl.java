package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.database.DataBase;
import main.java.com.zs.hobbies.database.TravellingDataBase;
import main.java.com.zs.hobbies.entity.Person;
import main.java.com.zs.hobbies.entity.Travelling;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class TravellingServiceImpl implements TravellingService {
    TravellingDataBase travellingDataBase;

    public TravellingServiceImpl() throws SQLException, ClassNotFoundException {
        travellingDataBase = new TravellingDataBase();
    }

    @Override
    public void insertTravelling(Travelling travelling) throws SQLException {
        int check = travellingDataBase.insertTravelling(travelling);

        if(check == 1) {
            System.out.println("Successfully travelling hobbies enter in database");
        }else {
            System.out.println("Some internally error comes.Please try again");
        }
    }

    @Override
    public void dateDetails(Person person, Date date) throws SQLException {
        ResultSet resultSet = travellingDataBase.dateTravellingDetails(person,date);

        System.out.println("This is all Travelling details on " + date.toString());
        System.out.println("startTime   :  EndTime   : startPoint   :   endPoint  : distance");
        while(resultSet.next()){
            System.out.println(resultSet.getTime("startTime") + " " + resultSet.getTime("endTime")
                    + " "+  resultSet.getString("startPoint") + " " + resultSet.getString("endPoint") +
                    " : " + resultSet.getFloat("distance")) ;
        }
    }

    @Override
    public void lastTick(Person person) throws SQLException {
        ResultSet resultSet = travellingDataBase.lastTick(person);

        if(resultSet.next()) {
            System.out.println("This is the last tick of Travelling ");

            System.out.println("Date : " + resultSet.getDate("day").toString() );
            System.out.println("Start Time : " + resultSet.getTime("startTime"));
            System.out.println("End time : " +  resultSet.getTime("endTime") );
            System.out.println("Start Location : " + resultSet.getString("startPoint"));
            System.out.println("End Location : " +  resultSet.getString("endPoint"));
            System.out.println("Total distance travel : " + resultSet.getFloat("distance"));
        }else {
            System.out.println("No tick available for you");
        }
    }

    @Override
    public void longestStreak(Person person) throws SQLException {
        ResultSet resultSet = travellingDataBase.longestTravellingStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }
        System.out.println(person.getId());
        System.out.println(days);
        int longestStreak = SimilarRequirement.longestStreak(days);
        System.out.print("Longest Travelling Streak for " + person.getName() + " : " + longestStreak);

        if(longestStreak == 1) {
            System.out.println(" day");
        }else {
            System.out.println(" days");
        }
    }

    @Override
    public void latestStreak(Person person) throws SQLException {
        ResultSet resultSet = travellingDataBase.longestTravellingStreak(person);
        SortedSet<String> days = new TreeSet<String>();

        while(resultSet.next()){
            days.add(resultSet.getDate("day").toString() );
        }

        int longestStreak = SimilarRequirement.latestStreak(days);
        System.out.print("Latest Travelling Streak for " + person.getName() + " : " + longestStreak );

        if(longestStreak == 1) {
            System.out.println(" day");
        }else {
            System.out.println(" days");
        }
    }
}
