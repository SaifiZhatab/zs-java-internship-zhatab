package main.java.com.zs.hobbies.dto;

/**
 * This is a main class which inherit by all hobbies
 */
public class Hobby {
    private int id;

    public Hobby(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
