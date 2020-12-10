package com.harsha.bookmyshow;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * created on: 10/12/20
 * created by: harsha
 */
public class Utils {
    public static boolean isDateInFuture(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return !sdf.format(new Date()).equals(sdf.format(date)) && date.after(new Date());
    }

    public static Date addDaysToDate(Date date, Integer days) {
        Calendar cdate = Calendar.getInstance();
        cdate.setTime(date);
        cdate.add(Calendar.DAY_OF_MONTH, days);
        return cdate.getTime();
    }
}
