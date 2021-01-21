package test.java.com.zs.hobbies.dto;

import main.java.com.zs.hobbies.dto.Badminton;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.Timing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class BadmintonTest {
    Person person;
    Timing timing;
    int numPlayers;
    String result;
    Badminton badminton;

    @BeforeEach
    void setUp() throws SQLException {
        person = new Person(9,"Rahul","8235456789","MP");
        timing = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2015-04-05"));
        numPlayers = 2;
        result = "Win";

        badminton = new Badminton(1,person,timing,numPlayers,result);
    }

    @Test
    void getPerson() {
        assertEquals(person,badminton.getPerson());
    }

    @Test
    void setPerson() {
    }

    @Test
    void getTime() {
        assertEquals(timing,badminton.getTime());
    }

    @Test
    void setTime() {
    }

    @Test
    void getNumPlayers() {
        assertEquals(numPlayers,badminton.getNumPlayers());
    }

    @Test
    void setNumPlayers() {
    }

    @Test
    void getResult() {
        assertEquals(result,badminton.getResult());
    }

    @Test
    void setResult() {
    }
}