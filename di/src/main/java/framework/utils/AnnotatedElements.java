package framework.utils;

import framework.Exceptions.ManyConstructorsWithAnnotationAutowireException;
import framework.annotations.Autowire;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotatedElements {
    public static List<Field> getFields(Class<?> clazz, Class ann){
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ann))
                .collect(Collectors.toList());
        return fields;
    }

    public static List<Method> getMethods(Class<?> clazz, Class ann){
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(ann))
                .collect(Collectors.toList());
        return methods;
    }

    public static List<Constructor> getConstructors(Class<?> clazz, Class ann){
        List<Constructor> constructors = Arrays
                .stream(clazz.getConstructors())
                .filter(c -> c.isAnnotationPresent(Autowire.class))
                .collect(Collectors.toList());
        return constructors;
    }
}
