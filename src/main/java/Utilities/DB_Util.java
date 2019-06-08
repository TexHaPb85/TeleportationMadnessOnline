package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "postgres";
    private static final String PASS = "Neskazu0";

    public static Connection connection;

    public static void connectToDB(){
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            if (!connection.isClosed()) {System.out.println("Соединение с БД установлено");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnectFromDB() {
        try {
            connection.close();
            if (connection.isClosed()) { System.out.println("Соединение с БД прервано"); }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
