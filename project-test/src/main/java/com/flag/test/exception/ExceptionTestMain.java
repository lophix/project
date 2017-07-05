package com.flag.test.exception;

/**
 * @author xuj
 * @since 2017-07-05-10:50
 */
public class ExceptionTestMain {
    public static void main(String[] args) {
        try {
            System.out.println(throwNullPoint());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String throwNullPoint() {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println("null point exception");
        }
        return "hello";
    }
}
