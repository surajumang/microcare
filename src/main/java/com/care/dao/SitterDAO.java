package com.care.dao;

import com.care.model.Sitter;

import java.util.Set;

public interface SitterDAO extends DAO {
    // jobId needs to be existing

    int addSitter(Sitter sitter) throws Exception;

    int editSitter(long sitterId, Sitter sitter) throws Exception;

    Sitter getSitter(long sitterId) throws Exception;

    Set<Sitter> getSitterByEmail(String email) throws Exception;

    /*
    Sitter close job may be added here.
     */
}
