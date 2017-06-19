package com.flag.method;

import java.util.Scanner;

/**
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
