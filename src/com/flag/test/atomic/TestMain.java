package com.flag.test.atomic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Authuor Administrator
 * @Create 2016-11-30-9:13
 */
public class TestMain {
    public static AtomicInteger num = new AtomicInteger(0);
    public static volatile Map<String, String> map = new ConcurrentHashMap<>();
    static AtomicInteger n = new AtomicInteger(0);
    public static void main(String[] args) {
        new Thread(()->{
            while (n.get() < 20) {
                int i = num.getAndIncrement();
                while (i > 0){
                    i = num.get();
                }
                if(n.get() < 20) {
                    map.put("test", n + " : thread1");
                    System.out.println(map.get("test"));
                    n.getAndIncrement();
                }
                num.set(0);
            }
        }).start();
        new Thread(()->{
            while (n.get() < 20) {
                int i = num.getAndIncrement();
                while (i > 0){
                    i = num.get();
                }
                if(n.get() < 20) {
                    map.put("test", n + " : thread2");
                    System.out.println(map.get("test"));
                    n.getAndIncrement();
                }
                num.set(0);
            }
        }).start();
//        new Thread(()->{
//            while (true) {
//                int i = num.get();
//                while (i > 0){
//                    i = num.get();
//                }
//                System.out.println(map.get("test"));
//            }
//        }).start();
    }
}
