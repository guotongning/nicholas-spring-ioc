package com.ning.spring.ioc.core;

import java.util.*;

/**
 * @Author: nicholas
 * @Date: 2021/2/6 13:44
 * @Descreption:
 */
public class AnnotationConfigApplicationContext {

    private Map<String, Object> ioc = new HashMap<>();
    private BeanInjector beanInjector = new BeanInjector(ioc);

    public AnnotationConfigApplicationContext(String path) {
        //1.扫描传入的包。封装BeanDefinition
        Set<BeanDefinition> beanDefinitions = new RawMaterialScan().scanning(path);
        for (BeanDefinition beanDefinition : beanDefinitions) {
            //2.根据BeanDefinition 创建 Bean 并注入容器。
            Object object = new BeanCreator(beanDefinition).create();
            ioc.put(beanDefinition.getName(), object);
            //3.完成自动注入
            beanInjector.inject(beanDefinition.getClazz(), beanDefinition.getName());
        }
    }

    public Object getBean(String name) {
        return ioc.get(name);
    }

    public Collection<Object> getAllBean() {
        return ioc.values();
    }


}
