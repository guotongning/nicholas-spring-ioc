package com.ning.spring.ioc.core;

import com.ning.spring.ioc.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Author: nicholas
 * @Date: 2021/2/6 15:24
 * @Descreption:
 */
public class BeanInjector {

    private Map<String, Object> ioc;

    public BeanInjector(Map<String, Object> ioc) {
        this.ioc = ioc;
    }

    public void inject(Class<?> clazz, String name) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation == null) continue;
            boolean required = annotation.required();
            if (!required) continue;
            Object object = ioc.get(field.getName());
            try {
                field.set(ioc.get(name), object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
