/*

*/
package com.care.dao;

import com.care.beans.Application;
import com.care.beans.Status;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAOImpl {


    // called to edit or post an Application
    private ApplicationDAOImpl(){

    }
    public static void postApplication(Application application, Connection connection){

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO APPLICATION" +
                    "VALUES(?,?,?,?,?,?,?)");
            statement.setInt(1,application.getId());
            statement.setInt(2,application.getJobId());

            int rowsAffected = statement.executeUpdate("insert into");
        }catch (SQLException e){
            e.getErrorCode();
        }
    }

    public static Application getApplication(int id, Connection connection){
        Application application = new Application();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION " +
                    "WHERE ID=?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();


        }catch (SQLException e){
            e.getErrorCode();
        }
        return application;
    }
    /*
    Gets all the applications corresponding to a particular JobId.
     */
    public static List<Application> getAllApplications(int JobId, Connection connection){
        List<Application> applications = new ArrayList<Application>();

        return applications;

    }
    /*
    Edits the details of the application corresponding to newApplication.getId()
    Id of the old and the new Application must remain same.
     */
    public static void editApplication(Application newApplication, Connection connection){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET" +
                    "");


            int rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.getErrorCode();
        }
    }

    public static void deleteApplication (int id, Connection connection){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION" +
                    "SET STATUS=?");
            statement.setInt(1, 2);


            int rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.getErrorCode();
        }
    }
    public static void deleteAllApplications(int memberId, Connection connection){

    }

}
