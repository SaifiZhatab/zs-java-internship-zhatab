package main.java.com.zs.hobbies.type;

public class Travelling {
    private int id;
    private User person;
    private Timing time;
    private String startTime;
    private String endTime;
    private float distance;

    public Travelling(int id, User person, Timing time, String startTime, String endTime, float distance) {
        this.id = id;
        this.person = person;
        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public Timing getTime() {
        return time;
    }

    public void setTime(Timing time) {
        this.time = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
