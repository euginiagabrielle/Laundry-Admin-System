package helper;
import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class connection {
    private static Connection conn;
    private static Connection Connect() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String host = dotenv.get("DB_HOST");
        String port = dotenv.get("DB_PORT");
        String user = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");
        String dbName = dotenv.get("DB_NAME");
        Connection con = DriverManager.getConnection(
                String.format("jdbc:mysql://%s:%s/%s", host, port, dbName),
                user,
                password
        );
        return con;
    }

    public static Connection GetConnection() throws SQLException {
        if (conn != null) {
            return conn;
        } else {
            conn = Connect();
            return conn;
        }
    }
}
