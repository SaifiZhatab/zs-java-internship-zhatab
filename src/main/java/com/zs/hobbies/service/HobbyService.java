package com.zs.hobbies.service;

import com.zs.hobbies.dto.VideoWatching;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Set;

public interface HobbyService<E> {
    /**
     * This function help you to find the user hobbies details on the basis of date
     * @param personId the person id
     * @param date the date
     * @throws SQLException
     */
    Set<E> dateDetails(int personId, Date date) throws SQLException;

    /**
     * This function help you to find the last tick of user
     * @param personId the person id
     * @throws SQLException
     * @return
     */
    E lastTick(int personId) throws SQLException;

    /**
     * This function help you to find the longest streak in databse
     * @param personId the person id
     * @throws SQLException
     */
    int longestStreak(int personId) throws SQLException;

    /**
     * this function help you to find the latest streak of user
     * @param personId the person id
     * @throws SQLException
     * @return
     */
    int latestStreak(int personId) throws SQLException;
}
