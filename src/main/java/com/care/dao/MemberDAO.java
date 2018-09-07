package com.care.dao;

import com.care.model.Member;
import com.care.model.Status;

import java.sql.SQLException;

public interface MemberDAO extends DAO{

    Member getMember(String email) throws SQLException;

    Member getMember(int memberId) throws SQLException;

    int addMember(Member member) throws SQLException;

    int editMember(int memberId, Member member) throws SQLException;

    int setMemberStatus(int memberId, Status status) throws SQLException;
}
