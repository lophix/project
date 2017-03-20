package com.flag.bitoperation;

/**
 * 用于验证位运算结果，熟悉位运算执行
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-02-28 16:50
 */
public class MyBitOperationTest {
    public static void main(String[] args) {
        int a = 101;
        int b = 41;
        System.out.println(calBit(a, b));
    }

    private static int calBit(int a, int b) {
        return a ^ b;
    }
}
