package com.flag.project.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Authuor xu
 * @Create 2017-02-23-17:41
 */
public class SpringConfigLoader {
    public static final ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
}
