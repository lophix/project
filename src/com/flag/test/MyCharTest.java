package com.flag.test;

import org.jetbrains.annotations.NotNull;

/**
 * @author xuj
 * @since 2017-02-28 11:13
 */
public class MyCharTest {

    private static final String[] HEX_SYMBOL = new String[]{"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public static void main(String[] args) {
        System.out.println(decimalToHex('8'));
        System.out.println(Integer.toHexString('8'));
    }

    @NotNull
    private static String decimalToHex(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = (num >> (7 - i) * 4) & 0xf;
            if (index ==0 && sb.length() == 0){
                continue;
            }
            sb.append(HEX_SYMBOL[index]);
        }
        sb.append("H");
        return sb.toString();
    }
}
