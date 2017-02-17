package com.flag.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-17-17:13
 * @Version
 */
public class ReflectTestMain {

    public static void main(String[] args) throws Exception {
        Class clz = Class.forName("com.flag.test.reflect.MyPrivateObject");
        MyPrivateObject obj = (MyPrivateObject) reflectMethod(clz);
        System.out.println("print from main method : " + obj.getName() + "\t" + obj.getPassword());
    }

    private static <T> T reflectMethod(Class<T> clz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Field[] fields = clz.getDeclaredFields();
        T obj = clz.newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(obj, "Get" + field.getName());
        }

        Method[] methods = clz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.getGenericParameterTypes().length == 0) {
                method.invoke(obj);
            }
        }
        return obj;
    }
}
