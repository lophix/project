package com.flag.test.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Authuor Administrator
 * @Create 2016-12-01-17:57
 */
public class LockTestMain {
    public int s = 0;
    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new TestThread("thread - 1 : "));
        executor.submit(new TestThread("thread - 2 : "));
        executor.shutdown();
        if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
            executor.shutdownNow();
        }
        System.out.println(KeyCommonClass.l.s);
    }
}

class TestThread implements Runnable {

    private String name;

    public TestThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
//            synchronized (KeyCommonClass.l){
            KeyCommonClass.l.lock.lock();
            KeyCommonClass.l.s++;
            System.out.println(name + KeyCommonClass.l.s);
            KeyCommonClass.l.lock.unlock();
//            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class KeyCommonClass {
    public static LockTestMain l = new LockTestMain();
}
