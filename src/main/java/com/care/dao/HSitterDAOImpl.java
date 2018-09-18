package com.care.dao;

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
    public int addSitter(Sitter sitter) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.save(sitter);

        return 1;
    }

    @Override
    public int editSitter(long sitterId, Sitter sitter) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.saveOrUpdate(sitter);
        return 0;
    }

    @Override
    public Sitter getSitter(long sitterId) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Sitter sitter = (Sitter) session.get(Sitter.class, sitterId);
        if (sitter == null){
            sitter = Sitter.emptySitter();
        }
        return sitter;
    }

    @Override
    public Set<Sitter> getSitterByEmail(String email) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Sitter where email like ? and status = ?");
        query.setString(0, "%" + email + "%");
        query.setString(1, Status.ACTIVE.name());

        List<Sitter> sitters = query.list();
        if (sitters == null){
            sitters = Collections.emptyList();
        }
        return new HashSet<>(sitters);
    }
}
