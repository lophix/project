package com.flag.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @Authuor Administrator
 * @Create 2016-11-11-15:13
 */
public class PathTest {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                Path path = Paths.get(".").resolve("resources/dbconfig.properties");
                try {
                    new Properties().load(Files.newInputStream(path));
                    System.out.println("start");
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
