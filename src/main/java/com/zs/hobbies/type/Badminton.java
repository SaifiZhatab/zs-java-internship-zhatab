package main.java.com.zs.hobbies.type;

public class Badminton {
    private int id;
    private User person;
    private Timing time;
    private int numPlayers ;
    private String result;

    public Badminton(int id, User person, Timing time, int numPlayers, String result) {
        this.id = id;
        this.person = person;
        this.time = time;
        this.numPlayers = numPlayers;
        this.result = result;
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
