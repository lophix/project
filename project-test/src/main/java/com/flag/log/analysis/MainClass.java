package com.flag.log.analysis;

import com.flag.project.core.utils.PathUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author xuj
 * @since 2017-07-13-14:43
 */
public class MainClass {
    public static void main(String[] args) throws IOException, ParseException {
        Path path = PathUtil.getPath(MainClass.class, "temp.log");
        List<String> lines = Files.readAllLines(path);
        printMaxRepeatNum(lines);
    }

    private static void printMaxCostTime(List<String> list) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        long max = 0L;
        int flag = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            long tem = dateFormat.parse(list.get(i + 1).substring(45, 54)).getTime() - dateFormat.parse(list.get(i).substring(45, 54)).getTime();
            if (tem > max) {
                max = tem;
                flag = i;
            }
        }
        System.out.println("max : " + max + ", flag : " + flag);
    }

    private static void printMaxRepeatNum(List<String> list) {
        int max = 0;
        int tem = 0;
        String temp, last = null;
        for (String s : list) {
            temp = s.substring(s.indexOf("bindCode is ") + "bindCode is ".length());
            if (StringUtils.isNotBlank(temp) && temp.length() == 7) {
                if (StringUtils.isNotBlank(last)) {
                    if (last.equals(temp)) {
                        tem++;
                    } else {
                        if (tem > max) {
                            max = tem;
                        }
                        tem = 0;
                    }
                } else {
                    last = temp;
                }
            }
        }
        System.out.println(max);
    }
}
