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
        String path = "E:\\tcp-logs";
        if (args.length > 0) {
            path = args[0];
        }
        submitTask(path);
    }

    private static void init(int size) {
        SEARCHER_NUMS = new CountDownLatch(size);
    }

    private static void submitTask(String path) {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            init(paths.filter(Files::isRegularFile).mapToInt(value -> 1).sum());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.filter(Files::isRegularFile).forEach((path1 -> {
                try {
                    POOL.submit(new LogSearcher(Files.lines(path1)));
//                    new LogSearcher(Files.lines(path1)).run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }

        POOL.submit(DataAggregator::new);
//        new DataAggregator().run();
        POOL.shutdown();
    }

    static void done() {
        if (SEARCHER_NUMS != null) {
            SEARCHER_NUMS.countDown();
        }
    }

    static void await() throws InterruptedException {
        SEARCHER_NUMS.await();
    }
}
