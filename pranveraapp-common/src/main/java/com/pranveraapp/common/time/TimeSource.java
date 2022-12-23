package com.pranveraapp.common.time;

/**
 * Created by elion on 03/02/16.
 */

/**
 * Provides an abstraction from the current system time.
 * Certain aspects of PranveraApp can be run in a mode that allows the end user to ovveride the current time.
 *
 */
public interface TimeSource {
    long timeInMillis();
}
