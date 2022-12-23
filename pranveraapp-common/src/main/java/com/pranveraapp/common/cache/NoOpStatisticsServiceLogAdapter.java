package com.pranveraapp.common.cache;

/**
 * Created by elion on 25/02/16.
 */
public class NoOpStatisticsServiceLogAdapter implements StatisticsServiceLogAdapter {

    @Override
    public void activateLogging(Class clazz) {
        //do nothing
    }

    @Override
    public void disableLogging(Class clazz) {
        //do nothing
    }
}
