package com.pranveraapp.common.time;

import com.pranveraapp.common.classloader.release.ThreadLocalManager;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by elion on 03/02/16.
 */
public class SystemTime {

    private static final TimeSource defaultTimeSource = new DefaultTimeSource();
    private static TimeSource globalTimeSource = null;
    private static final ThreadLocal<TimeSource> localTimeSource =
            ThreadLocalManager.createThreadLocal(TimeSource.class,false);

    public static TimeSource getTimeSource(){
        TimeSource applicableTimeSource;
        TimeSource localTS = localTimeSource.get();

        if(localTS !=null){
            applicableTimeSource = localTS;
        }
        else if(globalTimeSource !=null){
            applicableTimeSource = globalTimeSource;
        }
        else {
            applicableTimeSource = defaultTimeSource;
        }
        return applicableTimeSource;
    }

    public static long asMillis(){
        return asMillis(true);
    }

    public static long asMillis(boolean includeTime){
        if(includeTime){
            return getTimeSource().timeInMillis();
        }
        return asCalendar(includeTime).getTimeInMillis();
    }

    public static Calendar asCalendar(){
        return asCalendar(true);
    }

    public static Calendar asCalendar(boolean includeTime){
        return asCalendar(Locale.getDefault(),TimeZone.getDefault(),includeTime);
    }

    public static Calendar asCalendar(Locale locale, TimeZone timeZone, boolean includeTime){
        Calendar calendar = Calendar.getInstance(timeZone,locale);
        calendar.setTimeInMillis(asMillis());

        if(!includeTime){
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
        }
        return calendar;
    }

}
