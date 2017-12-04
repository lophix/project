package com.flag.sort.test;

import static com.flag.sort.test.ArrayUtils.*;

/**
 * 希尔排序
 *
 * @author xuj
 * @since 2017-11-21-11:09
 */
public class ShellSort {
    public static void main(String[] args) {
        double[] nums = new double[1024 * 1024];
        randomArray(nums);
//        printArray(nums);
        long start = System.currentTimeMillis();
        sort(nums);
        System.out.println("shell cost time : " + (System.currentTimeMillis() - start));
//        printArray(nums);
    }

    static void sort(double[] array) {
        int n = array.length;
        int h = 1;
        while (h < n / 3) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (array[j] < array[j - h]) {
                        exchange(array, j, j - h);
                    }
                }
            }
            h /= 3;
        }
    }
}
