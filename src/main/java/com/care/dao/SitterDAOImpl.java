/*

*/

package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class SitterDAOImpl implements SitterDAO {
    private Logger logger = Logger.getLogger("SitterDaoImpl");

    public SitterDAOImpl(){ }

    public int applyToJob(Application application) throws SQLException {
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        return applicationDAO.addApplication(application);
    }

    public List<Job> getJobs() throws SQLException {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        return jobDAO.getAllJobs();
    }

    public int closeApplication(int applicationId) throws SQLException {
        return 0;
    }

    public Member getMember(String email) throws SQLException {
        return null;
    }
}
