package main.java.com.zs.hobbies.dto;

import java.sql.SQLException;

/**
 * this class provide all travelling facility
 */
public class Travelling extends Hobby{
    private Person person;
    private Timing time;
    private String startPoint;
    private String endPoint;
    private float distance;

    public Travelling(int id, Person person, Timing time, String startPoint, String endPoint, float distance) throws SQLException {
        super(id);
        this.person = person;
        this.time = time;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
    }

    public Travelling(Person person, Timing time, String startPoint, String endPoint, float distance) throws SQLException {
        super(-1);
        this.person = person;
        this.time = time;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
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

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
