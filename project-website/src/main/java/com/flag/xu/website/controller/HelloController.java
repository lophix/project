package com.flag.xu.website.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Hello world
 *
 * @author xuj
 * @since 2017-04-28-15:14
 */
@Controller
public class HelloController {

    private static final Logger LOG = LogManager.getLogger(HelloController.class);

    @RequestMapping("/hello.html")
    public String hello(){
        LOG.info("welcome to hello method {}", System::currentTimeMillis);
        return "hello";
    }
}
