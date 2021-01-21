package main.java.com.zs.hobbies.service;

import java.sql.Date;
import java.util.*;

public class SimilarRequirement {

    public static int longestStreak(SortedSet<String> dates){
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

            if(previous_day == -1) {
                previous_day = date_day;
                previous_month = date_month;
                previous_year = date_year;
                count = 1;
            }else {
                if(date_day == previous_day+1 && previous_month == date_month && previous_year == date_year) {
                    /**
                     * when only date change
                     * month and year are same
                     */
                    previous_day = date_day;
                    count++;
                }else if(date_day == 1 && previous_day == 31 && previous_month + 1 == date_month && previous_year == date_year){
                    /**
                     * when month will change
                     * date start again 1
                     * year is same
                     */
                    previous_day = 1;
                    previous_month = date_month;
                    count++ ;
                }else if(date_day == 1 && previous_day == 31 && previous_month == 12 && date_month == 1 && previous_year +1 == date_year) {
                    previous_day = 1;
                    previous_month = 1;
                    previous_year = date_year;
                    count++;
                }else {
                    if(max < count) {
                        max = count;
                    }

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

    public static int latestStreak(SortedSet<String> dates){
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

            if(previous_day == -1) {
                previous_day = date_day;
                previous_month = date_month;
                previous_year = date_year;
                count = 1;
            }else {
                if(date_day == previous_day+1 && previous_month == date_month && previous_year == date_year) {
                    /**
                     * when only date change
                     * month and year are same
                     */
                    previous_day = date_day;
                    count++;
                }else if(date_day == 1 && previous_day == 31 && previous_month + 1 == date_month && previous_year == date_year){
                    /**
                     * when month will change
                     * date start again 1
                     * year is same
                     */
                    previous_day = 1;
                    previous_month = date_month;
                    count++ ;
                }else if(date_day == 1 && previous_day == 31 && previous_month == 12 && date_month == 1 && previous_year +1 == date_year) {
                    previous_day = 1;
                    previous_month = 1;
                    previous_year = date_year;
                    count++;
                }else {
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
