package com.flag.singleton.test;

/**
 * singleton generic class
 *
 * @author xuj
 * @since 2017-07-28-11:10
 */
public class SingletonGenericClass<T> {
    private static final SingletonGenericClass<Object> SINGLETON_GENERIC_CLASS = new SingletonGenericClass<>();
    private SingletonGenericClass(){
    }
    public void printT(T t) {
        System.out.println(t);
    }

    @SuppressWarnings("unchecked")
    public static <T> SingletonGenericClass<T> getSingletonGenericClass() {
        return (SingletonGenericClass<T>) SINGLETON_GENERIC_CLASS;
    }
}
