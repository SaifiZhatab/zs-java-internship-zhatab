package com.zs.hobbies.service;

import com.zs.hobbies.dto.Person;

import java.sql.Date;
import java.sql.SQLException;

public interface HobbyService {
    /**
     * This function help you to find the user hobbies details on the basis of date
     * @param personId the person id
     * @param date the date
     * @throws SQLException
     */
    void dateDetails(int personId, Date date) throws SQLException;

    /**
     * This function help you to find the last tick of user
     * @param personId the person id
     * @throws SQLException
     */
    void lastTick(int personId) throws SQLException;

    /**
     * This function help you to find the longest streak in databse
     * @param personId the person id
     * @throws SQLException
     */
    void longestStreak(int personId) throws SQLException;

    /**
     * this function help you to find the latest streak of user
     * @param personId the person id
     * @throws SQLException
     */
    void latestStreak(int personId) throws SQLException;
}
