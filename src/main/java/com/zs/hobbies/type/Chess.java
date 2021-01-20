package main.java.com.zs.hobbies.type;

public class Chess {
    private int id;
    private User person;
    private Timing time;
    private int numMoves;
    private String result;

    public Chess(int id,User person, Timing time, int numMoves, String result) {
        this.id = id;
        this.person = person;
        this.time = time;
        this.numMoves = numMoves;
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

    public int getNumMoves() {
        return numMoves;
    }

    public void setNumMoves(int numMoves) {
        this.numMoves = numMoves;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
