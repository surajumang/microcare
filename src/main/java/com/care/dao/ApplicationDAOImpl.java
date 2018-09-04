/*

*/
package com.care.dao;

import com.care.model.Application;
import com.care.model.Status;
import com.care.dto.form.ApplicationDTO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ApplicationDAOImpl implements ApplicationDAO {

    private Logger logger = Logger.getLogger("ApplicationDAOImpl");
    public ApplicationDAOImpl(){ }

    public int addApplication(Application application) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO APPLICATION (JOB_ID, MEMBER_ID, EXPECTED_PAY)" +
                "VALUES(?,?,?)");
        statement.setInt(1,application.getJobId());
        statement.setInt(2, application.getSitterId());
        statement.setDouble(3,application.getExpectedPay());
        return statement.executeUpdate();
    }
    /*
    [TODO]
    Fetch everything related to application.
    Decide whether to fetch everything related or not.
     */
    public Application getApplication(int applicationId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION WHERE ID = ?");
        statement.setInt(1,applicationId);
        ResultSet resultSet = statement.executeQuery();
        return populateApplication(resultSet);
    }

    /*
        [TODO]
        This method is focussed on sitter only.
        May need to join with the member and/or the job table to fetch other details.
     */
    public List<Application> getAllApplications(int sitterId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION WHERE MEMBER_ID = ?");
        statement.setInt(1, sitterId);
        ResultSet resultSet = statement.executeQuery();

        List<Application> applications= new ArrayList<>();
        while (resultSet.next()){
            applications.add(populateApplication(resultSet));
        }
        return null;
    }

    /*
        [TODO]
        Make sure that the list<Application> returned by this method contains all the required info bu the front end.
     */
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
    /*
        Fairly simple implementation.
     */
    public int deleteApplication(int applicationId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET STATUS = ? WHERE ID = ?");
        statement.setString(1, Status.INACTIVE.name());
        statement.setInt(2,applicationId);

        return statement.executeUpdate();
    }

    public int deleteAllApplications(int memberId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET STATUS = ? WHERE MEMBER_ID = ?");
        statement.setString(1, Status.INACTIVE.name());
        statement.setInt(2, memberId);

        return statement.executeUpdate();
    }
    /*
    Utility method. Populates non refrence type fields of Application.
     */
    private Application populateApplication( ResultSet resultSet) throws SQLException{
        Application application = new Application();

        application.setId(resultSet.getInt("ID"));
        application.setJobId(resultSet.getInt("JOB_ID"));
        application.setSitterId(resultSet.getInt("MEMBER_ID"));
        application.setExpectedPay(resultSet.getDouble("EXPECTED_PAY"));
        application.setStatus(Status.valueOf(resultSet.getString("STATUS")));
        application.setDateCreated(resultSet.getDate("DATE_CREATED"));
        application.setLastModified(resultSet.getDate("LAST_MODIFIED"));

        return application;
    }
}
