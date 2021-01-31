package com.zs.hobbies.service;

import com.zs.hobbies.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class SimilarRequirementTest {

    private SimilarRequirement similarRequirement;

    @BeforeEach
    void setUp() {
        similarRequirement = new SimilarRequirement();
    }

    @Test
    void longestStreakNull() {
        SortedSet<String> dates = null;

        assertThrows(InvalidInputException.class,
            () -> {
                similarRequirement.longestStreak(dates);
            });
    }

    @Test
    void longestStreakNotNull() {
        SortedSet<String> dates = new TreeSet<String>() ;
        dates.add("2019-01-29");
        dates.add("2019-01-30");
        dates.add("2019-01-31");
        dates.add("2019-02-01");
        dates.add("2020-12-31");
        dates.add("2021-01-01");

        assertEquals(4,similarRequirement.longestStreak(dates));
    }

    @Test
    void latestStreakNull() {
        SortedSet<String> dates = null;

        assertThrows(InvalidInputException.class,
            () -> {
                similarRequirement.latestStreak(dates);
            });
    }

    @Test
    void latestStreakNotNull() {
        SortedSet<String> dates = new TreeSet<String>() ;
        dates.add("2019-01-29");
        dates.add("2019-01-30");
        dates.add("2019-01-31");
        dates.add("2019-02-01");
        dates.add("2020-12-31");
        dates.add("2021-01-01");

        assertEquals(2,similarRequirement.latestStreak(dates));
    }
}