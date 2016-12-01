package com.flag.test.lock;

/**
 * @Authuor Administrator
 * @Create 2016-12-01-17:57
 */
public class LockTestMain {
    public int s = 0;
    static Thread the1, the;

    public static void main(String[] args) throws InterruptedException {
        the1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (KeyCommonClass.l){
                    KeyCommonClass.l.s++;
                    System.out.println("thread - 1 : " + KeyCommonClass.l.s);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        the1.start();

        the = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (KeyCommonClass.l){
                    KeyCommonClass.l.s++;
                    System.out.println("thread - 2 : " + KeyCommonClass.l.s);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        the.start();
        Thread.sleep(20000);
        System.out.println(KeyCommonClass.l.s);
    }
}

class KeyCommonClass {
    public final static LockTestMain l = new LockTestMain();
}
