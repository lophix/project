package com.flag.test;

/**
 * @Authuor Administrator
 * @Create 2016-10-31-14:08
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(0);
        StringBuilder strb = new StringBuilder("");
        strb.append((byte)0);
        System.out.println("stringBuilder = " + stringBuilder + "\tlength = " + stringBuilder.length());
        System.out.println("strb = " + strb + "\tlength = " + strb.length());
    }
}
