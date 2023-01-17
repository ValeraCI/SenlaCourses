package framework.source;

import framework.exceptions.ContextAlreadyExistsException;
import framework.exceptions.ContextNotCreatedException;

import java.util.Map;

public class ApplicationContext {
    private static ApplicationContext applicationContext;
    private final Map<Class<?>, Object> beans;

    public static ApplicationContext createContext(String packageName){
        if(applicationContext != null) throw new ContextAlreadyExistsException();
        applicationContext = new ApplicationContext(new BeanFactory(packageName).createBeans());
        return applicationContext;
    }

    public static ApplicationContext getInstance(){
        if(applicationContext == null) throw new ContextNotCreatedException();
        return applicationContext;
    }

    private ApplicationContext(Map<Class<?>, Object> beans){
        this.beans = beans;
    }

    public <T> T getBean(Class<T> clazz){
        return (T) beans.get(clazz);
    }
}
