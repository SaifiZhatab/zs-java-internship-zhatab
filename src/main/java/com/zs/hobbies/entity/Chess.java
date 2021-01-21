package main.java.com.zs.hobbies.entity;

import main.java.com.zs.hobbies.database.DataBase;

import java.sql.SQLException;

/**
 * this class provide all chess facility
 */
public class Chess extends Hobby{
    private Person person;
    private Timing time;
    private int numMoves;
    private String result;

    public Chess(Person person, Timing time, int numMoves, String result) throws SQLException {
         super(DataBase.findHigherKey("Chess" , "chess_id"));
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
