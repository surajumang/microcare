package com.care.dao;

import com.care.model.Member;
import com.care.model.Token;
import com.care.model.Status;


public interface MemberDAO extends DAO{

    Member getMember(String email) throws Exception;

    Member getMember(long memberId) throws Exception;

    Token getToken(String token)throws Exception;

    int updatePassword(Member member)throws Exception;

    int addToken(Token token) throws Exception;

    int invalidateToken(String token) throws Exception;

    int expireStaleTokens() throws Exception;

    int addMember(Member member) throws Exception;

    int editMember(long memberId, Member member) throws Exception;

    int setMemberStatus(long memberId, Status status) throws Exception;
}
