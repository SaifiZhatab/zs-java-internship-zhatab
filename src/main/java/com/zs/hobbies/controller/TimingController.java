package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Timing;
import com.zs.hobbies.exception.InvalidInputException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This is a timing Controller class
 */
public class TimingController {
    private Logger logger;

    public TimingController() throws IOException {
        logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This function check the given time is valid or not
     * start time always less than end time  and the end time is always less than or equal to current default time
     * date is always less than or equal to current default date
     * @param startTime the start time
     * @param endTime the end time
     * @param date  the date
     * @return  true/false
     * @throws ParseException
     */
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

    /**
     * This function return the timing object to all other controller class
     * @return  the timing object
     * @throws ParseException
     */
    public Timing getTime() throws ParseException {
        Scanner in = new Scanner(System.in);
        Time startTime , endTIme;
        Date date ;

        logger.info("Enter starting time : ");
        startTime = Time.valueOf(in.next());

        logger.info("Enter end time : ");
        endTIme = Time.valueOf(in.next());

        logger.info("Enter the date : ");
        date = new SimpleDateFormat("YYYY-MM-DD").parse(in.next());

        if(!checkTime(startTime,endTIme,date) ) {
            throw new InvalidInputException(400,"Wrong date and time given by user");
        }
        Timing timing = new Timing(startTime,endTIme,new java.sql.Date(date.getTime()));
        return timing;
    }
}
