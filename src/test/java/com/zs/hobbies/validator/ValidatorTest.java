package com.zs.hobbies.validator;

import com.zs.hobbies.dto.*;
import com.zs.hobbies.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void validResult() {
        assertEquals(true,validator.validResult("win"));
        assertEquals(true,validator.validResult("draw"));
        assertEquals(true,validator.validResult("lost"));
    }
    @Test
    void validResultException() {
        assertThrows(InvalidInputException.class,
                () -> {
                        validator.validResult("not define");
                });
        assertEquals(true,validator.validResult("win"));
    }

    @Test
    void validNumberOfPlayer() {
        assertEquals(true, validator.validNumberOfPlayer(4));
    }

    @Test
    void validNumberOfPlayerException() {
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validNumberOfPlayer(0);
                });
    }

    @Test
    void validNumberOfMoves() {
        assertEquals(true, validator.validNumberOfMoves(5));
    }

    @Test
    void validNumberOfMovesException() {
        assertThrows(InvalidInputException.class,
                () -> {
                    validator. validNumberOfMoves(105);
                });
    }

    @Test
    void validMobile() {
        assertEquals(true , validator.validMobile("8010311757"));
    }

    @Test
    void validMobileException() {
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validMobile("80103117bc");
                });
    }

    @Test
    void validMobileExceptionNumberSize() {
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validMobile("98765432198");
                });
    }

    @Test
    void validName() {
        assertEquals(true, validator.validName("Zhatab"));
    }

    @Test
    void validNameException() {
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validName("Zh12ab");
                });
    }

    @Test
    void validPosition() {
        assertEquals(true, validator.validPosition("UP"));
    }

    @Test
    void validPositionException() {
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validPosition("");
                });
    }

    @Test
    void validTime() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("12:20:31");

        assertEquals(true, validator.validTime(startTime,endTime));
    }

    @Test
    void validTimeException() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("09:20:31");

        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validTime(startTime,endTime);
                });
    }

    @Test
    void validDate() {
        Date date = Date.valueOf("2021-01-01");
        assertEquals(true,validator.validDate(date));
    }

    @Test
    void validDateException() {
        Date date = Date.valueOf("2021-05-21");
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validDate(date);
                });
    }

    @Test
    void validBadminton() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("12:20:31");
        Date date =  Date.valueOf("2021-01-01");
        Timing timing = new Timing(startTime,endTime,date);
        Badminton badminton = new Badminton(1,1,timing,4,"win");
        assertEquals(true,validator.validBadminton(badminton));
    }

    @Test
    void validBadmintonException() {

        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("01:20:31");

        Date date =  Date.valueOf("2021-01-01");
        Timing timing = new Timing(startTime,endTime,date);
        Badminton badminton = new Badminton(1,1,timing,4,"win");

        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validBadminton(badminton);
                });
    }

    @Test
    void validPerson() {
        Person person = new Person(1,"Zhatab","8010311757","up");
        assertEquals(true,validator.validPerson(person));
    }

    @Test
    void validPersonException() {
        Person person = new Person(1,"zha12b","8010311757","up");
        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validPerson(person);
                });
    }

    @Test
    void validChess() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("11:20:31");

        Date date =  Date.valueOf("2021-01-01");
        Timing timing = new Timing(startTime,endTime,date);
        Chess chess = new Chess(1,1,timing,2,"win");

        assertEquals(true,validator.validChess(chess));
    }

    @Test
    void validChessException() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("01:20:31");

        Date date =  Date.valueOf("2021-05-09");
        Timing timing = new Timing(startTime,endTime,date);
        Chess chess = new Chess(1,1,timing,0,"win");

        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validChess(chess);
                });
    }

    @Test
    void validVideoWatching() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("11:20:31");

        Date date =  Date.valueOf("2021-01-01");
        Timing timing = new Timing(startTime,endTime,date);

        VideoWatching videoWatching = new VideoWatching(1,1,timing,"lucifer");

        assertEquals(true,validator.validVideoWatching(videoWatching));
    }

    @Test
    void validVideoWatchingException() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("01:20:31");

        Date date =  Date.valueOf("2021-05-09");
        Timing timing = new Timing(startTime,endTime,date);

        VideoWatching videoWatching = new VideoWatching(1,1,timing,"lucifer");

        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validVideoWatching(videoWatching);
                });
    }

    @Test
    void validTravelling() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("11:20:31");

        Date date =  Date.valueOf("2021-01-01");
        Timing timing = new Timing(startTime,endTime,date);
        Travelling travelling = new Travelling(1,1,timing,"Up","Mp",20f);

        assertEquals(true,validator.validTravelling(travelling));
    }

    @Test
    void validTravellingException() {
        Time startTime = Time.valueOf("10:45:31");
        Time endTime = Time.valueOf("01:20:31");

        Date date =  Date.valueOf("2021-05-09");
        Timing timing = new Timing(startTime,endTime,date);
        Travelling travelling = new Travelling(1,1,timing,"Up","",20f);

        assertThrows(InvalidInputException.class,
                () -> {
                    validator.validTravelling(travelling);
                });
    }
}