/*

*/
package com.care.dao;

import com.care.model.*;

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
        PreparedStatement statement = connection.prepareStatement("INSERT INTO APPLICATION (JOB_ID, SITTER_ID, EXPECTED_PAY)" +
                "VALUES(?,?,?)");
        statement.setLong(1,application.getJobId());
        statement.setLong(2, application.getSitterId());
        statement.setDouble(3,application.getExpectedPay());
        return statement.executeUpdate();
    }
    /*
    [TODO]
    Fetch everything related to application.
    Decide whether to fetch everything related or not.
     */
    public Application getApplication(long applicationId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM APPLICATION WHERE ID = ?");
        statement.setLong(1,applicationId);
        ResultSet resultSet = statement.executeQuery();
        return populateApplication(resultSet);
    }

    /*

        This method is focussed on sitter only.
        May need to join with the member and/or the job table to fetch other details.
     */
    public List<Application> getAllApplications(long sitterId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT APPLICATION.ID, TITLE, EXPECTED_PAY, HOURLY_PAY, APPLICATION.STATUS FROM APPLICATION INNER JOIN JOB ON JOB.ID = APPLICATION.JOB_ID WHERE SITTER_ID = ? AND APPLICATION.STATUS <> 'CLOSED'");
        statement.setLong(1, sitterId);

        ResultSet resultSet = statement.executeQuery();
        List<Application> applications= new ArrayList<>();
        while (resultSet.next()){
            Application application = new Application();
            Job job = new Job();
            job.setTitle(resultSet.getString("TITLE"));
            job.setHourlyPay(resultSet.getDouble("HOURLY_PAY"));
            application.setStatus(Status.valueOf(resultSet.getString("APPLICATION.STATUS")));
            application.setExpectedPay(resultSet.getDouble("EXPECTED_PAY"));
            application.setId(resultSet.getLong("APPLICATION.ID"));
            application.setJob(job);

            applications.add(application);
        }
        return applications;
    }

    /*

        Make sure that the list<Application> returned by this method contains all the required info bu the front end.
     */
    public List<Application> getAllApplicationsOnJob(long jobId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        List<Application> applications = new ArrayList<Application>();
        logger.info("Getting all application on a JOB");

        PreparedStatement statement = connection.prepareStatement("SELECT JOB.ID, JOB.TITLE, MEMBER.FIRST_NAME, MEMBER.LAST_NAME, APPLICATION.STATUS, APPLICATION.EXPECTED_PAY, JOB_ID FROM APPLICATION INNER JOIN MEMBER ON APPLICATION.SITTER_ID = MEMBER.ID INNER JOIN  JOB ON APPLICATION.JOB_ID = JOB.ID WHERE APPLICATION.JOB_ID = ? ");
        statement.setLong(1, jobId);

        logger.info("Done with query " + statement);

        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            logger.info("Getting one row from the result Set");
            Application application = new Application();
            Sitter sitter = new Sitter();
            Job job = new Job();

            job.setTitle(resultSet.getString("JOB.TITLE"));
            job.setId(resultSet.getLong("JOB.ID"));
            sitter.setFirstName(resultSet.getString("MEMBER.FIRST_NAME"));
            sitter.setLastName(resultSet.getString("MEMBER.LAST_NAME"));
            application.setExpectedPay(resultSet.getDouble("APPLICATION.EXPECTED_PAY"));
            application.setStatus(Status.valueOf(resultSet.getString("APPLICATION.STATUS")));

            application.setSitter(sitter);
            application.setJob(job);

            logger.info(application + " ");

            applications.add(application);
        }

        return applications;
    }
    /*
        Fairly simple implementation.
     */
    public int setApplicationStatus(long applicationId, Status status) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET STATUS = ? WHERE ID = ?");
        statement.setString(1, status.name());
        statement.setLong(2,applicationId);

        return statement.executeUpdate();
    }

    public int setAllApplicationsStatusBySitter(long memberId, Status status) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET STATUS = ? WHERE SITTER_ID = ?");
        statement.setString(1, status.name());
        statement.setLong(2, memberId);

        return statement.executeUpdate();
    }

    public int setAllApplicationStatusByJob(long jobId, Status status) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET STATUS = ? WHERE JOB_ID = ?");
        statement.setString(1, status.name());
        statement.setLong(2, jobId);

        return statement.executeUpdate();
    }

    public int setAllApplicationsOnJobsPostedBy(long postedBy, Status status) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE APPLICATION SET STATUS = ? WHERE JOB_ID IN(SELECT ID FROM JOB WHERE POSTED_BY =?) ");
        statement.setString(1, status.name());
        statement.setLong(2, postedBy);

        return statement.executeUpdate();
    }

    /*
    Utility method. Populates non refrence type fields of Application.
     */
    private Application populateApplication( ResultSet resultSet) throws SQLException{
        Application application = new Application();

        application.setId(resultSet.getLong("ID"));
        application.setJobId(resultSet.getLong("JOB_ID"));
        application.setSitterId(resultSet.getLong("SITTER_ID"));
        application.setExpectedPay(resultSet.getDouble("EXPECTED_PAY"));
        application.setStatus(Status.valueOf(resultSet.getString("STATUS")));
        application.setDateCreated(resultSet.getDate("DATE_CREATED"));
        application.setLastModified(resultSet.getDate("LAST_MODIFIED"));

        return application;
    }
}
