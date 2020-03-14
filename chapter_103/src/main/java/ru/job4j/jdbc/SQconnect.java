package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;


public class SQconnect {
    private static final Logger Log = LoggerFactory.getLogger(SQconnect.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/testbase";
        String userName = "userone";
        String password = "password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
            Statement st = conn.createStatement();
         //   st.executeQuery("CREATE TABLE reasons (id SERIAL PRIMARY KEY, reason VARCHAR(200));");
            st.executeQuery("INSERT INTO reasons(reason) VALUES('strange')");
            ResultSet rs = st.executeQuery("SELECT * FROM reasons");
            while (rs.next()) {
                System.out.println(rs.getString(2));
            } rs.close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.error(e.getMessage(), e);
                }
            }
        }
    }


}
