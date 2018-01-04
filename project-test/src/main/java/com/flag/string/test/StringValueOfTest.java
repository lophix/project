package com.flag.string.test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuj
 * @since 2017-05-12-15:27
 */
public class StringValueOfTest {
//    public static void main(String[] args) {
//        byte b = 0x01;
//        System.out.println(String.valueOf(b));
//    }

    public static void main(String[] args) {
        System.out.println("polygenelubricants".hashCode());
        System.out.println(Math.pow(2, 31));
//        Pattern pattern = Pattern.compile("\\[\\S+\\-\\d+\\-\\d+\\]");
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        String s = "2017-12-30 04:06:47 [nioEventLoopGroup-3-3] INFO  VTMsgHandleTask:190 setCnnAttrToObject - terminal :PP20247 is register ,will update set common info  ";
        Matcher matcher = pattern.matcher(s);
        System.out.println(matcher.find());
        System.out.println(matcher.group());
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()) {
//            System.out.printf("%o\n",sc.nextInt());
//        }
    }

    public static int getResult(int n) {
        int result = 0;
        for(int i = 0; i < 32; i += 3) {
            result += ((n >>> i) & 0x07) * (cal(i));
        }
        return result ;
    }

    private static int cal(int i) {
        int m = i / 3;
        int result = 1;
        for (int j = 0; j < m; j++) {
            result *= 10;
        }
        return result;
    }
}
