package com.care.dao;

import com.care.beans.Member;

public interface MemberDAO extends DAO{
    Member getMember(String email);
    int addMember(Member member);

    int editMember(int memberId, Member member);
    int deleteMember(int memberId);


}
