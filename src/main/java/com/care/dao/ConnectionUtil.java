package com.care.dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionUtil {


    private static Connection connection;
    private static final String dburl = "jdbc:mysql://localhost:3306/sampledb";
    private static final String username = "javaadmin";
    private static final String password = "qwerty";

    private ConnectionUtil()  {
    }

    public static Connection getConnection() {

        return connection;
    }

    private static class InitialContext implements ServletContextListener {

        public void contextInitialized(ServletContextEvent servletContextEvent) {
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

        public void contextDestroyed(ServletContextEvent servletContextEvent) {
            try{
                connection.close();
            }catch (SQLException e){
                e.getCause();
            }
        }
    }
}

