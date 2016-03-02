package com.pranveraapp.common;

import com.pranveraapp.common.time.SystemTime;

import java.util.Calendar;

/**
 * Created by elion on 03/02/16.
 */
public class TimeDTO {

    private Calendar cal;

    public TimeDTO(){
        cal = SystemTime.asCalendar();
    }

    public TimeDTO(Calendar cal){
        this.cal = cal;
    }
}
