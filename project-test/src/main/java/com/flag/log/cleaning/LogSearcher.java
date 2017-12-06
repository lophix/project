package com.flag.log.cleaning;

import com.flag.project.core.utils.PathUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author xuj
 * @since 2017-12-06-10:24
 */
public class LogSearcher implements Runnable {

    private Deque<String> linesQueue = new LinkedList<>();

    private Stream<String> fileContent;

    private static final AtomicInteger ID = new AtomicInteger(1);

    private String fileName = "";

    private static final String skip1 = "|00000000| ";

    private static final String skip2 = "+--------+";

    private static final String FLAG = "7e 02 00";

    public LogSearcher(Stream<String> fileContent) {
        this.fileContent = fileContent;
        fileName = "result" + ID.getAndIncrement();
    }

    @Override
    public void run() {
        fileContent.filter(StringUtils::isNotBlank).forEach(s -> {
            if (linesQueue.size() == 100) {
                linesQueue.removeLast();
            }
            linesQueue.offerFirst(s);

            if (s.contains("INFO  LocationService:45 reportLocation - locationService reportLocation start，bindCode is " + "MP35579")) {
                List<String> list = new ArrayList<>(16);
                Iterator<String> iterator = linesQueue.iterator();
                while (iterator.hasNext()) {
                    String str = iterator.next();
                    if (str.startsWith(skip2)) {
                        list.add(str);
                    } else if (str.startsWith(skip1)) {
                        int skipIndex = skip1.length();
                        String flag = str.substring(skipIndex, skipIndex + FLAG.length());
                        if (flag.equals(FLAG)) {
                            list.add(str);
                            for (int i = 0; i < 4; i++) {
                                list.add(iterator.next());
                            }
                            break;
                        } else {
                            list.clear();
                        }
                    } else if (!list.isEmpty()) {
                        if (list.size() == 1) {
                            if (str.startsWith(" ")) {
                                list.clear();
                            } else {
                                list.add(str);
                            }
                        } else {
                            list.add(str);
                        }
                    }
                }
                writeFile(list);
            }
        });
        done();
    }

    private void writeFile(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.reverse(list);
        try {
            Path resultPath = PathUtil.getPath("F:\\07_self\\project\\project-test\\src\\main\\resources\\result\\", fileName);
            if (resultPath == null) {
                return;
            }
            if (Files.notExists(resultPath)) {
                resultPath = Files.createFile(resultPath);
            }
            Files.write(resultPath, list, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void done() {
        Main.done();
    }
}
