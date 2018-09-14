package com.care.dao;

import com.care.model.Member;
import com.care.model.PasswordResetToken;
import com.care.model.Status;

import java.sql.SQLException;

public class HMemberDAOImpl implements MemberDAO {
    @Override
    public Member getMember(String email) throws SQLException {
        return null;
    }

    @Override
    public Member getMember(long memberId) throws SQLException {
        return null;
    }

    @Override
    public Member getMemberUsingToken(String token) throws SQLException {
        return null;
    }

    @Override
    public int updatePassword(Member member) throws SQLException {
        return 0;
    }

    @Override
    public PasswordResetToken getToken(String email) throws SQLException {
        return null;
    }

    @Override
    public int addToken(PasswordResetToken passwordResetToken) throws SQLException {
        return 0;
    }

    @Override
    public int invalidateToken(String token) throws SQLException {
        return 0;
    }

    @Override
    public int expireStaleTokens() throws SQLException {
        return 0;
    }

    @Override
    public int addMember(Member member) throws SQLException {
        return 0;
    }

    @Override
    public int editMember(long memberId, Member member) throws SQLException {
        return 0;
    }

    @Override
    public int setMemberStatus(long memberId, Status status) throws SQLException {
        return 0;
    }
}
