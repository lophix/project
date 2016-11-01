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

        /*int a = 23156;
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
        System.out.println("r1 : " + r1coast + ", r2 : " + r2cost);*/

        byte b1 = (byte) -0x11;
        byte b2 = (byte) 0x10;
        byte[] bytes1 = {b1, b2};
        byte[] bytes2 = {b2, b1};
        System.out.println(Short.MAX_VALUE);
        System.out.println(bytesToShort(bytes1) + "\t" + bytesToShort(bytes2));
        System.out.println(bytesToShort1(bytes1) + "\t" + bytesToShort1(bytes2));
    }

    public static short bytesToShort(byte[] b){
        int s = 0;
        if (b[0] >= 0) {
            s = s + b[0];
        } else {
            s = s + 256 + b[0];
        }
        s = s * 256;
        if (b[1] >= 0) {
            s = s + b[1];
        } else {
            s = s + 256 + b[1];
        }
        short result = (short) s;
        return result;
    }

    public static short bytesToShort1(byte[] b){
        int s = 0;
        s += b[0] & 0xff;
        s = s << 8;
        s += b[1] & 0xff;
        short result = (short) s;
        return result;
    }

}
