package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Seeker;
import com.care.service.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HSeekerDAOImpl implements SeekerDAO {

    @Override
    public int addSeeker(Seeker seeker) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.save(seeker);
        return 1;
    }

    @Override
    public Seeker getSeeker(long seekerId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Seeker seeker = (Seeker) session.get(Seeker.class, seekerId);
        if (seeker == null){
            seeker = Seeker.emptySeeker();
        }

        return seeker;
    }

    @Override
    public Set<Seeker> getSeekerByEmail(String email) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Query query = session.createQuery("from Seeker where email like '%?%'");
        query.setString(0, email);
        List<Seeker> seekers = query.list();
        if (seekers == null){
            seekers = Collections.emptyList();
        }
        return new HashSet<>(seekers);
    }

    @Override
    public int editSeeker(long seekerId, Seeker seeker) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.saveOrUpdate(seeker);


        return 1;
    }

    @Override
    public Set<Job> listAllMyJobs(long seekerId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Seeker seeker = (Seeker) session.get(Seeker.class, seekerId );
        Set<Job> jobs = Collections.emptySet();
        if (seeker != null){
            jobs = seeker.getJobs();
        }
        return jobs;
    }

    @Override
    public Set<Application> listAllApplicationsOnMyJob(long jobId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Job job = (Job) session.get(Job.class, jobId);
        Set<Application> applications = Collections.emptySet();
        if (job != null){
            applications = job.getApplications();
        }
        return applications;
    }
}
