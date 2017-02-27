package com.flag.project.fire;

import com.flag.project.core.SpringConfigLoader;
import org.springframework.context.ApplicationContext;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-24-15:08
 * @Version
 */
public class MyFireTest {
    public static void main(String[] args) {
        ApplicationContext context = SpringConfigLoader.context;
        System.out.println(context);
    }
}
