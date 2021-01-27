package main.java.com.zs.hobbies.dto;

import java.sql.SQLException;

/**
 * this class provide all chess facility
 */
public class Chess extends Hobby{
    private Person person;
    private Timing time;
    private int numMoves;
    private String result;

    /**
     * if the user give id by itself
     * @param id the chess id
     * @param person the person object
     * @param time  the timing object
     * @param numMoves  number of moves
     * @param result the result
     * @throws SQLException
     */
    public Chess(int id, Person person, Timing time, int numMoves, String result) throws SQLException {
        super(id);
        this.person = person;
        this.time = time;
        this.numMoves = numMoves;
        this.result = result;
    }

    /**
     * if the user doesn't give id, then it take automatically
     * @param person the person object
     * @param time  the timing object
     * @param numMoves  number of moves
     * @param result the result
     * @throws SQLException
     */
    public Chess(Person person, Timing time, int numMoves, String result) throws SQLException {
        super(-1);
        this.person = person;
        this.time = time;
        this.numMoves = numMoves;
        this.result = result;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
