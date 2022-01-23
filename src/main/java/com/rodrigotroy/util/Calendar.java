package com.rodrigotroy.util;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 8/23/17
 * Time: 12:25
 */
public class Calendar {
    private java.util.Calendar calendar;

    private Calendar() {
        this.calendar = java.util.Calendar.getInstance();
    }

    public static Calendar getInstance() {
        return new Calendar();
    }

    public static Calendar getInstance(java.util.Calendar calendar) {
        final Calendar calendar1 = new Calendar();
        calendar1.calendar = calendar;

        return calendar1;
    }

    public Calendar addDay(Integer days) {
        this.calendar.add(java.util.Calendar.DAY_OF_MONTH,
                          days);

        return this;
    }

    public Calendar addMonth(Integer months) {
        this.calendar.add(java.util.Calendar.MONTH,
                          months);

        return this;
    }

    public Calendar addYear(Integer years) {
        this.calendar.add(java.util.Calendar.YEAR,
                          years);

        return this;
    }

    public Calendar setTime(Integer year,
                            Integer month,
                            Integer day) {
        this.calendar.set(year,
                          month,
                          day);

        return this;
    }

    public Date getTime() {
        return this.calendar.getTime();
    }
}
