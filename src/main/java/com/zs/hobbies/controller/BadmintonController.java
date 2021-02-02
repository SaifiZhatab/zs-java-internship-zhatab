package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Badminton;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.service.BadmintonService;
import com.zs.hobbies.service.BadmintonServiceImpl;
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
 * This is a Badminton Controller class
 * that call the badminton service call and using service interact with database
 */
@RestController
public class BadmintonController {
    private Logger logger;
    private BadmintonService badmintonService;

    public BadmintonController(){
        logger = Logger.getLogger(Application.class.getName());
        badmintonService = new BadmintonServiceImpl();
    }

    /**
     * This function help you to insert the badminton object in database
     */
   @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/Badminton/insert")
    public void insert(@RequestBody Badminton badminton) {
            badmintonService.insert(badminton);

    }

    /**
     * This function help you to find the longest streak in the badminton
     * @param personId  the person ID
     */
    @GetMapping("Badminton/longestStreak/{personId}")
    public String longestStreak(@PathVariable int personId) {
        try {
            int longestStreak = badmintonService.longestStreak(personId);
            return "This is the longest badminton streak for " + personId + " : " + longestStreak;
        }catch (InvalidInputException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }catch (ApplicationException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }
    }

    /**
     * This function help you to find the latest streak in the badminton
     * @param personId  the person ID
     */
    @GetMapping("Badminton/latestStreak/{personId}")
    public String latestStreak(@PathVariable int personId) {
        try {
            int latestStreak = badmintonService.latestStreak(personId);
            return "This is the latest streak for " + personId + " : " + latestStreak;
        }catch (InvalidInputException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }catch (ApplicationException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }
    }

    /**
     * This function help you to find the last streak in the badminton
     * @param personId  the person ID
     */
    @GetMapping("Badminton/lastTick/{personId}")
    public String lastTick(@PathVariable int personId) {
        try {
            Integer badmintonId = badmintonService.lastTick(personId);

            if(badmintonId == null) {
                badmintonId = 0;
            }

            return "This is the last tick for " + personId + " : " + badmintonId;
        }catch (InvalidInputException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }catch (ApplicationException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }
    }

    /**
     * This function help you to find the details according to details in the badminton
     * @param personId the person id
     * @param date the date
     */
    public void searchByDate(int personId, Date date) {
        Set<Badminton> setDetails = badmintonService.dateDetails(personId,date);

        if(setDetails.size() == 0) {
            logger.warning("No details present at this date");
            return;
        }

        Iterator<Badminton> iterator = setDetails.iterator();
        while(iterator.hasNext()) {
            logger.info("Badminton id : " + iterator.next().getId());
        }
    }
}
