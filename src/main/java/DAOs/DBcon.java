package DAOs;

import java.sql.*;
import java.util.*;

public final class DBcon {
    private DBcon() {
    }

    private static Connection con = null;

    public static void close() throws SQLException {
        if (con != null)
            con.close();
    }

    private static Connection getCon() throws ClassNotFoundException, SQLException {
        if (con == null) {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/iqds?characterEncoding=utf8";
            con = DriverManager.getConnection(url, "root", "1136");
        }
        return con;
    }

    static PreparedStatement prepareStatement(String sql) throws ClassNotFoundException, SQLException {
        Connection con = getCon();
        return con.prepareStatement(sql);
    }

    static ResultSet executeQuery(String sql) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = null;
        Connection con = getCon();
        PreparedStatement statement = con.prepareStatement(sql);
        resultSet = statement.executeQuery();
        return resultSet;
    }

    static ResultSet executeQuery(String sql, String str) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = null;
        Connection con = getCon();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, str);
        resultSet = statement.executeQuery();

        return resultSet;
    }

    static ResultSet executeQuery(String sql, String str1, String str2) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = null;
        Connection con = getCon();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, str1);
        statement.setString(2, str2);
        resultSet = statement.executeQuery();

        return resultSet;
    }

    static ResultSet executeQuery(String sql, List<String> stringList) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = null;
        Connection con = getCon();
        PreparedStatement statement = con.prepareStatement(sql);
        for (int i = 0; i < stringList.size(); ++i)
            statement.setString(i, stringList.get(i));
        resultSet = statement.executeQuery();

        return resultSet;
    }

    public static ResultSet executeQuery(String sql, int num) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = null;
        Connection con = getCon();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, num);
        resultSet = statement.executeQuery();
        return resultSet;
    }

    static int executeUpdate(String sql) throws ClassNotFoundException, SQLException {
        int result = -1;
        Connection con = getCon();
        PreparedStatement statement = con.prepareStatement(sql);
        result = statement.executeUpdate();
        return result;
    }

    static int executeUpdate(String sql, String str) throws ClassNotFoundException, SQLException {
        int result = -1;
        Connection con = getCon();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, str);
        result = statement.executeUpdate();
        return result;
    }

    static int executeUpdate(String sql, String str1, String str2) throws ClassNotFoundException, SQLException {
        int result = -1;
        Connection con = getCon();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, str1);
        statement.setString(2, str2);
        result = statement.executeUpdate();
        return result;
    }

    static int executeUpdate(String sql, List<String> stringList) throws ClassNotFoundException, SQLException {
        int result = -1;
        Connection con = getCon();
        PreparedStatement statement = con.prepareStatement(sql);
        for (int i = 0; i < stringList.size(); ++i)
            statement.setString(i, stringList.get(i));
        result = statement.executeUpdate();
        return result;
    }

    public static int executeUpdate(String sql, int num) throws ClassNotFoundException, SQLException {
        int result = -1;
        Connection con = getCon();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, num);
        result = statement.executeUpdate();
        return result;
    }

}
