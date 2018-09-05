package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Sitter;

import java.sql.SQLException;
import java.util.List;

public interface SitterDAO extends DAO {
    // jobId needs to be existing

    int addSitter(Sitter sitter) throws SQLException;

    int applyToJob(Application application) throws SQLException;

    int closeApplication(int applicationId) throws SQLException;

}
