package com.care.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {


    private static Connection connection;
    private static final String dburl = "jdbc:mysql:localhost:3306";
    private static final String username = "";
    private static final String password = "";

    static {
        try{
            connection = DriverManager.getConnection(dburl, username, password);
        }catch (SQLException e){
            e.getErrorCode();
        }

    }


//    public static ConnectionUtil getInstance() {
//        return ourInstance;
//    }

    private ConnectionUtil()  {
//        if (connection != null){
//            throw new InstantiationException("Can't instantiate here");
//        }
//        try{
//            connection = DriverManager.getConnection(dburl, username, password);
//        }catch (SQLException e){
//            e.getErrorCode();
//        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
