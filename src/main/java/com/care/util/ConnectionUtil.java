package com.care.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection()
             {

        // Here I using Oracle Database.
        // (You can change to use another database.)
        try {
            return OracleConnUtils.getOracleConnection();
        }catch(SQLException e){

        }catch(ClassNotFoundException e){

        }

        // return OracleConnUtils.getOracleConnection();
        // return MySQLConnUtils.getMySQLConnection();
        // return SQLServerConnUtils_JTDS.getSQLServerConnection_JTDS();
        // return SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
        // return PostGresConnUtils.getPostGresConnection();
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
