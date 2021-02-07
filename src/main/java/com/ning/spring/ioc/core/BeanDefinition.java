package com.ning.spring.ioc.core;

import com.ning.spring.ioc.annotation.Component;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: nicholas
 * @Date: 2021/2/6 13:49
 * @Descreption:
 */
@Data
public class BeanDefinition {
    private String name;
    private Class clazz;

    public BeanDefinition(Class<?> clazz) {
        Component component = clazz.getAnnotation(Component.class);
        if (component == null) return;
        String name = component.name();
        name = StringUtils.isEmpty(name) ? getBeanNameFromClazz(clazz) : name;
        this.name = name;
        this.clazz = clazz;
    }

    private static String getBeanNameFromClazz(Class<?> clazz) {
        String name = clazz.getSimpleName();
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}
