package com.care.dao;

import com.care.filter.HibernateFilter;
import com.care.model.Job;
import com.care.model.Seeker;
import com.care.model.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.*;

public class HJobDAOImpl implements JobDAO {
    @Override
    public int addJob(Job job) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.save(job);

        return 1;
    }

    @Override
    public int setJobStatus(long jobId, Status status) throws Exception {
        int operationStatus = -1;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Job job = (Job)session.get(Job.class, jobId);

        if (job != null){
            job.setStatus(status);
            operationStatus = 1;
        }
        return operationStatus;
    }

    @Override
    public int setAllJobsStatus(long postedBy, Status status) throws Exception {
        int operationStatus = -1;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        /*
        We are required to first fetch all the jobs posted by the given seeker, And then update their status to the
        given status.
         */
        Seeker seeker = (Seeker) session.get(Seeker.class, postedBy);
        if (seeker != null){
            operationStatus = 1;
            Set<Job> jobs = seeker.getJobs();
            for (Job job: jobs) {
                job.setStatus(status);
            }
        }

        return operationStatus;
    }

    @Override
    public int expireStaleJobs() throws Exception {
        return 0;
    }

    @Override
    public int editJob(Job job) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.saveOrUpdate(job);


        return 1;
    }

    @Override
    public Job getJob(long jobId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Job job = (Job) session.get(Job.class, jobId);
        if (job == null){
            job = Job.emptyJob();
        }

        return job;
    }

    @Override
    public Set<Job> getAllJobs(long postedBy) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        /*
        If the eager fetch starategy works, then fetching a seeker will also fetch it's jobs.
         */
        Seeker seeker = (Seeker) session.get(Seeker.class, postedBy);
        Set<Job> jobs = Collections.emptySet();
        if (seeker != null){
            jobs = seeker.getJobs();
        }
        return jobs;
    }

    /*
        Get all jobs for which this sitter is eligible to apply. All active jobs for which this SITTER doesn't have any ACTIVE or
        CLOSED application.
     */
    @Override
    public Set<Job> getAllAvailableJobs(long sitterId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Job j where j.id not in (select job.id from Application where sitter.id =?)");
        query.setLong(0, sitterId);
//        query.setString(1, Status.ACTIVE.name());
//        query.setString(2, Status.CLOSED.name());
        List<Job> jobs = query.list();

        return new HashSet<>(jobs);
    }
}
