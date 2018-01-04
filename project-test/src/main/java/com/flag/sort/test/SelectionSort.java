package com.flag.sort.test;

/**
 * 选择排序
 *
 * @author xuj
 * @since 2017-11-20-11:34
 */
public class SelectionSort {

    public static void main(String[] args) {
        double[] nums = new double[1000000];
        ArrayUtils.randomArray(nums);
//        printArray(nums);
        sort(nums);
//        printArray(nums);
    }

    static void sort(double[] array) {
        int n = array.length;
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            double tmp = array[i];
            array[i] = array[min];
            array[min] = tmp;
        }
        System.out.println("selection cost time : " + (System.currentTimeMillis() - start));
    }
}
