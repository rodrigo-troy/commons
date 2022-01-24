package com.rodrigotroy.commons.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 8/23/17
 * Time: 12:44
 */
public class CalendarTest {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @DataProvider
    public Object[][] fechas() {
        java.util.Calendar calendarAdd2Days = java.util.Calendar.getInstance();
        calendarAdd2Days.add(java.util.Calendar.DAY_OF_MONTH,
                             2);

        java.util.Calendar calendarAdd10Month = java.util.Calendar.getInstance();
        calendarAdd10Month.add(java.util.Calendar.MONTH,
                               10);

        java.util.Calendar calendarAdd5Year = java.util.Calendar.getInstance();
        calendarAdd5Year.add(java.util.Calendar.YEAR,
                             5);

        java.util.Calendar calendarAddCustom = java.util.Calendar.getInstance();
        calendarAddCustom.add(java.util.Calendar.YEAR,
                              5);
        calendarAddCustom.add(java.util.Calendar.MONTH,
                              15);
        calendarAddCustom.add(java.util.Calendar.DAY_OF_MONTH,
                              2);

        return new Object[][]{
                {Calendar.getInstance().getTime(), java.util.Calendar.getInstance().getTime()},
                {Calendar.getInstance().addDay(2).getTime(), calendarAdd2Days.getTime()},
                {Calendar.getInstance().addMonth(10).getTime(), calendarAdd10Month.getTime()},
                {Calendar.getInstance().addYear(5).getTime(), calendarAdd5Year.getTime()},
                {Calendar.getInstance().addYear(5).addMonth(15).addDay(2).getTime(), calendarAddCustom.getTime()}};
    }


    @Test(dataProvider = "fechas")
    public void test(Object o1,
                     Object o2) {
        Assert.assertEquals(simpleDateFormat.format(o1),
                            simpleDateFormat.format(o2));
    }
}
