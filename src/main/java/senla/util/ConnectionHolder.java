package senla.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.Connection;
import java.util.Map;

@Component
public class ConnectionHolder {
    private Map<String, Connection> connectionMap;

    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    public ConnectionHolder() {
        connectionMap = new HashMap<>();
    }

    public Connection getConnection(String threadName){
        if(connectionMap.containsKey(threadName)){
            return connectionMap.get(threadName);
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            connectionMap.put(threadName, connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e); //показываем, что в пропертях неверные данные от бд
        }
    }

    public void commit(String threadName){
        try {
            Connection connection = connectionMap.remove(threadName);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rollback(String threadName){
        try {
            Connection connection = connectionMap.remove(threadName);
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
