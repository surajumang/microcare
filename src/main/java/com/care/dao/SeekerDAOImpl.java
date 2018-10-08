package com.care.dao;

import com.care.exception.DataReadException;
import com.care.exception.DataWriteException;
import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Seeker;
import com.care.model.Status;
import com.care.service.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SeekerDAOImpl implements SeekerDAO {

    @Override
    public int addSeeker(Seeker seeker){
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.save(seeker);
        } catch (HibernateException e) {
            throw new DataWriteException(e);
        }
        return 1;
    }

    @Override
    public Seeker getSeeker(long seekerId)  {
        Seeker seeker = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            seeker = (Seeker) session.get(Seeker.class, seekerId);
        } catch (HibernateException e) {
            throw new DataReadException(e);
        }
        return seeker;
    }

    @Override
    public Set<Seeker> getSeekerByEmail(String email)  {
        List<Seeker> seekers ;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery("from Seeker where email like :pattern and status= :status");
            query.setString("pattern", "%" + email + "%" );
            query.setString("status", Status.ACTIVE.name());
            seekers = query.list();
        } catch (HibernateException e) {
            throw new DataReadException(e);
        }
        if (seekers == null){
            seekers = Collections.emptyList();
        }
        return new HashSet<>(seekers);
    }
}
