package com.flag.test;

import java.util.*;

/**
 * @Authuor Administrator
 * @Create 2016-12-19-19:30
 */
public class IntIntegerTestMain {
    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 11);
//        int a = 11;
//        System.out.println(list.contains(a));
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0 ; i < 10; i++){
            Map<String, String> map = new HashMap<>();
            map.put("hello", "hello" + i);
            map.put("world", "world" + i);
            list.add(map);
        }
        list.forEach((s) -> {
            System.out.println(s.get("hello") + s.get("world"));
        });
    }
}
