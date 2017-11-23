package com.flag.sort.test;

import static com.flag.sort.test.ArrayUtils.*;

/**
 * 插入排序
 *
 * @author xuj
 * @since 2017-11-20-14:38
 */
public class InsertionSort {

    public static void main(String[] args) {
        double[] nums = new double[1000000];
        randomArray(nums);
//        printArray(nums);
        sort(nums, 0, nums.length);
//        printArray(nums);
    }

    static void sort(double[] array, int offset, int len) {
        long start = System.currentTimeMillis();
        for (int i = offset + 1; i < len; i++) {
            double current = array[i];
            for (int j = i; j > 0; j--) {
                if (current < array[j - 1]) {
                    array[j] = array[j - 1];
                } else {
                    array[j] = current;
                    break;
                }

                if (j - 1 == 0) {
                    array[0] = current;
                }
            }
        }
        System.out.println("insertion cost time : " + (System.currentTimeMillis() - start));
    }
}
