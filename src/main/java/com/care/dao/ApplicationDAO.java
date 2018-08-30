package com.care.dao;

import com.care.beans.Application;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationDAO extends DAO {

    int addApplication(Application application) throws SQLException;
    boolean checkOwner(int applicationId, int memberId) throws SQLException;
    Application getApplication(int applicationId) throws SQLException;
    List<Application> getAllApplications(int memberId) throws SQLException;
    int deleteApplication(int applicationId) throws SQLException;
    int deleteAllApplications(int userId) throws SQLException;
}
