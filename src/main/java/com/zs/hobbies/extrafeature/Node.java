package main.java.com.zs.hobbies.extrafeature;

import main.java.com.zs.hobbies.dto.*;

public class Node {
    private Badminton badminton;
    private Travelling travelling;
    private Chess chess;
    private VideoWatching videoWatching;
    private Person person;

    public Node(){
        badminton = null;
        travelling = null;
        chess = null;
        videoWatching = null;
        person = null;
    }

    public Node(Badminton badminton) {
        this();
        this.badminton = badminton;
    }

    public Node(Travelling travelling) {
        this();
        this.travelling = travelling;
    }

    public Node(Chess chess) {
        this();
        this.chess = chess;
    }

    public Node(VideoWatching videoWatching) {
        this();
        this.videoWatching = videoWatching;
    }

    public Node(Person person) {
        this();
        this.person = person;
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
}
