package com.care.dao;

import com.care.model.Application;
import com.care.model.Member;
import com.care.dto.form.ApplicationDTO;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationDAO extends DAO {

    int addApplication(Application application) throws SQLException;
    boolean checkOwner(int applicationId, int memberId) throws SQLException;
    Application getApplication(int applicationId) throws SQLException;
    List<Application> getAllApplications(int memberId) throws SQLException;
    List<ApplicationDTO> getAllApplicationsOnJob(int jobId) throws SQLException;
    int deleteApplication(Member member, int applicationId) throws SQLException;
    int deleteAllApplications(int userId) throws SQLException;
}
