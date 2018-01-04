package com.flag.log.cleaning;

import com.flag.project.core.utils.PathUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import static com.flag.log.cleaning.PatternConst.DATE_PATTERN;

/**
 * @author xuj
 * @since 2017-12-06-10:25
 */
public class DataAggregator implements Runnable {

    @Override
    public void run() {
        System.out.println("start data aggregator");
        Path path = PathUtil.getPath("F:\\07_self\\project\\project-test\\src\\main\\resources\\result", "result.txt");
        if (path == null) {
            return;
        }
        if (Files.notExists(path)) {
            try {
                path = Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        await();
        try (Stream<Path> paths = Files.walk(Paths.get("F:\\07_self\\project\\project-test\\src\\main\\resources\\result\\tmp"))) {
            Path finalPath = path;
            paths.filter(Files::isRegularFile).sorted(new LogDateComparator()).forEach(path1 -> {
                try {
                    Files.lines(path1).filter(StringUtils::isNotBlank).forEach(s -> {
                        try {
                            Files.write(finalPath, (s + "\n").getBytes(), StandardOpenOption.APPEND);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void await() {
        try {
            Main.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class LogDateComparator implements Comparator<Path> {

        private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        @Override
        public int compare(Path o1, Path o2) {
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 == null) {
                return -1;
            } else if (o2 == null) {
                return 1;
            }
            try {
                Date o1Factor = null, o2Factor = null;
                Optional<String> o1First = Files.lines(o1).findFirst();
                if (o1First.isPresent()) {
                    Matcher matcher = DATE_PATTERN.matcher(o1First.get());
                    if (matcher.find()) {
                        o1Factor = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().parse(matcher.group());
                    }
                }

                Optional<String> o2First = Files.lines(o2).findFirst();
                if (o2First.isPresent()) {
                    Matcher matcher = DATE_PATTERN.matcher(o2First.get());
                    if (matcher.find()) {
                        o2Factor = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().parse(matcher.group());
                    }
                }

                if (o1Factor == null && o2Factor == null) {
                    return 0;
                } else if (o1Factor == null) {
                    return -1;
                } else if (o2Factor == null) {
                    return 1;
                } else {
                    return o1Factor.compareTo(o2Factor);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
