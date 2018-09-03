package com.care.dao;

import com.care.model.Member;

import java.sql.SQLException;

public interface MemberDAO extends DAO{
    Member getMember(String email) throws SQLException;
    int addMember(Member member) throws SQLException;

    int editMember(int memberId, Member member) throws SQLException;
    int deleteMember(int memberId) throws SQLException;


}
