package com.flag.log.cleaning;

import com.flag.project.core.utils.PathUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

/**
 * @author xuj
 * @since 2017-12-06-10:25
 */
public class DataAggregator implements Runnable {
    @Override
    public void run() {
        Path path = PathUtil.getPath("F:\\07_self\\project\\project-test\\src\\main\\resources\\result", "result.txt");if (path == null) {
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
        try (Stream<Path> paths = Files.walk(Paths.get("F:\\07_self\\project\\project-test\\src\\main\\resources\\result"))) {
            Path finalPath = path;
            paths.filter(Files::isRegularFile).forEach(path1 -> {
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
}
