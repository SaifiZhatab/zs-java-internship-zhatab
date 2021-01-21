package main.java.com.zs.hobbies.entity;

import main.java.com.zs.hobbies.database.DataBase;

import java.sql.SQLException;

/**
 * this class provide all videoWatching facility
 */
public class VideoWatching extends Hobby{
    private Person person;
    private Timing time;
    private String title;

    public VideoWatching(Person person, Timing time, String title) throws SQLException {
        super(DataBase.findHigherKey("VideoWatching" , "videoWatching_id"));
        this.person = person;
        this.time = time;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
