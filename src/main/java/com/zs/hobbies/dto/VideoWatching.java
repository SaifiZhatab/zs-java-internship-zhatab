package com.zs.hobbies.dto;

/**
 * this class provide all videoWatching facility
 */
public class VideoWatching extends Hobby{
    private int personId;
    private Timing time;
    private String title;

    /**
     *  if the user give id by itself
     * @param id the id
     * @param personId the person object
     * @param time the timing object
     * @param title the title of video
     */
    public VideoWatching(int id, int personId, Timing time, String title) {
        super(id);
        this.personId = personId;
        this.time = time;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
