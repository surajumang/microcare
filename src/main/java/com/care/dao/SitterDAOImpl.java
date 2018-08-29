/*

*/

package com.care.dao;

import com.care.beans.Member;
import com.care.service.MemberService;
import com.care.service.MemberServiceImpl;
import com.care.service.ServiceFactory;

public class SitterDAOImpl implements SitterDAO {
    //composition to delegate calls when no specific implementation required
    private MemberService memberService = ServiceFactory.get(MemberServiceImpl.class);

    private static SitterDAOImpl ourInstance = new SitterDAOImpl();

    private SitterDAOImpl(){

    }

    public int applyToJob(int memberId, int jobId) {
        return 0;
    }

    public int getJobs() {
        return 0;
    }

    public int getJobs(int zipCode) {
        return 0;
    }

    public int closeApplication(int applicationId) {
        return 0;
    }

    public Member getMember(String email, String password) {
        return null;
    }

    // MemberDAO methods delegate calls to these if you do not wish to provide a specific implementation.
    public int addMember(Member member) {
        return 0;
    }

    public int editMember(int memberId, Member member) {
        return 0;
    }

    public int editMember(int memberId) {
        return 0;
    }

    public int deleteMember(int memberId) {
        return 0;
    }
}
