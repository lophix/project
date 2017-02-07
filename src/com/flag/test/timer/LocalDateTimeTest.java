package com.flag.test.timer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-07-10:09
 * @Version
 */
public class LocalDateTimeTest {
    public static void main(String[] args) {
        printTime();
    }

    private static void printTime(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.get(ChronoField.MILLI_OF_DAY));
    }
}
