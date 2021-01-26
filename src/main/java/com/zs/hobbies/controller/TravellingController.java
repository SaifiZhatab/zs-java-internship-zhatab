package main.java.com.zs.hobbies.controller;

import main.java.com.zs.hobbies.cache.LruService;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.Timing;
import main.java.com.zs.hobbies.dto.Travelling;
import main.java.com.zs.hobbies.exception.InvalidInputException;
import main.java.com.zs.hobbies.service.TravellingService;
import main.java.com.zs.hobbies.service.TravellingServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class TravellingController {
    private Person person;
    private TravellingService travellingService;
    private TimingController timingController;
    Scanner in = new Scanner(System.in);

    public TravellingController(Connection con, LruService lru) throws SQLException, IOException, ClassNotFoundException {
        travellingService = new TravellingServiceImpl(con,lru);
        timingController = new TimingController();
    }

    boolean checkPosition(String position) {
        if(position.length() >0 && position.length() < 500) {
            return true;
        }else {
            return false;
        }
    }

    public void insert() throws ParseException {
        Timing timing;
        Travelling travelling;

        timing = timingController.getTime();

        String startPoint,endPoint;
        float distance;

        System.out.print("Enter the starting position ");
        startPoint = in.next();

        if(!checkPosition(startPoint)) {
            throw new InvalidInputException(400,"wrong start position given by user");
        }

        System.out.print("Enter the end position ");
        endPoint = in.next();

        if(!checkPosition(endPoint)) {
            throw new InvalidInputException(400,"wrong start position given by user");
        }

        System.out.print("Enter the total travelling distance ");
        distance = in.nextFloat();

        if(distance <= 0.0f) {
            throw new InvalidInputException(400,"Wrong distance given by user ");
        }

    }
}
