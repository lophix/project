package com.flag.test.atomic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Authuor Administrator
 * @Create 2016-11-30-10:19
 */
public class ConcurrentMain {
    public static AtomicInteger num = new AtomicInteger(0);
    public static Map<String, String> map = new ConcurrentHashMap<>();
    static AtomicInteger n = new AtomicInteger(10);
    public static void main(String[] args) {
        new Thread(()->{
            while (n.get() < 20) {
                int i = num.getAndIncrement();
                while (i > 0){
                    i = num.get();
                }
                System.out.println("start put");
                map.putIfAbsent("test", "thread : " + n);
                System.out.println("put : " + map.get("test"));
                n.getAndIncrement();
                num.set(0);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while (n.get() < 20) {
                int i = num.getAndIncrement();
                while (i > 0){
                    i = num.get();
                }
                System.out.println("remove : " + map.get("test"));
                map.remove("test");
                num.set(0);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while (n.get() < 20) {
//                int i = num.get();
//                while (i > 0){
//                    i = num.get();
//                }
                System.out.println(map.get("test"));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
