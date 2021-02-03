package com.zs.hobbies.service;

import com.zs.hobbies.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is badminton service testing implementation
 */
class SimilarRequirementTest {

    private SimilarRequirement similarRequirement;

    @BeforeEach
    void setUp() {
        /**
         * initialise service service object
         */
        similarRequirement = new SimilarRequirement();
    }

    /**
     * this function check the longest streak is null
     */
    @Test
    void longestStreakNull() {
        SortedSet<String> dates = null;

        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
            () -> {
                similarRequirement.longestStreak(dates);
            });
    }

    /**
     * This function test when the streak date isn't empty
     */
    @Test
    void longestStreakNotNull() {
        SortedSet<String> dates = new TreeSet<String>() ;
        dates.add("2019-01-29");
        dates.add("2019-01-30");
        dates.add("2019-01-31");
        dates.add("2019-02-01");
        dates.add("2020-12-31");
        dates.add("2021-01-01");

        /**
         * check the expected or actual data are same
         */
        assertEquals(4,similarRequirement.longestStreak(dates));
    }

    /**
     * this function check the latest streak is null
     */
    @Test
    void latestStreakNull() {
        SortedSet<String> dates = null;

        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
            () -> {
                similarRequirement.latestStreak(dates);
            });
    }

    /**
     * This function test when the streak date isn't empty
     */
    @Test
    void latestStreakNotNull() {
        SortedSet<String> dates = new TreeSet<String>() ;
        dates.add("2019-01-29");
        dates.add("2019-01-30");
        dates.add("2019-01-31");
        dates.add("2019-02-01");
        dates.add("2020-12-31");
        dates.add("2021-01-01");

        /**
         * check the expected or actual data are same
         */
        assertEquals(2,similarRequirement.latestStreak(dates));
    }
}