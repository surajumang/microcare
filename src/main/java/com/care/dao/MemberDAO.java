package com.care.dao;

import com.care.model.Member;
import com.care.model.Token;
import com.care.model.Status;


public interface MemberDAO extends DAO{

    Member getMember(String email) ;

    Member getMember(long memberId) ;

    Token getToken(String token);

    int updatePassword(Member member);

    int addToken(Token token);

    int invalidateToken(String token);

    int expireStaleTokens() ;
}
