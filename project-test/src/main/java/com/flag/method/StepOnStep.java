package com.flag.method;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * N阶楼梯上楼问题：一次可以走两阶或一阶，问有多少种上楼方式。（要求采用非递归）
 *
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
}
