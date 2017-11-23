package com.flag.sort.test;

import static com.flag.sort.test.ArrayUtils.*;

/**
 * 自底向上归并排序
 *
 * @author xuj
 * @since 2017-11-21-17:20
 */
public class MergeBottom_UpSort {

    private static double[] TMP;

    public static void main(String[] args) {
        double[] nums = new double[1000000];
        randomArray(nums);
//        printArray(nums);
        sort(nums);
//        printArray(nums);
    }

    static void sort(double[] array) {
        int n = array.length;
        TMP = new double[n];
        long start = System.currentTimeMillis();
        for (int i = 1; i < n; i *= 2) {
            for (int j = 0; j < n - i; j += 2 * i) {
                MergeSort.merge(array, j, j + i - 1, Math.min(j + i + i - 1, n - 1), TMP);
            }
        }
        System.out.println("merge bottom-up cost time : " + (System.currentTimeMillis() - start));
    }
}
