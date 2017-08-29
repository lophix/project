package com.flag.thread;

import org.apache.commons.lang.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuj
 * @since 2017-06-15-15:51
 */
public class SimpleDateFormatTest {
    static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static final ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        final DateNeco dateNeco = new DateNeco();
        Runnable run1 = () -> {
            for (int i = 0; i < 10; i++) {
//                dateNeco.concurrentPrint("2017-06-15 17:01:30");
                dateNeco.commonPrint(new Date(1497507285814L));
            }
        };
        Runnable run2 = () -> {
            for (int i = 0; i < 10; i++) {
//                dateNeco.concurrentPrint("2017-06-15 14:01:30");
                dateNeco.commonPrint(new Date(1497507685814L));
            }
        };

        THREAD_POOL.execute(run1);
        THREAD_POOL.execute(run2);
        THREAD_POOL.shutdown();
    }

    private static final class DateNeco {
        public void concurrentPrint(String time) {
            try {
                Date date = SimpleDateFormatTest.DATE_FORMAT_THREAD_LOCAL.get().parse(time);
                System.out.println("======================>" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public void print(String time) {
            try {
                System.out.println("<========================" + SimpleDateFormatTest.DATE_FORMAT.parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public void fastPrint(Date time) {
            System.out.println(Thread.currentThread() + "****************" + SimpleDateFormatTest.FAST_DATE_FORMAT.format(time));
        }

        public void commonPrint(Date time) {
            System.out.println(Thread.currentThread() + "<===================" + SimpleDateFormatTest.DATE_FORMAT_THREAD_LOCAL.get().format(time));
        }
    }

}