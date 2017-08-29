package com.flag.xu.website.controller;

import com.flag.xu.website.pojo.UserInfo;
import com.flag.xu.website.repository.TestMongoDbRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Hello world
 *
 * @author xuj
 * @since 2017-04-28-15:14
 */
@Controller
public class HelloController {

    private static final Logger LOG = LogManager.getLogger(HelloController.class);

    private TestMongoDbRepository testMongoDbRepository;

    @Autowired
    public HelloController(TestMongoDbRepository testMongoDbRepository) {
        this.testMongoDbRepository = testMongoDbRepository;
    }

    @RequestMapping("/hello.html")
    public String hello(UserInfo userInfo, String name){
        LOG.info("welcome to hello method {}", System::currentTimeMillis);
        LOG.info("name by user info is {}, name is {}", userInfo.getName(), name);
        testMongoDbRepository.insert();
        return "hello";
    }

    @ResponseBody
    @RequestMapping("/bye.do")
    public UserInfo bye() {
        UserInfo info = new UserInfo();
        info.setName("wang");
        info.setSex("gong");
        info.setAge(10);
        return info;
    }
}
