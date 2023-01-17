package framework.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotatedElements {
    public static List<Field> getFields(Class<?> clazz, Class ann){
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(ann))
                .collect(Collectors.toList());
    }

    public static List<Method> getMethods(Class<?> clazz, Class ann){
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(ann))
                .collect(Collectors.toList());
    }

    public static List<Constructor> getConstructors(Class<?> clazz, Class ann){
        return Arrays
                .stream(clazz.getConstructors())
                .filter(c -> c.isAnnotationPresent(ann))
                .collect(Collectors.toList());
    }
}
