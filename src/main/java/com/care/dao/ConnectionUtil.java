package com.care.dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUtil implements ServletContextListener {
    private static Logger logger = Logger.getLogger("ConncetionUtil");

    private static Connection connection;
    private static final String dburl = "jdbc:mysql://localhost:3306/sampledb";
    private static final String username = "javaadmin";
    private static final String password = "qwerty";

    public ConnectionUtil()  {
    }

    public static Connection getConnection() {
        return connection;
    }
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("\n\nServlet Context initialized \n\n");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dburl, username, password);
            System.err.println(connection);
        }catch (SQLException e){
            System.err.println("***********Can't create connection");
            logger.log(Level.SEVERE, "Sql", e.getCause());
        }catch (ClassNotFoundException e){
            logger.severe("**************Unable to load the driver");
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try{
            logger.info("***********Connection Destroyed");
            connection.close();
        }catch (SQLException e){
            logger.info("***********Connection Destroyed");
        }
    }
}

