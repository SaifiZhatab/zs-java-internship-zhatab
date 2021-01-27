package test.java.com.zs.hobbies.dto;

import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.dto.Timing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a testing class for Chess dto class
 */
class ChessTest {
    private Person person;
    private Timing timing;
    private int numMoves;
    private String result;
    private Chess chess;

    @BeforeEach
    void setUp() throws SQLException {
        person = new Person(9,"Rahul","8235456789","MP");
        timing = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2015-04-05"));
        numMoves = 5;
        result = "Draw";
        chess = new Chess(2,person,timing,numMoves,result);
    }

    @Test
    void getPerson() {
        assertEquals(person,chess.getPerson());
    }

    @Test
    void setPerson() {
    }

    @Test
    void getTime() {
        assertEquals(timing,chess.getTime());
    }

    @Test
    void setTime() {
    }

    @Test
    void getNumMoves() {
        assertEquals(numMoves,chess.getNumMoves());
    }

    @Test
    void setNumMoves() {
    }

    @Test
    void getResult() {
        assertEquals(result,chess.getResult());
    }

    @Test
    void setResult() {
    }
}