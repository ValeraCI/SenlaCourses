package framework.source;

import framework.Exceptions.IncorrectPropertyClassException;
import framework.annotations.Value;
import framework.utils.AnnotatedElements;
import framework.utils.Converter;
import framework.utils.PropertyReader;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ValueHandler implements Handler{

    private final Map<Class<?>, Object> beans;

    public ValueHandler(Map beans){
        this.beans = beans;
    }

    private void valueField(Class<?> clazz){
        List<Field> fields = AnnotatedElements.getFields(clazz, Value.class);

        for (Field field: fields) {
            Value value = field.getAnnotation(Value.class);
            String v = PropertyReader.readProperty(value.name(), value.path());
            field.setAccessible(true);
            try {
                Class fieldType = field.getType();
                field.set(beans.get(clazz), Converter.toObject(fieldType, v));
            } catch (IllegalAccessException e) {
                throw new IncorrectPropertyClassException(e);
            }
        }
    }

    @Override
    public void process() {
        for (Class clazz: beans.keySet()) {
            valueField(clazz);
        }
    }
}
