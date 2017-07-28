package com.flag.singleton.test;

/**
 * singleton test main
 *
 * @author xuj
 * @since 2017-07-28-11:15
 */
public class SingletonTestMain {
    public static void main(String[] args) {
        SingletonGenericClass<String> genericClass = SingletonGenericClass.getSingletonGenericClass();
        genericClass.printT("hello world");

        SingletonGenericClass<TestPojo1> pojo1 = SingletonGenericClass.getSingletonGenericClass();
        TestPojo1 testPojo1 = new TestPojo1();
        testPojo1.setName("Li");
        testPojo1.setSex("male");
        testPojo1.setHobby("KongFu");
        pojo1.printT(testPojo1);

        SingletonGenericClass<TestPojo2> pojo2 = SingletonGenericClass.getSingletonGenericClass();
        TestPojo2 testPojo2 = new TestPojo2();
        testPojo2.setName("Zero");
        testPojo2.setType(1);
        testPojo2.setPrice(39.99);
        pojo2.printT(testPojo2);

    }
}
