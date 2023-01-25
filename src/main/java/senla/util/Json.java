package senla.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import senla.exceptions.JsonDeserializeException;
import senla.exceptions.JsonSerialazeException;

@AllArgsConstructor
@Component
public class Json {
    private final ObjectMapper objectMapper;

    public <T> T deserialize(String json, Class<T> tClass){
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new JsonDeserializeException(e);
        }
    }

    public <T> String serialize(T object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonSerialazeException(e);
        }
    }
}
