package com.flag.sort.test;

/**
 * @author xuj
 * @since 2017-04-20-17:01
 */
public class QuickSortTest {
    private static int[] nums = new int[10];

    public static void main(String[] args) {
        randomArray();
        long startTime = System.nanoTime();
        quickSort(nums, 0, nums.length - 1);
        System.out.println(System.nanoTime() - startTime);
        printArray();
    }

    private static void quickSort(int[] array, int start, int end) {
        int left = start;
        int right = end;
        int temp = array[left];
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

    private static void printArray() {
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void randomArray() {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 100);
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
