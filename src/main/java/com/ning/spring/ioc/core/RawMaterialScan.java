package com.ning.spring.ioc.core;

import com.ning.spring.ioc.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: nicholas
 * @Date: 2021/2/6 13:56
 * @Descreption:
 */
public class RawMaterialScan {


    public Set<BeanDefinition> scanning(String basePackage) {
        Set<Class<?>> classes = FileUtil.getClasses(basePackage);
        return buildBeanDefinitions(classes);
    }

    /**
     * 根据反射构建BeanDefinition
     *
     * @param classes
     * @return
     */
    private Set<BeanDefinition> buildBeanDefinitions(Set<Class<?>> classes) {
        Set<BeanDefinition> result = new HashSet<>();
        for (Class<?> clazz : classes) {
            BeanDefinition definition = new BeanDefinition(clazz);
            if (StringUtils.isEmpty(definition.getName())) continue;
            result.add(definition);
        }
        return result;
    }
}
