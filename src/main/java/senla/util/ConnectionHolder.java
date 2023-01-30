package senla.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.util.Map;
import java.util.List;

@Component
public class ConnectionHolder {
    private Map<String, Connection> connectionMap;
    private List<Connection> unusedConnections;

    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    public ConnectionHolder() {
        connectionMap = new HashMap<>();
        unusedConnections = new ArrayList<>();
    }

    public synchronized Connection getConnection(String threadName){
        if(connectionMap.containsKey(threadName)){
            return connectionMap.get(threadName);
        }
        try {
            Connection connection = null;
            if(unusedConnections.size() > 0){
                while (unusedConnections.size() > 0){
                    connection = unusedConnections.remove(0);
                    if(!connection.isClosed()){
                        break;
                    }
                    unusedConnections.remove(connection);
                    if(unusedConnections.size() == 0){
                        connection = DriverManager.getConnection(url, username, password);
                        break;
                    }
                }
            }
            else {
                connection = DriverManager.getConnection(url, username, password);
            }
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
            unusedConnections.add(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rollback(String threadName){
        try {
            Connection connection = connectionMap.remove(threadName);
            connection.rollback();
            unusedConnections.add(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        for (Connection connection: unusedConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
