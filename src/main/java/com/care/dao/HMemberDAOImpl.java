package com.care.dao;

import com.care.filter.HibernateFilter;
import com.care.model.Member;
import com.care.model.Token;
import com.care.model.Status;

import java.util.List;

import javax.faces.bean.SessionScoped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HMemberDAOImpl implements MemberDAO {

    @Override
    public Member getMember(String email) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String query = "from Member where email= :email";

        Query query1 = session.createQuery(query);
        query1.setString("email", email);

        Member member = (Member) query1.uniqueResult();

        return member;
    }

    @Override
    public Member getMember(long memberId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Member member = (Member) session.get(Member.class, memberId);
        if (member == null){
            member = Member.emptyMember();
        }

        return member;
    }

    @Override
    public Token getToken(String token) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Token where TOKEN= :token");
        query.setString("token", token);

        Token token1 = (Token) query.uniqueResult();
        if (token1 == null){
            token1 = Token.emptyToken();
        }

        return token1;
    }

    /*
    It is provided with a member object completely filled with ID and all fields which were required to be updated.
     */
    @Override
    public int updatePassword(Member member) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.update(member);

        return 1;
    }


    @Override
    public int addToken(Token token) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.save(token);
        return 1;
    }
    // set status to Inactive
    @Override
    public int invalidateToken(String token) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        int status = -1;
        Query query = session.createQuery("from Token where token= :token");
        query.setString("token", token);
        Token token1 = (Token)query.uniqueResult();
        if (token != null){
            status = 1;
            token1.setStatus(Status.EXPIRED);
        }
        return status;
    }

    @Override
    public int expireStaleTokens() throws Exception {
        return 0;
    }

    // Two of the methods below are not being used.
    @Override
    public int addMember(Member member) throws Exception {
        return 0;
    }

    @Override
    public int editMember(long memberId, Member member) throws Exception {
        return 0;
    }

    @Override
    public int setMemberStatus(long memberId, Status status) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Member member = (Member) session.get(Member.class, memberId);
        if (member != null){
            member.setStatus(status);
        }
        return 1;
    }
}
