/*

*/

package com.care.dao;

import com.care.beans.Member;
import com.care.service.MemberService;
import com.care.service.MemberServiceImpl;
import com.care.service.ServiceFactory;

import java.sql.SQLException;

public class SitterDAOImpl implements SitterDAO {
    //composition to delegate calls when no specific implementation required
    private MemberService memberService = ServiceFactory.get(MemberServiceImpl.class);

    private static SitterDAOImpl ourInstance = new SitterDAOImpl();

    private SitterDAOImpl(){

    }

    public int applyToJob(int memberId, int jobId) throws SQLException {
        return 0;
    }

    public int getJobs() throws SQLException {
        return 0;
    }

    public int getJobs(int zipCode) throws SQLException {
        return 0;
    }

    public int closeApplication(int applicationId) throws SQLException {
        return 0;
    }

    public Member getMember(String email) throws SQLException {
        return null;
    }

    // MemberDAO methods delegate calls to these if you do not wish to provide a specific implementation.
    public int addMember(Member member) throws SQLException {
        return 0;
    }

    public int editMember(int memberId, Member member) throws SQLException {
        return 0;
    }

    public int editMember(int memberId) {
        return 0;
    }

    public int deleteMember(int memberId) throws SQLException {
        return 0;
    }
}
