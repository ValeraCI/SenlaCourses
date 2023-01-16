package framework.utils;

import framework.Exceptions.ComponentForImplementationNotFoundException;
import framework.Exceptions.SeveralSuitableInterfacesHaveBeenFoundException;

import java.util.*;

public class SearchingClasses {
    public static List<Object> findObjectsByInterface(Class<?> interf, Map<Class<?>, Object> beans){
        List<Object> answer = new ArrayList<>();

        for (Map.Entry<Class<?>, Object> entry : beans.entrySet()) {
            if(Arrays.asList(entry.getKey().getInterfaces()).contains(interf)){
                answer.add(entry.getValue());
            }
        }

        return answer;
    }

    public static Object findOneObjectByInterface(Class<?> interf, Map<Class<?>, Object> beans){
        List<Object> objects = SearchingClasses.findObjectsByInterface(interf, beans);
        if(objects.size() > 1) throw new SeveralSuitableInterfacesHaveBeenFoundException();
        else if(objects.size() == 0) throw new ComponentForImplementationNotFoundException();

        return objects.get(0);
    }

    public static boolean containsOneObjectByInterface(Class<?> interf, Map<Class<?>, Object> beans){
        return findObjectsByInterface(interf, beans).size() == 1;
    }

    public static List<Class<?>> findClassesByInterface(Class<?> interf, Set<Class<?>> classes){
        List<Class<?>> answer = new ArrayList<>();

        classes.stream()
                .filter(c -> Arrays.asList(c.getInterfaces()).contains(interf))
                .forEach(c -> answer.add(c));

        return answer;
    }

    public static Class<?> findOneClassByInterface(Class<?> interf, Set<Class<?>> classes){
        List<Class<?>> answer = findClassesByInterface(interf, classes);

        if(answer.size() > 1) throw new SeveralSuitableInterfacesHaveBeenFoundException();
        else if (answer.size() == 0) throw new ComponentForImplementationNotFoundException();

        return answer.get(0);
    }

    public static Object searchOneClass(Class<?> parameter, Map<Class<?>, Object> beans){
        if (beans.containsKey(parameter))
            return beans.get(parameter);
        else throw new ComponentForImplementationNotFoundException();
    }
}
