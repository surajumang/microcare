package com.care.dao;

import com.care.beans.Member;

public interface MemberDAO extends DAO{
    int addMember(Member member);

    int editMember(int memberId);
    int deleteMember(int memberId);


}
