package com.care.dao;

import com.care.model.Member;
import com.care.model.Token;
import com.care.model.Status;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.SessionScoped;

import org.hibernate.Query;
import org.hibernate.Session;

public class HMemberDAOImpl implements MemberDAO {
    @Override
    public Member getMember(String email) throws SQLException {
        String query = "from Member where email=?";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query1 = session.createQuery(query);
        query1.setString(0, email);

        Member member = Member.EMPTY_MEMBER;
        List<Member> members = query1.list();
        if (members.size() == 1){
            member = members.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return member;
    }

    @Override
    public Member getMember(long memberId) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Member member = (Member) session.get(Member.class, memberId);
        if (member == null){
            member = Member.EMPTY_MEMBER;
        }
        session.getTransaction().commit();
        session.close();
        return member;
    }

    @Override
    public Token getToken(String token) throws SQLException {
        Session session =HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Token where TOKEN=?");
        query.setString(0, token);
         = query.list();
        return null;
    }

    @Override
    public int updatePassword(Member member) throws SQLException {
        return 0;
    }


    @Override
    public int addToken(Token token) throws SQLException {
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
