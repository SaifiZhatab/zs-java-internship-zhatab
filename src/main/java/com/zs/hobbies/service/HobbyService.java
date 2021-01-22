package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.dto.Person;

import java.sql.Date;
import java.sql.SQLException;

public interface HobbyService {
    void dateDetails(Person person, Date date) throws SQLException;
    void lastTick(Person person) throws SQLException;
    void longestStreak(Person person) throws SQLException;
    void latestStreak(Person person) throws SQLException;
}
