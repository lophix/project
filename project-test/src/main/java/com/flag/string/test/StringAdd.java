package com.flag.string.test;

/**
 * @author xuj
 * @since 2017-10-20-11:15
 */
public class StringAdd {

    private static final String PREFIX_STR = "prefix_";

    public static void main(String[] args) {
//        test1();
        test2();
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
        String str = "hello";
        Entity entity = new Entity();
        entity.setStr("world");
        String s1 = PREFIX_STR + entity.getStr();
        String s2 = PREFIX_STR + entity.getStr();
        System.out.println(s1 == s2);
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
