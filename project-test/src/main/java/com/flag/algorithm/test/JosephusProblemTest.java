package com.flag.algorithm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuj
 * @since 2017-04-21-10:01
 */
public class JosephusProblemTest {

    private static List<Boolean> dancers;

    public static void main(String[] args) {
        initDancers(100);
        System.out.println("The last survivor is " + expulsion());
    }

    private static void initDancers(int total){
        if (dancers == null) {
            dancers = new ArrayList<>(total);
        }
        for (int i = 0; i < total; i++) {
            dancers.add(false);
        }
    }

    private static int expulsion(){
        int factor = 0;
        int survivor = dancers.size();
        while (true) {
            for (int i = 0; i < dancers.size(); i++) {
                if (dancers.get(i)) {
                    continue;
                }
                factor++;
                if (factor == 3) {
                    dancers.set(i, true);
                    survivor--;
                    factor = 0;
                }
                if (survivor == 0) {
                    return i + 1;
                }
            }
        }
    }
}
