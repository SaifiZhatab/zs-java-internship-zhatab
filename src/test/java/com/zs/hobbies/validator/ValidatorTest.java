package com.zs.hobbies.validator;

import com.zs.hobbies.dto.*;
import com.zs.hobbies.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class is validator class testing implementation
 */
class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validResult() {
        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true,validator.validResult("win"));
        assertEquals(true,validator.validResult("draw"));
        assertEquals(true,validator.validResult("lost"));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validResultException() {
        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                        validator.validResult("not define");
                });
        assertEquals(true,validator.validResult("win"));
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validNumberOfPlayer() {
        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true, validator.validNumberOfPlayer(4));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validNumberOfPlayerException() {
        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validNumberOfPlayer(0);
                });
    }


    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validNumberOfMoves() {
        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true, validator.validNumberOfMoves(5));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validNumberOfMovesException() {
        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator. validNumberOfMoves(105);
                });
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validMobile() {
        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true , validator.validMobile("8010311757"));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validMobileException() {
        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validMobile("80103117bc");
                });
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validMobileExceptionNumberSize() {
        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validMobile("98765432198");
                });
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validName() {
        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true, validator.validName("Zhatab"));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validNameException() {
        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validName("Zh12ab");
                });
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validPosition() {
        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true, validator.validPosition("UP"));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validPositionException() {

        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validPosition("");
                });
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validTime() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("12:20:31");

        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true, validator.validTime(startTime,endTime));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validTimeException() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("09:20:31");

        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validTime(startTime,endTime);
                });
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validDate() {
        Date date = Date.valueOf("2021-01-01");

        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true,validator.validDate(date));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validDateException() {
        Date date = Date.valueOf("2021-05-21");

        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validDate(date);
                });
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validBadminton() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("12:20:31");
        Date date =  Date.valueOf("2021-01-01");
        Timing timing = new Timing(startTime,endTime,date);
        Badminton badminton = new Badminton(1,1,timing,4,"win");

        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true,validator.validBadminton(badminton));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validBadmintonException() {

        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("01:20:31");

        Date date =  Date.valueOf("2021-01-01");
        Timing timing = new Timing(startTime,endTime,date);
        Badminton badminton = new Badminton(1,1,timing,4,"win");

        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validBadminton(badminton);
                });
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validPerson() {
        Person person = new Person(1,"Zhatab","8010311757","up");

        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true,validator.validPerson(person));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validPersonException() {
        Person person = new Person(1,"zha12b","8010311757","up");

        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validPerson(person);
                });
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validChess() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("11:20:31");

        Date date =  Date.valueOf("2021-01-01");
        Timing timing = new Timing(startTime,endTime,date);
        Chess chess = new Chess(1,1,timing,2,"win");

        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true,validator.validChess(chess));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validChessException() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("01:20:31");

        Date date =  Date.valueOf("2021-05-09");
        Timing timing = new Timing(startTime,endTime,date);
        Chess chess = new Chess(1,1,timing,0,"win");

        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validChess(chess);
                });
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validVideoWatching() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("11:20:31");

        Date date =  Date.valueOf("2021-01-01");
        Timing timing = new Timing(startTime,endTime,date);

        VideoWatching videoWatching = new VideoWatching(1,1,timing,"lucifer");

        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true,validator.validVideoWatching(videoWatching));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validVideoWatchingException() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("01:20:31");

        Date date =  Date.valueOf("2021-05-09");
        Timing timing = new Timing(startTime,endTime,date);

        VideoWatching videoWatching = new VideoWatching(1,1,timing,"lucifer");

        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validVideoWatching(videoWatching);
                });
    }

    /**
     * this function check the test function is perfectly work or not
     */
    @Test
    void validTravelling() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("11:20:31");

        Date date =  Date.valueOf("2021-01-01");
        Timing timing = new Timing(startTime,endTime,date);
        Travelling travelling = new Travelling(1,1,timing,"Up","Mp",20f);

        /**
         * check the expected or actual result is same or not
         */
        assertEquals(true,validator.validTravelling(travelling));
    }

    /**
     * this function check the test function return exception or not when require
     */
    @Test
    void validTravellingException() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("01:20:31");

        Date date =  Date.valueOf("2021-05-09");
        Timing timing = new Timing(startTime,endTime,date);
        Travelling travelling = new Travelling(1,1,timing,"Up","",20f);

        /**
         * check InvalidInput exception will throw or not
         */
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validTravelling(travelling);
                });
    }
}