package com.flag.test;

import org.junit.Test;

/**
 * @Authuor Administrator
 * @Create 2016-11-01-13:26
 */
public class StringTest {

    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String abc = "abc";
        if(abc.contains(a)){
            System.out.println("success a");
        }
        if(abc.contains(b)){
            System.out.println("success b");
        }
    }
    @Test
    public void testStringFormat(){
        for (int i = 0; i < 10; i++){
            System.out.println(String.format("%08d", i));
        }
    }

}
