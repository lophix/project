package multithread.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-20-14:05
 * @Version
 */
public class ObjectMethodTest {
    public static void main(String[] args) {
        MyPrintObject printObject = new MyPrintObject();
        ThreadFactoryBuilder factoryBuilder = new ThreadFactoryBuilder();
        factoryBuilder.setNameFormat("printObj");
        ThreadFactory factory= factoryBuilder.build();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20,factory);
        for (int i = 0; i < 20; i++) {
            executor.submit(new MyRunnableObject(printObject, String.valueOf(i)));
        }

        executor.shutdown();

        try {
            boolean b = !executor.awaitTermination(200000, TimeUnit.MILLISECONDS);
            if (b){
                try {
                    executor.shutdownNow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            try {
                Thread.currentThread().interrupt();
            } catch (Exception e1) {
                e.printStackTrace();
            }
        }
    }
}

class MyPrintObject{
    void print(String s){
        System.out.println(s);
    }
}

class MyRunnableObject implements Runnable{

    private MyPrintObject printObject;
    private String printMsg;

    public MyRunnableObject(MyPrintObject printObject, String printMsg){
        this.printObject = printObject;
        this.printMsg = printMsg;
    }

    @Override
    public void run() {
        printObject.print(this.printMsg);
    }
}
