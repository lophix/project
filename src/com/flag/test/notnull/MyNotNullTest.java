package com.flag.test.notnull;

import javax.annotation.Nonnull;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-24-10:24
 * @Version
 */
public class MyNotNullTest {
    public static void main(String[] args) {
        String s = "this is a string line";
        if (s.length() > 3) {
            s = null;
        }
        if (s != null) {
            println(s);
        }
        print(s);
    }

    public static void print(@Nonnull String str) {
        System.out.print(str);
    }

    public static void println(@Nonnull String str) {
        System.out.println(str);
    }
}
