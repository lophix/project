package com.flag.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author xuj
 * @since 2017-08-30-17:16
 */
public class PeopleNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("people", new PeopleDefinitionParser());
    }
}
