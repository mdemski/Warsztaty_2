package zadanie_1.db_connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionGen {
    private static final String DB_NAME = "program_school";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String DB_USER_NAME = "root";
    private static final String DB_USER_PASSWORD = "coderslab";

    public static Connection getConnection() throws SQLException {
        {
            return DriverManager.getConnection(CONNECTION_STRING, DB_USER_NAME, DB_USER_PASSWORD);
        }
    }


}
