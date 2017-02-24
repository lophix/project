package com.flag.project.webserver;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-23-18:23
 * @Version
 */
@Configuration
@ComponentScan("com.flag.project.webserver")
@ImportResource("spring-context.xml")
interface SpringConfig{
}
