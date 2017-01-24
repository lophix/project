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
        list.forEach(System.out::println);

        System.out.println("*************************************************");
        print();
    }

    private static void print(){
        int size = 10;
        List<List<Integer>> numList = new ArrayList<>(size);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size*3; i++){
            list.add(i);
        }
        for (int i = 0; i < size; i++){
            numList.add(new ArrayList<>());
        }
        for (int i = 0; i < list.size(); i++){
            numList.get(i%size).add(list.get(i));
        }
        numList.forEach(System.out::println);
    }
}
