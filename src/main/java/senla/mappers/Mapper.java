package senla.mappers;

import java.sql.ResultSet;
import java.util.List;

public interface Mapper {
    <T> List<T> createObjects(ResultSet resultSet);

    <T> T createObject(ResultSet resultSet);
}
