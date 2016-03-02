package com.pranveraapp.common.time;

/**
 * Created by elion on 03/02/16.
 */
public class DefaultTimeSource implements TimeSource{
    /**
     * Retunr the current time of the system in millis
     * @return
     */
    public long timeInMillis(){
        return System.currentTimeMillis();
    }
}
