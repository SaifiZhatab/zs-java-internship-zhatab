package com.zs.hobbies.validator;

import com.zs.hobbies.exception.InvalidInputException;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

/**
 * This class is used for check the given input by user is correct or not
 */
public class Validator {

    /**
     * This function check the result is valid or not
     * the only valid string is win/draw/losse
     * if the string is either win,draw or losse, then it return true else return false
     * @param result  the result string
     * @return true/false
     */

    public boolean checkResult(String result) throws InvalidInputException {
        if(result.compareToIgnoreCase("win")==0 || result.compareToIgnoreCase("draw")==0
                || result.compareToIgnoreCase("lost")==0 ) {
            return true;
        }else {
            throw new InvalidInputException(400,"Result can be one of win, lost or draw ");
        }
    }

    /**
     * This function check the number of player is valid or not
     * if the number of player greater than 0, then return true else return false
     * @param numOfPlayer   the number of player
     * @return  true / false
     */
    public boolean checkNumOfPlayer(int numOfPlayer) throws InvalidInputException{
        if(numOfPlayer > 0) {
            return true;
        }else {
            throw new InvalidInputException(400,"Number of player will not less than 0 and not greater than 4");
        }
    }

    /**
     * This function check the number of move is valid or not
     * if the number of move greater than 0 and less or equal to 100, then return true else return false
     * @param move   the number of move
     * @return  true / false
     */
    public boolean checkNumOfMove(int move) throws InvalidInputException{
        if(move >=0 && move <= 100) {
            return true;
        }else {
            throw new InvalidInputException(400,"Number of moves cannot be greater than 100");
        }
    }

    /**
     * This function check the mobile number is valid or not
     * it return true if the mobile number is valid
     * @param mobile  the mobile number string
     * @return true/false
     */
    public boolean checkMobile(String mobile) throws InvalidInputException{
        int length = mobile.length();

        /**
         * check all digit in mobile number is digit
         */
        for(int i=0;i<length;i++) {
            if(mobile.charAt(i) >= '0' && mobile.charAt(i) <='9'){}
            else {
                throw new InvalidInputException(400,"Wrong mobile number");
            }
        }

        /**
         * if the length of mobile number doesn't equal to 10
         */
        if(length != 10 ) {
            throw new InvalidInputException(400,"Mobile number doesn't less or equal to 10");
        }else {
            return true;
        }
    }


    /**
     * This function check the name is valid or not
     * it return false if any value come except charcharcter otherwise it return true
     * @param check the name string
     * @return true/false
     */
    public boolean checkName(String check) throws InvalidInputException{
        int length = check.length();

        /**
         * check only Character present in name
         */
        for(int i=0;i<length;i++) {
            if((check.charAt(i)>= 'a' && check.charAt(i) <= 'z') ||
                    (check.charAt(i)>='A' && check.charAt(i) <= 'Z') ) {

            }else {
                throw new InvalidInputException(400,"Name must contains only char ");
            }
        }
        return true;
    }

    /**
     * This function check the given position is valid or not
     * return true if the length of the position string is greater than 0 and less than 500
     * else return false
     * @param position  the position string
     * @return  true/false
     */
    public boolean checkPosition(String position) throws InvalidInputException{
        if(position.length() >0 && position.length() < 100) {
            return true;
        }else {
            throw new InvalidInputException(400,"Position size is always greater them 0 and less then 100");
        }
    }

    /**
     * This function check the given time is valid or not
     * start time always less than end time  and the end time is always less than or equal to current default time
     * @param startTime the start time
     * @param endTime the end time
     * @return  true/false
     * @throws InvalidInputException
     */
    public boolean checkTime(Time startTime, Time endTime) throws InvalidInputException {
        if(startTime.compareTo(endTime) < 0 && endTime.compareTo(Time.valueOf(LocalTime.now())) < 0) {
            return true;
        }else {
            throw new InvalidInputException(400,"Wrong time given by user");
        }
    }

    /**
     * check the given date is always less than or equal to today date
     * if date is correct then it return true otherwise it return exception
     * @param date  the given date
     * @return true or exception
     * @throws InvalidInputException  custom exception
     */
    public boolean checkDate(Date date) throws InvalidInputException{
        if(date.after(new Date(System.currentTimeMillis())) ){
            throw new InvalidInputException(400,"Date cannot be greater than today's date");
        }else {
            return true;
        }
    }
}
