package com.flag.c3p0.test.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * use jdbc connect db
 *
 * @Authuor Administrator
 * @Create 2016-10-27-15:50
 */
public class DBConnecterTest {

    private static Connection connection;

    public static Connection getConnection(){
        if (connection == null) {
            String url = "jdbc:mysql://192.168.10.245:3306/ys_charging_db?"
                    + "user=testuser1&password=test#2015TU";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(DBConnecterTest.getConnection());
    }
}
