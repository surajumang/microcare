package com.care.dao;

import com.care.model.Sitter;

import java.sql.SQLException;
import java.util.List;

public class HSitterDAOImpl implements SitterDAO {
    @Override
    public int addSitter(Sitter sitter) throws SQLException {
        return 0;
    }

    @Override
    public int editSitter(long sitterId, Sitter sitter) throws SQLException {
        return 0;
    }

    @Override
    public Sitter getSitter(long memberId) throws SQLException {
        return null;
    }

    @Override
    public List<Sitter> getSitterByEmail(String email) throws SQLException {
        return null;
    }
}
