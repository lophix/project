package com.flag.sort.test;

/**
 * @author xuj
 * @since 2017-04-20-17:01
 */
public class QuickSortTest {
    private static double[] nums = new double[1000000];

    public static void main(String[] args) {
        ArrayUtils.randomArray(nums);
        long startTime = System.currentTimeMillis();
        quickSort(nums, 0, nums.length - 1);
        System.out.println("quick cost time : " + (System.currentTimeMillis() - startTime));
//        printArray();
    }

    private static void quickSort(double[] array, int start, int end) {
        int left = start;
        int right = end;
        double temp = array[left];
        while (left < right) {
            while (temp <= array[right] && right > left) {
                right--;
            }
            array[left] = array[right];

            while (temp >= array[left] && left < right) {
                left++;
            }
            array[right] = array[left];
            if (right == left) {
                array[left] = temp;
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
}
