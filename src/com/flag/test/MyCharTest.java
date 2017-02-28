package com.flag.test;

/**
 * @author xuj
 * @since 2017-02-28 11:13
 */
public class MyCharTest {
    public static void main(String[] args) {
        int i = '8';
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 8; j++) {
            sb.append(String.valueOf((i >> (7-j)*4) & 0xf));
        }
        System.out.println(sb.toString());
    }
}
