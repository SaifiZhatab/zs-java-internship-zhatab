package com.zs.hobbies.dto;

/**
 * this class provide all badminton facility
 */
public class Badminton extends Hobby{
    private int personId;
    private Timing time;
    private int numPlayers ;
    private String result;

    /**
     * if the user give id by itself
     * @param id    the id
     * @param personId the person id
     * @param time     the timing object
     * @param numPlayers  number of player
     * @param result  the result
     */
    public Badminton(int id, int personId, Timing time, int numPlayers, String result) {
        super(id);
        this.personId = personId;
        this.time = time;
        this.numPlayers = numPlayers;
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
