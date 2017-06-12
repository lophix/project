package com.flag.method;

import java.util.*;

/**
 * @author xuj
 * @since 2017-06-12-16:50
 */
public class FindIndexOfDump {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String line = sc.nextLine();
            LinkedHashMap<Character, List<Integer>> map = new LinkedHashMap<Character, List<Integer>>();
            for(int i = 0; i < line.length(); i++) {
                List<Integer> indexes = map.get(line.charAt(i));
                if (indexes != null) {
                    indexes.add(i);
                    map.put(line.charAt(i), indexes);
                } else {
                    indexes = new ArrayList<>();
                    indexes.add(i);
                    map.put(line.charAt(i), indexes);
                }
            }

//            for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
//                List<Integer> v = entry.getValue();
//                Character key = entry.getKey();
//                if (v.size() > 1) {
//                    for (int i = 0; i < v.size(); i++) {
//                        System.out.print(key + ":" + v.get(i));
//                        if (i < v.size() - 1) {
//                            System.out.print(",");
//                        }
//                    }
//                    System.out.println();
//                }
//            }

            map.forEach((key, v) -> {
                if (v.size() > 1) {
                    for (int i = 0; i < v.size(); i++) {
                        System.out.print(key + ":" + v.get(i));
                        if (i < v.size() - 1) {
                            System.out.print(",");
                        }
                    }
                    System.out.println();
                }
            });
        }
    }
}
