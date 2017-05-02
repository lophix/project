package com.flag.xu.website.conf;

import com.flag.xu.website.mongodb.MongoConnectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * web app configuration class
 *
 * @author xuj
 * @since 2017-04-28-13:56
 */
@Configuration
@ComponentScan(value = "com.flag.xu.website", excludeFilters = {@ComponentScan.Filter({Controller.class})})
@PropertySource("classpath:profile.properties")
public class AppConfig {

    private Environment env;

    @Autowired
    public AppConfig(Environment environment) {
        this.env = environment;
    }

    @Bean
    public MongoConnectPool createMongoConnectPool() {
        return MongoConnectPool.build(env.getProperty("mongodb-hosts"));
    }
}
