package com.zs.hobbies.dto;

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
    private int personId, chessID ;
    private Timing timing;
    private int numMoves;
    private String result;

    private Chess chess;

    @BeforeEach
    void setUp() throws SQLException {
        personId = 9;
        chessID = 5;
        timing = new Timing(Time.valueOf("10:59:59"),Time.valueOf("12:59:59"), Date.valueOf("2015-04-05"));
        numMoves = 5;
        result = "Draw";
        chess = new Chess(chessID,personId,timing,numMoves,result);
    }

    @Test
    void getPersonId() {
        assertEquals(personId,chess.getPersonId());
    }

    @Test
    void setPersonId() {
        chess.setPersonId(5);
        assertEquals(5,chess.getPersonId());
    }

    @Test
    void getTime() {
        assertEquals(timing,chess.getTime());
    }

    @Test
    void setTime() {
        Timing time = new Timing(Time.valueOf("01:39:49"),Time.valueOf("12:59:59"), Date.valueOf("2012-12-15"));
        chess.setTime(time);
        assertEquals(time,chess.getTime());
    }

    @Test
    void getNumMoves() {
        assertEquals(numMoves,chess.getNumMoves());
    }

    @Test
    void setNumMoves() {
        chess.setNumMoves(5);
        assertEquals(5,chess.getNumMoves());
    }

    @Test
    void getResult() {
        assertEquals(result,chess.getResult());
    }

    @Test
    void setResult() {
        chess.setResult("draw");
        assertEquals("draw",chess.getResult());
    }
}