package com.care.dao;

import com.care.beans.Application;

public interface ApplicationDAO extends DAO {
    int addApplication(Application application);
    boolean checkOwner(int applicationId, int memberId);
    int getApplication(int applicationId);
    int getAllApplications(int memberId);
    int deleteApplication(int applicationId);
    int deleteAllApplications(int userId);
    int editApplication(int applicationId);
}
