package lgcsgwxt;

import java.sql.*;

public class DBAccess {

    private static final String DRIVER =
            "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    private static final String URL =
            "jdbc:microsoft:sqlserver://localhost:1433;databaseName=SuperMarket;";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";
    public static Connection getConnection() { //连接数据库
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

}
