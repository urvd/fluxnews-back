package com.backend.fluxnewsapi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    public static String today(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        return format.format(today);
    }

    public static String tomorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        return format.format(tomorrow);
    }
}
