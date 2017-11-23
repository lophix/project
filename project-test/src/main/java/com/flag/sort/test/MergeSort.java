package com.flag.sort.test;

import static com.flag.sort.test.ArrayUtils.*;

/**
 * 归并排序
 *
 * @author xuj
 * @since 2017-11-21-14:46
 */
public class MergeSort {

    private static double[] tmp;

    public static void main(String[] args) {
        double[] nums = new double[1000000];
        randomArray(nums);
//        printArray(nums);
        sort(nums);
//        printArray(nums);
    }

    static void sort(double[] array) {
        tmp = new double[array.length];
        long start = System.currentTimeMillis();
        sort(array, 0, array.length - 1);
        System.out.println("merge cost time : " + (System.currentTimeMillis() - start));
    }

    static void sort(double[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (lo + hi) / 2;
//        if (hi - lo < 10) {
//            InsertionSort.sort(array, lo, hi + 1);
//        } else {
        sort(array, lo, mid);
        sort(array, mid + 1, hi);
//        }
        merge(array, lo, mid, hi, tmp);
    }

    static void merge(double[] array, int lo, int mid, int hi, double[] tmp) {
        System.arraycopy(array, lo, tmp, lo, hi + 1 - lo);
        int idl = lo, idr = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (idl > mid) {
                array[i] = tmp[idr++];
            } else if (idr > hi) {
                array[i] = tmp[idl++];
            } else if (tmp[idl] > tmp[idr]) {
                array[i] = tmp[idr++];
            } else {
                array[i] = tmp[idl++];
            }
        }
    }
}
