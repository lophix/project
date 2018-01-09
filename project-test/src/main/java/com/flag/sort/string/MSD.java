package com.flag.sort.string;

/**
 * @author xuj
 * @since 2018-01-08-10:24
 */
public class MSD {
    private static int R = 256;
    private static final int M = 15;
    private static String[] aux;


    private static int charAt(String s, int d) {
        return d < s.length() ? s.charAt(d) : -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {  //计算频率
            count[charAt(a[i], d)]++;
        }

        for (int r = 0; r < R + 1; r++) {  //频率转换索引
            count[r + 1] += count[r];
        }

        for (int i = lo; i <= hi; i++) {   //数据分类
            aux[count[charAt(a[i], d)]++] = a[i];
        }

        System.arraycopy(aux, 0, a, lo, hi + 1 - lo);

        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    private static class Insertion {

        public static void sort(String[] a, int lo, int hi, int d) {
            for (int i = lo; i <= hi; i++) {
                for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                    exch(a, j, j - 1);
                }
            }
        }

        private static void exch(String[] a, int j, int i) {
            String tmp = a[j];
            a[j] = a[i];
            a[i] = tmp;
        }

        private static boolean less(String s, String s1, int d) {
            return s.substring(d).compareTo(s1.substring(d)) < 0;
        }
    }
}
