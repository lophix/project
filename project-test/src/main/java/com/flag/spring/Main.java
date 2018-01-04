package com.flag.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xuj
 * @since 2017-08-30-17:28
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("people.xml");
        People people = (People) context.getBean("pi");
        System.out.println(people);
    }
}
