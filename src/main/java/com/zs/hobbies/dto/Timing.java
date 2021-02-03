package com.zs.hobbies.dto;

import java.sql.Time;
import java.sql.Date;

public class Timing {
    private Time startTime;
    private Time endTime;
    private Date day;


    public Timing(String startTime, String endTime , String day) {
        this.startTime = Time.valueOf(startTime);
        this.endTime = Time.valueOf(endTime);
        this.day = Date.valueOf(day);
    }

    public Timing(Time startTime, Time endTime, Date day) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public void setStartTime(String startTime) {
        this.startTime = Time.valueOf(startTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = Time.valueOf(endTime);
    }

    public void setDay(String day) {
        this.day = Date.valueOf(day);
    }
}
