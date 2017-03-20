package com.flag.multithread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-13-16:18
 * @Version
 */
public class ExecutorServiceTest {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws InterruptedException {
        print();
    }

    private static void print(){
        List<String> strings = new ArrayList<>(50);
        for (int i = 0; i < 50; i++){
            strings.add(i + " : string");
        }

        strings.forEach(s -> executor.submit(() -> System.out.println(s + Thread.currentThread())));
        executor.shutdown();
    }
}
