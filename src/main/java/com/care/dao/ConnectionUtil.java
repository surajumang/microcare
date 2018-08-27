package com.care.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {


    private static Connection connection;
    private static final String dburl = "jdbc:mysql://localhost:3306/sampledb";
    private static final String username = "root";
    private static final String password = "qwerty";

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dburl, username, password);
            System.err.println(connection);
        }catch (SQLException e){
            System.err.println("Can't create connection");
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.err.println("Unable to load the driver");
        }
        System.err.println("Connection created");

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
        System.err.println(connection);
        return connection;
    }
}
