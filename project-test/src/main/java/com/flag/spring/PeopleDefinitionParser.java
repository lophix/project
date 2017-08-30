package com.flag.spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * @author xuj
 * @since 2017-08-30-17:17
 */
public class PeopleDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return People.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String name = element.getAttribute("name");
        String age = element.getAttribute("age");
        if (StringUtils.isNotEmpty(name)) {
            builder.addPropertyValue("name", name);
        }

        if (StringUtils.isNotEmpty(age)) {
            builder.addPropertyValue("age", age);
        }
    }
}
