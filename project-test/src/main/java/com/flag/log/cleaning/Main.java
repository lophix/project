package com.flag.log.cleaning;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static com.flag.log.cleaning.CommonConst.SIMPLE_DATE_FORMAT_THREAD_LOCAL;

/**
 * @author xuj
 * @since 2017-12-06-10:23
 */
public class Main {

    private static final ExecutorService POOL = Executors.newCachedThreadPool();
    private static CountDownLatch SEARCHER_NUMS;

    private static String sourceLogDir = "E:\\logs";
    private static String bindCode = "PP20247";

    static String tmpDir = "F:\\07_self\\project\\project-test\\src\\main\\resources\\result\\tmp";
    static String resultDir = "F:\\07_self\\project\\project-test\\src\\main\\resources\\result";
    static String resultFile = "result.txt";

    private static String searchFlag = null;

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private static String startTimeStr = null;
    private static String endTimeStr = null;

    private static Date startTime = null;
    private static Date endTime = null;

    public static void main(String[] args) {
        submitTask(sourceLogDir, bindCode);
    }

    public static Date getStartTime() {
        return startTime;
    }

    public static Date getEndTime() {
        return endTime;
    }

    private static void init(int size) {
        SEARCHER_NUMS = new CountDownLatch(size);
        try {
            if (StringUtils.isNotBlank(startTimeStr)) {
                startTime = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().parse(startTimeStr);
            }

            if (StringUtils.isNotBlank(endTimeStr)) {
                endTime = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().parse(endTimeStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void submitTask(String path, final String bindCode) {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            init(paths.filter(Files::isRegularFile).mapToInt(value -> 1).sum());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.filter(Files::isRegularFile).forEach((path1 -> {
                try {
                    POOL.submit(LogSearcher.LogSearcherBuilder
                            .getBuilder(Files.lines(path1), bindCode)
                            .searchFlag(searchFlag)
                            .build());
//                    LogSearcher.LogSearcherBuilder.getBuilder(Files.lines(path1), bindCode).build().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }

        POOL.submit(new DataAggregator());
//        new DataAggregator().run();
        POOL.shutdown();
    }

    static void done() {
        if (SEARCHER_NUMS != null) {
            SEARCHER_NUMS.countDown();
        }
    }

    static void await() throws InterruptedException {
        if (SEARCHER_NUMS != null) {
            SEARCHER_NUMS.await();
        }
    }
}
