package com.pranveraapp.common.cache;

/**
 * Created by elion on 25/02/16.
 */
public interface StatisticsService {

    void addCacheStat(String key, boolean isHit);

    Long getLogResolution();

    void setLogResolution(Long logResolution);

    void activateLogging();

    void disableLogging();

}
