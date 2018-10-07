package com.care.dao;

import com.care.exception.DataReadException;
import com.care.exception.DataWriteException;
import com.care.model.Seeker;
import com.care.model.Sitter;
import com.care.model.Status;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HSitterDAOImpl implements SitterDAO {
    /*
    Both member and sitter needs to be updated.
     */
    @Override
    public int addSitter(Sitter sitter)  {
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.save(sitter);
        }catch (Exception e){
            throw new DataWriteException(e);
        }

        return 1;
    }

    @Override
    public int editSitter(long sitterId, Sitter sitter)  {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.saveOrUpdate(sitter);
        return 1;
    }

    @Override
    public Sitter getSitter(long sitterId)  {
        Sitter sitter;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            sitter = (Sitter) session.get(Sitter.class, sitterId);
        }catch (Exception e){
            throw  new DataReadException(e);
        }

        return sitter;
    }

    @Override
    public Set<Sitter> getSitterByEmail(String email)  {
        List<Sitter> sitters;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery("from Sitter where email like :pattern and status = :status");
            query.setString("pattern", "%" + email + "%");
            query.setString("status", Status.ACTIVE.name());

            sitters = query.list();
        }catch (Exception e){
            throw new DataReadException(e);
        }

        return new HashSet<>(sitters);
    }
}
