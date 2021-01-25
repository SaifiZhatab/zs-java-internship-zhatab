package main.java.com.zs.hobbies.cache;

import main.java.com.zs.hobbies.dto.*;

public class Node {
    private String id;
    private Badminton badminton;
    private Travelling travelling;
    private Chess chess;
    private VideoWatching videoWatching;
    private Person person;

    private Node previous , next;

    private LongestStreak longestStreak;

    public Node(){
        badminton = null;
        travelling = null;
        chess = null;
        videoWatching = null;
        person = null;
    }

    public Node(Badminton badminton) {
        this();
        id = String.valueOf(badminton.getPerson().getId() ) + "_badminton";
        this.badminton = badminton;
    }

    public Node(Travelling travelling) {
        this();
        id = String.valueOf(travelling.getPerson().getId() ) + "_travelling";
        this.travelling = travelling;
    }

    public Node(Chess chess) {
        this();
        id = String.valueOf(chess.getPerson().getId() ) + "_chess";
        this.chess = chess;
    }

    public Node(VideoWatching videoWatching) {
        this();
        id = String.valueOf(videoWatching.getPerson().getId() ) + "_videoWatching";
        this.videoWatching = videoWatching;
    }

    public Node(Person person) {
        this();
        id = String.valueOf(person.getId() ) + "_person";
        this.person = person;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Badminton getBadminton() {
        return badminton;
    }

    public void setBadminton(Badminton badminton) {
        this.badminton = badminton;
    }

    public Travelling getTravelling() {
        return travelling;
    }

    public void setTravelling(Travelling travelling) {
        this.travelling = travelling;
    }

    public Chess getChess() {
        return chess;
    }

    public void setChess(Chess chess) {
        this.chess = chess;
    }

    public VideoWatching getVideoWatching() {
        return videoWatching;
    }

    public void setVideoWatching(VideoWatching videoWatching) {
        this.videoWatching = videoWatching;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public LongestStreak getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(LongestStreak longestStreak) {
        this.longestStreak = longestStreak;
    }
}
