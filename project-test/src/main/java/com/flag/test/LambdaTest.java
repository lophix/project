package com.flag.test;

import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-06-10:10
 * @Version
 */
public class LambdaTest {
    public static void main(String[] args) {
//        printIterator();
//        testBreak();
        printFilter();
    }

    private static void printIterator(){
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(""+i);
        }
        Iterator<?> it = strings.iterator();
        it.forEachRemaining(System.out::println);
    }

    private static void testBreak(){
        List<String> strings = new ArrayList<>(10);
        for (int i = 0; i < 10; i++){
            strings.add(i + "");
        }
        strings.forEach(s -> {
            if (s.equals("4")){
                return;
            }
            System.out.println(s);
        });
    }

    private static void printFilter() {
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> tempMap = new HashMap<>();
            if (i % 2 == 0) {
                tempMap.put("1", "1");
                tempMap.put(i + "", i + "");
            } else {
                tempMap.put("1", "");
            }
            data.add(tempMap);
        }

        data = data.stream().filter(map -> StringUtils.isNotEmpty(map.get("1"))).collect(Collectors.toList());

        System.out.println(data.size());
    }
}
