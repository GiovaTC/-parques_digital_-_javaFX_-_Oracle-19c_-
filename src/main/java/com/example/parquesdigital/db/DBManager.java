package com.example.parquesdigital.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBManager {
    private static final String URL  = "jdbc:oracle:thin//@localhost:1521:orcl";
    private static final String USER = "system";
    private static final String PASS = "Tapiero123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
