package com.ning.spring.ioc.core;

import com.ning.spring.ioc.annotation.Value;
import com.ning.spring.ioc.excption.IoCException;

import java.lang.reflect.Field;

/**
 * @Author: nicholas
 * @Date: 2021/2/6 15:12
 * @Descreption:
 */
public class BeanCreator {

    private BeanDefinition definition;

    public BeanCreator(BeanDefinition definition) {
        this.definition = definition;
    }

    /**
     * 根据BeanDefinition创建Bean
     *
     * @return
     */
    public Object create() {
        Class clazz = definition.getClazz();
        try {
            Object instance = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Value annotation = field.getAnnotation(Value.class);
                if (annotation == null) continue;
                String value = annotation.value();
                Class<?> type = field.getType();
                switch (type.getSimpleName()) {
                    case "String":
                        field.set(instance, value);
                        break;
                    case "Integer":
                    case "int":
                        field.set(instance, Integer.parseInt(value));
                        break;
                    case "Double":
                    case "double":
                        field.set(instance, Double.parseDouble(value));
                        break;
                    case "Float":
                    case "float":
                        field.set(instance, Float.parseFloat(value));
                        break;
                    case "Long":
                    case "long":
                        field.set(instance, Long.parseLong(value));
                        break;
                    default:
                        throw new IoCException(String.format("@Value 不支持的属性类型。属性类型 = %s,属性所属类 = %s", type.getName(), clazz.getName()));
                }
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
