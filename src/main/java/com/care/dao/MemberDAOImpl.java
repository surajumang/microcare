package com.care.dao;

import com.care.exception.DataReadException;
import com.care.exception.DataWriteException;
import com.care.filter.HibernateFilter;
import com.care.model.Member;
import com.care.model.Token;
import com.care.model.Status;

import java.util.List;

import javax.faces.bean.SessionScoped;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public Member getMember(String email)  {
        Member member ;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            String query = "from Member where email= :email";
            Query query1 = session.createQuery(query);
            query1.setString("email", email);
            member = (Member) query1.uniqueResult();
        } catch (HibernateException e) {
            throw new DataReadException(e);
        }
        if (member == null){
            throw new DataReadException("can't get Member");
        }
        return member;
    }

    @Override
    public Member getMember(long memberId) {
        Member member;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            member = (Member) session.get(Member.class, memberId);
        } catch (HibernateException e) {
            throw new DataReadException(e);
        }
        if (member == null){
            throw new DataReadException("can't get Member");
        }

        return member;
    }

    @Override
    public Token getToken(String token)  {
        Token token1 = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery("from Token where TOKEN= :token");
            query.setString("token", token);

            token1 = (Token) query.uniqueResult();
        } catch (HibernateException e) {
            throw new DataReadException(e);
        }
        if (token == null){
            throw new DataReadException("can't get Token");
        }

        return token1;
    }

    /*
    It is provided with a member object completely filled with ID and all fields which were required to be updated.
     */
    @Override
    public int updatePassword(Member member) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.update(member);
        } catch (HibernateException e) {
            throw new DataWriteException(e);
        }

        return 1;
    }


    @Override
    public int addToken(Token token)  {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.save(token);
        } catch (HibernateException e) {
            throw new DataWriteException(e);
        }
        return 1;
    }
    // set status to Inactive
    @Override
    public int invalidateToken(String token)  {
        int status = 0;
        Token token1 = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            status = -1;
            Query query = session.createQuery("from Token where token= :token");
            query.setString("token", token);
            token1 = (Token)query.uniqueResult();
        } catch (HibernateException e) {
            throw new DataReadException(e);
        }
        if (token == null){
            throw new DataReadException("while reading");
        }
        status = 1;
        token1.setStatus(Status.EXPIRED);
        return status;
    }

    @Override
    public int expireStaleTokens() {
        return 0;
    }
}
