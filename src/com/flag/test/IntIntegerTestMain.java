package com.flag.test;

import java.util.Arrays;
import java.util.List;

/**
 * @Authuor Administrator
 * @Create 2016-12-19-19:30
 */
public class IntIntegerTestMain {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 11);
        int a = 11;
        System.out.println(list.contains(a));
    }
}
