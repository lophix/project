package com.flag.test.atomic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Authuor Administrator
 * @Create 2016-11-30-10:19
 */
public class ConcurrentMain {
    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger count = new AtomicInteger(0);
        final Map<String, String> map = new ConcurrentHashMap<>();
        final AtomicInteger num = new AtomicInteger(10);
        ExecutorService executorService = Executors.newCachedThreadPool(Thread::new);
        executorService.submit(new PutJob(map, count, num, "Thread-1"));
        executorService.submit(new PrintJob(map));
        executorService.submit(new PutJob(map, count, num, "Thread-2"));
        executorService.submit(new RemoveJob(map, num, count));
        executorService.shutdown();

        if (!executorService.awaitTermination(20000, TimeUnit.MILLISECONDS)) {
            executorService.shutdownNow();
        }
        System.out.println("finally is : " + map.get("test"));
    }
}


class PutJob implements Runnable {

    private final Map<String, String> map;
    private final AtomicInteger count;
    private final AtomicInteger num;
    private String name;

    public PutJob(Map<String, String> map, AtomicInteger count, AtomicInteger num, String name) {
        this.map = map;
        this.count = count;
        this.num = num;
        this.name = name;
    }

    @Override
    public void run() {
        while (num.get() < 20) {
            while (count.getAndIncrement() > 0) {
                if (num.get() >= 20){
                    return;
                }
            }
            System.out.println(name + " will put");
            String result = map.putIfAbsent("test", "thread : " + num);
            if (result == null) {
                System.out.println(name + " putted : " + num);
                num.getAndIncrement();
            }
            count.set(0);
        }
    }
}

class RemoveJob implements Runnable {

    private final Map<String, String> map;
    private final AtomicInteger num;
    private final AtomicInteger count;

    public RemoveJob(Map<String, String> map, AtomicInteger num, AtomicInteger count) {
        this.map = map;
        this.num = num;
        this.count = count;
    }

    @Override
    public void run() {
        while (num.get() < 20) {
            while (count.getAndIncrement() > 0) {
                if (num.get() >= 20){
                    return;
                }
            }
            System.out.println("remove : " + map.get("test"));
            map.remove("test");
            count.set(0);
        }
    }
}

class PrintJob implements Runnable {

    private final Map<String, String> map;
    private String lastValue = null;

    public PrintJob(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        while (true) {
            String value = map.get("test");
            if (value != null && !value.equals(lastValue)) {
                lastValue = value;
                System.out.println("print value is " + value);
            }
        }
    }
}