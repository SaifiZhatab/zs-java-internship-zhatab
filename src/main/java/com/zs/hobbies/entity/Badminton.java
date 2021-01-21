package main.java.com.zs.hobbies.entity;

import main.java.com.zs.hobbies.database.DataBase;

import java.sql.SQLException;

/**
 * this class provide all badminton facility
 */
public class Badminton extends Hobby{
    private Person person;
    private Timing time;
    private int numPlayers ;
    private String result;

    public Badminton(Person person, Timing time, int numPlayers, String result) throws SQLException {
        super(DataBase.findHigherKey("Badminton" , "badminton_id"));
        this.person = person;
        this.time = time;
        this.numPlayers = numPlayers;
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

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
