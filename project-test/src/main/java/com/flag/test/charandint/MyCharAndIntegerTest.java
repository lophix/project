package com.flag.test.charandint;


/**
 * @author xuj
 * @since 2017-03-03-9:55
 */
public class MyCharAndIntegerTest {
    public static void main(String[] args) {
        print();
    }

    private static void print() {
        String bit = "01100111000101";
        int[] ints = new int[bit.length()];
        for (int i = 0; i < bit.length(); i++) {
            if (i % 2 == 0) {
                ints[i] = Integer.valueOf(bit.substring(i, i + 1));
            } else {
                ints[i] = bit.charAt(i);
            }
        }
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
