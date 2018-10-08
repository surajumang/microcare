package com.care.dao;

import com.care.model.Sitter;

import java.util.Set;

public interface SitterDAO extends DAO {

    int addSitter(Sitter sitter) ;

    Sitter getSitter(long sitterId) ;

    Set<Sitter> getSitterByEmail(String email) ;

}
