package com.zs.hobbies.dto;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

/**
 * this class provide all chess facility
 */
public class Chess extends Hobby{
    private int personId ;
    private Timing time;
    private int numMoves;
    private String result;

    /**
     * if the user give id by itself
     * @param id the chess id
     * @param personId the person object
     * @param time  the timing object
     * @param numMoves  number of moves
     * @param result the result
     * @throws SQLException
     */
    public Chess(int id, int personId, Timing time, int numMoves, String result) throws SQLException {
        super(id);
        this.personId = personId;
        this.time = time;
        this.numMoves = numMoves;
        this.result = result;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Timing getTime() {
        return time;
    }

    public void setTime(Timing time) {
        this.time = time;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public void setNumMoves(int numMoves) {
        this.numMoves = numMoves;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
