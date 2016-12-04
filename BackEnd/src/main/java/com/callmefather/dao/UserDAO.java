package com.callmefather.dao;

import com.callmefather.enity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by xinliu on 12/2/16.
 */
public class UserDAO {
    private static final String TBNAME = "user";
    public static final String NAME = "_name";
    public static final String PASSWORD = "_password";

    public static boolean newUser(User user) {
        Statement statement = DAOUtil.getStatement();
        boolean success = false;
        boolean exist = isUserExist(user, statement);
        if (exist) {
            return success;
        }
        synchronized (DAOUtil.lock) {
            try {
                statement.execute("INSERT INTO " + TBNAME + " (" + NAME + "," + PASSWORD + ") " +
                        "VALUES ('" + user.getName() + "','" + user.getPassword() + "')");
                success = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println(success ? "new User" : "new User failed");
                return success;
            }
        }
    }

    public static boolean isUserExist(User user, Statement statement) {
        if (statement == null) {
            statement = DAOUtil.getStatement();
        }
        boolean isExist = false;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TBNAME +
                    " WHERE " + NAME + "='" + user.getName() + "'");
            isExist = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return isExist;
        }
    }

    public static boolean isLoginSuccess(User user) {
        Statement statement = DAOUtil.getStatement();
        boolean success = false;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TBNAME +
                    " WHERE " + NAME + "='" + user.getName()
                    + "' AND " + PASSWORD + "='" + user.getPassword() + "'");
            success = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(success ? "login" : "login failed");
            return success;
        }

    }

}
