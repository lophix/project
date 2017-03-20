package com.flag.test;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-08-10:57
 * @Version
 */
public class SyntaxTest {
    public static void main(String[] args) {
        printMethod(1, "hello");
        printMethod("world", 2);
    }

    private static void printMethod(String string, Integer integer){
        System.out.println("This is a test about method head " + string + integer);
    }

    private static void printMethod(Integer integer, String string){
        System.out.println("This is a test about method head " + integer + string);
    }
}
