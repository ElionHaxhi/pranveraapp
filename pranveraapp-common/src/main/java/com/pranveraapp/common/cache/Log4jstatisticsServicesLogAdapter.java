package com.pranveraapp.common.cache;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

/**
 * Created by elion on 25/02/16.
 */
public class Log4jstatisticsServicesLogAdapter implements StatisticsServiceLogAdapter {
    @Override
    public void activateLogging(Class clazz) {
        LogManager.getLogger(clazz).setLevel(Level.INFO);
    }

    @Override
    public void disableLogging(Class clazz) {
        LogManager.getLogger(StatisticsServiceImpl.class).setLevel(Level.DEBUG);
    }

}
