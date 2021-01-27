package com.zs.hobbies.dto;

import java.sql.Date;
import java.sql.Time;

/**
 * this class provide all time facility
 */
public class Timing {
    private Time startTime;
    private Time endTime;
    private Date day;

    public Timing(Time startTime, Time endTime,Date day) {
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
}
