package com.flag.code;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class MessageSequence {

    private static Logger log = LogManager.getLogger(MessageSequence.class);

    public static void main(String[] args) {
        System.out.println("Its just a test for my personal computer");
        String str = "xmlxxx:xxdz:edit";
        List<String> list = Arrays.asList(str.split(":"));
        list.forEach(System.out::println);
//        Float f = Float.valueOf(String.format("%.3f", null));
        Double d = 2.5D;
        if (d > 0){
            d = null;
        }
        System.out.println(String.format("%.3f", d));
        log.info(" log test {}", str);
        System.out.println("********************************************************");
        print1();
        System.out.println("********************************************************");
        getConfig();
    }

    private static void print() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("hello", "Hello");
        map.put("world", "World!");
        System.out.println(map.contains("hello") + "\t" + map.contains("Hello"));
    }

    private static void print1() {
        String s = "hello=world";
        List<String> strings = Arrays.asList(s.split("="));
        strings.forEach(System.out::println);
    }

    private static void getConfig() {
        String fileName = "dbconfig.properties";
        try {
//            URL url = new File(fileName).toURI().toURL();
//            URL url = MessageSequence.class.getClassLoader().getResource(fileName);
            URL url = MessageSequence.class.getResource("/" + fileName);
            System.out.println(url);
            Properties properties = new Properties();
            properties.load(url.openStream());
            System.out.println(properties.getProperty("jdbc.url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
