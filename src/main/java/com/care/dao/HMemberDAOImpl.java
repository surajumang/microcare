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
        String query = "from Member where email=?";

        Query query1 = session.createQuery(query);
        query1.setString(0, email);

        Member member = Member.emptyMember();
        List<Member> members = query1.list();
        if (members.size() == 1){
            member = members.get(0);
        }
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
        Query query = session.createQuery("from Token where TOKEN=?");
        query.setString(0, token);

        return null;
    }

    @Override
    public int updatePassword(Member member) throws Exception {
        return 0;
    }


    @Override
    public int addToken(Token token) throws Exception {
        return 0;
    }

    @Override
    public int invalidateToken(String token) throws Exception {
        return 0;
    }

    @Override
    public int expireStaleTokens() throws Exception {
        return 0;
    }

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
        return 0;
    }
}
