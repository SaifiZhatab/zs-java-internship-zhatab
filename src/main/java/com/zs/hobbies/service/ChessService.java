package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.entity.Chess;
import main.java.com.zs.hobbies.entity.Person;

import java.sql.Date;
import java.sql.SQLException;

/**
 * This is a remote class of Chess.
 * this class help you to achieve run time polymorphism.
 * this class help you to implement abstraction
 * this class control all the functionality
 */
public interface ChessService {
    void insertChess(Chess chess) throws SQLException;
    void dateDetails(Person person, Date date) throws SQLException;
    void lastTick(Person person) throws SQLException;
    void longestStreak(Person person) throws SQLException;
    void latestStreak(Person person) throws SQLException;
}
