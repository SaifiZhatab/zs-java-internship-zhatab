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

    /**
     *  if the user give id by itself
     * @param id  the travelling id
     * @param person the person object
     * @param time the timing object
     * @param startPoint the starting point
     * @param endPoint the ending point
     * @param distance  the total distance cover
     * @throws SQLException
     */
    public Travelling(int id, Person person, Timing time, String startPoint, String endPoint, float distance) throws SQLException {
        super(id);
        this.person = person;
        this.time = time;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
    }

    /**
     * if the user doesn't give id, then it take automatically
     * @param person the person object
     * @param time the timing object
     * @param startPoint the starting point
     * @param endPoint the ending point
     * @param distance  the total distance cover
     * @throws SQLException
     */
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
