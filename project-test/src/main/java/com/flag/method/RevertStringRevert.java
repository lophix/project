package com.flag.method;

import java.util.Scanner;

/**
 * 给出一个长度不超过1000的字符串，判断它是不是回文(顺读，逆读均相同)的。
 *
 * @author xuj
 * @since 2017-06-19-14:47
 */
public class RevertStringRevert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();

            boolean flag = true;
            for (int i = 0; i < line.length(); i++) {
                int tail = line.length() - 1 - i;
                if (tail < i) {
                    break;
                }
                if (line.charAt(i) != line.charAt(tail)) {
                    flag = false;
                    break;
                }
            }
            System.out.println(flag ? "Yes!" : "No!");
        }
        sc.close();
    }
}
