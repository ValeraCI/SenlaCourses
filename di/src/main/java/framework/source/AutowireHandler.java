package framework.source;

import framework.Exceptions.FrameworkException;
import framework.Exceptions.PrivateMethodException;
import framework.annotations.Autowired;
import framework.utils.AnnotatedElements;
import framework.utils.SearchingClasses;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutowireHandler implements Handler{
    private final Map<Class<?>, Object> beans;

    public AutowireHandler(Map beans){
        this.beans = beans;
    }

    private void autowireSet(Class<?> clazz){
        List<Method> methods = AnnotatedElements.getMethods(clazz, Autowired.class);

        for (Method method: methods) {
            int modifers = method.getModifiers();
            if (Modifier.isPrivate(modifers)) {
                throw new PrivateMethodException(String.format("The %s method in the %s class is private",
                        method.getName(), clazz.getName()));
            }
            Class<?>[] parametersType = method.getParameterTypes();

            List<Object> parameters = new ArrayList<>();

            for(Class parameter: parametersType){
                if(!parameter.isInterface()){
                    parameters.add(SearchingClasses.searchOneClass(parameter, beans));
                }
                else {
                    Object object = SearchingClasses.findOneObjectByInterface(parameter, beans);
                    parameters.add(object);
                }
            }

            try {
                method.invoke(beans.get(clazz), parameters.toArray());
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new FrameworkException("An unexpected error has occurred", e);
            }
        }
    }

    private void autowireField(Class<?> clazz) {
        List<Field> fields = AnnotatedElements.getFields(clazz, Autowired.class);

        for (Field field: fields) {
            field.setAccessible(true);
            try {
                if(!field.getType().isInterface()){
                    field.set(beans.get(clazz), beans.get(field.getType()));
                }
                else{
                    Object object = SearchingClasses.findOneObjectByInterface(clazz, beans);
                    field.set(beans.get(clazz), object);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void process(){
        for (Class clazz: beans.keySet()) {
            autowireSet(clazz);
            autowireField(clazz);
        }
    }
}
