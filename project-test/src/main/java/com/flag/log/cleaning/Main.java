package com.flag.log.cleaning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author xuj
 * @since 2017-12-06-10:23
 */
public class Main {

    private static final ExecutorService POOL = Executors.newCachedThreadPool();
    private static CountDownLatch SEARCHER_NUMS;

    public static void main(String[] args) {
        String path = "E:\\logs";
        String bindCode = "PP20247";
        if (args.length > 1) {
            path = args[0];
            bindCode = args[1];
        }
        submitTask(path, bindCode);
    }

    private static void init(int size) {
        SEARCHER_NUMS = new CountDownLatch(size);
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
//                    POOL.submit(LogSearcher.LogSearcherBuilder.getBuilder(Files.lines(path1), bindCode).build());
                    LogSearcher.LogSearcherBuilder.getBuilder(Files.lines(path1), bindCode).build().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        POOL.submit(new DataAggregator());
        new DataAggregator().run();
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
