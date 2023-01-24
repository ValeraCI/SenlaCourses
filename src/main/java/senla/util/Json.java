package senla.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.Application;
import senla.Exceptions.JsonDeserializeException;
import senla.Exceptions.JsonSerialazeException;

public class Json {
    private static final ObjectMapper objectMapper = new AnnotationConfigApplicationContext(Application.class)
            .getBean(ObjectMapper.class);

    public static  <T> T deserialize(String json, Class<T> tClass){
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new JsonDeserializeException(e);
        }
    }

    public static<T> String serialize(T object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonSerialazeException(e);
        }
    }
}
