package com.care.dao;

import com.care.model.Member;
import com.care.model.Token;
import com.care.model.Status;

import java.sql.SQLException;

public interface MemberDAO extends DAO{

    Member getMember(String email) throws SQLException;

    Member getMember(long memberId) throws SQLException;

    Token getToken(String token)throws SQLException;

    int updatePassword(Member member)throws SQLException;

    int addToken(Token token) throws SQLException;

    int invalidateToken(String token) throws SQLException;

    int expireStaleTokens() throws SQLException;

    int addMember(Member member) throws SQLException;

    int editMember(long memberId, Member member) throws SQLException;

    int setMemberStatus(long memberId, Status status) throws SQLException;
}
