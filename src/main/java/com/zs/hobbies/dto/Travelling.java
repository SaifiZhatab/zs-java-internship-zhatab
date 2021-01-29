package com.zs.hobbies.dto;

/**
 * this class provide all travelling facility
 */
public class Travelling extends Hobby{
    private int personId;
    private Timing time;
    private String startPoint;
    private String endPoint;
    private float distance;

    /**
     *  if the user give id by itself
     * @param id  the travelling id
     * @param personId the person object
     * @param time the timing object
     * @param startPoint the starting point
     * @param endPoint the ending point
     * @param distance  the total distance cover
     */
    public Travelling(int id, int personId, Timing time, String startPoint, String endPoint, float distance) {
        super(id);
        this.personId = personId;
        this.time = time;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
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
