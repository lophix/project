package com.flag.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-06-10:10
 * @Version
 */
public class LambdaTest {
    public static void main(String[] args) {
        printIterator();
    }

    private static void printIterator(){
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(""+i);
        }
        Iterator<?> it = strings.iterator();
        it.forEachRemaining(System.out::println);
    }
}
