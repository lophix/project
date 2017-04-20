package com.flag.multithread.test.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xuj
 * @since 2017-04-20-10:13
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int totalThread = 5;
        CyclicBarrier barrier = new CyclicBarrier(totalThread, () -> System.out.println("hello"));
        for (int i = 0; i < totalThread; i++) {
            String threadName = "Thread-" + i;
            new Thread(() -> {
                if ("Thread-2".equals(threadName)) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(threadName + " is await");
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " ended");
            }).start();
        }
    }
}
