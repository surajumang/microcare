/*

*/
package com.care.dao;

import com.care.beans.Application;
import com.care.beans.Member;
import com.care.beans.Status;
import com.care.dto.form.ApplicationDTO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationDAOImpl implements ApplicationDAO {

    private Logger logger = Logger.getLogger("ApplicationDAOImpl");
    public ApplicationDAOImpl(){ }


    /*
    Edits the details of the application corresponding to newApplication.getId()
    Id of the old and the new Application must remain same.
     */

    public int addApplication(Application application) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO APPLICATION" +
                "VALUES(?,?,?,?,?,?,?)");
        statement.setInt(1,application.getJobId());

        int rowsAffected = statement.executeUpdate("insert into");
        return 0;
    }

    public boolean checkOwner(int applicationId, int memberId) throws SQLException {

        return false;
    }

    public Application getApplication(int applicationId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION WHERE ID = ?");
        statement.setInt(1,applicationId);

        ResultSet resultSet = statement.executeQuery();

        return null;
    }

    public List<Application> getAllApplications(int appliedBy) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION WHERE MEMBER_ID = ?");
        statement.setInt(1, appliedBy);

        ResultSet resultSet = statement.executeQuery();
        return null;
    }

    public List<ApplicationDTO> getAllApplicationsOnJob(int jobId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        List<ApplicationDTO> applicationDTOsList = new ArrayList<ApplicationDTO>();
        logger.info("Getting all application on a JOB");

        PreparedStatement statement = connection.prepareStatement("SELECT JOB.TITLE, MEMBER.FIRST_NAME," +
                " MEMBER.LAST_NAME, APPLICATION.STATUS, APPLICATION.EXPECTED_PAY FROM APPLICATION" +
                "INNER JOIN MEMBER ON APPLICATION.MEMBER_ID = MEMBER.ID" +
                "INNER JOIN  JOB ON APPLICATION.JOB_ID = JOB.ID" +
                "WHERE APPLICATION.JOB_ID = ? AND APPLICATION.STATUS = ?;");
        statement.setInt(1, jobId);
        statement.setString(2,Status.ACTIVE.name());

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            logger.info("Getting one row from the result Set");
            ApplicationDTO applicationDTO = new ApplicationDTO();
            applicationDTO.setTitle(resultSet.getString("TITLE"));
            applicationDTO.setFirstName(resultSet.getString("FIRST_NAME"));
            applicationDTO.setLastName(resultSet.getString("LAST_NAME"));
            applicationDTO.setExpectedPay(resultSet.getString("EXPECTED_PAY"));
            applicationDTO.setStatus(resultSet.getString("STATUS"));

            applicationDTOsList.add(applicationDTO);
        }

        return applicationDTOsList;
    }

    public int deleteApplication(Member member, int applicationId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET STATUS = ? WHERE ID = ?" +
                "AND MEMBER_ID = ?");
        statement.setString(1, Status.INACTIVE.name());
        statement.setInt(2,applicationId);
        statement.setInt(3, member.getId());

        return statement.executeUpdate();
    }

    public int deleteAllApplications(int memberId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET STATUS = ? WHERE MEMBER_ID = ?");
        statement.setString(1, Status.INACTIVE.name());
        statement.setInt(2, memberId);

        return statement.executeUpdate();
    }
}
