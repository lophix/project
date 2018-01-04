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
import java.util.stream.Stream;

import static com.flag.log.cleaning.PatternConst.DATE_PATTERN;
import static com.flag.log.cleaning.PatternConst.THREAD_NAME_PATTERN;

/**
 * @author xuj
 * @since 2017-12-06-10:24
 */
public class LogSearcher implements Runnable {

    private static final int LOAD_LINES_NUM = 100;

    private Deque<String> linesQueue = new LinkedList<>();

    private final Stream<String> fileContent;

    private static final AtomicInteger ID = new AtomicInteger(1);

    private String fileName;

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
            linesQueueMaintain(s);

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
                            if (addFullLog(iterator, list)) {
                                break;
                            }
                            if (!checkTarget(list.get(list.size() - 1), checkPoint)) {
                                list.clear();
                                continue;
                            }
                            break;
                        } else {
                            if (addFullLog(iterator, list)) {
                                break;
                            }
                            if (checkTarget(list.get(list.size() - 1), checkPoint)) {
                                list.clear();
                                break;
                            }
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
                if (!list.isEmpty()) {
                    Matcher matcher = DATE_PATTERN.matcher(list.get(list.size() - 1));
                    if (matcher.find()) {
                        writeFile(list);
                    }
                }
            }
        });
        done();
    }

    public String getFileName() {
        return fileName;
    }

    public String getTargetLine() {
        return targetLine;
    }

    public String getBindCode() {
        return bindCode;
    }

    public String getSearchFlag() {
        return searchFlag;
    }

    private void linesQueueMaintain(String s) {
        if (linesQueue.size() == LOAD_LINES_NUM) {
            linesQueue.removeLast();
        }
        linesQueue.offerFirst(s);
    }

    private <T> boolean addFullLog(Iterator<T> iterator, List<T> list) {
        boolean fail = false;
        int i = 0;
        while (iterator.hasNext() && i < 4) {
            list.add(iterator.next());
            i++;
        }
        if (i < 4) {
            list.clear();
            fail = true;
        }
        return fail;
    }

    private boolean checkTarget(String s, String real) {
        Matcher matcher = THREAD_NAME_PATTERN.matcher(s);
        return matcher.find() && StringUtils.equals(matcher.group(), real);
    }

    private String getCheckPoint(String s) {
        Matcher matcher = THREAD_NAME_PATTERN.matcher(s);
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
            Path resultPath = PathUtil.getPath("F:\\07_self\\project\\project-test\\src\\main\\resources\\result\\tmp", fileName);
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
