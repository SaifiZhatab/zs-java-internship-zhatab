package main.java.com.zs.hobbies.controller;

import main.java.com.zs.hobbies.Application;
import main.java.com.zs.hobbies.cache.LruService;
import main.java.com.zs.hobbies.dto.Badminton;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.Timing;
import main.java.com.zs.hobbies.exception.InvalidInputException;
import main.java.com.zs.hobbies.service.BadmintonService;
import main.java.com.zs.hobbies.service.BadmintonServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This is a Badminton Controller class
 * that call the badminton service call and using service interact with database
 */
public class BadmintonController {
    private Person person;
    private TimingController timingController;
    private Logger logger;


    BadmintonService badmintonService;
    Scanner in = new Scanner(System.in);

    public BadmintonController(Connection con,LruService lru) throws SQLException, IOException, ClassNotFoundException {
        badmintonService = new BadmintonServiceImpl(con,lru);
        timingController = new TimingController();
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Application.class.getName());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * This function check the number of player is valid or not
     * if the number of player greater than 0, then return true else return false
     * @param numOfPlayer   the number of player
     * @return  true / false
     */
    boolean checkNumOfPlayer(int numOfPlayer){
        if(numOfPlayer > 0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * This function check the result is valid or not
     * the only valid string is win/draw/losse
     * if the string is either win,draw or losse, then it return true else return false
     * @param result  the result string
     * @return true/false
     */
    boolean checkResult(String result) {
        if(result.compareToIgnoreCase("win")==0 || result.compareToIgnoreCase("draw")==0
                || result.compareToIgnoreCase("loose")==0 ) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * This funciton help you to insert the badminton object in database
     * @throws ParseException
     * @throws SQLException
     */
    public void insert() throws ParseException, SQLException {
        Badminton badminton;
        Timing timing;
        int numOfPlayer;
        String result;

        timing = timingController.getTime();

        logger.info("Enter number of player in badminton : ");
        numOfPlayer = in.nextInt();

        if(!checkNumOfPlayer(numOfPlayer)) {
            throw new InvalidInputException(400,"Wrong player details given by user");
        }

        logger.info("Enter the result of game ");
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
