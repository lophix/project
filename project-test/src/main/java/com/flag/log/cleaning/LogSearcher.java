package com.flag.log.cleaning;

import com.flag.project.core.utils.PathUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author xuj
 * @since 2017-12-06-10:24
 */
public class LogSearcher implements Runnable {

    private static final Pattern PATTERN = Pattern.compile("\\[\\S+\\-\\d+\\-\\d+\\]");

    private Deque<String> linesQueue = new LinkedList<>();

    private final Stream<String> fileContent;

    private static final AtomicInteger ID = new AtomicInteger(1);

    private String fileName = "";

    private static final String skip1 = "|00000000| ";

    private static final String skip2 = "+--------+";

    private String targetLine = "INFO  VTMsgHandleTask:190 setCnnAttrToObject - terminal :$bindCode is register ,will update set common info";

    private final String bindCode;

    private String searchFlag = "7e 02 00";


    public LogSearcher(Stream<String> fileContent, String searchFlag, String targetLine, String bindCode) {
        this.fileContent = fileContent;
        if (searchFlag != null) {
            this.searchFlag = searchFlag;
        }
        if (targetLine != null) {
            this.targetLine = targetLine;
        } else {
            this.targetLine = this.targetLine.replace("$bindCode", bindCode);
        }
        this.bindCode = bindCode;
        fileName = "result" + ID.getAndIncrement();
    }

    @Override
    public void run() {
        fileContent.filter(StringUtils::isNotBlank).forEach(s -> {
            if (linesQueue.size() == 100) {
                linesQueue.removeLast();
            }
            linesQueue.offerFirst(s);

            if (s.contains(targetLine)) {
                String checkPoint = getCheckPoint(s);
                List<String> list = new ArrayList<>(16);
                Iterator<String> iterator = linesQueue.iterator();
                while (iterator.hasNext()) {
                    String str = iterator.next();
                    if (str.startsWith(skip2)) {
                        list.add(str);
                    } else if (str.startsWith(skip1)) {
                        int skipIndex = skip1.length();
                        String flag = str.substring(skipIndex, skipIndex + searchFlag.length());
                        if (flag.equals(searchFlag)) {
                            list.add(str);
                            int i = 0;
                            while (iterator.hasNext() && i < 4) {
                                list.add(iterator.next());
                                i++;
                            }
                            if (checkTarget(list.get(list.size() - 1), checkPoint)) {
                                list.clear();
                                continue;
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

    private boolean checkTarget(String s, String real) {
        Matcher matcher = PATTERN.matcher(s);
        return matcher.find() && StringUtils.equals(matcher.group(), real);
    }

    private String getCheckPoint(String s) {
        Matcher matcher = PATTERN.matcher(s);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
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

    public static class LogSearcherBuilder {
        private Stream<String> fileContent;

        private String bindCode;

        private String targetLine;

        private String searchFlag;

        private LogSearcherBuilder(Stream<String> fileContent, String bindCode) {
            this.fileContent = fileContent;
            this.bindCode = bindCode;
        }

        public static  LogSearcherBuilder getBuilder(Stream<String> fileContent, String bindCode) {
            return new LogSearcherBuilder(fileContent, bindCode);
        }

        public LogSearcherBuilder targetLine(String targetLine) {
            this.targetLine = targetLine;
            return this;
        }

        public LogSearcherBuilder searchFlag(String searchFlag) {
            this.searchFlag = searchFlag;
            return this;
        }

        public LogSearcher build() {
            return new LogSearcher(fileContent, searchFlag, targetLine, bindCode);
        }
    }
}
