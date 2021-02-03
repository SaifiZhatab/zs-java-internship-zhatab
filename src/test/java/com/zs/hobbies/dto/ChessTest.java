package com.zs.hobbies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getPersonId() {
        assertEquals(personId,chess.getPersonId());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setPersonId() {
        chess.setPersonId(5);

        /**
         * check the expected or actual value is same
         */
        assertEquals(5,chess.getPersonId());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getTime() {
        assertEquals(timing,chess.getTime());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setTime() {
        Timing time = new Timing(Time.valueOf("01:39:49"),Time.valueOf("12:59:59"), Date.valueOf("2012-12-15"));
        chess.setTime(time);

        /**
         * check the expected or actual value is same
         */
        assertEquals(time,chess.getTime());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getNumMoves() {
        assertEquals(numMoves,chess.getNumMoves());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setNumMoves() {
        chess.setNumMoves(5);

        /**
         * check the expected or actual value is same
         */
        assertEquals(5,chess.getNumMoves());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getResult() {
        assertEquals(result,chess.getResult());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setResult() {
        chess.setResult("draw");

        /**
         * check the expected or actual value is same
         */
        assertEquals("draw",chess.getResult());
    }
}