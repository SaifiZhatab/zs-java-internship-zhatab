package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.service.ChessService;
import com.zs.hobbies.service.ChessServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This is a Chess Controller class
 * that call the chess service call and using service interact with database
 */
@RestController
public class ChessController {
    private Logger logger;
    private ChessService chessService;

    public ChessController() {
        logger = Logger.getLogger(Application.class.getName());
        chessService = new ChessServiceImpl();
    }

    /**
     * This function help you to insert the chess object in database
     */
    @PostMapping("/Chess/insert")
    public ResponseEntity<String> insert(@RequestBody  Chess chess) {
        try {
            chessService.insert(chess);
            return new ResponseEntity<>("Added", HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(e.getErrorCode() + " " + e.getErrorCode(), HttpStatus.BAD_REQUEST);
        } catch (ApplicationException e) {
            return new ResponseEntity<>(e.getErrorCode() + " " + e.getErrorCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function help you to find the longest streak in the chess
     * @param personId  the person ID
     */
    @GetMapping("/Chess/longestStreak/{personId}")
    public String longestStreak(@PathVariable int personId) {
        try {
            int longestStreak = chessService.longestStreak(personId);
            return "This is the longest Chess streak for " + personId + " : " + longestStreak;
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
    @GetMapping("/Chess/lastestStreak/{personId")
    public String latestStreak(@PathVariable int personId) {
        try{
            int latestStreak = chessService.latestStreak(personId);
            return "This is the lastest Chess streak for " + personId + " : " + latestStreak;
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
    @GetMapping("/Chess/lastTick/{personId}")
    public String lastTick(@PathVariable int personId) {
        try{
            Integer lastTick = (Integer) chessService.lastTick(personId);
            if(lastTick == null) {
                lastTick =0 ;
            }
            return "This is the last chess tick for " + personId + " : " + lastTick;
        }catch (InvalidInputException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }catch (ApplicationException e) {
            return e.getErrorCode() + " error message : " + e.getErrorDescription();
        }
    }

    /**
     * This function help you to find the details according to details in the chess
     * @param personId  the person ID
     */
    public void searchByDate(int personId, Date date) {
        Set<Chess> setDetails = chessService.dateDetails(personId,date);

        if(setDetails.size() == 0) {
            logger.warning("No details present at this date");
            return;
        }

        Iterator<Chess> iterator = setDetails.iterator();
        while(iterator.hasNext()) {
            logger.info("Chess id : " + iterator.next().getId());
        }
    }
}
