package com.flag.xu.website.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * this configuration for spring sevlet dispatcher
 *
 * @author xuj
 * @since 2017-04-28-13:58
 */
@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan(value = "com.flag.xu.website.controller", includeFilters = {@ComponentScan.Filter({Controller.class})})
public class WebmvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public SimpleServletHandlerAdapter createSimpleServletHandlerAdapter(){
        return new SimpleServletHandlerAdapter();
    }

    @Bean
    public CommonsMultipartResolver createCommonsMultipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }

    @Bean("defaultViewResolver")
    public InternalResourceViewResolver createInternalResourceViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setContentType("text/html");
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        return  viewResolver;
    }
}
