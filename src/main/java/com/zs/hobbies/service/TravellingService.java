package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.Travelling;

import java.sql.Date;
import java.sql.SQLException;

/**
 * This is a remote class of Travelling.
 * this class help you to achieve run time polymorphism.
 * this class help you to implement abstraction
 * this class control all the functionality
 */
public interface TravellingService {
    void insertTravelling(Travelling travelling) throws SQLException;
    void dateDetails(Person person, Date date) throws SQLException;
    void lastTick(Person person) throws SQLException;
    void longestStreak(Person person) throws SQLException;
    void latestStreak(Person person) throws SQLException;
}
