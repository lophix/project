package com.flag.code;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class MessageSequence {

    private static Logger log = LogManager.getLogger(MessageSequence.class);
	
    public static void main(String[] args) {
    	System.out.println("Its just a test for my personal computer");
        String str = "xmlxxx:xxdz:edit";
        List<String> list = Arrays.asList(str.split(":"));
        list.forEach(System.out::println);
//        Float f = Float.valueOf(String.format("%.3f", null));
        System.out.println(String.format("%.3f", null));
        log.info(" log test {}", str);
    }
}
