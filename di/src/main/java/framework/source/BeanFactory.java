package framework.source;

import framework.exceptions.ComponentForImplementationNotFoundException;
import framework.exceptions.ManyConstructorsWithAnnotationAutowireException;
import framework.annotations.Autowired;
import framework.annotations.Component;
import framework.utils.AnnotatedElements;
import framework.utils.SearchingClasses;
import org.reflections.Reflections;
import java.lang.reflect.Constructor;
import java.util.*;

public class BeanFactory {

    private final Map<Class<?>, Object> beans;
    private final Set<Class<?>> components;

    public BeanFactory(String path) {
        Reflections reflections = new Reflections(path);
        components = reflections.getTypesAnnotatedWith(Component.class);
        beans = new HashMap<>();
    }


    private <T> void createBeanWithoutConstructor(Class<T> clazz) {
        T been = null;
        try {
            been = clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        beans.put(clazz, been);
    }

    private void addObject(Class<?> parameter, List<Object> objects){
        if (beans.containsKey(parameter)) {
            objects.add(beans.get(parameter));
        } else if (components.contains(parameter)) {
            createBean(parameter);
            objects.add(beans.get(parameter));
        } else {
            throw new ComponentForImplementationNotFoundException();
        }
    }

    private void addInterface(Class<?> interfaceParameter, List<Object> objects) {
        if(SearchingClasses.containsOneObjectByInterface(interfaceParameter, beans)){
            Object objectWithInterface = SearchingClasses.findOneObjectByInterface(interfaceParameter, beans);
            objects.add(interfaceParameter.cast(objectWithInterface));
        }
        else {
            Class<?> clazzWithInterface = SearchingClasses.findOneClassByInterface(interfaceParameter, components);
            createBean(clazzWithInterface);
            objects.add(interfaceParameter.cast(beans.get(clazzWithInterface)));
        }

    }


    private <T> void createBeanWithConstructor(Class<T> clazz) {
        List<Constructor> constructors = AnnotatedElements.getConstructors(clazz, Autowired.class);
        Constructor constructor = constructors.get(0);

        Class<?>[] parameters = constructor.getParameterTypes();

        List<Object> objects = new ArrayList<>();

        for(Class parameter: parameters){
            if(!parameter.isInterface())
              addObject(parameter, objects);
            else addInterface(parameter, objects);
        }
        T been = null;
        try {
            been = (T) constructor.newInstance(objects.toArray());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        beans.put(clazz, been);
    }


    private <T> void createBean(Class<T> clazz) {
        long autowireConstructorsNum = AnnotatedElements.getConstructors(clazz, Autowired.class).size();

        if (autowireConstructorsNum > 1) throw new ManyConstructorsWithAnnotationAutowireException();

        if(!beans.containsKey(clazz)) {
            if (autowireConstructorsNum == 1) createBeanWithConstructor(clazz);
            else createBeanWithoutConstructor(clazz);
        }
    }

    public Map<Class<?>, Object> createBeans(){
        for (Class clazz: components) {
            createBean(clazz);
        }

        new AutowireHandler(beans).process();
        new ValueHandler(beans).process();

        return beans;
    }
}
