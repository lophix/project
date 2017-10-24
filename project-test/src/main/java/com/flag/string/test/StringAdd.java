package com.flag.string.test;

/**
 * @author xuj
 * @since 2017-10-20-11:15
 */
public class StringAdd {

    private static final String PREFIX_STR = "prefix_";
    private static final String Hello = "hello";

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    private static void test1() {
        String s1 = PREFIX_STR + "hello";
        Entity entity = new Entity();
        entity.setStr("hello");
        String str = "hello";
        String s2 = PREFIX_STR + str;
        System.out.println(s1 == s2);
    }

    private static void test2() {
//        String str = "hello";
        Entity entity = new Entity();
        entity.setStr(Hello);
        final String str = entity.getStr();
        String s = PREFIX_STR + Hello;
        String s1 = PREFIX_STR + str;
        String s2 = PREFIX_STR + str;
        String s3 = PREFIX_STR + "hello";
        System.out.println(s1 == s2);
        System.out.println(s == s1);
        System.out.println(s == s2);
        System.out.println(s == s3);
        System.out.println();
    }

    private static void test3() {
        String prefix = "p_";
        for (int i = 0; i < 300000; i++) {
            String tmpi = String.valueOf(i);
            String tmp = "haha" + i;
            String tmp2 = prefix + tmp;
        }
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }

    private static class Entity {
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }
}
