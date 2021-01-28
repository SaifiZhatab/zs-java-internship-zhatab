package com.zs.hobbies.service;

import com.zs.hobbies.exception.InvalidInputException;

import java.util.*;

/**
 * This class will used in all Service all that's why make this class separately and make object of this
 * class in every service
 */
public class SimilarRequirement {

    /**
     * This function help you to find the longest streak
     * it return the longest streak integer value in according to the given data
     * @param dates     Sorted Set of date data
     * @return      return the integer value
     */
    public int longestStreak(SortedSet<String> dates) {
        int previous_day = -1, previous_month = -1, previous_year = -1;
        int max=0, count=0 ;

        Iterator<String> iterator = dates.iterator();

        while(iterator.hasNext()){
            String date =  iterator.next();

            /**
             * date formate : YYYY-MM-DD
             */
            int date_day = Integer.parseInt(date.substring(8));
            int date_month = Integer.parseInt(date.substring(5,7));
            int date_year = Integer.parseInt(date.substring(0,4));

            /**
             * This is the starting of loop
             * this if condition run only 1 time
             */
            if(previous_day == -1) {
                previous_day = date_day;
                previous_month = date_month;
                previous_year = date_year;
                count = 1;
            }else {
                /**
                 * when only date change
                 * month and year are same
                 */
                if(date_day == previous_day+1 && previous_month == date_month && previous_year == date_year) {
                    previous_day = date_day;
                    count++;
                }
                /**
                 * when month will change
                 * then new date is 1 and previous date is 31
                 * year is same
                 */
                else if(date_day == 1 && previous_day == 31 && previous_month + 1 == date_month && previous_year == date_year){
                    previous_day = 1;
                    previous_month = date_month;
                    count++ ;
                }
                /**
                 * when year will change in the date
                 * at this time new date is 1 and month is 1
                 * and previous date is 31 and month is 12
                 */
                else if(date_day == 1 && previous_day == 31 && previous_month == 12 && date_month == 1 && previous_year +1 == date_year) {
                    previous_day = 1;
                    previous_month = 1;
                    previous_year = date_year;
                    count++;
                }
                /**
                 * when streak will break, then this condition execute
                 */
                else {
                    if(max < count) {
                        max = count;
                    }

                    /**
                     * change the date
                     */
                    previous_day = date_day;
                    previous_month = date_month;
                    previous_year = date_year;
                    count = 1;
                }
            }
        }
        if(max < count) {
            max = count;
        }
        return max;
    }

    /**
     * This function help you to find the latest streak make
     * @param dates     Sorted Set of date data
     * @return      return the integer value
     */
    public int latestStreak(SortedSet<String> dates){
        int previous_day = -1, previous_month = -1, previous_year = -1;
        int max=0, count=0 ;

        Iterator<String> iterator = dates.iterator();

        while(iterator.hasNext()){
            String date =  iterator.next();

            /**
             * date formate : YYYY-MM-DD
             */
            int date_day = Integer.parseInt(date.substring(8));
            int date_month = Integer.parseInt(date.substring(5,7));
            int date_year = Integer.parseInt(date.substring(0,4));

            /**
             * This is the starting of loop
             * this if condition run only 1 time
             */
            if(previous_day == -1) {
                previous_day = date_day;
                previous_month = date_month;
                previous_year = date_year;
                count = 1;
            }else {
                /**
                 * when only date change
                 * month and year are same
                 */
                if(date_day == previous_day+1 && previous_month == date_month && previous_year == date_year) {
                    previous_day = date_day;
                    count++;
                }
                /**
                 * when month will change
                 * date start again 1
                 * year is same
                 */
                else if(date_day == 1 && previous_day == 31 && previous_month + 1 == date_month && previous_year == date_year){
                    previous_day = 1;
                    previous_month = date_month;
                    count++ ;
                }
                /**
                 * when year will change in the date
                 * at this time new date is 1 and month is 1
                 * and previous date is 31 and month is 12
                 */
                else if(date_day == 1 && previous_day == 31 && previous_month == 12 && date_month == 1 && previous_year +1 == date_year) {
                    previous_day = 1;
                    previous_month = 1;
                    previous_year = date_year;
                    count++;
                }
                /**
                 * when streak will break, then this condition execute
                 */
                else {
                    previous_day = date_day;
                    previous_month = date_month;
                    previous_year = date_year;
                    count = 1;
                }
            }
        }
        max = count;
        return max;
    }
}
