package com.care.dao;

import com.care.model.Sitter;

import java.sql.SQLException;
import java.util.List;

public interface SitterDAO extends DAO {
    // jobId needs to be existing

    int addSitter(Sitter sitter) throws SQLException;

    int editSitter(int sitterId, Sitter sitter) throws SQLException;

    Sitter getSitter(int memberId) throws SQLException;

    List<Sitter> getSitterByEmail(String email) throws SQLException;

    /*
    Sitter close job may be added here.
     */
}
