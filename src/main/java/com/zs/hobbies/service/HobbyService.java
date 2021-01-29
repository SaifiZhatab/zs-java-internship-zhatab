package com.zs.hobbies.service;

import java.sql.Date;
import java.util.Set;

public interface HobbyService<E> {
    /**
     * This function help you to find the user hobbies details on the basis of date
     * @param personId the person id
     * @param date the date
     */
    Set<E> dateDetails(int personId, Date date);

    /**
     * This function help you to find the last tick of user
     * @param personId the person id
     * @return
     */
    E lastTick(int personId);

    /**
     * This function help you to find the longest streak in databse
     * @param personId the person id
     */
    int longestStreak(int personId);

    /**
     * this function help you to find the latest streak of user
     * @param personId the person id
     * @return
     */
    int latestStreak(int personId);
}
