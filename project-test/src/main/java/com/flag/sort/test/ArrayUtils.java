package com.flag.sort.test;

/**
 * @author xuj
 * @since 2017-11-21-11:15
 */
public class ArrayUtils {
    static void printArray(double[] nums) {
        for (double i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static void randomArray(double[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.random();
//            System.out.print(nums[i] + " ");
        }
//        System.out.println();
    }


    static void exchange(double[] array, int a, int b) {
        double tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
