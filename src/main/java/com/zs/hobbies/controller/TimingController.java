package main.java.com.zs.hobbies.controller;

import main.java.com.zs.hobbies.dto.Timing;
import main.java.com.zs.hobbies.exception.InvalidInputException;

import java.sql.Time;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TimingController {

    public boolean checkTime(Time startTime, Time endTime, Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String currentDate =  dateFormat.format(cal.getTime());

        Format timeFormat = new SimpleDateFormat("HH.mm.ss");
        String currentTime = timeFormat.format(new Date());

        if(startTime.compareTo(endTime) < 0 && endTime.compareTo(Time.valueOf(currentTime)) < 0
                && date.compareTo(new SimpleDateFormat("yyyy-mm-dd").parse(currentDate)) <= 0) {
            return true;
        }else {
            return false;
        }
    }

    public Timing getTime() throws ParseException {
        Scanner in = new Scanner(System.in);
        Time startTime , endTIme;
        Date date ;

        System.out.print("Enter starting time : ");
        startTime = Time.valueOf(in.next());

        System.out.print("Enter end time : ");
        endTIme = Time.valueOf(in.next());

        System.out.print("Enter the date : ");
        date = new SimpleDateFormat("YYYY-MM-DD").parse(in.next());

        if(!checkTime(startTime,endTIme,date) ) {
            throw new InvalidInputException(400,"Wrong date given by user");
        }
        Timing timing = new Timing(startTime,endTIme,new java.sql.Date(date.getTime()));
        return timing;
    }
}
