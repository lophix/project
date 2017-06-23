package com.flag.method;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * N阶楼梯上楼问题：一次可以走两阶或一阶，问有多少种上楼方式。（要求采用非递归）
 * <p>
 * 根本不是正解,如果不是从1顺序输入结果就是错误的
 *
 * @author xuj
 * @since 2017-06-19-15:26
 */
public class StepOnStep {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n > 3) {
                n = list.get(list.size() - 1) + list.get(list.size() - 2);
            }
            list.add(n);
            System.out.println(n);
        }
        sc.close();
    }

    @Test
    public void test() {
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        try {
            Date start = dateFormat.parse("113630");
            Date current = new Date();
            Date end = dateFormat.parse("125530");
            System.out.println(isInRang(current, start, end));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean isInRang(Date currDate, Date rangStart, Date rangeEnd) {
        Calendar currCal = convertDateToCalendar(currDate);
        Calendar startCal = convertDateToCalendar(rangStart);
        Calendar endRange = convertDateToCalendar(rangeEnd);

        int currMint = currCal.get(Calendar.MINUTE);

        int currHours = currCal.get(Calendar.HOUR_OF_DAY);

        boolean startR = currHours > startCal.get(Calendar.HOUR_OF_DAY) || currHours == startCal.get(Calendar.HOUR_OF_DAY) && currMint >= startCal.get(Calendar.MINUTE);
        boolean endR = currHours < endRange.get(Calendar.HOUR_OF_DAY) || currHours == endRange.get(Calendar.HOUR_OF_DAY) && currMint <= endRange.get(Calendar.MINUTE);
        return startR && endR;
    }

    private Calendar convertDateToCalendar(Date data) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(data);
        return curr;
    }
}
