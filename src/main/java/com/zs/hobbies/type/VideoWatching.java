package main.java.com.zs.hobbies.type;

public class VideoWatching {
    private int id;
    private User person;
    private Timing time;
    private String title;

    public VideoWatching(int id, User person, Timing time, String title) {
        this.id = id;
        this.person = person;
        this.time = time;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
