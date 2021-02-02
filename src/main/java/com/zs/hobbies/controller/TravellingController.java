package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Travelling;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.service.TravellingService;
import com.zs.hobbies.service.TravellingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This is a travelling Controller class
 * that call the travelling service call and using service interact with database
 */
@RestController
public class TravellingController {
    private Logger logger;
    private TravellingService travellingService;

    public TravellingController() {
        logger = Logger.getLogger(Application.class.getName());
        travellingService = new TravellingServiceImpl();
    }

    /**
     * This function help you to insert the travelling object in database
     * @param travelling
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/Travelling/insert")
    public void insert(@RequestBody  Travelling travelling){
        travellingService.insert(travelling);
    }

    /**
     * This function help you to find the longest streak in the chess
     * @param personId  the person ID
     */
    @GetMapping("/Travelling/longestStreak/{personId}")
    public String longestStreak(@PathVariable  int personId) {
        try{
            int longestStreak = travellingService.longestStreak(personId);
            return "This is the longest travelling streak for " + personId + " : " + longestStreak;
        }catch (InvalidInputException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }catch (ApplicationException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }
    }

    /**
     * This function help you to find the latest streak in the chess
     * @param personId  the person ID
     */
    @GetMapping("/Travelling/latestStreak/{personId}")
    public String latestStreak(@PathVariable int personId) {
        try{
            int latestStreak = travellingService.latestStreak(personId);
            return "This is the latest travelling streak for " + personId + " : " + latestStreak;
        }catch (InvalidInputException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }catch (ApplicationException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }
    }

    /**
     * This function help you to find the last streak in the chess
     * @param personId  the person ID
     */
    @GetMapping("/Travelling/lastTick/{personId}")
    public String lastTick(int personId) {
        try{
             Integer lastTick =  travellingService.lastTick(personId);
             if(lastTick == null) {
                 lastTick = 0;
             }
            return "This is the last travelling tick for " + personId + " : " + lastTick;
        }catch (InvalidInputException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }catch (ApplicationException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }
    }

    /**
     * This function help you to find the details according to details in the travelling
     * @param personId the person id
     * @param date the date
     */
    public void searchByDate(int personId, Date date) {
        Set<Travelling> setDetails = travellingService.dateDetails(personId,date);

        if(setDetails.size() == 0) {
            logger.warning("No details present at this date");
            return;
        }

        Iterator<Travelling> iterator = setDetails.iterator();
        while(iterator.hasNext()) {
            logger.info("Travelling id : " + iterator.next().getId());
        }
    }
}