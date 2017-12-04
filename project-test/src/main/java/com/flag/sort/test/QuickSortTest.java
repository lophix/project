package com.flag.sort.test;

import static com.flag.sort.test.ArrayUtils.*;

/**
 * @author xuj
 * @since 2017-04-20-17:01
 */
public class QuickSortTest {
    private static double[] nums = new double[1024 * 1024 * 256];

    public static void main(String[] args) {
        ArrayUtils.randomArray(nums);
//        printArray(nums);
        long startTime = System.currentTimeMillis();
        qSort(nums, 0, nums.length - 1);
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

    private static void qSort(double[] array, int start, int end) {
        if (start >= end - 10) {
            InsertionSort.sort(array, start, end + 1);
            return;
        }
        int i, p = i = start, j, q = j = end;
        //        double mid = mid(array[start], array[(end -start) / 2], array[end]);
        int vI = midIndex(array, start, (end - start) / 2, end);
        double mid = array[vI];
        array[vI] = array[start];
        while (i < j) {

            while (array[j] >= mid && i < j) {
                if (array[j] == mid) {
                    exchange(array, j, q--);
                }
                j--;
            }
            array[i] = array[j];

            while (array[i] <= mid && i < j) {
                if (array[i] == mid) {
                    exchange(array, i, p++);
                }
                i++;
            }
            array[j] = array[i];
//            exchange(array, i, j);
        }
        array[i] = mid;

        int left = start;
        int right = end;
        int midIndex = i;
        while (left < i && array[left] == mid) {
            exchange(array, left++, --i);
        }

        while (right > j && array[right] == mid) {
            exchange(array, right--, ++j);
        }
        qSort(array, start, midIndex - 1 - p);
        qSort(array, midIndex + 1 + (end - q), end);
    }

    private static int midIndex(double[] array, int first, int second, int third) {
        if (array[first] > array[second] && array[first] < array[third] || (array[first] > array[third] && array[first] < array[second])) {
            return first;
        } else if (array[second] > array[first] && array[second] < array[third] || (array[second] > array[third] && array[second] < array[first])) {
            return second;
        } else {
            return third;
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
