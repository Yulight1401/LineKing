package com.callmefather.dao;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by xinliu on 12/3/16.
 */
public class DAOUtil {
    private static final String DBNAME = "bbs";
    private static final String USEDB = "use bbs";
    private static java.sql.Connection connection;
    public static final Object lock = new Object();
    private static Statement statement;

    /**
     * Init.
     * this method is called when server is being started
     */
    public static void init() {
        try {
            System.out.println("open database for the first time");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "yuliang");
            statement = connection.createStatement();
            statement.execute(USEDB);
            System.out.println("successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
//        if (connection == null) {
//            synchronized (lock) {
//                if (connection == null) {
//
//                }
//            }
//        }
    }

    public static Statement getStatement() {
        return statement;
    }

    /**
     * Close db.
     * this method is called when ths server is being stop
     */
    public static void closeDb() {
        try {
            if (!statement.isClosed()) {
                statement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
            System.out.println("close db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
