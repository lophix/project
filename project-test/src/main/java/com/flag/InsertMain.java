package com.flag;

import com.flag.project.core.utils.PathUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author xuj
 * @since 2017-09-30-22:45
 */
public class InsertMain {
    public static void main(String[] args) throws Exception {
        Path path2;
        String s;
        try {
            Path path1 = PathUtil.getPath(InsertMain.class, "tmp1.txt");
            path2 = PathUtil.getPath(InsertMain.class, "type.txt");
            List<String> allCarP = Files.readAllLines(path1);
            StringBuilder sb = new StringBuilder(allCarP.size());
            final int[] i = {0};
            allCarP.stream().filter(StringUtils::isNotBlank).forEach(carP -> {
                i[0]++;
                sb.append(carP).append(",");
                if (i[0] == 10) {
                    System.out.println(sb);
                    i[0] = 0;
                    sb.delete(0, sb.length());
                }
            });
            s = sb.substring(0, sb.length() - 1);
//            if (s != null) {
//                throw new Exception("exception");
//            }
        } finally {
            System.out.println("ok");
        }
//        System.out.println(s);
        System.out.println("hello");
        List<String> allCarT = Files.readAllLines(path2);
    }
}
