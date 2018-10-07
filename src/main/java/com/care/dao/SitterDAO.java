package com.care.dao;

import com.care.model.Sitter;

import java.util.Set;

public interface SitterDAO extends DAO {
    // jobId needs to be existing

    int addSitter(Sitter sitter) ;

    int editSitter(long sitterId, Sitter sitter) ;

    Sitter getSitter(long sitterId) ;

    Set<Sitter> getSitterByEmail(String email) ;

    /*
    Sitter close job may be added here.
     */
}
