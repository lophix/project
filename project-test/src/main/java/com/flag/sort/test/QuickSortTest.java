package com.flag.sort.test;

import static com.flag.sort.test.ArrayUtils.*;

/**
 * @author xuj
 * @since 2017-04-20-17:01
 */
public class QuickSortTest {
    private static double[] nums = new double[1000000];

    public static void main(String[] args) {
        ArrayUtils.randomArray(nums);
        long startTime = System.currentTimeMillis();
//        printArray(nums);
        quickSort(nums, 0, nums.length - 1);
        System.out.println("quick cost time : " + (System.currentTimeMillis() - startTime));
//        printArray(nums);
    }

    private static void quickSort(double[] array, int start, int end) {
        if (start > end - 10) {
            InsertionSort.sort(array, start, end + 1);
            return;
        }
        int left = start;
        int right = end;
        double mid = mid(array[left], array[right], array[(left + right) / 2]);
        while (left < right) {
            while (mid < array[right] && right > left) {
                right--;
            }

            while (mid > array[left] && left < right) {
                left++;
            }
            if (right == left) {
                array[left] = mid;
            } else {
                exchange(array, left, right);
            }
        }
        if (start < left - 1) {
            int finalLeft = left - 1;
            quickSort(array, start, finalLeft);
        }
        if (end > right + 1) {
            int finalRight = right + 1;
            quickSort(array, finalRight, end);
        }
    }

    private static double mid(double first, double second, double third) {
        if (first > second && first < third || (first > third && first < second)) {
            return first;
        } else if (second > first && second < third || (second > third && second < first)) {
            return second;
        } else {
            return third;
        }
    }
}
