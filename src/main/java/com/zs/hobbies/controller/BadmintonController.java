package main.java.com.zs.hobbies.controller;

import main.java.com.zs.hobbies.cache.LruService;
import main.java.com.zs.hobbies.dto.Badminton;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.Timing;
import main.java.com.zs.hobbies.exception.InvalidInputException;
import main.java.com.zs.hobbies.service.BadmintonService;
import main.java.com.zs.hobbies.service.BadmintonServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class BadmintonController {
    private Person person;
    private TimingController timingController;

    BadmintonService badmintonService;
    Scanner in = new Scanner(System.in);

    public BadmintonController(Connection con,LruService lru) throws SQLException, IOException, ClassNotFoundException {
        badmintonService = new BadmintonServiceImpl(con,lru);
        timingController = new TimingController();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    boolean checkNumOfPlayer(int numOfPlayer){
        if(numOfPlayer > 0) {
            return true;
        }else {
            return false;
        }
    }
    boolean checkResult(String result) {
        if(result.compareToIgnoreCase("win")==0 || result.compareToIgnoreCase("draw")==0
                || result.compareToIgnoreCase("loose")==0 ) {
            return true;
        }else {
            return false;
        }
    }

    public void insert() throws ParseException, SQLException {
        Badminton badminton;
        Timing timing;
        int numOfPlayer;
        String result;

        timing = timingController.getTime();

        System.out.print("Enter number of player in badminton : ");
        numOfPlayer = in.nextInt();

        if(!checkNumOfPlayer(numOfPlayer)) {
            throw new InvalidInputException(400,"Wrong player details given by user");
        }

        System.out.print("Enter the result of game ");
        result = in.next();

        if(!checkResult(result)) {
            throw new InvalidInputException(400,"Wrong result status given by user");
        }

        badminton = new Badminton(person,timing,numOfPlayer,result);

        badmintonService.insert(badminton);
    }
    public void insert(Person person) throws ParseException, SQLException {
        insert();
        this.person = person;
    }
}
