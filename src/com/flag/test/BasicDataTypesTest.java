package com.flag.test;

import java.util.Date;

/**
 * @Authuor Administrator
 * @Create 2016-10-31-15:30
 */
public class BasicDataTypesTest {

    public static void main(String[] args) {
        /*byte a = (byte) 0xf5;
        int s1 = (int) a;
        int s2 = a & 0xff;
        int s3 = 256 + a;
        System.out.println("s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3);*/

        int a = 23156;
        long start, end;
        int r1 = 0, r2 = 0;
        start = System.nanoTime();
        for (int i = 0; i < 1000000; i ++) {
            r1 = a % 256;
        }
        end = System.nanoTime();
        long r1coast, r2cost;
        r1coast = end - start;
        start = System.nanoTime();
        for (int i = 0; i < 1000000; i ++) {
            r2 = a & 255;
        }
        end = System.nanoTime();
        r2cost = end - start;
        System.out.println("r1 = " + r1 + ", r2 = " + r2);
        System.out.println("r1 : " + r1coast + ", r2 : " + r2cost);
    }

}
